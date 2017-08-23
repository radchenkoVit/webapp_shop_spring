<%--
  Created by IntelliJ IDEA.
  User: vradchenko
  Date: 8/21/2017
  Time: 1:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add application page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="resources/script/displayApplications.js"></script>
</head>
<body>

<%--navigation bar starts--%>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Application Data Art</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="#">Home</a></li>
            <li class="active"><a href="#">New</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
            <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
        </ul>
    </div>
</nav>
<%--navigation bar ends--%>

<%--container for adding application--%>
<div class="container">
    <h3>Add your application here:</h3>

    <div class="container">
        <ul id="applications">

        </ul>
    </div>

    <div class="container">
        <table class="table table-striped">
            <c:if test="${ not empty applications}">
                <c:forEach var="application" items="${applications}">
                    <tr><td>Application Name</td><td><c:out value="${application.name}"/></td></tr>
                    <c:if test="${not empty application.description}">
                        <tr><td>Application Description</td><td><c:out value="${application.description}"/></td></tr>
                    </c:if>
                </c:forEach>
            </c:if>
        </table>
    </div>

</div>

</body>

</html>
