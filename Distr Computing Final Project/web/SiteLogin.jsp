<%-- 
    Document   : SiteLogin
    Created on : 11-Apr-2018, 12:36:10 PM
    Author     : RATHA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${sessionScope.ID != null && sessionScope.ROLE != null}">
    <c:redirect url="SiteHome.jsp"/>
</c:if>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login page</title>
    </head>
    <body>
        <h1>Login</h1> <br />
        
        <form action="${pageContext.request.contextPath}/LoginServlet" method="POST" name="loginForm">

            <p>Username: </p> <input type="text" name="name"/><br/>
            <p>Password: </p> <input type="password" name="pass"/><br/>
            <input type="submit" name="login" value="Login"/>
            <br/>
            <br/>
            <a href="new">Create an Account</a>
            &nbsp;&nbsp;
            <a href="forget">Forgot your password?</a>
        </form>
    </body>
</html>
