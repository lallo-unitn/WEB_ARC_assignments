package it.unitn.disi.web.rg209272.assignment4_wildfly.bean;

import it.unitn.disi.web.rg209272.assignment4_wildfly.entities.Student;
import org.jboss.logging.Logger;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Local
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class StudentBean {

    private static final Logger logger = Logger.getLogger(StudentBean.class);
    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    public Student getStudentByMatriculation(int matriculation) {
        TypedQuery<Student> query = this.entityManager.createQuery("SELECT s FROM Student s WHERE s.id = :id", Student.class);
        query.setParameter("id", matriculation);
        Student student = null;
        try {
            student = query.getSingleResult();
        }catch(NoResultException e){
            logger.info("Student [ " + matriculation + " ] is not registered");
            return null;
        }
        return student;
    }
}
