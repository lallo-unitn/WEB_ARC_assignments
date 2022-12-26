package it.unitn.disi.web.rg209272.demoejb;

import javax.ejb.Remote;

@Remote
public interface SimpleBeanIF {
    public int incrementNumber(int n);
}
