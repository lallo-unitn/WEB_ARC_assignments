package it.unitn.disi.web.rg209272.assignment3.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class SheetServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("spreadSheet.html");
        rd.forward(request, response);
    }
}
