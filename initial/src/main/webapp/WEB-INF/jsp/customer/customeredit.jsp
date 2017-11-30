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
</head>

<body>

<springForm:form action="../customer/addnew" method="post" commandName="customer" id="myForm">
    <input class="hidden" id="command" name="command" value="">
    <input class="hidden" id="Id" name="docId" value="">

    <div class="container-fluid bg-grey">
        <div style="width: 60%; float: left">
            <h3>
                Customer
            </h3>

            <div class="form-group">
                <label class="lb-sm">ID
                    <input class="form-control input-sm" value="${customer.customerId}" name="customerId">
                </label>
            </div>

            <div class="form-group">
                <label class="lb-sm">Name
                    <springForm:input class="form-control input-sm" path="name" name="name"/>
                </label>
                <springForm:errors path="name" cssClass="error" />
            </div>
            <div class="form-group">
                <label class="lb-sm">Email
                    <springForm:input class="form-control input-sm" path="email" name="email"/>
                </label>
                <springForm:errors path="email" cssClass="error" />
            </div>
            <div class="form-group">
                <label class="lb-sm">LoyaltyCardId
                    <input class="form-control input-sm" readonly value="${loyaltycard.id}" name="lcardId">
                </label>
            </div>
            <div class="form-group">
                <label class="lb-sm">Sum
                    <input class="form-control input-sm" readonly value="${loyaltycard.sum}" name="lcardSum">
                </label>
            </div>
        </div>
        <div style="width: 40%; float: right">
            <div class="form-group">
                <label class="lb-sm">zipCode
                    <springForm:input class="form-control input-sm" path="address.zipCode" name="address.zipCode"/>
                </label>
                <springForm:errors path="address.zipCode" cssClass="error" />
            </div>
            <div class="form-group">
                <label class="lb-sm">city
                    <springForm:input class="form-control input-sm" path="address.city" name="address.city"/>
                </label>
                <springForm:errors path="address.city" cssClass="error" />
            </div>
            <div class="form-group">
                <label class="lb-sm">streetName
                    <input class="form-control input-sm" value="${customer.address.streetName}" name="address.streetName">
                </label>
            </div>
            <div class="form-group">
                <label class="lb-sm">buildingNo
                    <input class="form-control input-sm" value="${customer.address.buildingNo}" name="address.buildingNo">
                </label>
            </div>
            <div class="form-group">
                <label class="lb-sm">appNo
                    <input class="form-control input-sm" value="${customer.address.appNo}" name="address.appNo">
                </label>
            </div>
            <div class="form-group">
                <label class="lb-sm">type
                    <input class="form-control input-sm" value="${customer.address.type}" name="address.type">
                </label>
            </div>
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