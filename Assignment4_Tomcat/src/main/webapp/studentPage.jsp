<%@ page import="it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.EnrollmentDTO" %><%--
  Created by IntelliJ IDEA.
  User: ricca
  Date: 17/12/2022
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Page</title>
</head>
<body>
<h2>Student Info</h2>
<jsp:useBean id="studentDTO" scope="request" class="it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.StudentDTO"/>
<ul>
    <li>Name: ${studentDTO.name}</li>
    <li>Surname: ${studentDTO.surname}</li>
    <li>Matriculation: ${studentDTO.id}</li>
</ul>
<ul>
<jsp:useBean id="enrollmentDTO"
             scope="request"
             class="it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.EnrollmentDTO"/>

<jsp:useBean id="enrollmentDTOList"
             type="java.util.List"
             scope="request"/>

<% for (EnrollmentDTO e :
        (List<EnrollmentDTO>)enrollmentDTOList){%>
<li>
    Course name: <%=e.getCourseName()%> Grade: <%=e.getGrade()%>
</li>
<%}%>
</ul>
</body>
</html>
