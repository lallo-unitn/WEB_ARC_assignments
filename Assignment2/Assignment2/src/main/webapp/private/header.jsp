<jsp:useBean id="userBean"
             type="it.unitn.disi.web.rg209272.assignment2.beans.UserBean"
             scope="session"/>
<h1 style="background-color: aqua; font-family: 'Comic Sans MS', serif">
  <%=userBean.getUsername()%>
</h1>