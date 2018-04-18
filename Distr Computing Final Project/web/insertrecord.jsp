<%-- 
    Document   : insertrecord
    Created on : Apr 11, 2018, 1:42:41 AM
    Author     : Amanda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert record</title>
        <style>
            #title{
                text-align: center;
            }

            table{
                background-color: gainsboro;
                margin: auto;
                width: 50%;
                padding: 10px;
                border-style: groove;
                width: 350px;
            }
            
            input[type=submit]{
                float: right;
            }
        </style>
    </head>
    <body>
        <h1 id="title">Insert Record</h1>
        <form action="InsertDataServlet" method="post">
        <table>
              <tr>
                <td>Location Name</td>
                <td><input type="text" name="locationName"></td>
            </tr>
              <tr>
                <td>Distribution Capacity</td>
                <td><input type="text" name="distributionCapacity"></td>
            </tr>
              <tr>
                <td></td>
                <td><input type="submit" value="submit"></td>
            </tr>
        </table>
        </form>
    </body>
</html>
