package it.unitn.disi.web.rg209272.assignment4_wildfly.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ENROLLMENT")
public class Enrollment {
    @EmbeddedId
    private EnrollmentId id;

    @MapsId("courseName")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "COURSE_NAME", nullable = false)
    private Course courseName;

    @MapsId("stMatriculation")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ST_MATRICULATION", nullable = false)
    private Student stMatriculation;

    @Column(name = "GRADE")
    private Integer grade;

    public EnrollmentId getId() {
        return id;
    }

    public void setId(EnrollmentId id) {
        this.id = id;
    }

    public Course getCourseName() {
        return courseName;
    }

    public void setCourseName(Course courseName) {
        this.courseName = courseName;
    }

    public Student getStMatriculation() {
        return stMatriculation;
    }

    public void setStMatriculation(Student stMatriculation) {
        this.stMatriculation = stMatriculation;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return id.equals(that.id) && courseName.equals(that.courseName) && stMatriculation.equals(that.stMatriculation) && grade.equals(that.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseName, stMatriculation, grade);
    }
}