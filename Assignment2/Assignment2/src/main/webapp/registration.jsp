<%--
  Created by IntelliJ IDEA.
  User: ricca
  Date: 11/10/2022
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form action="AddUserServlet" method="POST">
    <label for="username"></label>
    <input type="text"
           placeholder="Username"
           name="username"
           id="username"
           required="required">
    <label for="password"></label>
    <input type="password"
           placeholder="Password"
           name="password"
           id="password"
           required="required">
    <label for="repeatPsw"></label>
    <input type="password"
           placeholder="Repeat password"
           name="repeatPsw"
           id="repeatPsw"
           required="required">
    <input type="submit" value="Submit">
</form>
<%
    String message = (String) request.getAttribute("message");
    if (message != null) {
%>
<br>
<%= message%>
<%}%>
</body>
</html>
