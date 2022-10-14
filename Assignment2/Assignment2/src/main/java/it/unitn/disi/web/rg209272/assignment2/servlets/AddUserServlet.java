package it.unitn.disi.web.rg209272.assignment2.servlets;

import it.unitn.disi.web.rg209272.assignment2.beans.UserBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;

public class AddUserServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.setProperty("file.encoding", "UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext ctx = getServletContext();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repeatPsw = request.getParameter("repeatPsw");
        RequestDispatcher rd;
        System.out.println("ADDUSER: start");
        if (!password.equals(repeatPsw)) {
            System.out.println("ADDUSER: Wrong credential");
            System.out.println(password + " / " + repeatPsw);
            rd = request.getRequestDispatcher("RegistrationServlet");
            rd.forward(request, response);
            return;
        }
        System.out.println("ADDUSER: checked psw == repPsw");
        HashMap<String, UserBean> usersMap = (HashMap<String, UserBean>) ctx.getAttribute("users");
        UserBean ub = new UserBean(username, password);
        if (!usersMap.containsKey(username)) {
            usersMap.put(username, ub);
            ctx.setAttribute("users", usersMap);
            System.out.println("ADDUSER: User ADDED");
            addUserToTxt(ub);
        } else {
            rd = request.getRequestDispatcher("RegistrationServlet");
            request.setAttribute("message", "User already exists");
            System.out.println("ADDUSER: User AE");
        }

        rd = request.getRequestDispatcher("LoginServlet");
        rd.forward(request, response);
    }

    private void addUserToTxt(UserBean ub) {
        try {
            FileOutputStream f = new FileOutputStream(this.getServletContext().getRealPath("users/info.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(ub);
            System.out.println("ADDUSER*addUserToTxt(Userbean): User ADDED");

            o.close();
            f.close();

            FileInputStream fi = new FileInputStream(this.getServletContext().getRealPath("users/info.txt"));
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            UserBean pr1;
            try {
                while (true) {
                    pr1 = (UserBean) oi.readObject();
                    System.out.println(pr1.toString());
                }
            } catch (EOFException e) {
                oi.close();
                fi.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("ADDUSER*addUserToTxt(Userbean): File not found");
        } catch (IOException e) {
            System.out.println("ADDUSER*addUserToTxt(Userbean): Error initializing stream");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
