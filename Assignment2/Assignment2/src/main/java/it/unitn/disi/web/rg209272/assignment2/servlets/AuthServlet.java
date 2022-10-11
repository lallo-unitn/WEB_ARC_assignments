package it.unitn.disi.web.rg209272.assignment2.servlets;

import it.unitn.disi.web.rg209272.assignment2.beans.UserBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AuthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("Home");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext ctx = request.getServletContext();
        HttpSession session = request.getSession();
        List<UserBean> usersList = (LinkedList<UserBean>) ctx.getAttribute("users");
        String username = (String) session.getAttribute("username");
        String destination = (String) request.getAttribute("destination");
        RequestDispatcher rd;
        if (destination == null || destination.equals("null"))
            destination = "HomeServlet";
        if (username != null && !username.equals("null")) {
            System.out.println("AUTHSERVLET: Already auth");
            rd = request.getRequestDispatcher(destination);
            rd.forward(request, response);
            return;
        }
        String inUsername = (String) request.getParameter("username");
        String inPassword = (String) request.getParameter("password");
        UserBean user = new UserBean(inUsername, inPassword);
        System.out.println(usersList.contains(user));
        if (usersList != null && usersList.contains(user)) {
            session.setAttribute("username", inUsername);
            System.out.println("AUTHSERVLET: °°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
            System.out.println("AUTHSERVLET: " + destination);
            rd = request.getRequestDispatcher(destination);
        }else {
            request.setAttribute("message", "Credenziali errate");
            rd = request.getRequestDispatcher("LoginServlet");
        }
        rd.forward(request, response);
    }
}