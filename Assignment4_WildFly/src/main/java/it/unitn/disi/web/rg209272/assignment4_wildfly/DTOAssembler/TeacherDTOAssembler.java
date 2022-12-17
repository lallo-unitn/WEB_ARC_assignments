package it.unitn.disi.web.rg209272.assignment4_wildfly.DTOAssembler;

import it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.TeacherDTO;
import it.unitn.disi.web.rg209272.assignment4_wildfly.entities.Teacher;

public class TeacherDTOAssembler {

    public static TeacherDTO getTeacherDTO(Teacher teacher){
        TeacherDTO dto = new TeacherDTO();
        dto.setName(teacher.getName());
        dto.setSurname(teacher.getId());
        return dto;
    }
}
