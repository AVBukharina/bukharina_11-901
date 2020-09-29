<%@ page import="models.Users" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 29.09.2020
  Time: 0:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <th>Email</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Number</th>
    </tr>

    <%
        List<Users> users = (List<Users>) request.getAttribute("usersForJsp");
        for (int i = 0; i < users.size(); i++) {
    %>
    <tr>
        <td><%=users.get(i).getEmail()%></>
        <td><%=users.get(i).getName()%></>
        <td><%=users.get(i).getSurname()%></>
        <td><%=users.get(i).getNumber()%></>
    </tr>
    <%}%>
</table>

</body>
</html>
