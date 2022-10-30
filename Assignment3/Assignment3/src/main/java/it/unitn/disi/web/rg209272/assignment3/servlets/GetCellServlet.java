package it.unitn.disi.web.rg209272.assignment3.servlets;

import it.unitn.disi.web.rg209272.assignment3.auxiliary.Cell;
import it.unitn.disi.web.rg209272.assignment3.auxiliary.Json;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class GetCellServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext ctx = this.getServletContext();
        String outCellID = request.getParameter("id");
        HashMap<String, Cell> cellMap =
                (HashMap<String, Cell>) ctx.getAttribute("cellMap");
        Cell outCell = cellMap.get(outCellID);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(Json.getJson(outCell));
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
