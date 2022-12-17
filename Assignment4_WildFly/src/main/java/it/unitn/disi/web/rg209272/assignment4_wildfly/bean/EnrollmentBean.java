package it.unitn.disi.web.rg209272.assignment4_wildfly.bean;


import it.unitn.disi.web.rg209272.assignment4_wildfly.entities.Course;
import it.unitn.disi.web.rg209272.assignment4_wildfly.entities.Enrollment;
import it.unitn.disi.web.rg209272.assignment4_wildfly.entities.Student;

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
public class EnrollmentBean {
    private static final Logger logger = Logger.getLogger(String.valueOf(EnrollmentBean.class));

    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    public List<Enrollment> getEnrollmentByStudent(Student student) {
        TypedQuery<Enrollment> query = this.entityManager.createQuery("SELECT e FROM Enrollment e WHERE e.stMatriculation = :stMatriculation", Enrollment.class);
        query.setParameter("stMatriculation", student);
        return query.getResultList();
    }
}
