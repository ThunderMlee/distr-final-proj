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
        <style>
            #title{
                text-align: center;
            }
            
            p{
                font-weight: bold;
            }
            
            form {
                margin: auto;
                width: 400px;
                border: 3px solid #f1f1f1;
            }

            input[type=text], input[type=password] {
                width: 100%;
                padding: 12px 20px;
                margin-bottom: 8px;
                display: inline-block;
                border: 1px solid #ccc;
                box-sizing: border-box;
            }
            
            input[type=submit]{
                border: none;
                background-color: lightgreen;
                width: 100%;
                height: 30px;
            }
            
            #forgot{
                float: right;
            }
            </style>
        </head>
        <body>
            <h1 id="title">Login</h1>
            <form action="login" method="post" name="loginForm">

                <p>Username:</p> <input type="text" name="name"/><br/>
                <p>Password:</p> <input type="password" name="pass"/><br/>
                <input type="submit" name="login" value="Login"/>
                <br/>
                <br/>
                <a href="new">Create an Account</a>
                &nbsp;&nbsp;
                <a href="forget" id="forgot">Forgot your password?</a>
            </form>
        </body>
    </html>
