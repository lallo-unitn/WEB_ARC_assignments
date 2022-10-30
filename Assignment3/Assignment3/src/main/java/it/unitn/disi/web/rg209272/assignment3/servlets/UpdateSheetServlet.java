package it.unitn.disi.web.rg209272.assignment3.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonSyntaxException;
import it.unitn.disi.web.rg209272.assignment3.auxiliary.Cell;
import it.unitn.disi.web.rg209272.assignment3.auxiliary.SSEngine;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class UpdateSheetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder jb = new StringBuilder();
        String line;
        ObjectMapper objectMapper = new ObjectMapper();
        Cell cellNewValue;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) { /*report an error*/ }

        try {
            System.out.println(jb.toString());
            cellNewValue = objectMapper.readValue(jb.toString(), Cell.class);
        } catch (JsonSyntaxException e) {
            // crash and burn
            throw new IOException("Error parsing JSON request string");
        }
        ServletContext ctx = this.getServletContext();
        SSEngine engine = (SSEngine) ctx.getAttribute("engine");
        String json;
        ArrayList<Cell> modCellsArray = null;
        try {
            modCellsArray = new ArrayList<>(engine.modifyCell(cellNewValue.getId(), cellNewValue.getFormula()));
        } catch (NullPointerException e) {
            json = "{ \"size\" : \"0\"}";
            sendJSON(json, response);
            return;
        }
        modCellsArray.sort(Cell::compareTo);
        json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(modCellsArray);
        json = "{ \"size\" : \"" + modCellsArray.size() + "\", \n\"updatedCells\" :\n" + json + "\n}";
        System.out.println("================[INIT UPDATESHEETSERVLET]===================");
        System.out.println(json);
        System.out.println("================[END UPDATESHEETSERVLET]====================");
        sendJSON(json, response);
    }
    private void sendJSON(String json, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(json);
        out.flush();
    }
}
