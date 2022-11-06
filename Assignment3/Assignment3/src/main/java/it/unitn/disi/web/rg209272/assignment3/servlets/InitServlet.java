package it.unitn.disi.web.rg209272.assignment3.servlets;

import it.unitn.disi.web.rg209272.assignment3.auxiliary.CellMapBridge;
import it.unitn.disi.web.rg209272.assignment3.auxiliary.Json;
import it.unitn.disi.web.rg209272.assignment3.auxiliary.SSEngine;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class InitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SSEngine engine = SSEngine.getSSEngine();
        ServletContext ctx = this.getServletContext();
        ctx.setAttribute("engine", engine);
        ctx.setAttribute("cellMap", CellMapBridge.getCellMap(engine));
        long currentTimeMillis = System.currentTimeMillis();
        ctx.setAttribute("lastModServerMill", currentTimeMillis);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(Json.getJson(engine, currentTimeMillis));
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
