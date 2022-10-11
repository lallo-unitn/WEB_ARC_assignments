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
<div>
    <form action="AuthServlet" method="POST">
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
        <input type="submit" value="Login">
        <input type="hidden" value="<%= request.getAttribute("destination")%>">

        <%String message = (String) request.getAttribute("message");
            if(message != null){%>
        <br>
        <%= message%>
        <%}%>
    </form>
</div>
</body>
</html>
