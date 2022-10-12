package it.unitn.disi.web.rg209272.assignment2.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext ctx = getServletContext();
        ArrayList<String> flagsArray = (ArrayList<String>) ctx.getAttribute("flagsArray");
        String flagPath = "flags/";
        Random random = new Random(System.currentTimeMillis());
        //PICK THREE RANDOM FLAGS PATHS AND PUT IT IN REQUEST
        //THIS IMPLEMENTATION SHOULD BE SCALABLE
        for (int i = 1; i <= 3 ; i++) {
            request.setAttribute( "flag" + i, flagPath + flagsArray.get(random.nextInt(flagsArray.size())) );
        }
        RequestDispatcher rd = request.getRequestDispatcher("private/game.jsp");
        rd.forward(request, response);
    }
}
