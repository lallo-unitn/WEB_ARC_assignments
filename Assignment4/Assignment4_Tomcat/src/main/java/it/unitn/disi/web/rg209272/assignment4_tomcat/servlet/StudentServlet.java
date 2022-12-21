package it.unitn.disi.web.rg209272.assignment4_tomcat.servlet;

import it.unitn.disi.web.rg209272.assignment4_tomcat.business_delegate.StudentManagerBD;
import it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.EnrollmentDTO;
import it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.StudentDTO;

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

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("messageStudent", "");
        request.setAttribute("messageAdvisor", "");
        int matriculation = Integer.parseInt(request.getParameter("matriculation"));
        StudentManagerBD stbd = StudentManagerBD.getInstance();
        StudentDTO studentDTO = stbd.getStudent(matriculation);
        if(studentDTO == null){
            request.setAttribute("messageStudent", "Matriculation is not registered");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        request.setAttribute("studentDTO", studentDTO);
        List<EnrollmentDTO> enrollmentDTOList = stbd.getStudentCourses(matriculation);
        request.setAttribute("enrollmentDTOList", enrollmentDTOList);
        request.getRequestDispatcher("studentPage.jsp").forward(request, response);
    }
}