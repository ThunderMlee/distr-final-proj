<%-- 
    Document   : LocationAdd
    Created on : Apr 11, 2018, 1:42:41 AM
    Author     : Amanda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${sessionScope.ID == null || sessionScope.ROLE == null}">
    <c:redirect url="SiteLogin.jsp"/>
</c:if>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert location</title>
    </head>
    <body>
        <form action="InsertDataServlet" method="POST" name="locationAddForm">
            <input type="hidden" name="location" value="add"/>
            <table>
                  <tr>
                    <td>Location Name</td>
                    <td><input type="text" name="locationName"/></td>
                </tr>
                  <tr>
                    <td>Distribution Capacity</td>
                    <td><input type="text" name="distributionCapacity"/></td>
                </tr>
                  <tr>
                    <td></td>
                    <td><input type="submit" value="Submit"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>
