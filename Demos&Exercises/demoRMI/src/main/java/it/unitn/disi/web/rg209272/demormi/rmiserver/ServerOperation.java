package it.unitn.disi.web.rg209272.demormi.rmiserver;

import it.unitn.disi.web.rg209272.demormi.rmiinterface.RMIInterface;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerOperation extends UnicastRemoteObject implements RMIInterface {

    private static final long serialVersionUID = 1L;

    protected ServerOperation() throws RemoteException {

        super();

    }

    @Override
    public String helloTo(String name) throws RemoteException {

        System.err.println(name + " is trying to contact!");
        return "Server says hello to " + name;

    }

    public static void main(String[] args) {

        try {
            Runtime.getRuntime().exec("rmiregistry 1024");
            System.setProperty("java.rmi.server.hostname","localhost");
            Naming.rebind("//localhost:1024/MyServer", new ServerOperation());
            System.err.println("Server ready");

        } catch (Exception e) {

            System.err.println("Server exception: " + e);
            e.printStackTrace();

        }

    }
}
