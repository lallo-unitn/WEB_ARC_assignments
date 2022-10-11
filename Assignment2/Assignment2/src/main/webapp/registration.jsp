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
        username: <label for="username"></label>
        <input type="text"
               name="username"
               id="username"
               required="required">
        <br>
        password: <label for="password"></label>
        <input type="password"
               name="password"
               id="password"
               required="required">
        <br>
        <input type="password"
               name="repeatPws"
               id="repeatPws"
               required="required">
        <br>
        <input type="submit" value="Login">
    </form>
</body>
</html>
