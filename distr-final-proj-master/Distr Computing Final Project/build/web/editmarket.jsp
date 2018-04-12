<%-- 
    Document   : editmarket
    Created on : 11-Apr-2018, 8:32:36 PM
    Author     : RATHA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit market</title>
    </head>
    <body>
        <form action="update" method="post" name="editForm">
        <!-- the hidden input is use to get the id that you need to do the edit and delete function-->
        <input type="hidden" name="id" id="marketid" value="${market.id}"/>
                <table cellpadding="5" border="1">
                    <tr>
                        <th>First Name: </th>
                        <td><input type="text" name="fname" id="fname" value="${market.firstName}"></td>
                    </tr>
                    <tr>
                        <th>Last Name: </th>
                        <td><input type="text" name="lname" id="lname" value="${market.lastName}"></td>
                    </tr>
                    <tr>
                        <th>Phone Number: </th>
                        <td><input type="text" name="phone" id="phone" value="${market.phoneNo}"></td>
                    </tr>
                    
                    
                    <tr>
                        <th>email: </th>
                        <td><input type="email" name="email" id="email" value="${market.email}"></td>
                    </tr>
                    
                    
                    <tr>
                        <th>UserName</th>
                        <td><input type="text" name="uname" id="uname" value="${market.userName}"></td>
                    </tr>
                    <tr>
                        <th>Password</th>
                        <td><input type="text" name="pass" id="pass" value="${market.password}"></td>
                    </tr>
                    
                    
                    
                    <tr>
                        <td colspan="2"><input type="submit" name="submit" value="Save"></td>
                    </tr>
                </table>
        
        
        
        
        </form>
    </body>
</html>
