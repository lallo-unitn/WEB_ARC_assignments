package it.unitn.disi.web.rg209272.assignment2.auxiliary;

import it.unitn.disi.web.rg209272.assignment2.beans.UserBean;

import javax.servlet.ServletContext;
import java.util.LinkedList;
import java.util.List;

public class Initializer {
    public Initializer(ServletContext ctx) {
        List<UserBean> userList = new LinkedList<>();
        String[] userdata = {
                "admin",
                "admin"
        };
        userList.add(new UserBean(userdata[0], userdata[1]));
        ctx.setAttribute("users", userList);
    }
}
