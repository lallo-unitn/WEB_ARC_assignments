package it.unitn.disi.web.rg209272.assignment4_tomcat.serviceLocator;

import it.unitn.disi.web.rg209272.assignment4_wildfly.facade.AdvisorChoiceManagerFacade;
import it.unitn.disi.web.rg209272.assignment4_wildfly.facade.StudentManagerFacade;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.HashMap;
import java.util.Properties;

public class RemoteServiceInitializer {
    private static RemoteServiceInitializer instance;
    private Context ctx;
    private StudentManagerFacade studentManagerFacade;
    private AdvisorChoiceManagerFacade advisorChoiceManagerFacade;

    private static HashMap<String, StudentManagerFacade> cacheStudent;
    private static HashMap<String, AdvisorChoiceManagerFacade> cacheAdvisor;
    static {
        cacheStudent = new HashMap<String, StudentManagerFacade> ();
        cacheAdvisor = new HashMap<String, AdvisorChoiceManagerFacade> ();
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

    public synchronized StudentManagerFacade getStudentManagerFacade() {
        String jndiName = "ejb:/Assignment4_WildFly-1.0-SNAPSHOT/StudentManagerBean!" +
                "it.unitn.disi.web.rg209272.assignment4_wildfly.facade.StudentManagerFacade";
        this.studentManagerFacade = cacheStudent.get(jndiName);
        if (this.studentManagerFacade == null) {
            try {
                this.studentManagerFacade = (StudentManagerFacade) ctx.lookup(jndiName);
                cacheStudent.put(jndiName, this.studentManagerFacade);
            } catch (NamingException e) {
                e.printStackTrace();
                this.studentManagerFacade = null;
            }
        }
        return this.studentManagerFacade;
    }

    public synchronized AdvisorChoiceManagerFacade getAdvisorChoiceManagerFacade() {
        String jndiName = "ejb:/Assignment4_WildFly-1.0-SNAPSHOT/AdvisorChoiceManagerBean!" +
                "it.unitn.disi.web.rg209272.assignment4_wildfly.facade.AdvisorChoiceManagerFacade";
        this.advisorChoiceManagerFacade = cacheAdvisor.get(jndiName);
        if (this.advisorChoiceManagerFacade == null) {
            try {
                this.advisorChoiceManagerFacade = (AdvisorChoiceManagerFacade) ctx.lookup(jndiName);
                cacheAdvisor.put(jndiName, this.advisorChoiceManagerFacade);
            } catch (NamingException e) {
                e.printStackTrace();
                this.advisorChoiceManagerFacade = null;
            }
        }
        return this.advisorChoiceManagerFacade;
    }
}
