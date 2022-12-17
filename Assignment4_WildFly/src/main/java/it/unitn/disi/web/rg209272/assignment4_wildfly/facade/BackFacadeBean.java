package it.unitn.disi.web.rg209272.assignment4_wildfly.facade;

import it.unitn.disi.web.rg209272.assignment4_wildfly.DTOAssembler.EnrollmentDTOAssembler;
import it.unitn.disi.web.rg209272.assignment4_wildfly.DTOAssembler.StudentDTOAssembler;
import it.unitn.disi.web.rg209272.assignment4_wildfly.DTOAssembler.TeacherDTOAssembler;
import it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.EnrollmentDTO;
import it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.StudentDTO;
import it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.TeacherDTO;
import it.unitn.disi.web.rg209272.assignment4_wildfly.bean.EnrollmentBean;
import it.unitn.disi.web.rg209272.assignment4_wildfly.bean.StudentBean;
import it.unitn.disi.web.rg209272.assignment4_wildfly.bean.TeacherBean;
import it.unitn.disi.web.rg209272.assignment4_wildfly.entities.Enrollment;
import it.unitn.disi.web.rg209272.assignment4_wildfly.entities.Student;
import it.unitn.disi.web.rg209272.assignment4_wildfly.entities.Teacher;
import org.jboss.logging.Logger;

import javax.ejb.*;
import java.util.LinkedList;
import java.util.List;

@Stateless
@Remote(BackFacade.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BackFacadeBean implements BackFacade {

    private static final Logger logger = Logger.getLogger(BackFacadeBean.class);

    @EJB
    private StudentBean studentBean;

    @EJB
    private TeacherBean teacherBean;

    @EJB
    private EnrollmentBean enrollmentBean;

    @Override
    public StudentDTO getStudent(int matriculation) {
        Student student = this.studentBean.getStudentByMatriculation(matriculation);
        return StudentDTOAssembler.getStudentDTO(student);
    }

    @Override
    public TeacherDTO getTeacher(String surname) {
        Teacher teacher = this.teacherBean.getTeacherBySurname(surname);
        return TeacherDTOAssembler.getTeacherDTO(teacher);
    }

    @Override
    public List<EnrollmentDTO> getStudentCourses(int matriculation) {
        Student student = this.studentBean.getStudentByMatriculation(matriculation);
        List<Enrollment> enrollment = this.enrollmentBean.getEnrollmentByStudent(student);
        List<EnrollmentDTO> enrollmentDTOList = (List<EnrollmentDTO>) new LinkedList();
        for (Enrollment e:
             enrollment) {
            enrollmentDTOList.add(EnrollmentDTOAssembler.getEnrollmentDTO(e));
        }
        return enrollmentDTOList;
    }
}