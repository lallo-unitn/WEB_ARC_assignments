<%--
  Created by IntelliJ IDEA.
  User: ricca
  Date: 13/10/2022
  Time: 19:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.HashMap" %>
<%@ page import="it.unitn.disi.web.rg209272.assignment2.beans.UserBean" %>
<%@ page import="java.util.Set" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Page</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<jsp:useBean id="activeUsers"
             type="java.util.HashMap"
             scope="application"/>
<ol>
        <% for (String key :
            ( (HashMap<String, UserBean>)activeUsers ).keySet() ){
        UserBean ub = (UserBean)activeUsers.get(key);%>
    <li>
        Username: <%=ub.getUsername()%> Score: <%=ub.getScore()%>
    </li>
        <%}%>
</body>
</html>
