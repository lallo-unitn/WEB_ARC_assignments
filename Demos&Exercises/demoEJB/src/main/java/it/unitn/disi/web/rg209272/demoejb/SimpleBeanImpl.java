package it.unitn.disi.web.rg209272.demoejb;

import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;

@Stateless
@Remote(SimpleBeanIF.class)
public class SimpleBeanImpl implements SimpleBeanIF {
    @Override
    public int incrementNumber(int n) {
        return n + 1;
    }
}
