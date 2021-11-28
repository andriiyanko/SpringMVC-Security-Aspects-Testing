<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: itvdn
  Date: 11.11.18
  Time: 00:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employees</title>
</head>
<body>

<style>
    table, th, td {
        border: 1px solid black;
        padding: 10px;
        margin: 5px;
    }
    input[type=text] {
        width: 20%;
        padding: 12px 20px;
        margin: 8px 0;
        box-sizing: border-box;
    }
</style>
<h1>Employees table : </h1>
<table>
    <thead>
        <tr>
            <th>#</th>
            <th>ID</th>
            <th>Name</th>
            <th>Position</th>
            <th>Phone</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
    <%--@elvariable id="employees" type="java.util.List<com.itvdn.persistence.model.Employee>"--%>
    <c:forEach items="${employees}" var="employee" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <td>${employee.id}</td>
            <td>${employee.name}</td>
            <td>${employee.position}</td>
            <td>${employee.phone}</td>
            <td><a href="/employee/remove/${employee.id}">Remove</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<br/>
<h2>To add new employee press "Add employee" button</h2>
<form method="post" action="/employee/add">
    <p><label for="name">Employee name </label></p>
    <p><input type="text" name="name" id="name"></p>
    <br/>

    <p><label for="position">Employee position </label></p>
    <p><input type="text" name="position" id="position"></p>
    <br/>

    <p><label for="phone">Employee phone </label></p>
    <p><input type="text" name="phone" id="phone"></p>
    <p><input type="submit" value="Add employee"></p>
</form>
<br/>
<br/>
<form method="post" action="/employee/findByName">
    <h2>Search employee by name</h2>
    <p>Name : </p>
    <p><input name="name" type="text"></p>
    <p><input type="submit" value="Search by name"></p>
</form>
<br/>
<br/>
<form method="post" action="/employee/findByNameAndPosition">
    <h2>Search employee by name and position</h2>
    <p>Name : </p>
    <p><input name="name" type="text"></p>
    <p>Position : </p>
    <p><input name="position" type="text"></p>
    <p><input type="submit" value="Search by name and position"></p>
</form>
<br/>
<br/>
<form method="post" action="/employee/findByNameAndPhone">
    <h2>Search employee by name and phone</h2>
    <p>Name : </p>
    <p><input name="name" type="text"></p>
    <p>Phone : </p>
    <p><input name="phone" type="text"></p>
    <p><input type="submit" value="Search by name and phone"></p>
</form>
</body>
</html>
