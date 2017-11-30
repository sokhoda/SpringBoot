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
        <%@include file='../../proj.css' %>
    </style>
</head>

<body>


<input class="hidden" id="command" name="command" value="">


<div class="container-fluid bg-grey">
    <h2>
        List of Customers
    </h2>
    <h3>
        <a href="/PizzaService/app/dashboard">&LongLeftArrow; back to Dashboard</a>
    </h3>
    <table class="table table-striped table-hover table-condensed " id="documenttab" style="width:95%;">
        <thead>
        <tr>
            <th><h3>ID</h3></th>
            <th><h3>Name</h3></th>
            <th><h3>Email</h3></th>
            <th><h3>LoyaltyCardId</h3></th>
            <th><h3>Sum</h3></th>
            <th><h3></h3></th>
            <th><h3></h3></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="customer" items="${customerlist}" varStatus="count">
            <tr>
                <td>${customer.customerId}</td>
                <td align="left" id="row${customer.customerId}">${customer.name}</td>
                <td align="left">${customer.email}</td>
                <td align="left">${customer.loyaltyCard.id}</td>
                <td align="left">${customer.loyaltyCard.sum}</td>
                <td>
                    <form action="../customer/edit" method="get">
                        <input class="hidden" name="customerId" value="${customer.customerId}">
                        <button type="submit" class="btn btn-primary btn-xs">Edit</button>
                    </form>
                </td>
                <td>
                    <form action="../customer/remove" method="post">
                        <input class="hidden" name="customerId" value="${customer.customerId}">
                        <button type="submit" class="btn btn-warning btn-xs">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <%--<sec:authorize access="hasRole('ADMIN')">--%>
        <div class="text-center">
            <a href="../customer/create">
                <div class="btn-group">
                    <button type="button" id="crtupd" class="btn1  btn-default btn-lg btn-success">
                        Create
                    </button>
                </div>
            </a>
        </div>
    <%--</sec:authorize>--%>

    <%--<c:url var="logoutUrl" value="/app/logout"/>--%>
    <%--<form action="${logoutUrl}" method="post">--%>
    <%--<button type="submit" class="btn btn-warning btn-xs">Log out</button>--%>
    <%--<sec:csrfInput/>--%>
    <%--</form>--%>

    <footer style="margin-top:10px; text-align: center">&copy;Alex, Kyiv, 2017</footer>

</div>
</body>

<script type="text/javascript">
    var name, latch = false;

    function doEdit(item) {
        var customerId = item.currentTarget.getAttribute("id");
        console.log('customerID=' + customerId);
        $('#customerId').val(customerId);
        <%--
       $('#Id').val($('.modalDeleteBtn').attr('id'));
        --%>
        $('#editForm').submit();
    }
    ;

    function showDelete(event) {
        $('.modalDeleteBtn').attr('id', event.target.id);
        name = $('#documenttab #row' + event.target.id).text();
        if (!latch) {
            $('#myModalLabel').text($('#myModalLabel').text() + ' \'' + name + '\'');
            latch = true;
        }
        $('#myModal').modal('show');
    }
    ;
</script>
</html>