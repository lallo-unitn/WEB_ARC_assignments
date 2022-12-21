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
import javax.persistence.NoResultException;
import java.util.LinkedList;
import java.util.List;

@Stateless
@Remote(AdvisorChoiceManagerFacade.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AdvisorChoiceManagerBean implements AdvisorChoiceManagerFacade {

    private static final Logger logger = Logger.getLogger(AdvisorChoiceManagerBean.class);

    @EJB
    private StudentBean studentBean;

    @EJB
    private TeacherBean teacherBean;

    @Override
    public StudentDTO getStudent(int matriculation) {
        logger.info("Retrieving student [ " + matriculation + " ] info");
        Student student = this.studentBean.getStudentByMatriculation(matriculation);
        return DTOAssembler.getStudentDTO(student);
    }

    @Override
    public List<TeacherDTO> getTeacherByStudent(int matriculation) {
        Student student = this.getStudentAux(matriculation);
        List<Teacher> teacherList = null;
        if(student == null){
            return null;
        }
        teacherList = this.teacherBean.getTeacherByStudent(student);
        if(teacherList == null || teacherList.isEmpty()){
            return null;
        }
        List<TeacherDTO> teacherDTOList = (List<TeacherDTO>) new LinkedList();
        for (Teacher t:
                teacherList) {
            teacherDTOList.add(DTOAssembler.getTeacherDTO(t));
        }
        return teacherDTOList;
    }

    private Student getStudentAux(int matriculation){
        Student student = null;
        student = this.studentBean.getStudentByMatriculation(matriculation);
        return student;
    }
}