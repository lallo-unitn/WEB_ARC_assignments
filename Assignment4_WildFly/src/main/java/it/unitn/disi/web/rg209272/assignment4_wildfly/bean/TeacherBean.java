package it.unitn.disi.web.rg209272.assignment4_wildfly.bean;

import it.unitn.disi.web.rg209272.assignment4_wildfly.entities.Student;
import it.unitn.disi.web.rg209272.assignment4_wildfly.entities.Teacher;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;

@Local
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class TeacherBean {

    private static final Logger logger = Logger.getLogger(String.valueOf(TeacherBean.class));

    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    public Teacher getTeacherBySurname(String surname) {
        TypedQuery<Teacher> query = this.entityManager.createQuery("SELECT t FROM Teacher t WHERE t.id = :surname", Teacher.class);
        query.setParameter("surname", surname);
        return query.getSingleResult();
    }

    //SELECT * FROM TEACHER WHERE TEACHED_COURSE IN
    //(SELECT COURSE_NAME FROM ENROLLMENT WHERE ST_MATRICULATION = 111)
    public List<Teacher> getTeacherByStudent(Student student){
        TypedQuery<Teacher> query = this.entityManager.createQuery("SELECT t FROM Teacher t WHERE t.teachedCourse IN" +
                "(SELECT e.courseName FROM Enrollment e WHERE e.stMatriculation = :stMatriculation)", Teacher.class);
        query.setParameter("stMatriculation", student);
        return query.getResultList();
    }
}
