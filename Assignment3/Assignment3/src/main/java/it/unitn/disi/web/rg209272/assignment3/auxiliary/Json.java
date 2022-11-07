package it.unitn.disi.web.rg209272.assignment3.auxiliary;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Json {
    public static String getJson(Cell cell) {
        return "{" + "\n    \"id\" : \"" + cell.id + "\"," +
                "\n    \"value\" : \"" + cell.value + "\"," +
                "\n    \"formula\" : \"" + cell.formula + "\"" +
                "\n}";
    }

    public static String getJson(SSEngine engine, long lastModServerMill) {
        HashMap<String, Cell> cellMap = engine.cellMap;
        StringBuilder sb = new StringBuilder(
                "{\"empty\" : \"false\"," +
                        "\n\"lastModTimeServer\" :\"" + lastModServerMill + "\"," +
                        "\n\"cells\" : [\n"
        );
        Cell cell;
        ArrayList<String> keysArray = new ArrayList<>(cellMap.keySet());
        keysArray.sort(String::compareTo);
        for (String cellId :
                keysArray) {
            cell = cellMap.get(cellId);
            sb.append(Json.getJson(cell)).
                    append(",");
        }
        String json = sb.toString();
        json = json.substring(0, json.length() - 1);
        json = json + "\n]}";
        System.out.println("[CELLMAPTOJSON]\n" + json);
        return json;
    }

    public static StringBuilder readJson(HttpServletRequest request) {
        StringBuilder jb = new StringBuilder();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jb;
    }
}
