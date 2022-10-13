package it.unitn.disi.web.rg209272.assignment2.servlets;

import it.unitn.disi.web.rg209272.assignment2.beans.UserBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

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
        HashMap<String, UserBean> usersMap = (HashMap<String, UserBean>) ctx.getAttribute("users");
        UserBean ub = new UserBean(username, password);
        if(!usersMap.containsKey(username)){
            usersMap.put(username, ub);
            ctx.setAttribute("users", usersMap);
        }else{
            rd = request.getRequestDispatcher("RegistrationServlet");
            request.setAttribute("message", "User already exists");
            System.out.println("ADDUSER: User AE");
        }
        rd = request.getRequestDispatcher("LoginServlet");
        rd.forward(request, response);
    }
}
