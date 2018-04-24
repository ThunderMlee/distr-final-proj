<%-- 
    Document   : SiteError
    Created on : Apr 11, 2018, 2:18:53 AM
    Author     : Amanda
--%>

<%@page import="com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException"%>
<%@page import="java.sql.SQLException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${sessionScope.ID == null || sessionScope.ROLE == null}">
    <c:redirect url="SiteLogin.jsp"/>
</c:if>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error...</title>
    </head>
    <body>
        <h3>Error: <%=(MySQLSyntaxErrorException) request.getAttribute("Error")%></h3> <br />
        <input type="button" value="Home" onclick="location.href='SiteHome.jsp';"/>
    </body>
</html>
