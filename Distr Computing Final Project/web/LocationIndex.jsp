<%-- 
    Document   : LocationIndex
    Created on : Apr 11, 2018, 3:57:29 AM
    Author     : Amanda
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${sessionScope.ID == null || sessionScope.ROLE == null}">
    <c:redirect url="SiteLogin.jsp"/>
</c:if>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Location Index</title>
    </head>
    <body>
        <script type="text/javascript">
            function editRecord(id) {
                url = "EditRecordServlet";
                window.location.href = "http://localhost:8080/Distr_Computing_Final_Project/" + url + "?id=" + id;
            }
            function deleteRecord(id) {
                url = "DeleteRecordServlet2";
                window.location.href = "http://localhost:8080/Distr_Computing_Final_Project/" + url + "?id=" + id;
            }
        </script>
        <table cellpadding="5" border="1">
            <thead>
            <th>ID</th>
            <th>Location Name</th>
            <th>Distribution Capacity</th>
        </thead>
        <tbody>
            <%Iterator itr;%>
            <%List data = (List) request.getAttribute("LocData");
                for (itr = data.iterator(); itr.hasNext();) {%>
            <tr>

                <% String s = (String) itr.next();%>

                <td><%=s%></td>

                <td><%= itr.next()%></td>
                <td><%= itr.next()%></td>

                <c:if test="${sessionScope.ROLE == 'ADMIN'}">
                <td><input type=submit value="Edit" name="edit" onclick="editRecord(<%=s%>);"/></td>
                <td><input type=submit value="Delete" name="delete" onclick="deleteRecord(<%=s%>);"/></td>
                </c:if>
                <%}%>
            </tr>

        </tbody>
    </table>
</body>
</html>
