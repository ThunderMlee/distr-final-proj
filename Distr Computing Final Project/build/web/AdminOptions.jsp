<%-- 
    Document   : AgentOptions
    Created on : 11-Apr-2018, 1:00:39 PM
    Author     : RATHA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Agent"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${sessionScope.ID == null && sessionScope.ROLE != 'ADMIN'}">
    <c:redirect url="Login.jsp"></c:redirect>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin page</title>
    </head>
    <body>
    <center>
        <a href="new location">Add new location</a>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <a href="add">Add new marketing agent</a>
    </center>
    <br/>
    <center>
        <!-- the hidden input is use to get the id that you need to do the edit and delete function-->
        <input type="hidden" name="id" id="id" value="${agent.id}"/>
        <h1>List of marketing agents</h1>
        <table cellpadding="5" border="1">
            <thead>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Number</th>
            <th>Phone No.</th>
            <th>Email</th>
            <th>Action</th>
            </thead>
            <tbody>
                <c:forEach var="agent" items="${agentlist}">
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

    </center>
</body>
</html>
