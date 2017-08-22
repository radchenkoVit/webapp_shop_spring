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
    <title>Download page</title>
    <%--<script>--%>
        <%--$(document).ready(function() {--%>
            <%--$('#download').click(function(){--%>
                <%--$.ajax({--%>
                    <%--type: "POST",--%>
                    <%--url: "/webshop/applications/download/",--%>
                    <%--data: "appId=2",--%>
                    <%--success : function () {--%>
                        <%--console.log("passed");--%>
                    <%--},--%>
                    <%--error: function (error) {--%>
                        <%--console.log("failed");--%>
                    <%--}--%>
                <%--});--%>
            <%--});--%>
        <%--});--%>
    <%--</script>--%>
</head>
<body>
    <div class="form-container">
        <h1>Welcome to FileDownloader Example</h1>
        <br/><br/>


        <form action="/webshop/applications/download" method="post" class="col-sm-4">
            <div class="form-group">
                <label for="appId">Application id:</label>
                <input id="appId" class="form-control" name="appId">
            </div>

            <button type="submit" class="btn btn-default right">Download</button>
        </form>

        <%--<br/><br/>--%>
        <%--<button type="submit" id="download" class="download">Download variant two</button>--%>
    </div>

</body>
</html>
