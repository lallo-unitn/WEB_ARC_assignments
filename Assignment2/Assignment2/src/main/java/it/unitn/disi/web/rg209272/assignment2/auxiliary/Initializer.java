package it.unitn.disi.web.rg209272.assignment2.auxiliary;

import it.unitn.disi.web.rg209272.assignment2.beans.UserBean;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Initializer {
    public Initializer(ServletContext ctx, File flagsFile) {
        //SETTING FIRST USER
        List<UserBean> userList = new LinkedList<>();
        String[] userdata = {
                "admin",
                "admin"
        };
        userList.add(new UserBean(userdata[0], userdata[1]));
        ctx.setAttribute("users", userList);

        //SETTING GAME FLAGS
        ArrayList<String> flagsArray = new ArrayList<String>();
        String[] flagsList = flagsFile.list();
        try {
            for(String name:flagsList){
                System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                System.out.println(name);
                flagsArray.add(name);
            }
            ctx.setAttribute("flagsArray", flagsArray);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}
