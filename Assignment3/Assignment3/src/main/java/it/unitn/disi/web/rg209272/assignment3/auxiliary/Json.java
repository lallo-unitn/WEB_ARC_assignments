package it.unitn.disi.web.rg209272.assignment3.auxiliary;

import java.util.ArrayList;
import java.util.HashMap;

public class Json {
    public static String getJson(Cell cell) {
        StringBuilder sb = new StringBuilder("{");
        sb.append("\n    \"id\" : \"" + cell.id + "\",")
                .append("\n    \"value\" : \"" + cell.value + "\",")
                .append("\n    \"formula\" : \"" + cell.formula + "\"")
                .append("\n}");
        return sb.toString();
    }

    public static String getJson(SSEngine engine) {
        HashMap<String, Cell> cellMap = engine.cellMap;
        StringBuilder sb = new StringBuilder("{\"cells\" : [\n");
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
        json = json.substring(0, json.length()-1);
        json = json + "\n]}";
        System.out.println("[CELLMAPTOJSON]\n" + json);
        return json;
    }

}
