package it.unitn.disi.web.rg209272.assignment4_shared.DTOs;

import java.io.Serializable;

public class TeacherDTO implements Serializable {
    private String name;
    private String surname;

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
}
