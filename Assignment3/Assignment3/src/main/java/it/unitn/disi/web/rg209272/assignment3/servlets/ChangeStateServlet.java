package it.unitn.disi.web.rg209272.assignment3.servlets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonSyntaxException;
import it.unitn.disi.web.rg209272.assignment3.auxiliary.Cell;
import it.unitn.disi.web.rg209272.assignment3.auxiliary.Json;
import it.unitn.disi.web.rg209272.assignment3.auxiliary.SSEngine;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Set;

public class ChangeStateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder jb = Json.readJson(request);
        ObjectMapper objectMapper = new ObjectMapper();
        Cell cellNewValue;
        try {
            System.out.println(jb);
            cellNewValue = objectMapper.readValue(jb.toString(), Cell.class);
        } catch (JsonSyntaxException | JsonProcessingException e) {
            throw new IOException("Error parsing JSON request string");
        }
        ServletContext ctx = this.getServletContext();
        SSEngine engine = (SSEngine) ctx.getAttribute("engine");
        String json;
        ArrayList<Cell> modCellsArray;
        Set<Cell> modCellsSet;
        modCellsSet = engine.modifyCell(cellNewValue.getId(), cellNewValue.getFormula());
        if (modCellsSet != null) {
            modCellsArray = new ArrayList<>(modCellsSet);
            modCellsArray.sort(Cell::compareTo);
            json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(modCellsArray);
            long currentTimeMillis = System.currentTimeMillis();
            ctx.setAttribute("lastModTimeServer", currentTimeMillis);

            json = "{ \"size\" : \"" +
                    modCellsArray.size() +
                    "\", \n\"updatedCells\" :\n" +
                    json +
                    "\n, \"lastModTimeServer\" : " + currentTimeMillis +
                    "}";

            System.out.println("================[INIT UPDATESHEETSERVLET]===================");
            System.out.println(json);
            System.out.println("================[END UPDATESHEETSERVLET]====================");

        } else {
            json = "{ \"size\" : \"0\"}";
        }
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(json);
        out.flush();
    }
}
