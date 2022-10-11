package it.unitn.disi.web.rg209272.assignment2.servlets;

import it.unitn.disi.web.rg209272.assignment2.beans.UserBean;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.LinkedList;

public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("HomePage");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext ctx = getServletContext();
        String username = (String)request.getAttribute("username");
        String password = (String)request.getAttribute("password");
        String repeatPsw = (String)request.getAttribute("repeatPsw");
        RequestDispatcher rd;
        if (!password.equals(repeatPsw)){
            rd = request.getRequestDispatcher("RegistrationServlet");
            rd.forward(request, response);
            return;
        }
        LinkedList<UserBean> userBeanLinkedList = (LinkedList<UserBean>) ctx.getAttribute("users");
        UserBean ub = new UserBean(username, password);

    }
}
