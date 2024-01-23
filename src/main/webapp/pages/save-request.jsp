<%--
  Created by IntelliJ IDEA.
  User: Даниил
  Date: 23.01.2024
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<form action="/form" method="post">
<div class="input-group">
    <span class="input-group-text">First and last name</span>
    <input type="text" aria-label="First name" class="form-control" name="firstName">
    <input type="text" aria-label="Last name" class="form-control" name="lastName">
</div>
<div class="input-group mb-3">
    <span class="input-group-text" id="inputGroup-sizing-default">Age</span>
    <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default"
    name="age">
</div>
<button type="submit" class="btn btn-primary">Submit</button>
</form>
</body>
</html>
