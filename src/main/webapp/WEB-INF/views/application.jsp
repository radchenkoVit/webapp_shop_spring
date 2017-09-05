<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vradchenko
  Date: 8/22/2017
  Time: 5:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Download Application Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value='/resources/css/applicationPage.css'/>">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<c:url value='/resources/script/applicationPage.js'/>"></script>
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
            <li><span class="glyphicon glyphicon-user"></span>${pageContext.request.userPrincipal.name}</li>
            <li><a href="<c:url value="/logout" />"><span class="glyphicon glyphicon-log-in"></span>LogOut</a></li>
        </ul>
    </div>
</nav>
<%--navigation bar ends--%>

<%--applications block--%>
<div class="container-fluid text-center">
    <c:if test="${ not empty applications}">
        <div class="row">
            <c:forEach var="application" items="${applications}">
                <div class="col-sm-2">
                    <div class="panel panel-primary">
                        <div class="panel-heading"><c:out value="${application.name}"/></div>
                            <%--<div class="panel-body"><img class="img-rounded img-responsive" src="/webshop/applications/picture/<c:out value="${application.id}"/>/main" style="width:128px;height:128px;"></div>--%>
                        <div class="panel-body"><img class="img-rounded img-responsive" src="/webshop/picture/preview/<c:out value="${application.id}"/>" style="width:100%" ></div>
                        <div class="panel-footer"><c:out value="${application.getCategoriesName()}"/></div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>
</div>
<%--ends application block--%>


<%--page body--%>
<div class="container" id="application">
    <div class="row">
        <div class="col-sm-3">
            <c:if test="${not empty app}">
                <div id="title"><c:out value="${app.name}"/></div>
                <div id="image"><img class="img-rounded img-responsive" src="/webshop/picture/main/<c:out value="${app.id}"/>" style="width:100%" ></div>
                <p id="desc">Desription:<br/><c:out value="${app.description}"/></p>
                <button class="btn btn-default download" data-id="<c:out value="${app.id}"/>">Download</button>
            </c:if>
        </div>
    </div>
</div>
<%--end page body--%>

</body>
</html>
