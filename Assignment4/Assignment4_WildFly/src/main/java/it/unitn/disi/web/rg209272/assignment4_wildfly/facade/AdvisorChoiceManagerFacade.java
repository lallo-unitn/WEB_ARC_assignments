package it.unitn.disi.web.rg209272.assignment4_wildfly.facade;

import it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.StudentDTO;
import it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.TeacherDTO;
import it.unitn.disi.web.rg209272.assignment4_wildfly.auxiliary.Service;

import java.util.List;

public interface AdvisorChoiceManagerFacade extends Service {
    StudentDTO getStudent(int matriculation);
    List<TeacherDTO> getTeacherByStudent(int matriculation);
}
