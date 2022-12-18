package it.unitn.disi.web.rg209272.assignment4_wildfly.facade;

import it.unitn.disi.web.rg209272.assignment4_wildfly.DTOAssembler.DTOAssembler;
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
        return DTOAssembler.getStudentDTO(student);
    }

    @Override
    public List<EnrollmentDTO> getStudentCourses(int matriculation) {
        Student student = this.studentBean.getStudentByMatriculation(matriculation);
        List<Enrollment> enrollmentList = this.enrollmentBean.getEnrollmentByStudent(student);
        List<EnrollmentDTO> enrollmentDTOList = (List<EnrollmentDTO>) new LinkedList();
        for (Enrollment e:
                enrollmentList) {
            enrollmentDTOList.add(DTOAssembler.getEnrollmentDTO(e));
        }
        return enrollmentDTOList;
    }
    @Override
    public List<TeacherDTO> getTeacherByStudent(int matriculation) {
        Student student = this.studentBean.getStudentByMatriculation(matriculation);
        List<Teacher> teacherList = this.teacherBean.getTeacherByStudent(student);
        List<TeacherDTO> teacherDTOList = (List<TeacherDTO>) new LinkedList();
        for (Teacher t:
                teacherList) {
            teacherDTOList.add(DTOAssembler.getTeacherDTO(t));
        }
        return teacherDTOList;
    }
}