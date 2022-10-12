package it.unitn.disi.web.rg209272.assignment2.servlets;

import it.unitn.disi.web.rg209272.assignment2.beans.UserBean;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.LinkedList;

public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO: CHANGE DOGET
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext ctx = getServletContext();
        String username = (String)request.getParameter("username");
        String password = (String)request.getParameter("password");
        String repeatPsw = (String)request.getParameter("repeatPsw");
        RequestDispatcher rd;
        if (!password.equals(repeatPsw)){
            System.out.println("ADDUSER: Wrong credential");
            System.out.println(password + " / " + repeatPsw);
            rd = request.getRequestDispatcher("RegistrationServlet");
            rd.forward(request, response);
            return;
        }
        LinkedList<UserBean> userBeanLinkedList = (LinkedList<UserBean>) ctx.getAttribute("users");
        UserBean ub = new UserBean(username, password);
        if(!userBeanLinkedList.contains(ub)){
            userBeanLinkedList.add(ub);
        }else{
            rd = request.getRequestDispatcher("RegistrationServlet");
            request.setAttribute("message", "User already exists");
            System.out.println("ADDUSER: User AE");
        }
        rd = request.getRequestDispatcher("LoginServlet");
        rd.forward(request, response);
    }
}
