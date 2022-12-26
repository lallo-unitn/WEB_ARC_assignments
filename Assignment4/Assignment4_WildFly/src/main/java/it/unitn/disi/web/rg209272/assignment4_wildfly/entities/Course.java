package it.unitn.disi.web.rg209272.assignment4_wildfly.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "COURSE")
public class Course {
    @Id
    @Column(name = "NAME", nullable = true, length = 30)
    private String NAME;

    public Course() {
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NAME")
    private Teacher teacher;

    public Course(String _name) {
        this.NAME = _name;
    }

    public String getName() {
        return this.NAME;
    }

    public void setName(String _name) {
        this.NAME = _name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return NAME.equals(course.NAME);
    }

    @Override
    public int hashCode() {
        return Objects.hash(NAME);
    }
}