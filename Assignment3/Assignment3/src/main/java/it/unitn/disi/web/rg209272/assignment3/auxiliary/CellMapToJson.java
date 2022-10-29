package it.unitn.disi.web.rg209272.assignment3.auxiliary;

import java.util.ArrayList;
import java.util.HashMap;

public class CellMapToJson {
    public CellMapToJson() {
    }

    public String getJson(SSEngine engine) {
        HashMap<String, Cell> cellMap = engine.cellMap;
        StringBuilder sb = new StringBuilder("{\"cells\" : [\n");
        Cell cell;
        CellToJson ctj = new CellToJson();
        ArrayList<String> keysArray = new ArrayList<>();
        keysArray.addAll(cellMap.keySet());
        keysArray.sort(String::compareTo);
        for (String cellId :
                keysArray) {
            cell = cellMap.get(cellId);
            sb.append(ctj.getJson(cell)).
                    append(",");
        }
        String json = sb.toString();
        json = json.substring(0, json.length()-1);
        json = json + "\n]}";
        System.out.println("[CELLMAPTOJSON]\n" + json);
        return json;
    }
}
