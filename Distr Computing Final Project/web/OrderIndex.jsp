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
    <jsp:forward page="${pageContext.request.contextPath}/OrderServlet/orderList"/>
    <%--<c:redirect url="/OrderServlet/orderList">
        <c:param name="orderList" value="orderList"/>
    </c:redirect>--%>
</c:if> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
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
            <c:if test="${sessionScope.ROLE == 'ADMIN'}">
            <th>Action</th>
            </c:if>
            </thead>
            <tbody>
                <c:forEach var="order" items="${orderList}">
                    <tr>
                        <td><c:out value="${order.id}"/></td>
                        <td><c:out value="${order.firstName}"/></td>
                        <td><c:out value="${order.lastName}"/></td>
                        <td><c:out value="${order.phoneNo}"/></td>
                        <td><c:out value="${order.email}"/></td>
                        <c:if test="${sessionScope.ROLE == 'ADMIN'}">
                        <td><a href="edit?id=<c:out value='${order.id}'/>">
                                Edit
                            </a>
                            &nbsp;&nbsp;
                            <a href="delete?id=<c:out value='${order.id}'/>">
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
