package it.unitn.disi.web.rg209272.assignment4_wildfly.facade;

import it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.EnrollmentDTO;
import it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.StudentDTO;
import it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.TeacherDTO;
import it.unitn.disi.web.rg209272.assignment4_wildfly.entities.Student;

import java.util.List;

public interface BackFacade {
    StudentDTO getStudent(int matriculation);

    TeacherDTO getTeacher(String surname);

    List<EnrollmentDTO> getStudentCourses(int matriculation);
}
