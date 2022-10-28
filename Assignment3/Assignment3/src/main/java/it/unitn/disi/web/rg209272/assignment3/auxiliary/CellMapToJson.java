package it.unitn.disi.web.rg209272.assignment3.auxiliary;

import java.util.HashMap;

public class CellMapToJson {
    public CellMapToJson() {
    }

    public String getJson(SSEngine engine) {
        HashMap<String, Cell> cellMap = engine.cellMap;
        StringBuilder sb = new StringBuilder("{\n");
        Cell cell;
        CellToJson ctj = new CellToJson();
        for (String cellId :
                cellMap.keySet()) {
            cell = cellMap.get(cellId);
            sb.append("\"").
                    append(cellId).
                    append("\" : ").
                    append(ctj.getJson(cell)).
                    append(",");
        }
        String json = sb.toString();
        json = json.substring(0, json.length()-1);
        json = json + "\n}";
        System.out.println("[CELLMAPTOJSON]\n" + json);
        return sb.toString();
    }
}
