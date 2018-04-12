<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create new agent</title>
    </head>
    <body>
        <h2>Create new agent</h2>
            <form action="" method="post" name="createAg">
                <table cellpadding="5" border="1">
                    <tr>
                        <th>First Name</th>
                        <td><input type="text" name="fname" id="fName"></td>
                    </tr>
                    <tr>
                        <th>Last Name</th>
                        <td><input type="text" name="lname" id="lName"></td>
                    </tr>
                    <tr>
                        <th>Street Number</th>
                        <td><input type="text" name="stnum" id="stNum"></td>
                    </tr>
                    <tr>
                        <th>Street Name</th>
                        <td><input type="text" name="stname" id="stName"></td>
                    </tr> 
                    <tr>
                        <th>City</th>
                        <td><input type="text" name="city" id="City"></td>
                    </tr>
                    <tr>
                        <th>Province</th>
                        <td><input type="text" name="prov" id="Prov"></td>
                    </tr>
                    <tr>
                        <th>Postal Code</th>
                        <td><input type="text" name="postcode" id="postCode"></td>
                    </tr>
                    <tr>
                        <th>Office Number (Ex: 4161234567)</th>
                        <td><input type="text" name="offnum" id="offNum"></td>
                    </tr>
                    <tr>
                        <th>Cellphone Number (Ex: 4161234567)</th>
                        <td><input type="text" name="cellnum" id="cellNum"></td>
                    </tr>
                    <tr>
                        <th>Email</th>
                        <td><input type="email" name="email" id="Email"></td>
                    </tr>
                    <tr>
                        <th>Company Name</th>
                        <td><input type="text" name="compname" id="compName"></td>
                    </tr>
                    <tr>
                        <th>Company Type</th>
                        <td><input type="text" name="comptype" id="compType"></td>
                    </tr>
                    
                    <tr>
                        <td colspan="2"><input type="submit" name="submit" value="Create"></td>
                    </tr>
                </table>
            </form>
    </body>
</html>
