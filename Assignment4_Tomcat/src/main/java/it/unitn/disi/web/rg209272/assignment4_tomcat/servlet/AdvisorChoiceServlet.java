package it.unitn.disi.web.rg209272.assignment4_tomcat.servlet;

import it.unitn.disi.web.rg209272.assignment4_tomcat.serviceLocator.RemoteServiceInitializer;
import it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.EnrollmentDTO;
import it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.StudentDTO;
import it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.TeacherDTO;
import it.unitn.disi.web.rg209272.assignment4_wildfly.facade.BackFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

//An advisorChoice page, where given a matriculation number we can obtain the
//anagraphic data of the student, together with the names of the teachers of the
//courses the student is enrolled in

public class AdvisorChoiceServlet extends HttpServlet {

    private BackFacade backFacade;

    public void init() {
        this.backFacade = RemoteServiceInitializer.getInstance().getBackFacade();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int matriculation = Integer.parseInt(request.getParameter("matriculation"));
        StudentDTO studentDTO = this.backFacade.getStudent(matriculation);
        request.setAttribute("studentDTO", studentDTO);
        List<TeacherDTO> teacherDTOList = this.backFacade.getTeacherByStudent(matriculation);
        request.setAttribute("teacherDTOList", teacherDTOList);
        request.getRequestDispatcher("advisorChoice.jsp").forward(request, response);
    }
}
