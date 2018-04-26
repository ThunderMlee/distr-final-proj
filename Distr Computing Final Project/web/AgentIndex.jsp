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
        <link href="CSS/GlobalFont.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/GlobalTables.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/GlobalNav.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="wrapper">
            <div id="title">
                <h1>List of Agents</h1>
                <div class="navBar">
                    <a href="SiteHome.jsp">Home</a>
                    <a href="LocationIndex.jsp">Locations</a>
                    <a href="AgentIndex.jsp" class="active">Agents</a>
                    <a href="ClientIndex.jsp">Clients</a>
                    <a href="OrderIndex.jsp">Orders</a>
                    <a href="SiteError.jsp">Error</a>
                </div>
            </div>
            <center>
                <!-- the hidden input is use to get the id that you need to do the edit and delete function-->
                <input type="hidden" name="id" id="id" value="${agent.ID}"/>
                <table cellpadding="5" border="1">
                    <thead>
                        <c:if test="${sessionScope.ROLE == 'ADMIN'}">
                        <th>ID</th>
                        </c:if>
                        <th>First Name</th>
                        <th>Last Name</th>
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
                                    <td><a href="${pageContext.request.contextPath}/AgentEdit.jsp?id=${agent.ID}">
                                            Edit
                                        </a>
                                        &nbsp;&nbsp;
                                        <a href="${pageContext.request.contextPath}/AgentServlet?id=${agent.ID}&amp;agent=delete">
                                            Delete
                                        </a>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <input type="button" onclick="location.href='LocationAdd.jsp'" value="Add New Location" class="btnAdd"/>
                <input type="button" onclick="location.href='AgentAdd.jsp'" value="Add New Marketing Agent" class="btnAdd"/>
            </center>
            <div id="foot"><p>Distribution Assignment</p></div>
        </div>
    </body>
</html>
