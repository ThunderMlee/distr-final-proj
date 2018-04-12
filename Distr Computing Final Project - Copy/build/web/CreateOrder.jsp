<%-- 
    Document   : CreateRecord
    Created on : Apr 11, 2018, 12:00:15 PM
    Author     : OWNER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Order</title>
    </head>
    <body>
        <h2>Create new Order</h2>
            <form action="" method="post" name="createOrd">
                <table cellpadding="5" border="1">
                    <tr>
                        <th>Number of Flyers</th>
                        <td><input type="text" name="flynum" id="flyNum"></td>
                    </tr>
                    <tr>
                        <th>Flyer Layout</th>
                        <td><input type="text" name="flylay" id="flyLay"></td>
                    </tr>
                    <tr>
                        <th>Locations</th>
                        <td><!--<input type="text" name="lname" id="lName"> Place radio buttons--></td>
                    </tr>
                    <tr>
                        <th>Flyer Image</th>
                        <td><input type="file" name="flyimg" id="flyImg" /></td>
                    </tr>
                    <tr>
                        <th>Payment Info</th>
                        <td><input type="radio" name="visa" id="Visa"/></td>
                        <td><input type="radio" name="master" id="Master"/></td>
                        <td><input type="radio" name="amex" id="Amex"/></td>
                    </tr> 
                    <tr>
                        <th>Client</th>
                        <td><!--<input type="text" name="city" id="City">Add drop down--></td>
                    </tr>
                    <tr>
                        <th>Invoice Number</th>
                        <td></td>
                    </tr>
                    <tr>
                        <th>Comments</th>
                        <td><textarea name="comment" form="createOrd"></textarea></td>
                    </tr>
                    
                    
                    <tr>
                        <td colspan="2"><input type="submit" name="submit" value="Create"></td>
                    </tr>
                </table>
            </form>
    </body>
</html>
