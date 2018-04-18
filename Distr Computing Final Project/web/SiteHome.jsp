<%-- 
    Document   : SiteHome
    Created on : Apr 17, 2018, 11:04:27 AM
    Author     : GhavinBahra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%--<c:if test="${sessionScope.ID == null && sessionScope.ROLE == null}">
    <c:redirect url="SiteLogin.jsp"/>
</c:if>--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <h1>Home page</h1> <br />
        <h2>This is the main hub page. All pages will be linked here so we can navigate much more quickly</h2> <br /><br />
        <div>
            <button name="home" value="Home" onclick="<c:redirect url='SiteHome.jsp'></c:redirect>"></button> <br/>
            <button name="location" value="Location Main" onclick="<c:redirect url='LocationMain.jsp'></c:redirect>"></button> <br />
            <button name="agentAdmin" value="Agent Index(ADMIN)" onclick="<c:redirect url='AgentAdminIndex.jsp'/>"></button> <br />
            <button name="orderAdmin" value="Order Index(ADMIN)" onclick="<c:redirect url='OrderAdminIndex.jsp'/>"></button> <br />
            <button name="error" value="Error page" onclick="<c:redirect url='Error.jsp'/>"></button>
        </div>
    </body>
</html>
