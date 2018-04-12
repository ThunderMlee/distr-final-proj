<%-- 
    Document   : AddAgent
    Created on : 11-Apr-2018, 3:12:02 PM
    Author     : RATHA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Marketing Agent</title>
    </head>
    <body>
        <form action="insert" method="post">
        First Name: <input type="text" name="Fname"/><br/>
        Second Name: <input type="text" name="Lname"/><br/>
        Phone Number: <input type="text" name="phone"/><br/>
        Email: <input type="email" name="email"/><br/>
        UserName: <input type="text" name="Uname"/><br/>
        Password: <input type="password" name="pass"/><br/>
        Confirmation: <input type="password" name="conf"/><br/>
        <input type="submit" value="Create"/>
        <br/>
        </form>
    </body>
</html>
