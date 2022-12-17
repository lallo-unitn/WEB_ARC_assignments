package it.unitn.disi.web.rg209272.assignment4_wildfly.DTOAssembler;

import it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.StudentDTO;
import it.unitn.disi.web.rg209272.assignment4_wildfly.entities.Student;

public class StudentDTOAssembler {
    public static StudentDTO getStudentDTO(Student student){
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setSurname(student.getSurname());
        return dto;
    }
}
