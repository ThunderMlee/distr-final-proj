<%-- 
    Document   : AgentAdd
    Created on : 11-Apr-2018, 3:12:02 PM
    Author     : RATHA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%--<c:if test="${sessionScope.ID == null && sessionScope.ROLE == null}">
    <c:redirect url="SiteLogin.jsp"/>
</c:if>--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Create Marketing Agent</title>
        <link href="CSS/LocalAgentAdd.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/GlobalFont.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <h1 id="title">Add Agent</h1>
        <form action="insert" method="POST" name="addAgentForm">
            <label class="text">First Name: </label><input class="textbox" type="text" name="Fname"/> <br/>
            <label class="text">Second Name: </label><input class="textbox" type="text" name="Lname"/> <br/>
            <label class="text">Phone Number: </label><input class="textbox" type="text" name="phone"/> <br/>
            <label class="text">Email: </label><input class="textbox" type="email" name="email"/> <br/>
            <label class="text">Username: </label><input class="textbox" type="text" name="Uname"/> <br/>
            <label class="text">Password: </label><input class="textbox" type="password" name="pass"/> <br/>
            <label class="text">Confirmation: </label><input class="textbox" type="password" name="conf"/> <br/>
            <input type="submit" name="submit" value="Create"/> <br/>
        </form>
    </body>
</html>
