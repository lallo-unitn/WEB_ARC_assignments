package it.unitn.disi.web.rg209272.assignment4_wildfly.bean;

import it.unitn.disi.web.rg209272.assignment4_wildfly.entities.Student;
import it.unitn.disi.web.rg209272.assignment4_wildfly.entities.Teacher;
import org.jboss.logging.Logger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Local
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class TeacherBean {
    private static final org.jboss.logging.Logger logger = Logger.getLogger(TeacherBean.class);
    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    /*
    public Teacher getTeacherBySurname(String surname) {
        TypedQuery<Teacher> query = this.entityManager.createQuery("SELECT t FROM Teacher t WHERE t.id = :surname", Teacher.class);
        query.setParameter("surname", surname);
        return query.getSingleResult();
    }
    */

    //SELECT * FROM TEACHER WHERE TEACHED_COURSE IN
    //(SELECT COURSE_NAME FROM ENROLLMENT WHERE ST_MATRICULATION = 111)
    public List<Teacher> getTeacherByStudent(Student student) {
        TypedQuery<Teacher> query = this.entityManager.createQuery("SELECT t FROM Teacher t WHERE t.teachedCourse IN" +
                "(SELECT e.courseName FROM Enrollment e WHERE e.stMatriculation = :stMatriculation)", Teacher.class);
        query.setParameter("stMatriculation", student);
        List<Teacher> teacherList = null;
        try {
            teacherList = query.getResultList();
        }catch(NoResultException e){
            logger.info("Student [ " + student.getId() + " ] is not enrolled in any course");
            return null;
        }
        return teacherList;
    }
}
