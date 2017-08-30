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
    <script type="text/javascript" src="<c:url value='/resources/script/displayApplications.js'/>"></script>
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

<%--&lt;%&ndash;container for adding application&ndash;%&gt;--%>
<%--<div class="container">--%>
    <%--<h3>List of all applications:</h3>--%>

    <%--<div class="container">--%>
        <%--<ul id="applications">--%>

        <%--</ul>--%>
    <%--</div>--%>



    <div class="container text-center" id="applications">
        <c:if test="${ not empty applications}">
            <div class="row-sm-10">
                    <c:forEach var="application" items="${applications}">
                        <div class="col-sm-2">
                            <div class="panel panel-primary">
                                <div class="panel-heading"><c:out value="${application.name}"/></div>
                                <div class="panel-body"><img src="/webshop/applications/picture/<c:out value="${application.id}"/>/main" style="width:130px;height:120px;"></div>
                                <div class="panel-footer"><c:out value="${application.getCategoriesName()}"></c:out></div>
                            </div>
                        </div>
                    </c:forEach>
            </div>
        </c:if>
    </div>

    <div class="container text-center">
        <div class="row">
            <div class="col-sm-3 well">
                <ul>
                    <c:forEach var="category" items="${categories}">
                        <li><c:out value="${category}"/></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>

<%--</div>--%>

</body>

</html>
