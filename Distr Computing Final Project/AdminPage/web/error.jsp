<%-- 
    Document   : error
    Created on : Apr 11, 2018, 2:18:53 AM
    Author     : Amanda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3>Error: <%=(String)request.getAttribute("Error")%></h3>
    </body>
</html>
