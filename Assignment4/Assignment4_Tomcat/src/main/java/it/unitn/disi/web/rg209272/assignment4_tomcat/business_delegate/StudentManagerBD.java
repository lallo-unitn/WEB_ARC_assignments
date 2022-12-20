package it.unitn.disi.web.rg209272.assignment4_tomcat.business_delegate;

import it.unitn.disi.web.rg209272.assignment4_tomcat.serviceLocator.RemoteServiceInitializer;
import it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.EnrollmentDTO;
import it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.StudentDTO;
import it.unitn.disi.web.rg209272.assignment4_wildfly.facade.StudentManagerFacade;

import java.util.List;

public class StudentManagerBD {
    private final StudentManagerFacade studentManagerFacade;
    private static StudentManagerBD instance;

    private StudentManagerBD(){
        this.studentManagerFacade = RemoteServiceInitializer.getInstance().getStudentManagerFacade();
    };

    public static StudentManagerBD getInstance() {
        if (instance == null) {
            instance = new StudentManagerBD();
        }
        return instance;
    }

    public StudentDTO getStudent(int matriculation){
        return this.studentManagerFacade.getStudent(matriculation);
    }

    public List<EnrollmentDTO> getStudentCourses(int matriculation){
        return this.studentManagerFacade.getStudentCourses(matriculation);
    }

}
