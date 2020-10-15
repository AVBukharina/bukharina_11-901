<%@ page import="ru.itis.models.User" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 11.10.2020
  Time: 14:52
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

    <c:forEach items="${usersForJsp}" var="user">
        <tr>
        <td>${user.id}</td>
        <td>${user.firstName}</td>
        <td>${user.lastName}</td>
        <td>${user.age}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
