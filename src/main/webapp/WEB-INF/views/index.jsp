
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
    <link rel="stylesheet" href="<c:url value='/resources/css/index.css'/>">
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
            <li class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>
            <li ><a href="${pageContext.request.contextPath}/addApplication.html">Add Apllication</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <ul class="nav navbar-nav navbar-right">
                <li><span class="glyphicon glyphicon-user"></span>${pageContext.request.userPrincipal.name}</li>
                <li><a href="<c:url value="/logout" />"><span class="glyphicon glyphicon-log-in"></span>LogOut</a></li>
            </ul>
            <%--<li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>--%>
            <%--<li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>--%>
        </ul>
    </div>
</nav>
<%--navigation bar ends--%>

<div class="container-fluid text-center">
    <c:if test="${ not empty applications}">
        <div class="row">
            <c:forEach var="application" items="${applications}">
                <div class="col-sm-2">
                    <div class="panel panel-primary">
                        <div class="panel-heading"><c:out value="${application.name}"/></div>
                            <%--<div class="panel-body"><img class="img-rounded img-responsive" src="/webshop/applications/picture/<c:out value="${application.id}"/>/main" style="width:128px;height:128px;"></div>--%>
                        <div class="panel-body"><img class="img-rounded img-responsive" src="/webshop/picture/preview/<c:out value="${application.id}"/>" style="width:100%" ></div>
                        <div class="panel-footer"><c:out value="${application.getCategoriesName()}"></c:out></div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>
</div>

<br/>
<br/>
<br/>

<div class="row" id="categories">
    <div class="col-sm-2">
        <ul>
            <c:forEach var="category" items="${categories}">
                <li class="pointer" data-id="<c:out value="${category.id}"/>"><c:out value="${category.name}"/></li><br/>
            </c:forEach>
        </ul>
    </div>

    <div class="col-sm-9">
        <div class="mycontent-right" id="applications">
            <c:forEach var="app" items="${applications_by_category}">
                <div class="app preview block">
                    <div class="app body"><img data-id="<c:out value="${app.id}"/>" class="img-rounded img-responsive pointer" src="/webshop/picture/preview/<c:out value="${app.id}"/>" style="width:100%"></div>
                    <div class="app title">App name: <c:out value="${app.name}"/>
                        <br/>Downloaded time: <c:out value="${app.downloadedTimes}"/><br/>
                        <button class="btn btn-default download" data-id="<c:out value="${app.id}"/>">Download</button>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

</body>

</html>