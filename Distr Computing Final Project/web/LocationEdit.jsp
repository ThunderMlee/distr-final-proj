<%-- 
    Document   : LocationEdit
    Created on : Apr 11, 2018, 5:08:04 AM
    Author     : Amanda
--%>

<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${sessionScope.ID == null || sessionScope.ROLE == null}">
    <c:redirect url="SiteLogin.jsp"/>
</c:if>

<c:if test="${requestScope.EditData == null}">
    <c:redirect url="/LocationServlet">
        <c:param name="location" value="edit"/>
        <c:param name="id" value="${param.id}"/>
    </c:redirect>
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
            
            <input type="hidden" name="location" value="update"/>
            
            <table border="1px" width="80%">
                <%List res = (List) request.getAttribute("EditData");%>
                <%if (res.isEmpty() != true) {%>
                <tr>
                    <td>ID</td>
                    <td><input readonly type="text" name="id" value='<%=res.get(0)%>'/></td>
                </tr>
                <tr>
                    <td>location Name</td>
                    <td><input type ="text" name="locationName" value='<%=res.get(1)%>'/></td>
                </tr>
                <tr>
                    <td>Distribution Capacity</td>
                    <td><input type ="text" name="distributionCapacity" value='<%=res.get(2)%>'/></td>
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
