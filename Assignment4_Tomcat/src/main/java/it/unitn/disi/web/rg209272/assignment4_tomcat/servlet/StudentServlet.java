package it.unitn.disi.web.rg209272.assignment4_tomcat.servlet;

import it.unitn.disi.web.rg209272.assignment4_tomcat.RemoteServiceInitializer;
import it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.EnrollmentDTO;
import it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.StudentDTO;
import it.unitn.disi.web.rg209272.assignment4_wildfly.facade.BackFacade;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//a Student page, where given a matriculation number we can obtain all the
//anagraphic data of the student, together with the list of all the courses he is
//enrolled in and the votes.

public class StudentServlet extends HttpServlet {
    private BackFacade backFacade;

    public void init() {
        this.backFacade = RemoteServiceInitializer.getInstance().getBackFacade();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int matriculation = Integer.parseInt(request.getParameter("matriculation"));
        StudentDTO studentDTO = this.backFacade.getStudent(matriculation);
        request.setAttribute("studentDTO", studentDTO);
        List<EnrollmentDTO> enrollmentDTOList = this.backFacade.getStudentCourses(matriculation);
        request.setAttribute("enrollmentDTOList", enrollmentDTOList);
        request.getRequestDispatcher("studentPage.jsp").forward(request, response);
    }
}