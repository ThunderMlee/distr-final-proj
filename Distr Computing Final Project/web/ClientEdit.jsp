<%-- 
    Document   : ClientEdit
    Created on : Apr 24, 2018, 12:59:24 PM
    Author     : GhavinBahra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${sessionScope.ID == null || sessionScope.ROLE == null}">
    <c:redirect url="SiteLogin.jsp"/>
</c:if>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Edit client</title>
        <link href="CSS/GlobalFont.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <form action="ClientServlet" method="POST" name="editClientForm">
            
            <input type="hidden" name="client" value="add"/>
            <input type="hidden" name="id" value="${client.Id}"/>
            
            <table>
                <tr>
                    <td>Agent ID:</td>
                    <td><input type="text" name="agentId" value="${client.agentId}"/></td>
                </tr>
                <tr>
                    <td>First Name:</td>
                    <td><input type="text" name="firstName" value="${client.firstName}"/></td>
                </tr>
                <tr>
                    <td>Last Name:</td>
                    <td><input type="text" name="lastName" value="${client.lastName}"/></td>
                </tr>
                <tr>
                    <td>Street Number:</td>
                    <td><input type="text" name="streetNumber" value="${client.streetNumber}"/></td>
                </tr>
                <tr>
                    <td>Street Name:</td>
                    <td><input type="text" name="streetName" value="${client.streetName}"/></td>
                </tr>
                <tr>
                    <td>City:</td>
                    <td><input type="text" name="city" value="${client.city}"/></td>
                </tr>
                <tr>
                    <td>Province:</td>
                    <td><input type="text" name="province" value="${client.province}"/></td>
                </tr>
                <tr>
                    <td>Postal Code:</td>
                    <td><input type="text" name="postalCode" value="${client.postalCode}"/></td>
                </tr>
                <tr>
                    <td>Office Phone:</td>
                    <td><input type="text" name="telOffice" value="${client.telOffice}"/></td>
                </tr>
                <tr>
                    <td>Cell Phone:</td>
                    <td><input type="text" name="telCell" value="${client.telCell}"/></td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td><input type="text" name="email" value="${client.email}"/></td>
                </tr>
                <tr>
                    <td>Company:</td>
                    <td><input type="text" name="company" value="${client.company}"/></td>
                </tr>
                <tr>
                    <td>Company Type:</td>
                    <td><input type="text" name="companyType" value="${client.companyType}"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="Submit" name="submit" value="Create" />
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
