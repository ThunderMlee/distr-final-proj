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
        <!--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/TestCSS.css" />-->

        <style>
            #title{
                text-align: center;
            }

            #textboxgroup{
                background-color: gainsboro;
                margin: auto;
                width: 50%;
                padding: 10px;
                border-style: groove;
                width: 350px;
            }

            .text{
                width: 30%;
                float: left;
                text-align: right;
                margin-bottom: 4px;
            }

            .textbox{
                margin-left: 10px;
                margin-bottom: 4px;
                width: 60%;
            }

            #buttonbox{
                margin: auto;
                width: 41%;
                padding-bottom: 25px;
            }
            
            #submit{
                float: right;
                margin-top: 10px;
            }
        </style>

    </head>
    <body>
        <form action="insert" method="post">
            <h1 id="title">Add Agent</h1>
            <div id="buttonbox">
                <div id="textboxgroup">
                    <label class="text">First Name:</label> <input type="text" name="Fname" class="textbox"/><br/>
                    <label class="text">Second Name:</label> <input type="text" name="Lname" class="textbox"/><br/>
                    <label class="text">Phone Number:</label> <input type="text" name="phone" class="textbox"/><br/>
                    <label class="text">Email:</label> <input type="email" name="email" class="textbox"/><br/>
                    <label class="text">UserName:</label> <input type="text" name="Uname" class="textbox"/><br/>
                    <label class="text">Password:</label> <input type="password" name="pass" class="textbox"/><br/>
                    <label class="text">Confirmation:</label> <input type="password" name="conf" class="textbox"/><br/>
                </div>
                <input type="submit" value="Create" id="submit"/>
            </div>
            <br/>
        </form>
    </body>
</html>
