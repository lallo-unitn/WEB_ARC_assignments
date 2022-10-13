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
import java.util.HashMap;

public class AuthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("LoginServlet");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServletContext ctx = request.getServletContext();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String destination = (String) request.getAttribute("destination");
        if( destination == null ){
            destination = "HomeServlet";
        }
        RequestDispatcher rd = request.getRequestDispatcher("LoginServlet");
        UserBean ub = new UserBean(username, password);
        if ( checkCredentials(ctx, ub) ){
            rd = request.getRequestDispatcher(destination);
            session.setAttribute("userBean", ub);
            if(ub.getUsername().equals("admin")){
                rd = request.getRequestDispatcher("ControlServlet");
            }
        }
        rd.forward(request, response);
    }

    private boolean checkCredentials(ServletContext ctx, UserBean ub) {
        HashMap<String, UserBean> usersMap = (HashMap<String, UserBean>) ctx.getAttribute("users");
        return usersMap.containsValue(ub);
    }
}