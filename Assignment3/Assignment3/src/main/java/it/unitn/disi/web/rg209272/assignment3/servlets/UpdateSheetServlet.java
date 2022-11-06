package it.unitn.disi.web.rg209272.assignment3.servlets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonSyntaxException;
import it.unitn.disi.web.rg209272.assignment3.auxiliary.Cell;
import it.unitn.disi.web.rg209272.assignment3.auxiliary.CellMapBridge;
import it.unitn.disi.web.rg209272.assignment3.auxiliary.Json;
import it.unitn.disi.web.rg209272.assignment3.auxiliary.SSEngine;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;

public class UpdateSheetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder jb = readJson(request);
        String json = jb.toString();
        if (json.contains("lastModTimeClient")){
            answerToPolling(jb, response);
        } else {
            answerToUpdate(jb, response);
        }

    }

    private void answerToPolling(StringBuilder jb, HttpServletResponse response) throws IOException {
        ServletContext ctx = this.getServletContext();
        String json = jb.toString();
        System.out.println("================[POLLING ANSWER]===================");
        System.out.println("JSON");
        System.out.println(json);
        String millisString = json.split("[{]\"lastModTimeClient\": \"")[1];
        millisString = millisString.split("\"[}]")[0];
        System.out.println("MILLISEC CLIENT");
        System.out.println(Long.parseLong(millisString));
        System.out.println("DATE SERVER/CLIENT");
        long lastModTimeClientMillis = Long.parseLong(millisString);
        Timestamp lastModTimeClient = new Timestamp(lastModTimeClientMillis);
        long lastModServerMill = (long) ctx.getAttribute("lastModServerMill");
        Timestamp lastModTimeServer = new Timestamp(lastModServerMill);
        System.out.println(lastModTimeServer + " / " + lastModTimeClient);
        System.out.println("================[END POLLING ANSWER]===================");
        if (lastModTimeClient.before(lastModTimeServer)) {
            SSEngine engine = SSEngine.getSSEngine();
            ctx.setAttribute("engine", engine);
            ctx.setAttribute("cellMap", CellMapBridge.getCellMap(engine));
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(Json.getJson(engine, lastModServerMill));
            out.flush();
        } else {
            ctx.setAttribute("lastModServerMill", lastModTimeClientMillis);
            json = "{" +
                    "\"empty\" : \"true\"," +
                    "\"lastModTimeServer\":\"" + lastModServerMill + "\"" +
                    "}";
            sendJSON(json, response);
        }
    }

    private void answerToUpdate(StringBuilder jb, HttpServletResponse response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Cell cellNewValue;
        try {
            System.out.println(jb);
            cellNewValue = objectMapper.readValue(jb.toString(), Cell.class);
        } catch (JsonSyntaxException | JsonProcessingException e) {
            throw new IOException("Error parsing JSON request string");
        }
        ServletContext ctx = this.getServletContext();
        SSEngine engine = (SSEngine) ctx.getAttribute("engine");
        String json;
        ArrayList<Cell> modCellsArray;
        try {
            modCellsArray = new ArrayList<>(engine.modifyCell(cellNewValue.getId(), cellNewValue.getFormula()));
        } catch (NullPointerException e) {
            json = "{ \"size\" : \"0\"}";
            sendJSON(json, response);
            return;
        }
        modCellsArray.sort(Cell::compareTo);
        json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(modCellsArray);
        long currentTimeMillis = System.currentTimeMillis();
        ctx.setAttribute("lastModTimeServer", currentTimeMillis);
        json = "{ \"size\" : \"" +
                modCellsArray.size() +
                "\", \n\"updatedCells\" :\n" +
                json +
                "\n, \"lastModTime\" : " + currentTimeMillis +
                "}";
        System.out.println("================[INIT UPDATESHEETSERVLET]===================");
        System.out.println(json);
        System.out.println("================[END UPDATESHEETSERVLET]====================");

        sendJSON(json, response);

    }

    private void sendJSON(String json, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(json);
        out.flush();
    }

    private StringBuilder readJson(HttpServletRequest request) {
        StringBuilder jb = new StringBuilder();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jb;
    }
}
