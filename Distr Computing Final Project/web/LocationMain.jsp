<%-- 
    Document   : LocationMain
    Created on : Apr 11, 2018, 12:54:52 AM
    Author     : Amanda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${sessionScope.ID == null && sessionScope.ROLE == null}">
    <c:redirect url="SiteLogin.jsp"/>
</c:if>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Location Main</title>
    </head>
    <body>
        <h1>Location Page</h1> <br />
        <ul>
            <li> <a href="LocationAdd.jsp">Create New Location</a></li>
            <li> <a href="DisplayRecordServlet">Display Locations</a></li>
        </ul>

    </body>
</html>
