<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: itvdn
  Date: 11.11.18
  Time: 02:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee search result</title>
</head>
<body>
<h1>List of employee : </h1>

<c:forEach items="${employees}" var="employee" varStatus="status">
    <p><h2>Result #${status.index + 1}</h2> ${employee}</p>
</c:forEach>
</body>
</html>
