<%-- 
    Document   : AgentAdd
    Created on : 11-Apr-2018, 3:12:02 PM
    Author     : RATHA
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
        <title>Create Marketing Agent</title>
        <link href="CSS/LocalAgentAdd.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/GlobalFont.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/GlobalTables.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <!--<form action="insert" method="POST" name="addAgentForm">
            <input type="hidden" name="agent" value="add"/>
            <label>First Name: </label><input type="text" name="Fname"/> <br/>
            <label>Last Name: </label><input type="text" name="Lname"/> <br/>
            <label>Phone Number: </label><input type="text" name="phone"/> <br/>
            <label>Email: </label><input type="email" name="email"/> <br/>
            <label>Username: </label><input type="text" name="Uname"/> <br/>
            <label>Password: </label><input type="password" name="pass"/> <br/>
            <label>Re-enter password: </label><input type="password" name="conf"/> <br/>
            <input type="submit" name="submit" value="Create"/> <br/>
        </form>-->
        <form action="${pageContext.request.contextPath}/AgentServlet" method="POST" name="addAgentForm">
            <input type="hidden" name="agent" value="add"/>
            <table>
                <tr>
                    <td>First Name:</td>
                    <td><input type="text" name="id" /></td>
                </tr>
                <tr>
                    <td>Last Name:</td>
                    <td><input type="text" name="agentId" /></td>
                </tr>
                <tr>
                    <td>First Name:</td>
                    <td><input type="text" name="firstName" /></td>
                </tr>
                <tr>
                    <td>Phone Number:</td>
                    <td><input type="text" name="lastName" /></td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td><input type="text" name="streetNumber" /></td>
                </tr>
                <tr>
                    <td>Username:</td>
                    <td><input type="text" name="streetName" /></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="city" /></td>
                </tr>
                <tr>
                    <td>Re-enter password:</td>
                    <td><input type="password" name="province" /></td>
                </tr>
                
                <tr>
                    <td colspan="2">
                        <input type="Submit" name="submit" value="Create" />
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
