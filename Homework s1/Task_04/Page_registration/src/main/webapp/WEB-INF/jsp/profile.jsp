<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 14.10.2020
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1 style="color: ${cookie.get("color").value}">It's your profile</h1>
</body>
<form action="/profile" method="post">
    <select name="color">
        <option value="red">RED</option>
        <option value="green">GREEN</option>
        <option value="blue">BLUE</option>
    </select>
<%--    <input type="text" placeholder="Some string">--%>
    <input type="submit" value="OK">
</form>
</html>
