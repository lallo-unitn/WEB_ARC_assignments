package it.unitn.disi.web.rg209272.assignment3.auxiliary;

public class CellToJson {
    public CellToJson() {
    }

    public String getJson(Cell cell) {
        StringBuilder sb = new StringBuilder("{");
        sb.append("\n    \"id\" : \"" + cell.id + "\",")
                .append("\n    \"value\" : \"" + cell.value + "\",")
                .append("\n    \"formula\" : \"" + cell.formula + "\"")
                .append("\n}");
        return sb.toString();
    }
}
