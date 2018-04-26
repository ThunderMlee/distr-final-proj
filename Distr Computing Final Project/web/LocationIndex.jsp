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

<c:if test="${requestScope.LocData == null}">
    <c:redirect url="/LocationServlet">
        <c:param name="location" value="list"/>
    </c:redirect>
</c:if> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Location Index</title>
        <link href="CSS/GlobalFont.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/GlobalTables.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/GlobalNav.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/GlobalSiteBack.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript">
            function editRecord(id) {
                url = "LocationEdit.jsp";
                window.location.href = "${pageContext.request.contextPath}/" + url + "?id=" + id;
            }
            function deleteRecord(id) {
                url = "LocationServlet";
                window.location.href = "${pageContext.request.contextPath}/" + url + "?id=" + id + "&location=delete";
            }
        </script>
    </head>
    <body>
        <div id="bgPage"></div>
        <div id="wrapper">
            <div id="title">
                <h1>List of Locations</h1>
                <div class="navBar">
                    <a href="SiteHome.jsp">Home</a>
                    <a href="LocationIndex.jsp" class="active">Locations</a>
                    <a href="AgentIndex.jsp">Agents</a>
                    <a href="ClientIndex.jsp">Clients</a>
                    <a href="OrderIndex.jsp">Orders</a>
                    <a href="SiteError.jsp">Error</a>
                </div>
            </div>
            <center>
                <table cellpadding="5" border="1">
                    <thead>
                        <c:if test="${sessionScope.ROLE == 'ADMIN'}">
                        <th>ID</th>
                        </c:if>
                        <th>Location Name</th>
                        <th>Distribution Capacity</th>
                        <c:if test="${sessionScope.ROLE == 'ADMIN'}">
                        <th>Action</th>
                        </c:if>
                    </thead>
                    <tbody>
                        <%Iterator itr;%>
                        <%List data = (List) request.getAttribute("LocData");
                            for (itr = data.iterator(); itr.hasNext();) {%>
                        <tr>
                            <% String s = (String) itr.next();%>

                            <c:if test="${sessionScope.ROLE == 'ADMIN'}">
                            <td><%=s%></td>
                            </c:if>
                            <td><%= itr.next()%></td>
                            <td><%= itr.next()%></td>
                            <c:if test="${sessionScope.ROLE == 'ADMIN'}">
                            <td><input type=submit value="Edit" name="edit" onclick="editRecord(<%=s%>);"/><input type=submit value="Delete" name="delete" onclick="deleteRecord(<%=s%>);"/></td>
                            </c:if>
                                <%}%>
                        </tr>
                    </tbody>
                </table>
                <input type="button" onclick="location.href = 'LocationAdd.jsp'" value="Add New Location" class="btnAdd"/>
            </center>
            <div id="foot"><p>Distribution Assignment</p></div>
        </div>
    </body>
</html>
