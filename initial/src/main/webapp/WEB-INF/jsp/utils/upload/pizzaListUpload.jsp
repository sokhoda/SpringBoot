<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page errorPage="../generalErrorPage.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

    <style>
        <%@include file='../../../proj.css' %>
    </style>
    <%--
      <script src="js/FillClick.js"></script>
      --%>
</head>

<body>

<form action="../pizzalist/upload" method="post" enctype="multipart/form-data" id="uploadForm">
    <div class="container-fluid bg-grey">
        <h2>
            Upload pizza price list
        </h2>
        <h3>
            <a href="/PizzaService/app/dashboard">&LongLeftArrow; back to Dashboard</a>
        </h3>
        <div class="form-group">
            <label class="lb-lg">ffile
                <input class="form-control input-lg" type="file" name="ffile">
            </label>
            <label class="lb-lg label-success">${resultMessage}</label>
        </div>

        <div class="form-group">
            <label class="lb-lg">ParamA
                <input class="form-control input-lg" value="20DS" name="nname">
            </label>
        </div>
        <div class="text-center">
            <div class="btn-group">
                <button type="button" id="upload" class="btn1  btn-default btn-lg btn-success"
                        onclick="$('#uploadForm').submit()">
                    Upload
                </button>
            </div>
        </div>
        <footer style="margin-top:10px; text-align: center">&copy;Alex, Kyiv, 2017</footer>

    </div>
</form>

<c:url var="logoutUrl" value="/logout"/>
<form action="${logoutUrl}" method="post">
    <button type="submit" class="btn btn-warning btn-xs">Log out</button>
    <sec:csrfInput/>
</form>

</body>

</html>