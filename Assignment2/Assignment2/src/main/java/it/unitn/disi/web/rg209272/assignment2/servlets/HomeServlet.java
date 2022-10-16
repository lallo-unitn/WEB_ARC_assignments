package it.unitn.disi.web.rg209272.assignment2.servlets;

import it.unitn.disi.web.rg209272.assignment2.auxiliary.Initializer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HomeServlet extends HttpServlet {

    @Override
    public void init() {
        ServletContext ctx = getServletContext();
        try {
            if (ctx.getAttribute("users") == null || ctx.getAttribute("flagsArray") == null) {
                File flagsFile = new File(this.getServletContext().getRealPath("flags/"));
                new Initializer(ctx, flagsFile);
            }
        } catch (NullPointerException e) {
            System.out.println("HOMESERVLET*init(): File not found");
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
