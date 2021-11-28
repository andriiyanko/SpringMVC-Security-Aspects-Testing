<%--
  Created by IntelliJ IDEA.
  User: itvdn
  Date: 2019-07-02
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>

    <h1>Login Page</h1>
    <h2>Enter your credentials</h2>
    <form action="/login" method="post">
        <p>Login: </p>
        <input type="text" name="username"/><br/>
        <br/>
        <p>Password: </p>
        <input type="password" name="password">
        <br/>
        <input type="submit" value="Login">
    </form>

</body>
</html>
