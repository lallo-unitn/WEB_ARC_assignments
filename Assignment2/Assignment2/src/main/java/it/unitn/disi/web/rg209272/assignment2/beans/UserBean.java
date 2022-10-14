package it.unitn.disi.web.rg209272.assignment2.beans;

import java.io.Serializable;

public class UserBean implements Serializable {
    private String username = null;
    private String password = null;
    private int score;

    public UserBean() {
    }

    public UserBean(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (
                o instanceof UserBean &&
                        (this.username).equals(((UserBean) o).getUsername()) &&
                        (this.password).equals(((UserBean) o).getPassword())
        ) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.username.hashCode() + this.password.hashCode();
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public int getScore() {
        return this.score;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "username=" + username +
                ", password=" + password + '\n';
    }
}
