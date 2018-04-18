<%-- 
    Document   : ClientAdminIndex
    Created on : 11-Apr-2018, 1:00:39 PM
    Author     : Shanshan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Client"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:if test="${sessionScope.ID == null && sessionScope.ROLE == null}">
    <c:redirect url="SiteLogin.jsp"/>
</c:if>

<c:if test="${sessionScope.ID == null && sessionScope.ROLE != 'USER'}">
    <c:redirect url="SiteHome.jsp"/>
</c:if>

<c:if test="${clientList == null}">
    <c:redirect url="${pageContext.request.contextPath}/PrinterServlet" context="clientList"/>
</c:if> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Client main</title>
    </head>
    <body>
    <center>
        <a href="ClientAdd.jsp">Add new client</a>
    </center>
    <br/>
    <center>
        <!-- the hidden input is use to get the id that you need to do the edit and delete function-->
        <input type="hidden" name="id" id="id" value="${client.id}"/>
        <h1>List of marketing clients</h1>
        <table cellpadding="5" border="1">
            <thead>
            <th>Client ID</th>
            <th>Agent Id</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Street Number</th>
            <th>Street Name</th>
            <th>City</th>
            <th>Province</th>
            <th>Postal Code</th>
            <th>Office Phone</th>
            <th>Cell Phone</th>
            <th>Email</th>
            <th>Company</th>
            <th>Company Type</th>
            </thead>
            <tbody>
                <c:forEach var="client" items="${clientList}">
                    <tr>
                        <td><c:out value="${client.agentId}"/></td>
                        <td><c:out value="${client.firstName}"/></td>
                        <td><c:out value="${client.lastName}"/></td>
                        <td><c:out value="${client.streetNumber}"/></td>
                        <td><c:out value="${client.streetName}"/></td>
                        <td><c:out value="${client.city}"/></td>
                        <td><c:out value="${client.province}"/></td>
                        <td><c:out value="${client.postalCode}"/></td>
                        <td><c:out value="${client.telOffice}"/></td>
                        <td><c:out value="${client.telCell}"/></td>
                        <td><c:out value="${client.email}"/></td>
                        <td><c:out value="${client.company}"/></td>
                        <td><c:out value="${client.companyType}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </center>
</body>
</html>
