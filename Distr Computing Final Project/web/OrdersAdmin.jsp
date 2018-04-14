<%-- 
    Document   : OrdersAdmin
    Created on : Apr 14, 2018, 2:13:56 PM
    Author     : Ghavin Bahra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${sessionScope.ID == null && sessionScope.ROLE =! 'ADMIN'}">
    <c:redirect url="Login.jsp"></c:redirect>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Orders</title>
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
                <c:forEach var="order" items="${agentlist}">
                    <tr>
                        <td><c:out value="${agent.id}"/></td>
                        <td><c:out value="${agent.firstName}"/></td>
                        <td><c:out value="${agent.lastName}"/></td>
                        <td><c:out value="${agent.phoneNo}"/></td>
                        <td><c:out value="${agent.email}"/></td>

                        <td><a href="edit?id=<c:out value='${agent.id}'/>">
                                Edit
                            </a>
                            &nbsp;&nbsp;
                            <a href="delete?id=<c:out value='${agent.id}'/>">
                                Delete
                            </a>
                        </td>
                    </tr>
                </c:forEach>


            </tbody>
        </table>
    </body>
</html>
