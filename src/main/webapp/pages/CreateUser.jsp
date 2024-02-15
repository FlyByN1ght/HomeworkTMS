<%--
  Created by IntelliJ IDEA.
  User: Даниил
  Date: 14.02.2024
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create</title>
</head>
<body style="background-color: darkcyan; text-align: center">
<form action="/create" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>

    <label for="age">Age:</label>
    <input type="text" id="age" name="age" required>

    <button type="submit">Add User</button>
</form>
</body>
</html>
