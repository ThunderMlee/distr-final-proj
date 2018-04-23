<%-- 
    Document   : OrderAdd
    Created on : Apr 17, 2018, 12:20:29 PM
    Author     : GhavinBahra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${sessionScope.ID == null && sessionScope.ROLE == null}">
    <c:redirect url="SiteLogin.jsp"/>
</c:if>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add an order</title>
    </head>
    <body>
        <form action="OrderServlet" enctype="multipart/form-data" method="POST" name="orderAddForm">
            <table>
                <tr>
                    <td>Agent</td>
                    <td><input type="text" name="locationName"></td>
                </tr>
                <tr>
                    <td>Client</td>
                    <td><input type="text" name="distributionCapacity"></td>
                </tr>
                <tr>
                    <td>Flyer quantity</td>
                    <td><input type="text" name="flyerQty"></td>
                </tr>
                <tr>
                    <td>Flyer Layout</td>
                    <td><input type="text" name="flyerLayout"></td>
                </tr>
                <tr>
                    <td>Flyer Image</td>
                    <td><input type="file" name="flyerImg"/></td>
                </tr>
                <tr>
                    <td>Personal Copy</td>
                    <td><input type="text" name="personalCopy"></td>
                </tr>
                <tr>
                    <td>Invoice Number</td>
                    <td><input type="text" name="invoiceNum"></td>
                </tr>
                <tr>
                    <td>Comments</td>
                    <td><input type="text" name="comments"></td>
                </tr>
                <tr>
                    <td>Is flyer art approved?</td>
                    <td><input type="radio" name="artApprove"/><input type="radio" name="artApprove"/></td>
                </tr>
                <tr>
                    <td>Is payment received?</td>
                    <td><input type="radio" name="payReceive"/><input type="radio" name="payReceive"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Submit"></td>
                </tr>
            </table>
        </form>
        <!--<th>ID</th>
            <th>Agent ID</th>
            <th>Client ID</th>
            <th>Flyer Quantity</th>
            <th>Flyer Layout</th>
            <th>Flyer Image</th>
            <th>Personal Copy</th>
            <th>Payment Information</th>
            <th>Invoice Number</th>
            <th>Comments</th>
            <th>Is flyer art approved</th>
            <th>Is payment received</th>-->
    </body>
</html>
