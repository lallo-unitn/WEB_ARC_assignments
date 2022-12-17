package it.unitn.disi.web.rg209272.assignment4_wildfly.DTOAssembler;

import it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.EnrollmentDTO;
import it.unitn.disi.web.rg209272.assignment4_wildfly.entities.Enrollment;

public class EnrollmentDTOAssembler {
    public static EnrollmentDTO getEnrollmentDTO(Enrollment enrollment){
        EnrollmentDTO dto = new EnrollmentDTO();
        dto.setCourseName(enrollment.getCourseName().getName());
        dto.setStMatriculation(enrollment.getStMatriculation().getId());
        dto.setGrade(enrollment.getGrade());
        return dto;
    }
}
