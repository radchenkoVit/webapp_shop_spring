<%--<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>--%>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vradchenko
  Date: 8/21/2017
  Time: 1:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE>
<html>
<head>
    <title>Add application page</title>
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
            <c:if test="${ not empty message}">
                <p>${message}</p>
            </c:if>
        </div>

        <form action="/webshop/applications/add" method="post" class="col-sm-4" enctype="multipart/form-data">
            <div class="form-group">
                <label for="appName">Name:</label>
                <input class="form-control" id="appName" placeholder="Enter application name" name="appName" required autofocus/>
            </div>

            <div class="form-group">
                <label for="appDesc">Application Description:</label>
                <textarea id="appDesc" class="form-control" rows="5" maxlength="2500" placeholder="Enter description " name="appDesc"></textarea>
            </div>

            <div class="form-group">
                <label for="appDesc">Application category:</label>
                <select class="selectpicker form-control" name="categories">
                    <option>Games</option>
                    <option>Education</option>
                </select>
            </div>

            <div class="form-group">
                <label for="appUpload">Select a file to upload</label>
                <input type="file" class="form-control" id="appUpload" name="appUpload"/>
            </div>
            <button type="submit" class="btn btn-default right">Submit</button>
        </form>
    </div>

</body>

</html>
