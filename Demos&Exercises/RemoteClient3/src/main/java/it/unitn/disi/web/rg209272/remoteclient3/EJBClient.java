package it.unitn.disi.web.rg209272.remoteclient3;

import it.unitn.disi.web.rg209272.demoejb.SimpleBeanIF;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class EJBClient {
    EJBClient() {
        Properties jndiProperties = new Properties();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,"org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        Context ctx = null;
        SimpleBeanIF bean = null;
        try {
            ctx = new InitialContext(jndiProperties);
            System.out.println("before");
            bean = (SimpleBeanIF) ctx.lookup("ejb:/demoEJB-1.0-SNAPSHOT/SimpleBeanImpl!it.unitn.disi.web.rg209272.demoejb.SimpleBeanIF");
            System.out.println(bean.getClass().getSimpleName());
            System.out.println(bean.incrementNumber(5));
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String a[]) {
        EJBClient client = new EJBClient();
    }
}