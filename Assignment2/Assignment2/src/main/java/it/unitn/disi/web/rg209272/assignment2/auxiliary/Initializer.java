package it.unitn.disi.web.rg209272.assignment2.auxiliary;

import it.unitn.disi.web.rg209272.assignment2.beans.UserBean;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Initializer {
    public Initializer(ServletContext ctx, File flagsFile) {
        //SETTING FIRST USER
        HashMap<String, UserBean> usersMap = new HashMap<>();
        String[] userdata = {
                "admin",
                "nimda"
        };
        usersMap.put(userdata[0], new UserBean(userdata[0], userdata[1]));
        ctx.setAttribute("users", usersMap);

        //INIT ACTIVE USERS
        HashMap<String, UserBean> activeUsers = new HashMap<>();
        ctx.setAttribute("activeUsers", activeUsers);

        //SETTING GAME FLAGS
        ArrayList<String> flagsArray = new ArrayList();
        String[] flagsList = flagsFile.list();
        try {
            for(String name:flagsList){
                System.out.println(name);
                flagsArray.add(name);
            }
            ctx.setAttribute("flagsArray", flagsArray);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}
