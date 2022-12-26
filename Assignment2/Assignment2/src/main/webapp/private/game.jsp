<%--
  Created by IntelliJ IDEA.
  User: ricca
  Date: 12/10/2022
  Time: 13:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Let's Play!</title>
    <style>
        .large {
            display: block;
            margin-bottom: 1em;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp"/>
<ol>
    <li>Algiers</li>
    <li>Yerevan</li>
    <li>N'Djamena</li>
    <li>Prague</li>
    <li>Djibouti</li>
    <li>Libreville</li>
    <li>Jakarta</li>
    <li>Vilnius</li>
    <li>La Valletta</li>
    <li>Kiev</li>
</ol>
<br>
<form action="CheckAnswersServlet" method="POST">
    <img src="<%= request.getAttribute("flag1")%>">
    <input type="number"
           required="required"
           id="flag1Ans"
           name="flag1Ans">
    <span class="large"></span>
    <img src="<%= request.getAttribute("flag2")%>">
    <input type="number"
           required="required"
           id="flag2Ans"
           name="flag2Ans">
    <span class="large"></span>
    <img src="<%= request.getAttribute("flag3")%>">
    <input type="number"
           required="required"
           id="flag3Ans"
           name="flag3Ans">
    <span class="large"></span>
    <input type="submit" value="Submit">
</form>
</body>
</html>
