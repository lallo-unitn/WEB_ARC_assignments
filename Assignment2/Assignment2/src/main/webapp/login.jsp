<%--
  Created by IntelliJ IDEA.
  User: ricca
  Date: 19/06/2022
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="AuthServlet" method="POST">
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
    <input type="submit" value="Login">
    <input type="hidden" value="<%= request.getAttribute("destination")%>">

    <%
        String message = (String) request.getAttribute("message");
        if (message != null) {
    %>
    <br>
    <%= message%>
    <%}%>
</form>
<a href="RegistrationServlet">Click here to register</a>
</body>
</html>
