<%--
  Created by IntelliJ IDEA.
  User: vradchenko
  Date: 8/21/2017
  Time: 8:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Error Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<%--navigation bar starts--%>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Application Data Art</a>
        </div>
        <ul class="nav navbar-nav">
            <ul class="nav navbar-nav">
                <li class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>
                <li ><a href="${pageContext.request.contextPath}/addApplication.html">Add Apllication</a></li>
            </ul>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href=<span class="glyphicon glyphicon-user"></span>${pageContext.request.userPrincipal.name}</a></li>
            <li><a href="<c:url value="/logout" />"><span class="glyphicon glyphicon-log-in"></span>LogOut</a></li>
        </ul>
    </div>
</nav>
<%--navigation bar ends--%>

    <div class="container">
        <c:if test="${ not empty message}">
            <p>${message}</p>
        </c:if>
    </div>

</body>
</html>
