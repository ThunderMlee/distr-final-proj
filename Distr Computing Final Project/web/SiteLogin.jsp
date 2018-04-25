<%-- 
    Document   : SiteLogin
    Created on : 11-Apr-2018, 12:36:10 PM
    Author     : RATHA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${sessionScope.ID != null && sessionScope.ROLE != null}">
    <c:redirect url='SiteHome.jsp'/>
</c:if>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Login page</title>
        <link href="CSS/LocalLogin.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/GlobalFont.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/GlobalNav.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="bgPage"></div>
        <div id="wrapper">
            <div id="title">
                <h1>Login</h1>
            </div>


            <form action="${pageContext.request.contextPath}/LoginServlet" method="POST" name="loginForm">
                <input type="hidden" name="log" value="login"/>
                <p>Username: </p> <input type="text" name="name"/><br/>
                <p>Password: </p> <input type="password" name="pass"/><br/>
                <input type="submit" value="Login"/>
                <br/>
                <br/>
                <a href="new">Create an Account</a>
                &nbsp;&nbsp;
                <a href="forget">Forgot your password?</a>
            </form>
            <div id="foot"><p>Distribution Assignment</p></div>
        </div>
    </body>
</html>
