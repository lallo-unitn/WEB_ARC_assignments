package it.unitn.disi.web.rg209272.assignment4_shared.facade;

import it.unitn.disi.web.rg209272.assignment4_shared.DTOs.EnrollmentDTO;
import it.unitn.disi.web.rg209272.assignment4_shared.DTOs.StudentDTO;
import it.unitn.disi.web.rg209272.assignment4_shared.DTOs.TeacherDTO;

import java.util.List;

public interface StudentManagerFacade {
    StudentDTO getStudent(int matriculation);
    List<EnrollmentDTO> getStudentCourses(int matriculation);
    List<TeacherDTO> getTeacherByStudent(int matriculation);
}
