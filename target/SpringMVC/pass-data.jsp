<%--
  Created by IntelliJ IDEA.
  User: itvdn
  Date: 30.07.18
  Time: 23:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pass data</title>
</head>
<body>

<form id="user-form" action="/pass-data" method="post" modelAttribute="${user}">
    <p>Name : <input type="text" name="name"></p>
    <p>Surname : <input type="text" name="surname"></p>
    <p>Years : <input type="number" name="years"></p>
    <input type="submit" value="Pass data">
</form>

<br/>
<br/>
<br/>
This view has name pass-data.
</body>
</html>
