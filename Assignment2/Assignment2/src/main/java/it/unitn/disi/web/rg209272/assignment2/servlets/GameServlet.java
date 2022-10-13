package it.unitn.disi.web.rg209272.assignment2.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext ctx = getServletContext();
        HttpSession session = request.getSession();
        ArrayList<String> flagsArray = (ArrayList<String>) ctx.getAttribute("flagsArray");
        String flagPath = "flags/";
        Random random = new Random(System.currentTimeMillis());
        int randomIndex = 0;
        Integer[] chosenFlagsIndex = new Integer[3];
        System.out.println("GAMESERVLET: ");
        for (int i = 1; i <= 3 ; i++) {
            randomIndex = random.nextInt(flagsArray.size());
            request.setAttribute( "flag" + i, flagPath + flagsArray.get(randomIndex) );
            chosenFlagsIndex[i-1] = randomIndex;
            System.out.println("chosen index: " + chosenFlagsIndex[i-1]);
        }
        session.setAttribute("chosenFlagsIndex", chosenFlagsIndex);
        RequestDispatcher rd = request.getRequestDispatcher("private/game.jsp");
        rd.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
