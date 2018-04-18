<%-- 
    Document   : OrderAdminIndex
    Created on : Apr 14, 2018, 2:13:56 PM
    Author     : Ghavin Bahra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${sessionScope.ID == null && sessionScope.ROLE == null}">
    <c:redirect url="SiteLogin.jsp"/>
</c:if>

<c:if test="${sessionScope.ID == null && sessionScope.ROLE =! 'ADMIN'}">
    <c:redirect url="SiteHome.jsp"/>
</c:if>

<c:if test="${orderList == null}">
    <c:redirect url="${pageContext.request.contextPath}/OrderServlet" context="orderList"/>
</c:if> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Orders Index</title>
    </head>
    <body>
        <h1>List of Orders</h1>
        <table cellpadding="5" border="1">
            <thead>
            <th>ID</th>
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
            <th>Is payment received</th>
            </thead>
            <tbody>
                <c:forEach var="order" items="${orderList}">
                    <tr>
                        <td><c:out value="${order.id}"/></td>
                        <td><c:out value="${order.firstName}"/></td>
                        <td><c:out value="${order.lastName}"/></td>
                        <td><c:out value="${order.phoneNo}"/></td>
                        <td><c:out value="${order.email}"/></td>

                        <td><a href="edit?id=<c:out value='${order.id}'/>">
                                Edit
                            </a>
                            &nbsp;&nbsp;
                            <a href="delete?id=<c:out value='${order.id}'/>">
                                Delete
                            </a>
                        </td>
                    </tr>
                </c:forEach>


            </tbody>
        </table>
    </body>
</html>
