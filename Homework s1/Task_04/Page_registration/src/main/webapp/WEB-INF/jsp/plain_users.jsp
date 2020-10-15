<%@ page import="java.util.List" %>
<%@ page import="ru.itis.models.User" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 11.10.2020
  Time: 14:32
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
        <th>ID</th>
        <th>First name</th>
        <th>Last name</th>
        <th>Age</th>
    </tr>

    <%
        List<User> users = (List<User>) request.getAttribute("usersForJsp");
        for (int i = 0; i < users.size(); i++){
            %>
    <tr>
        <td><%=users.get(i).getId()%></>
        <td><%=users.get(i).getFirstName()%></>
        <td><%=users.get(i).getLastName()%></>
        <td><%=users.get(i).getAge()%></>
    </tr>
    <% }%>
</table>

</body>
</html>
