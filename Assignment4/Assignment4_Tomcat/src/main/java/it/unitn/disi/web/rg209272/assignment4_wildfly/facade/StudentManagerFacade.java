package it.unitn.disi.web.rg209272.assignment4_wildfly.facade;

import it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.EnrollmentDTO;
import it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.StudentDTO;
import it.unitn.disi.web.rg209272.assignment4_wildfly.auxiliary.Service;

import java.util.List;

public interface StudentManagerFacade extends Service {
    StudentDTO getStudent(int matriculation);
    List<EnrollmentDTO> getStudentCourses(int matriculation);
}