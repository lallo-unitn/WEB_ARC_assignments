package it.unitn.disi.web.rg209272.assignment4_tomcat.auxiliary;

public enum JndiName {

    STUDENT("ejb:/Assignment4_WildFly-1.0-SNAPSHOT/StudentManagerBean!" +
            "it.unitn.disi.web.rg209272.assignment4_wildfly.facade.StudentManagerFacade"),
    ADVISOR("ejb:/Assignment4_WildFly-1.0-SNAPSHOT/AdvisorChoiceManagerBean!" +
            "it.unitn.disi.web.rg209272.assignment4_wildfly.facade.AdvisorChoiceManagerFacade");

    private final String jndiName;

    JndiName(String jndiName){
        this.jndiName = jndiName;
    }

    public String getJndiName(){
        return this.jndiName;
    }

}
