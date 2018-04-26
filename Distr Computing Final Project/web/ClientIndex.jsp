<%-- 
    Document   : ClientIndex
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

<c:if test="${clientList == null}">
    <c:redirect url="/ClientServlet">
        <c:param name="client" value="list"/>
    </c:redirect>
</c:if> 


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Client main</title>
        <link href="CSS/GlobalFont.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/GlobalTables.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/GlobalNav.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/GlobalSiteBack.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="bgPage"></div>
        <div id="wrapper">
            <div id="title">
                <h1>List of Clients</h1>
                <div class="navBar">
                    <a href="SiteHome.jsp">Home</a>
                    <a href="LocationIndex.jsp">Locations</a>
                    <a href="AgentIndex.jsp">Agents</a>
                    <a href="ClientIndex.jsp" class="active">Clients</a>
                    <a href="OrderIndex.jsp">Orders</a>
                    <a href="SiteError.jsp">Error</a>
                </div>
            </div>
            <center>
                <center>
                    <!-- the hidden input is use to get the id that you need to do the edit and delete function-->
                    <input type="hidden" name="id" id="id" value="${client.id}"/>
                    <!--Table of clients-->
                    <table cellpadding="5" border="1">
                        <thead>
                            <c:if test="${sessionScope.ROLE == 'ADMIN'}">
                            <th>ID</th>
                            <th>Agent ID</th>
                            </c:if>
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
                            <c:if test="${sessionScope.ROLE == 'ADMIN'}">
                            <th>Action</th>
                            </c:if>
                        </thead>
                        <tbody>
                            <c:forEach var="client" items="${clientList}">
                                <tr>
                                    <c:if test="${sessionScope.ROLE == 'ADMIN'}">
                                        <td><c:out value="${client.id}"/></td>
                                        <td><c:out value="${client.agentId}"/></td>
                                    </c:if>
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
                                    <c:if test="${sessionScope.ROLE == 'ADMIN'}">
                                        <td><a href="edit?id=<c:out value='${client.id}'/>">
                                                Edit
                                            </a>
                                            &nbsp;&nbsp;
                                            <a href="delete?id=<c:out value='${client.id}'/>">
                                                Delete
                                            </a>
                                        </td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <input type="button" onclick="location.href='ClientAdd.jsp'" value="Add New Client" class="btnAdd"/>
                </center>
                <div id="foot"><p>Distribution Assignment</p></div>
        </div>
    </body>
</html>
