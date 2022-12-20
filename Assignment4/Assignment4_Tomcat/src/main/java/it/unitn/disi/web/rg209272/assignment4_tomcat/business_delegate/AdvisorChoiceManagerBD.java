package it.unitn.disi.web.rg209272.assignment4_tomcat.business_delegate;

import it.unitn.disi.web.rg209272.assignment4_tomcat.serviceLocator.RemoteServiceInitializer;
import it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.StudentDTO;
import it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.TeacherDTO;
import it.unitn.disi.web.rg209272.assignment4_wildfly.facade.AdvisorChoiceManagerFacade;

import java.util.List;

public class AdvisorChoiceManagerBD {
    private final AdvisorChoiceManagerFacade advisorChoiceManagerFacade;
    private static AdvisorChoiceManagerBD instance;

    private AdvisorChoiceManagerBD(){
        this.advisorChoiceManagerFacade = RemoteServiceInitializer.getInstance().getAdvisorChoiceManagerFacade();
    };

    public static AdvisorChoiceManagerBD getInstance() {
        if (instance == null) {
            instance = new AdvisorChoiceManagerBD();
        }
        return instance;
    }

    public StudentDTO getStudent(int matriculation){
        return this.advisorChoiceManagerFacade.getStudent(matriculation);
    }

    public List<TeacherDTO> getTeacherByStudent(int matriculation){
        return this.advisorChoiceManagerFacade.getTeacherByStudent(matriculation);
    }
}
