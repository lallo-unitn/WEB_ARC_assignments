package it.unitn.disi.web.rg209272.assignment2.auxiliary;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JspFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse httpResponse= (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        httpResponse.sendError(404);
    }
}
