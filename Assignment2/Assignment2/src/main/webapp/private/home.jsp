<%--
  Created by IntelliJ IDEA.
  User: ricca
  Date: 10/10/2022
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Game</title>
</head>
<body>
<jsp:useBean id="userBean"
             type="it.unitn.disi.web.rg209272.assignment2.beans.UserBean"
             scope="session"/>
<jsp:include page="header.jsp"/>
Score: <%= userBean.getScore()%>
<form action="GameServlet" method="GET">
    <input type="submit" value="Login">
</form>
</body>
</html>
