<%-- 
    Document   : LocationEdit
    Created on : Apr 11, 2018, 5:08:04 AM
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
        <title>Edit location</title>
    </head>
    <body>
        <h1>Not implemented yet...</h1>
    </body>
</html>
