<%-- 
    Document   : SiteHome
    Created on : Apr 17, 2018, 11:04:27 AM
    Author     : GhavinBahra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${sessionScope.ID == null || sessionScope.ROLE == null}">
    <c:redirect url="SiteLogin.jsp"/>
</c:if>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Home</title>
    </head>
    <body>
        <h1>Home page</h1> <br />
        <h2>Welcome user! Use this site for any order related service</h2> <br /><br />
        <div id="sideBar">
            <input type="button" value="Home" onclick="location.href='SiteHome.jsp';"/> <br/>
            <input type="button" value="Location Main" onclick="location.href='LocationMain.jsp';"/> <br/>
            <input type="button" value="Agent Index" onclick="location.href='AgentIndex.jsp';"/> <br/>
            <input type="button" value="Client Index" onclick="location.href='ClientIndex.jsp';"/> <br/>
            <input type="button" value="Order Index" onclick="location.href='OrderIndex.jsp';"/> <br/>
            <input type="button" value="Error Page" onclick="location.href='SiteError.jsp';"/> <br/>
        </div>
    </body>
</html>
