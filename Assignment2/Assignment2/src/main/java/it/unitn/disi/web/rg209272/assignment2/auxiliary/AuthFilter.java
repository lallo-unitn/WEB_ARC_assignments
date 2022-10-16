package it.unitn.disi.web.rg209272.assignment2.auxiliary;

import it.unitn.disi.web.rg209272.assignment2.beans.UserBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        String path = httpRequest.getServletPath();
        String query = httpRequest.getQueryString();
        String destination = path;
        if (query != null && !query.equals("null"))
            destination = path + "?" + query;
        httpRequest.setAttribute("destination", destination);
        UserBean ub = (UserBean) session.getAttribute("userBean");
        RequestDispatcher rd;
        if (ub != null && ub.getUsername() != null && !ub.getUsername().equals("null")) {
            System.out.println("AUTHFILTER: " + ub.getUsername());
            System.out.println("AUTHFILTER: " + destination);
            if (ub.getUsername().equals("admin")) {
                rd = httpRequest.getRequestDispatcher("ControlServlet");
            } else {
                rd = httpRequest.getRequestDispatcher(destination);
            }
        } else {
            System.out.println("AUTHFILTER: " + "not auth");
            rd = httpRequest.getRequestDispatcher("LoginServlet");
        }
        rd.forward(request, response);
    }
}