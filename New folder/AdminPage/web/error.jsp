<%-- 
    Document   : error
    Created on : Apr 11, 2018, 2:18:53 AM
    Author     : Amanda
--%>

<%@page import="com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException"%>
<%@page import="java.sql.SQLException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3>Error: <%=(MySQLSyntaxErrorException)request.getAttribute("Error")%></h3>
    </body>
</html>
