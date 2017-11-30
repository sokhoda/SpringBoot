<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>
<%@ page errorPage="../generalErrorPage.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

    <style>
        <%@include file='../../proj.css' %>
    </style>
    <%--
      <script src="js/FillClick.js"></script>
      --%>
</head>

<body>

<springForm:form action="../pizza/addnew" method="post" modelAttribute="pizza" id="myForm">
    <input class="hidden" id="command" name="command" value="">
    <input class="hidden" id="Id" name="docId" value="">

    <div class="container-fluid bg-grey">
        <h2>
            Pizza
        </h2>

        <div class="form-group">
            <label class="lb-lg">ID
                <input class="form-control input-lg" value="${pizza.pizzaId}" name="pizzaId">
            </label>
        </div>

        <div class="form-group">
            <label class="lb-lg">Name
                <springForm:input class="form-control input-lg" path="name" name="name"/>
            </label>
            <springForm:errors path="name" cssClass="error"/>
        </div>
        <div class="form-group">
            <label class="lb-lg">Price
                <springForm:input class="form-control input-lg" path="price" name="price"/>
            </label>
            <springForm:errors path="price" cssClass="error" />
        </div>
        <div class="form-group">
            <label class="lb-lg">Type
                <input class="form-control input-lg" value="${pizza.type}" name="type">
            </label>
        </div>

        <div class="text-center">
            <div class="btn-group">
                <button type="button" id="crtupd" class="btn1 btn-default btn-lg btn-success"
                        onclick="$('#myForm').submit()">
                    Save
                </button>
            </div>
        </div>
        <footer style="margin-top:10px; text-align: center">&copy;Alex, Kyiv, 2017</footer>

    </div>
    <sec:csrfInput/>
</springForm:form>

<c:url var="logoutUrl" value="/logout"/>
<form action="${logoutUrl}" method="post">
    <button type="submit" class="btn btn-warning btn-xs">Log out</button>
    <sec:csrfInput/>
</form>

</body>

<script type="text/javascript">
    var name, latch = false;

    function doDelete(comm) {
        $('#command').val(comm);
        $('#Id').val($('.modalDeleteBtn').attr('id'));
        $('#myForm').submit();
    };

    function showDelete(event) {
        $('.modalDeleteBtn').attr('id', event.target.id);
        name = $('#documenttab #row' + event.target.id).text();
        if (!latch) {
            $('#myModalLabel').text($('#myModalLabel').text() + ' \'' + name + '\'');
            latch = true;
        }
        $('#myModal').modal('show');
    };
</script>
</html>