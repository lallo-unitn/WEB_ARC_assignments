package it.unitn.disi.web.rg209272.assignment4_wildfly.DTOAssembler;

import it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.EnrollmentDTO;
import it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.StudentDTO;
import it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.TeacherDTO;
import it.unitn.disi.web.rg209272.assignment4_wildfly.entities.Enrollment;
import it.unitn.disi.web.rg209272.assignment4_wildfly.entities.Student;
import it.unitn.disi.web.rg209272.assignment4_wildfly.entities.Teacher;

public class DTOAssembler {
    public static EnrollmentDTO getEnrollmentDTO(Enrollment enrollment) {
        EnrollmentDTO dto = new EnrollmentDTO();
        dto.setCourseName(enrollment.getCourseName().getName());
        dto.setStMatriculation(enrollment.getStMatriculation().getId());
        dto.setGrade(enrollment.getGrade());
        return dto;
    }

    public static StudentDTO getStudentDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setSurname(student.getSurname());
        return dto;
    }

    public static TeacherDTO getTeacherDTO(Teacher teacher) {
        TeacherDTO dto = new TeacherDTO();
        dto.setName(teacher.getName());
        dto.setSurname(teacher.getId());
        return dto;
    }
}
