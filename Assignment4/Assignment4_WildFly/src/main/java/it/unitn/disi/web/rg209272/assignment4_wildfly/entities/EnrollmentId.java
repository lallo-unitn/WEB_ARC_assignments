package it.unitn.disi.web.rg209272.assignment4_wildfly.entities;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EnrollmentId implements Serializable {
    private static final long serialVersionUID = 692556755555737329L;
    @Column(name = "COURSE_NAME", nullable = false, length = 30)
    private String courseName;

    @Column(name = "ST_MATRICULATION", nullable = false)
    private Integer stMatriculation;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getStMatriculation() {
        return stMatriculation;
    }

    public void setStMatriculation(Integer stMatriculation) {
        this.stMatriculation = stMatriculation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EnrollmentId entity = (EnrollmentId) o;
        return Objects.equals(this.courseName, entity.courseName) &&
                Objects.equals(this.stMatriculation, entity.stMatriculation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName, stMatriculation);
    }

}