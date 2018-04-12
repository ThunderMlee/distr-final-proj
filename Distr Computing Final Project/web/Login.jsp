<%-- 
    Document   : Login
    Created on : 11-Apr-2018, 12:36:10 PM
    Author     : RATHA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login page</title>
    </head>
    <body>
        <form action="login" method="post" name="loginForm">

            <p>Username:</p> <input type="text" name="name"/><br/>
            <p>Password:</p> <input type="password" name="pass"/><br/>
            <input type="submit" name="login" value="Login"/>
            <br/>
            <br/>
            <a href="new">Create an Account</a>
            &nbsp;&nbsp;
            <a href="forget">Forgot your password?</a>
        </form>
    </body>
</html>
