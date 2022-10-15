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
        int randomIndex;
        ArrayList<Integer> chosenFlagsIndex = new ArrayList<>(3);
        System.out.println("GAMESERVLET: ");
        for (int i = 1; i <= 3; i++) {
            //DROP ALREADY CHOSEN FLAGS INDEXES
            do {
                randomIndex = random.nextInt(flagsArray.size());
            } while (chosenFlagsIndex.contains(randomIndex));
            //SET FLAGS IN REQUEST
            request.setAttribute("flag" + i, flagPath + flagsArray.get(randomIndex));
            chosenFlagsIndex.add(i - 1, randomIndex);
            System.out.println("chosen index: " + chosenFlagsIndex.get(i - 1));
        }
        //SET FLAGS IN SESSION
        session.setAttribute("chosenFlagsIndex", chosenFlagsIndex);
        RequestDispatcher rd = request.getRequestDispatcher("private/game.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
