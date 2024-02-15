<%--
  Created by IntelliJ IDEA.
  User: Даниил
  Date: 14.02.2024
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Get ID</title></head>
<body style="background-color: darkcyan">
<div style="text-align: center">
    <h2>ID: ${userId}</h2>
    <h2>Username: ${username}</h2>
    <h2>Age: ${userAge}</h2>
    <h2>Created: ${userCreated} </h2>
    <h2>Change: ${userChange == null ? 'not change' : userChange}</h2>
</div>
</body>
</html>