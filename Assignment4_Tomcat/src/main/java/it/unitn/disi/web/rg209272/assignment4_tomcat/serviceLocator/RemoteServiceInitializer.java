package it.unitn.disi.web.rg209272.assignment4_tomcat.serviceLocator;

import it.unitn.disi.web.rg209272.assignment4_wildfly.facade.BackFacade;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.HashMap;
import java.util.Properties;

public class RemoteServiceInitializer {
    private static RemoteServiceInitializer instance;
    private Context ctx;
    private BackFacade backFacade;

    private static HashMap<String, BackFacade> cache;
    static {
        cache = new HashMap<String, BackFacade> ();
    }

    private RemoteServiceInitializer() {
        this.ctx = createInitialContext();
    }

    public static RemoteServiceInitializer getInstance() {
        if (instance == null) {
            instance = new RemoteServiceInitializer();
        }
        return instance;
    }

    private Context createInitialContext() {
        try {
            Properties jndiProperties = new Properties();
            jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,
                    "org.jboss.naming.remote.client.InitialContextFactory");
            jndiProperties.put(Context.URL_PKG_PREFIXES,
                    "org.jboss.ejb.client.naming");
            jndiProperties.put(Context.PROVIDER_URL,
                    "http-remoting://localhost:8080");
            jndiProperties.put("jboss.naming.client.ejb.context", true);
            return new InitialContext(jndiProperties);
        } catch (NamingException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public synchronized BackFacade getBackFacade() {
        String jndiName = "ejb:/Assignment4_WildFly-1.0-SNAPSHOT/BackFacadeBean!" +
            "it.unitn.disi.web.rg209272.assignment4_wildfly.facade.BackFacade";
        this.backFacade = cache.get(jndiName);
        if (this.backFacade == null) {
            try {
                this.backFacade = (BackFacade) ctx.lookup(jndiName);
                cache.put(jndiName, this.backFacade);
            } catch (NamingException e) {
                e.printStackTrace();
                this.backFacade = null;
            }
        }
        return this.backFacade;
    }
}
