package it.unitn.disi.web.rg209272.assignment4_wildfly.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "TEACHER")
public class Teacher {
    @Id
    @Column(name = "SURNAME", nullable = false, length = 30)
    private String id;

    @Column(name = "NAME", nullable = false, length = 30)
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEACHED_COURSE")
    private Course teachedCourse;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course getTeachedCourse() {
        return teachedCourse;
    }

    public void setTeachedCourse(Course teachedCourse) {
        this.teachedCourse = teachedCourse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return id.equals(teacher.id) && name.equals(teacher.name) && teachedCourse.equals(teacher.teachedCourse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, teachedCourse);
    }
}