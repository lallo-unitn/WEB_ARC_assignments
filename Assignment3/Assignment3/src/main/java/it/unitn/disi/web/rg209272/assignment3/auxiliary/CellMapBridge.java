package it.unitn.disi.web.rg209272.assignment3.auxiliary;

import java.util.HashMap;

public class CellMapBridge {

    public CellMapBridge() {
        super();
    }

    public static HashMap<String, Cell> getCellMap(SSEngine engine) {
        return engine.cellMap;
    }
}
