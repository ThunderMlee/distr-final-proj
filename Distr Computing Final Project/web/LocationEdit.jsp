<%-- 
    Document   : LocationEdit
    Created on : Apr 11, 2018, 5:08:04 AM
    Author     : Amanda
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${sessionScope.ID == null || sessionScope.ROLE == null}">
    <c:redirect url="SiteLogin.jsp"/>
</c:if>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Edit location</title>
        <link href="CSS/GlobalFont.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <form action="LocationServlet" method="POST">
            
            <input type="hidden" name="location" value="edit"/>
            
            <table border="1px" width="80%">
                <%ResultSet res = (ResultSet) request.getAttribute("EditData");%>
                <%if (res.next()) {%>
                <tr>
                    <td>ID</td>
                    <td><input readonly type="text" name="id" value=<%=res.getString("id")%>/></td>
                </tr>
                <tr>
                    <td>location Name</td>
                    <td><input type ="text" name="id" value=<%=res.getString("locationName")%>/></td>
                </tr>
                <tr>
                    <td>Distribution Capacity</td>
                    <td><input type ="text" name="id" value=<%=res.getString("distributionCapacity")%>/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Update" name="update"/></td>
                </tr>
                <%}%>
            </table>
        </form>
    </body>
</html>
