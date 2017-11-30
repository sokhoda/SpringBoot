<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%-- Created by IntelliJ IDEA.
 User: s_okhoda
 Date: 20.01.2016
 Time: 17:52
 To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<script src="../../../js/taxi.js" type="text/javascript">    </script>--%>
<%--<script src="../../../js/angular.js"></script>--%>
<%--<script src="../../../js/registerAjax.js"></script>--%>

<html>
<head>
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

  <style>
     <%@include file='../proj.css' %>
  </style>
  <%--
    <script src="js/FillClick.js"></script>
    --%>
</head>

    <body>
    <form:form  action="${pageContext.request.contextPath}/app/login" method="POST">
        <div class="container-fluid bg-grey">
            <label for="username" class="lb-lg">LOGIN: </label>
            <input class="form-control input-lg"  id="username" name="username"><br>

            <label for="password" class="lb-lg">PASSWORD: </label>
            <input  class="form-control input-lg" type="password" value="" name="password"><br>

            <br>
            <br>
            <%--
            <p>
                Hello <b>${pageContext.request.remoteUser}</b>
            </p>
            --%>
            <div class="text-left">
                <div class="btn-group">
                    <button type="submit" class="btn btn-primary btn-lg">
                    Log in</button>
                </div>
            </div>
            <footer style="text-align: center">&copy;Alex, Kyiv, 2017</footer>
        </div>
    </form:form>
    </body>
</html>
