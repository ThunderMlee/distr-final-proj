<%-- 
    Document   : index
    Created on : Apr 11, 2018, 12:54:52 AM
    Author     : Amanda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${sessionScope.ID == null && sessionScope.ROLE == null}">
    <c:redirect url="Login.jsp"></c:redirect>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index</title>
    </head>
    <body>
        <h1>Admin Page</h1>
        <ul>
            <li> <a href="insertrecord.jsp">Create New Location</a></li>
            <li><a href="DisplayRecordServlet">Display Locations</a></li>
            
        </ul>
        
    </body>
</html>
