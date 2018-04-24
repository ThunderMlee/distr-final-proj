<%-- 
    Document   : AgentIndex
    Created on : 11-Apr-2018, 1:00:39 PM
    Author     : RATHA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Agent"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${sessionScope.ID == null && sessionScope.ROLE == null}">
    <c:redirect url="SiteLogin.jsp"/>
</c:if>

<c:if test="${agentList == null}">
    <c:redirect url="/AgentServlet">
        <c:param name="agent" value="list"/>
    </c:redirect>
</c:if> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Agent main</title>
    </head>
    <body>
    <center>
        <a href="LocationAdd.jsp">Add new location</a>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <a href="AgentAdd.jsp">Add new marketing agent</a>
    </center>
    <br/>
    <center>
        <!-- the hidden input is use to get the id that you need to do the edit and delete function-->
        <input type="hidden" name="id" id="id" value="${agent.ID}"/>
        <h1>List of marketing agents</h1>
        <table cellpadding="5" border="1">
            <thead>
                <c:if test="${sessionScope.ROLE == 'ADMIN'}">
                <th>ID</th>
                </c:if>
                <th>First Name</th>
                <th>Last Number</th>
                <th>Phone No.</th>
                <th>Email</th>
                <c:if test="${sessionScope.ROLE == 'ADMIN'}">
                <th>Action</th>
                </c:if>
            </thead>
            <tbody>
                <c:forEach var="agent" items="${agentList}">
                    <tr>
                        <c:if test="${sessionScope.ROLE == 'ADMIN'}">
                        <td><c:out value="${agent.ID}"/></td>
                        </c:if>
                        <td><c:out value="${agent.fName}"/></td>
                        <td><c:out value="${agent.lName}"/></td>
                        <td><c:out value="${agent.phoneNo}"/></td>
                        <td><c:out value="${agent.email}"/></td>
                        <c:if test="${sessionScope.ROLE == 'ADMIN'}">
                        <td><a href="edit?id=<c:out value='${agent.ID}'/>">
                                Edit
                            </a>
                            &nbsp;&nbsp;
                            <a href="delete?id=<c:out value='${agent.ID}'/>">
                                Delete
                            </a>
                        </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </center>
</body>
</html>
