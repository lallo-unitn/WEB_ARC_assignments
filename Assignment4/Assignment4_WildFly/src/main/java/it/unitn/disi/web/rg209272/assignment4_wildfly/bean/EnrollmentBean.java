package it.unitn.disi.web.rg209272.assignment4_wildfly.bean;

import it.unitn.disi.web.rg209272.assignment4_wildfly.entities.Enrollment;
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
public class EnrollmentBean {

    private static final org.jboss.logging.Logger logger = Logger.getLogger(EnrollmentBean.class);
    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    public List<Enrollment> getEnrollmentByStudent(Student student) {
        TypedQuery<Enrollment> query = this.entityManager.createQuery("SELECT e FROM Enrollment e WHERE e.stMatriculation = :stMatriculation", Enrollment.class);
        query.setParameter("stMatriculation", student);
        List<Enrollment> enrollmentList = null;
        try {
            enrollmentList = query.getResultList();
        }catch(NoResultException e){
            logger.info("Student [ " + student.getId() + " ] is not enrolled in any course");
            return null;
        }
        return enrollmentList;
    }
}
