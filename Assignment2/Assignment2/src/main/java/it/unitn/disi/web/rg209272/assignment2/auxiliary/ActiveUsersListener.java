package it.unitn.disi.web.rg209272.assignment2.auxiliary;

import it.unitn.disi.web.rg209272.assignment2.beans.UserBean;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.HashMap;
import java.util.LinkedList;

public class ActiveUsersListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    public ActiveUsersListener() {
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        ServletContext ctx = sbe.getSession().getServletContext();
        HttpSession session = sbe.getSession();
        HashMap<String, UserBean> activeUsers = (HashMap<String, UserBean>) ctx.getAttribute("activeUsers");
        UserBean ub = (UserBean) session.getAttribute("userBean");
        activeUsers.put(ub.getUsername(), ub);
        ctx.setAttribute("activeUsers", activeUsers);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        removeActiveUser(se);
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        removeActiveUser(sbe);
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        ServletContext ctx = sbe.getSession().getServletContext();
        HttpSession session = sbe.getSession();
        HashMap<String, UserBean> activeUsers = (HashMap<String, UserBean>) ctx.getAttribute("activeUsers");
        UserBean ub = (UserBean) sbe.getValue();
        activeUsers.replace(ub.getUsername(), ub);
        ctx.setAttribute("activeUsers", activeUsers);
    }

    private void removeActiveUser(HttpSessionEvent se) {
        ServletContext ctx = se.getSession().getServletContext();
        HttpSession session = se.getSession();
        HashMap<String, UserBean> activeUsers = (HashMap<String, UserBean>) ctx.getAttribute("activeUsers");
        UserBean ub = (UserBean) session.getAttribute("userBean");
        activeUsers.remove(ub.getUsername(), ub);
        ctx.setAttribute("activeUsers", activeUsers);
        System.out.println("SESSIONLISTENER: " + ub.getUsername() + "removed from active users");
    }
}
