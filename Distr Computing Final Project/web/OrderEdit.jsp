<%-- 
    Document   : OrderEdit
    Created on : Apr 17, 2018, 11:49:22 AM
    Author     : GhavinBahra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Order"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${sessionScope.ID == null || sessionScope.ROLE == null}">
    <c:redirect url="SiteLogin.jsp"/>
</c:if>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Edit order</title>
        <link href="CSS/GlobalFont.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <form action="OrderServlet" enctype="multipart/form-data" method="POST" name="orderEditForm">
            
            <input type="hidden" name="id" value="${order.ID}"/>
            <input type="hidden" name="order" value="edit"/>
            
            <table>
                <tr>
                    <td>Agent ID</td>
                    <td><input type="text" name="locationName" value="${order.agentID}"/></td>
                </tr>
                <tr>
                    <td>Client ID</td>
                    <td><input type="text" name="distributionCapacity" value="${order.clientID}" /></td>
                </tr>
                <tr>
                    <td>Flyer Quantity</td>
                    <td><input type="text" name="flyerQty" value="${order.flyerQty}" /></td>
                </tr>
                <tr>
                    <td>Flyer Layout</td>
                    <td><input type="text" name="flyerLayout" value="${order.flyerLayout}" /></td>
                </tr>
                <tr>
                    <td>Flyer Image</td>
                    <td><input type="file" name="flyerImg"/></td>
                </tr>
                <tr>
                    <td>Personal Copy</td>
                    <td><input type="text" name="personalCopy" value="${order.personalCopy}"/></td>
                </tr>
                <tr>
                    <td>Invoice Number</td>
                    <td><input type="text" name="invoiceNum" value="${order.invoiceNum}"/></td>
                </tr>
                <tr>
                    <td>Comments</td>
                    <td><input type="text" name="comments" value="${order.comments}"/></td>
                </tr>
                <tr>
                    <td>Is flyer art approved?</td>
                    <td><input type="radio" name="artApprove" <c:if test="${order.isFlyerArtApproved == true}">checked</c:if>/><input type="radio" name="artApprove" <c:if test="${order.IsArtApproved == false}">checked</c:if>/></td>
                </tr>
                <tr>
                    <td>Is payment received?</td>
                    <td><input type="radio" name="payReceive" <c:if test="${order.isPaymentReceived == true}">checked</c:if>/><input type="radio" name="payReceive" <c:if test="${order.IsArtApproved == false}">checked</c:if>/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Save"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>
