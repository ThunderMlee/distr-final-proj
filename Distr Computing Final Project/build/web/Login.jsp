<%-- 
    Document   : Login
    Created on : Apr 11, 2018, 1:24:01 PM
    Author     : OWNER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action="" method="post" name="login">
                <table cellpadding="5" border="1">
                    <tr>
                        <th>Username</th>
                        <td><input type="text" name="uname" id="uName"></td>
                    </tr>
                    <tr>
                        <th>Password</th>
                        <td><input type="password" name="pass" id="Pass"></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" name="login" value="Login"></td>
                    </tr>
                </table>
            </form>
    </body>
</html>
