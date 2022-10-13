package it.unitn.disi.web.rg209272.assignment2.servlets;

import it.unitn.disi.web.rg209272.assignment2.beans.UserBean;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;

public class ControlServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext ctx = getServletContext();
        HashMap<String, UserBean> activeUsers = (HashMap<String, UserBean>) ctx.getAttribute("activeUsers");
        for (String key :
                ( (HashMap<String, UserBean>)activeUsers ).keySet() ){
            UserBean ub = (UserBean)activeUsers.get(key);
            System.out.println(ub.getUsername() + ub.getScore());
        }
        RequestDispatcher rd = request.getRequestDispatcher("private/controlPage.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
