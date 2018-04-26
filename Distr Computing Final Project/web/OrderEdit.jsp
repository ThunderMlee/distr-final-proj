<%-- 
    Document   : OrderEdit
    Created on : Apr 17, 2018, 11:49:22 AM
    Author     : GhavinBahra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Order"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${sessionScope.ID == null || sessionScope.ROLE == null}">
    <c:redirect url="SiteLogin.jsp"/>
</c:if>

<c:if test="${requestScope.order == null}">
    <c:redirect url="/OrderServlet">
        <c:param name="order" value="edit"/>
        <c:param name="id" value="${param.id}"/>
    </c:redirect>
</c:if> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Edit order</title>
        <link href="CSS/GlobalFont.css" rel="stylesheet" type="text/css"/>
        <script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
        <script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>
        <script>
            function readURL(input) {
                if (input.files && input.files[0]) {
                    var reader = new FileReader();

                    reader.onload = function (e) {
                        $('#dispImg')
                                .attr('src', e.target.result)
                                .width(150)
                                .height(200);
                    };

                    reader.readAsDataURL(input.files[0]);
                }
            }
        </script>
    </head>
    <body>
        <form action="OrderServlet" enctype="multipart/form-data" method="POST" name="orderEditForm">
            
            <input type="hidden" name="ID" value="${order.ID}"/>
            <input type="hidden" name="order" value="update"/>
            
            <table>
                <tr>
                    <td>Agent ID</td>
                    <td><input type="text" name="agentID" value="${order.agentID}"/></td>
                </tr>
                <tr>
                    <td>Client ID</td>
                    <td><input type="text" name="clientID" value="${order.clientID}" /></td>
                </tr>
                <tr>
                    <td>Flyer Quantity</td>
                    <td><input type="text" name="flyerQty" value="${order.flyerQty}" /></td>
                </tr>
                <tr>
                    <td>Flyer Layout</td>
                    <td>
                        <select name="flyerLayout">
                            <option value="landscape" <c:if test="${order.flyerLayout == 'landscape'}"><c:out value="selected"/></c:if>>Landscape</option>
                            <option value="portrait" <c:if test="${order.flyerLayout == 'portrait'}"><c:out value="selected"/></c:if>>Portrait</option>
                            <option value="both" <c:if test="${order.flyerLayout == 'both'}"><c:out value="selected"/></c:if>>Both</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Flyer Image</td>
                    <td><img id="dispImg" src="<c:out value="${order.flyerImgBase64}"/>" alt="Flyer Image"/><input type="file" name="flyerImg" onchange="readURL(this);"/></td>
                </tr>
                <tr>
                    <td>Personal Copy</td>
                    <td><input type="text" name="personalCopy" value="${order.personalCopy}"/></td>
                </tr>
                <tr>
                    <td>Payment Info</td>
                    <td><input type="text" name="paymentInfo" value="${order.paymentInfo}"/></td>
                </tr>
                <tr>
                    <td>Invoice Number</td>
                    <td><input type="text" name="invoiceNum" value="${order.invoiceNum}"/></td>
                </tr>
                <tr>
                    <td>Comments</td>
                    <td><input type="text" name="comments" value="${order.comments}"/></td>
                </tr>
                <tr>
                    <td>Is flyer art approved?</td>
                    <td>
                        <br/>
                        <label>Yes</label><input type="radio" value="true" name="artApprove" <c:if test="${order.isFlyerArtApproved == true}"><c:out value="checked"/></c:if>/> <br/>
                        <label>No</label><input type="radio" value="false" name="artApprove" <c:if test="${order.isFlyerArtApproved == false}"><c:out value="checked"/></c:if>/> <br/>
                        <br/>
                    </td>
                </tr>
                <tr>
                    <td>Has payment received?</td>
                    <td>
                        <br/>
                        <label>Yes</label><input type="radio" value="true" name="payReceive" <c:if test="${order.isPaymentReceived == true}"><c:out value="checked"/></c:if>/> <br/>
                        <label>No</label><input type="radio" value="false" name="payReceive" <c:if test="${order.isPaymentReceived == false}"><c:out value="checked"/></c:if>/> <br/>
                        <br/>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Save"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>
