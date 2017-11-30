<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %>

<%@ page errorPage="generalErrorPage.jsp" %>

<%--<c:redirect url="/app/dashboard"/>--%>
<%--<jsp:forward page="dashboard.jsp" />--%>
<html>
    <head>
        <title>Welcome to Pizza order management service</title>
        <script>
            console.log('Hello JS');
        </script>
    </head>
    <body>
    <form>
        <label for="login" >LOGIN: </label>
        <input  type="text" name="login" id="login" ng-model="login"><br>

        <label for="pass">PASSWORD: </label>
        <input  type="password" value="" name="pass" id="pass"  ng-model="pass">
        <br><br>
        <br><br>
        <p style="text-align: center;">
            <input type="submit" name="signIn" value="Sign in"
                   formaction="/checkLoginPass.html" >

            <input type="submit" name="register" value="Register"
                   formaction="/register.html">

            <input type="button" value="Clear All"
                   onclick="
               clearElemContent('login');
               clearElemContent('pass');">
        </p>
        <br>
        <br>
        <label id="message" style="width: 100%; margin-top:10%;
                color:${messageColor}; text-align: center; font-size:x-large">${messageText}
        </label>
    </form>

    </body>
</html>