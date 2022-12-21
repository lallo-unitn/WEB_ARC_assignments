package it.unitn.disi.web.rg209272.assignment4_tomcat.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class HomeServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("messageStudent", "");
        request.setAttribute("messageAdvisor", "");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
