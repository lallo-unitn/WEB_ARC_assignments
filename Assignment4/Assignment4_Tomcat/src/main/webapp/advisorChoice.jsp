<%@ page import="it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.TeacherDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Advisor Choice</title>
</head>
<body>
<h2>Advisory Choice</h2>
<jsp:useBean id="studentDTO"
             scope="request"
             class="it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.StudentDTO"/>
<ul>
    <li>Name: ${studentDTO.name}</li>
    <li>Surname: ${studentDTO.surname}</li>
    <li>Matriculation: ${studentDTO.id}</li>
</ul>
<hr>
<h3>Teachers</h3>
<ul>
    <jsp:useBean id="teacherDTO"
                 scope="request"
                 class="it.unitn.disi.web.rg209272.assignment4_wildfly.DTOs.TeacherDTO"/>

    <jsp:useBean id="teacherDTOList"
                 type="java.util.List"
                 scope="request"/>

    <% for (TeacherDTO t :
            (List<TeacherDTO>)teacherDTOList){%>
    <li>
        <%=t.getName() + " "%>  <%=t.getSurname()%>
    </li>
    <%}%>
</ul>
</body>
</html>
