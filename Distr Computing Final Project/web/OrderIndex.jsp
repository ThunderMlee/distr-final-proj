<%-- 
    Document   : OrderIndex
    Created on : Apr 14, 2018, 2:13:56 PM
    Author     : GhavinBahra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Order"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${sessionScope.ID == null && sessionScope.ROLE == null}">
    <c:redirect url="SiteLogin.jsp"/>
</c:if>

<c:if test="${orderList == null}">
    <c:redirect url="/OrderServlet">
        <c:param name="order" value="list"/>
    </c:redirect>
</c:if> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Orders Index</title>
    </head>
    <body>
        <h1>List of Orders</h1>
        <center>
            <a href="OrderAdd.jsp">Add new order</a>
        </center>
        <table cellpadding="5" border="1">
            <thead>
            <c:if test="${sessionScope.ROLE == 'ADMIN'}">
            <th>ID</th>
            <th>Agent ID</th>
            <th>Client ID</th>
            </c:if>
            <th>Flyer Quantity</th>
            <th>Flyer Layout</th>
            <th>Flyer Image</th>
            <th>Personal Copy</th>
            <th>Payment Information</th>
            <th>Invoice Number</th>
            <th>Comments</th>
            <th>Is flyer art approved</th>
            <th>Is payment received</th>
            <c:if test="${sessionScope.ROLE == 'ADMIN'}">
            <th>Action</th>
            </c:if>
            </thead>
            <tbody>
                <c:forEach var="order" items="${orderList}">
                    <tr>
                        <c:if test="${sessionScope.ROLE == 'ADMIN'}">
                        <td><c:out value="${order.ID}"/></td>
                        <td><c:out value="${order.agentID}"/></td>
                        <td><c:out value="${order.clientID}"/></td>
                        </c:if>
                        <td><c:out value="${order.flyerQty}"/></td>
                        <td><c:out value="${order.flyerLayout}"/></td>
                        <td><c:out value="${order.flyerImg}"/></td>
                        <td><c:out value="${order.personalCopy}"/></td>
                        <td><c:out value="${order.paymentInfo}"/></td>
                        <td><c:out value="${order.invoiceNum}"/></td>
                        <td><c:out value="${order.comments}"/></td>
                        <td><c:out value="${order.isFlyerArtApproved}"/></td>
                        <td><c:out value="${order.isPaymentReceived}"/></td>
                        <c:if test="${sessionScope.ROLE == 'ADMIN'}">
                        <td><a href="edit?id=<c:out value='${order.ID}'/>">
                                Edit
                            </a>
                            &nbsp;&nbsp;
                            <a href="delete?id=<c:out value='${order.ID}'/>">
                                Delete
                            </a>
                        </td>
                        </c:if>
                    </tr>
                </c:forEach>


            </tbody>
        </table>
    </body>
</html>
