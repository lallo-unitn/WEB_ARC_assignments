package it.unitn.disi.web.rg209272.assignment2.servlets;

import it.unitn.disi.web.rg209272.assignment2.auxiliary.Initializer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class HomeServlet extends HttpServlet {

    @Override
    public void init(){
        ServletContext ctx = getServletContext();
        File flagsFile = new File(this.getServletContext().getRealPath("flags/"));
        if(ctx.getAttribute("users") == null || ctx.getAttribute("flagsArray") == null){
            new Initializer(ctx, flagsFile);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("private/home.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("private/home.jsp");
        rd.forward(request, response);
    }
}
