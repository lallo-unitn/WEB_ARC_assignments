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
import java.util.ArrayList;
import java.util.HashMap;

public class CheckAnswersServlet extends HttpServlet {
    final String invalidAnsMess = "Answer not a integer";
    final String nullAnsMess = "Answer was empty";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("HomeServlet");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Parsing answer
        Integer[] ans = new Integer[3];
        RequestDispatcher rd = request.getRequestDispatcher("HomeServlet");
        try {
            for (int i = 0; i < 3; i++) {
                ans[i] = Integer.parseInt(request.getParameter("flag" + (i + 1) + "Ans"));
            }
        } catch (Exception e) {
            if (e instanceof NumberFormatException) {
                System.out.println(invalidAnsMess);
                request.setAttribute("errMess", invalidAnsMess);
            } else if (e instanceof NullPointerException) {
                System.out.println(nullAnsMess);
                request.setAttribute("errMess", nullAnsMess);
            } else {
                System.out.println("An error has occurred while parsing the answer");
                request.setAttribute("errMess", "An error has occurred while evaluating your answer");
            }
            rd.forward(request, response);
            return;
        }
        HttpSession session = request.getSession();
        UserBean ub = (UserBean) session.getAttribute("userBean");
        ArrayList<Integer> chosenFlagsIndex = (ArrayList<Integer>) session.getAttribute("chosenFlagsIndex");
        boolean isAllCorrect = true;
        for (int i = 0; i < 3 && isAllCorrect; i++) {
            System.out.println("CHECKANSWERS: " + ans[i] + " / " + chosenFlagsIndex.get(i));
            //CONVERT ANS TO 0-INDEX
            ans[i] = ans[i] - 1;
            //CHECK ANS
            if (!ans[i].equals(chosenFlagsIndex.get(i))) {
                ub.setScore(ub.getScore() - 1);
                isAllCorrect = false;
            }
        }
        if (isAllCorrect) ub.setScore(ub.getScore() + 3);
        session.setAttribute("userBean", ub);
        ServletContext ctx = getServletContext();
        HashMap<String, UserBean> usersMap = (HashMap<String, UserBean>) ctx.getAttribute("users");
        usersMap.replace(ub.getUsername(), ub);
        ctx.setAttribute("users", usersMap);
        rd.forward(request, response);
    }
}
