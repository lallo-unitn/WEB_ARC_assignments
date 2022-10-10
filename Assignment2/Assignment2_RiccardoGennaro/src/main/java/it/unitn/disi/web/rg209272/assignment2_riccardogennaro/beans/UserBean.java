package it.unitn.disi.web.rg209272.assignment2_riccardogennaro.beans;

import java.io.Serializable;

public class UserBean implements Serializable {
    private String username = null;
    private String password = null;

    public UserBean(){}
    public UserBean(String username, String password){
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean equals(Object o){
        System.out.println(this.username);
        System.out.println( ((UserBean) o).getUsername() );
        if(
                o instanceof UserBean &&
                        (this.username).equals( ((UserBean) o).getUsername() ) &&
                        (this.password).equals( ((UserBean) o).getPassword() )
        ){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode(){
        return this.username.hashCode() + this.password.hashCode();
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
