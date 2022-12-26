package it.unitn.disi.web.rg209272.assignment2.auxiliary;

import it.unitn.disi.web.rg209272.assignment2.beans.UserBean;

import javax.servlet.ServletContext;
import java.io.*;
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
        initUsersFromTxt(ctx);

        //INIT ACTIVE USERS
        HashMap<String, UserBean> activeUsers = new HashMap<>();
        ctx.setAttribute("activeUsers", activeUsers);

        //SETTING GAME FLAGS
        ArrayList<String> flagsArray = new ArrayList();
        String[] flagsList = flagsFile.list();
        try {
            for (String name : flagsList) {
                System.out.println(name);
                flagsArray.add(name);
            }
            ctx.setAttribute("flagsArray", flagsArray);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void initUsersFromTxt(ServletContext ctx) {
        try {
            String line;
            BufferedReader bufferReader = new BufferedReader(
                    new FileReader(ctx.getRealPath("users/info.txt")));
            HashMap<String, UserBean> usersMap = (HashMap<String, UserBean>) ctx.getAttribute("users");
            while ((line = bufferReader.readLine()) != null) {
                String[] splitLine = line.split("username=");
                String username = splitLine[1].split(",")[0];
                String unParsedPassword = splitLine[1].split(",")[1];
                String password = unParsedPassword.split("password=")[1].replace('\n', ' ');
                usersMap.put(username, new UserBean(username, password));
            }
            ctx.setAttribute("users", usersMap);
        } catch (
                FileNotFoundException e) {
            System.out.println("ADDUSER*addUserToTxt(Userbean): File not found");
        } catch (
                IOException e) {
            System.out.println("ADDUSER*addUserToTxt(Userbean): Error initializing stream");
        }
    }
}
