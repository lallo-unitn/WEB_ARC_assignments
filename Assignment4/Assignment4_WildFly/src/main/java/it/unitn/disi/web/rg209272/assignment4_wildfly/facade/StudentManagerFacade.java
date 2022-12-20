package it.unitn.disi.web.rg209272.assignment4_wildfly.facade;

import it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.EnrollmentDTO;
import it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.StudentDTO;
import it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.TeacherDTO;

import java.util.List;

public interface StudentManagerFacade {
    StudentDTO getStudent(int matriculation);
    List<EnrollmentDTO> getStudentCourses(int matriculation);
}