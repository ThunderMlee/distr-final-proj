<%-- 
    Document   : LocationIndex
    Created on : Apr 11, 2018, 3:57:29 AM
    Author     : Amanda
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<c:if test="${sessionScope.ID == null && sessionScope.ROLE == null}">
    <c:redirect url="SiteLogin.jsp"/>
</c:if>--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Location Index</title>
        <link href="CSS/GlobalFont.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/GlobalTables.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <script type="text/javascript">

            function editLocation(id) {
                url = "LocationEdit";
                window.location.href = "http://localhost:/AdminPage/" + url + "?id=" + id;


            }
        </script>

        <table align="center" border="1px" width="80%">
            <%Iterator itr;%>
            <%List data = (List) request.getAttribute("EmpData");
                for (itr = data.iterator(); itr.hasNext();) {
            %>
            <tr>
                <% String s = (String) itr.next();%>
                <td><%=s%></td>
                <td><%= itr.next()%></td>
                <td><%= itr.next()%></td>
                <td><%= itr.next()%></td>
                <td><input type ="submit" value="Edit" name="edit" onclick="editLocation(<%=s%>);" /></td>
                <td><input type ="submit" value="Delete" name="delete" onclick="LocationDelete(<%=s%>);"/></td>

            </tr>
            <%}%>
        </table>
    </body>
</html>
