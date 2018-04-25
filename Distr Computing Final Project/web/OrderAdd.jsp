<%-- 
    Document   : OrderAdd
    Created on : Apr 17, 2018, 12:20:29 PM
    Author     : GhavinBahra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${sessionScope.ID == null || sessionScope.ROLE == null}">
    <c:redirect url="SiteLogin.jsp"/>
</c:if>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>New order</title>
        <link href="CSS/GlobalFont.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/OrderServlet" id="order" method="POST" name="orderAddForm">
            <input type="hidden" name="order" value="add"/>
            <table>
                <tr>
                    <td>Agent</td>
                    <td><input type="text" name="agentID"></td>
                </tr>
                <tr>
                    <td>Client</td>
                    <td><input type="text" name="clientID"></td>
                </tr>
                <tr>
                    <td>Flyer quantity</td>
                    <td><input type="text" name="flyerQty"></td>
                </tr>
                <tr>
                    <td>Flyer Layout</td>
                    <td>
                        <select name="flyerLayout">
                            <option value="landscape">Landscape</option>
                            <option value="portrait">Portrait</option>
                            <option value="both">Both</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Personal Copy</td>
                    <td><input type="text" name="personalCopy"></td>
                </tr>
                <tr>
                    <td>Flyer Image</td>
                    <td><input type="file" name="flyerImg"/></td>
                </tr>
                <tr>
                    <td>Invoice Number</td>
                    <td><input type="text" name="invoiceNum"></td>
                </tr>
                <tr>
                    <td>Comments</td>
                    <td><input type="text" name="comments"></td>
                </tr>
                <tr>
                    <td>Is flyer art approved?</td>
                    <td>
                        <br/>
                        <label>Yes</label><input type="radio" value="true" name="artApprove"/> <br/>
                        <label>No</label><input type="radio" value="false" name="artApprove"/> <br/>
                        <br/>
                    </td>
                </tr>
                <tr>
                    <td>Has payment received?</td>
                    <td>
                        <br/>
                        <label>Yes</label><input type="radio" value="true" name="payReceive"/> <br/>
                        <label>No</label><input type="radio" value="false" name="payReceive"/> <br/>
                        <br/>
                    </td>
                </tr>
                
                <tr>
                    <td></td>
                    <td><input type="submit" value="Submit" /></td>
                </tr>
            </table>
        </form>
    </body>
</html>
