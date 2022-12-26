package it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs;

import java.io.Serializable;

public class EnrollmentDTO implements Serializable {

    private String courseName;
    private int stMatriculation;

    private int grade;

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getStMatriculation() {
        return stMatriculation;
    }

    public void setStMatriculation(int stMatriculation) {
        this.stMatriculation = stMatriculation;
    }
}
