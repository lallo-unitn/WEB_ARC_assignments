package it.unitn.disi.web.rg209272.assignment4_wildfly.entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "STUDENT")
public class Student {
    @Id
    @Column(name = "MATRICULATION", nullable = false)
    private Integer id;

    @Column(name = "NAME", length = 30)
    private String name;

    @Column(name = "SURNAME", length = 30)
    private String surname;

    @OneToMany(mappedBy = "stMatriculation")
    private Set<Enrollment> enrollments = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Set<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Set<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id.equals(student.id) && name.equals(student.name) && surname.equals(student.surname) && enrollments.equals(student.enrollments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, enrollments);
    }
}