package it.unitn.disi.web.rg209272.assignment3.servlets;

import it.unitn.disi.web.rg209272.assignment3.auxiliary.CellMapToJson;
import it.unitn.disi.web.rg209272.assignment3.auxiliary.SSEngine;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class SheetServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SSEngine engine = SSEngine.getSSEngine();
        CellMapToJson cellMapToJson = new CellMapToJson();
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(cellMapToJson.getJson(engine));
        out.flush();
        RequestDispatcher rd = request.getRequestDispatcher("spreadSheet.jsp");
        rd.forward(request, response);
    }
}
