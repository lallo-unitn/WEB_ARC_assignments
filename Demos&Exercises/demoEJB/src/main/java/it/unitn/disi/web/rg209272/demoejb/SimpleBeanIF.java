package it.unitn.disi.web.rg209272.demoejb;

import jakarta.ejb.Remote;

@Remote
public interface SimpleBeanIF {
    public int incrementNumber(int n);
}
