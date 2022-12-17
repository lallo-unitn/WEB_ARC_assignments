package it.unitn.disi.web.rg209272.demormi.rmiinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface RMIInterface extends Remote {

    public String helloTo(String name) throws RemoteException;

}