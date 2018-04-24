<%-- 
    Document   : AgentEdit
    Created on : 11-Apr-2018, 8:32:36 PM
    Author     : RATHA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${sessionScope.ID == null || sessionScope.ROLE == null}">
    <c:redirect url="SiteLogin.jsp"/>
</c:if>

<c:if test="${agent == null}">
    <c:redirect url="AgentIndex.jsp"/>
</c:if>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Edit agent ${agent.firstName}</title>
        <link href="CSS/LocalAgentEdit.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/GlobalFont.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <form action="update" method="POST" name="editAgentForm">
            <!-- the hidden input is use to get the id that you need to do the edit and delete function-->
            <input type="hidden" name="agent" value="edit"/>
            <input type="hidden" name="id" id="marketid" value="${agent.id}"/>
            
            <table cellpadding="5" border="1">
                <tr>
                    <th>First Name: </th>
                    <td><input type="text" name="fname" id="fname" value="${agent.firstName}"></td>
                </tr>
                <tr>
                    <th>Last Name: </th>
                    <td><input type="text" name="lname" id="lname" value="${agent.lastName}"></td>
                </tr>
                <tr>
                    <th>Phone No.: </th>
                    <td><input type="text" name="phone" id="phone" value="${agent.phoneNo}"></td>
                </tr>

                <tr>
                    <th>Email: </th>
                    <td><input type="email" name="email" id="email" value="${agent.email}"></td>
                </tr>

                <tr>
                    <th>Username</th>
                    <td><input type="text" name="uname" id="uname" value="${agent.userName}"></td>
                </tr>
                <tr>
                    <th>Password</th>
                    <td><input type="password" name="pass" id="pass" value="${agent.password}"></td>
                </tr>


                <tr>
                    <td colspan="2"><input type="submit" name="save" value="Save"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
