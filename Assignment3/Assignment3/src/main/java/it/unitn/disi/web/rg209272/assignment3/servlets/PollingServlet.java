package it.unitn.disi.web.rg209272.assignment3.servlets;

import it.unitn.disi.web.rg209272.assignment3.auxiliary.CellMapBridge;
import it.unitn.disi.web.rg209272.assignment3.auxiliary.Json;
import it.unitn.disi.web.rg209272.assignment3.auxiliary.SSEngine;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

public class PollingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder jb = Json.readJson(request);
        String json = jb.toString();
        ServletContext ctx = this.getServletContext();

        String millisString = json.split("[{]\"lastModTimeClient\": \"")[1];
        millisString = millisString.split("\"[}]")[0];

        long lastModTimeClientMillis = Long.parseLong(millisString);
        Timestamp lastModTimeClient = new Timestamp(lastModTimeClientMillis);
        long lastModTimeServerMillis = (long) ctx.getAttribute("lastModServerMill");
        Timestamp lastModTimeServer = new Timestamp(lastModTimeServerMillis);

        printServerLog(json, lastModTimeClient, lastModTimeServer);

        if (lastModTimeClient.before(lastModTimeServer)) {
            updateState(response, lastModTimeServerMillis);
        } else {
            confirmState(response, lastModTimeClientMillis, lastModTimeServerMillis);
        }
    }

    private void updateState(HttpServletResponse response, long lastModTimeServerMillis) throws IOException {
        SSEngine engine = SSEngine.getSSEngine();
        ServletContext ctx = this.getServletContext();
        ctx.setAttribute("engine", engine);
        ctx.setAttribute("cellMap", CellMapBridge.getCellMap(engine));
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(Json.getJson(engine, lastModTimeServerMillis));
        out.flush();
    }

    private void confirmState(HttpServletResponse response, long lastModTimeClientMillis, long lastModTimeServerMillis) throws IOException {
        ServletContext ctx = this.getServletContext();
        ctx.setAttribute("lastModServerMill", lastModTimeClientMillis);
        String json = "{" +
                "\"empty\" : \"true\"," +
                "\"lastModTimeServer\":\"" + lastModTimeServerMillis + "\"" +
                "}";
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(json);
        out.flush();
    }

    private void printServerLog(String json, Timestamp lastModTimeClient, Timestamp lastModTimeServer) {
        System.out.println("================[POLLING ANSWER]===================");
        System.out.println("JSON");
        System.out.println(json);
        System.out.println("MILLISEC CLIENT");
        System.out.println("DATE SERVER/CLIENT");
        System.out.println(lastModTimeServer + " / " + lastModTimeClient);
        System.out.println("================[END POLLING ANSWER]===================");
    }
}
