package it.unitn.disi.web.rg209272.assignment4_wildfly.facade;

import it.unitn.disi.web.rg209272.assignment4_wildfly.DTOAssembler.DTOAssembler;
import it.unitn.disi.web.rg209272.assignment4_wildfly.bean.EnrollmentBean;
import it.unitn.disi.web.rg209272.assignment4_wildfly.bean.StudentBean;
import it.unitn.disi.web.rg209272.assignment4_wildfly.bean.TeacherBean;
import it.unitn.disi.web.rg209272.assignment4_wildfly.entities.Enrollment;
import it.unitn.disi.web.rg209272.assignment4_wildfly.entities.Student;
import it.unitn.disi.web.rg209272.assignment4_wildfly.entities.Teacher;
import it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.EnrollmentDTO;
import it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.StudentDTO;
import it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.TeacherDTO;
import org.jboss.logging.Logger;

import javax.ejb.*;
import java.util.LinkedList;
import java.util.List;

@Stateless
@Remote(StudentManagerFacade.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class StudentManagerBean implements StudentManagerFacade {

    private static final Logger logger = Logger.getLogger(StudentManagerBean.class);

    @EJB
    private StudentBean studentBean;

    @EJB
    private EnrollmentBean enrollmentBean;

    @Override
    public StudentDTO getStudent(int matriculation) {
        logger.info("Retrieving student [ " + matriculation + " ] info");
        Student student = this.studentBean.getStudentByMatriculation(matriculation);
        return DTOAssembler.getStudentDTO(student);
    }

    @Override
    public List<EnrollmentDTO> getStudentCourses(int matriculation) {
        logger.info("Retrieving student [ " + matriculation + " ] enrollments");
        Student student = this.studentBean.getStudentByMatriculation(matriculation);
        if(student == null){
            return null;
        }
        List<Enrollment> enrollmentList = this.enrollmentBean.getEnrollmentByStudent(student);
        if(enrollmentList == null || enrollmentList.isEmpty()){
            return null;
        }
        List<EnrollmentDTO> enrollmentDTOList = (List<EnrollmentDTO>) new LinkedList();
        for (Enrollment e:
                enrollmentList) {
            enrollmentDTOList.add(DTOAssembler.getEnrollmentDTO(e));
        }
        return enrollmentDTOList;
    }

}