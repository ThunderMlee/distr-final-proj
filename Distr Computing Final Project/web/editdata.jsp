<%-- 
    Document   : editdata
    Created on : Apr 13, 2018, 2:44:14 PM
    Author     : Amanda
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit location</title>
    </head>
    <body>
        <form action="UpdateDataServlet" method="post">
            <table border="1px" width="80%">
                <%ResultSet res = (ResultSet) request.getAttribute("EditData");%>
                <%if (res.next()) {
                %>
                <tr>
                    <td>ID</td>
                    <td><input readonly="true" type ="text" name="id" value=<%=res.getString("id")%>></td>
                </tr>
                <tr>
                    <td>location Name</td>
                    <td><input type ="text" name="id" value=<%=res.getString("locationName")%>></td>
                </tr>
                <tr>
                    <td>Distribution Capacity</td>
                    <td><input type ="text" name="id" value=<%=res.getString("distributionCapacity")%>></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Update" name="update"</td>
                </tr>
                <%}%>
            </table>
        </form>
    </body>
</html>
