package it.unitn.disi.web.rg209272.assignment4_wildfly.bean;

import it.unitn.disi.web.rg209272.assignment4_wildfly.entities.Student;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.logging.Logger;

@Local
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class StudentBean {

    private static final Logger logger = Logger.getLogger(String.valueOf(StudentBean.class));

    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    public Student getStudentByMatriculation(int matriculation) {
        TypedQuery<Student> query = this.entityManager.createQuery("SELECT s FROM Student s WHERE s.id = :id", Student.class);
        query.setParameter("id", matriculation);
        return query.getSingleResult();
    }
}
