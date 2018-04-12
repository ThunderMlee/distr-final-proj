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
    </head>
    <body>
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
