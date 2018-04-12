<%-- 
    Document   : displayrecord
    Created on : Apr 11, 2018, 3:57:29 AM
    Author     : Amanda
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <script type="text/javascript">
            
            function editRecord(id){
                url= "EditRecord";
                window.location.href = "http://localhost:/AdminPage/"+url+"?id="+id;
                
                
            }
        
        <table align="center" border="1px" width="80%">
            <%Iterator itr;%>
            <%List data = (List) request.getAttribute("EmpData");
            for(itr = data.iterator(); itr.hasNext();){
                %>
                <tr>
                    <% String s = (String) itr.next();%>
                    <td><%=s%></td>
                    <td><%= itr.next() %></td>
                    <td><%= itr.next() %></td>
                    <td><%= itr.next() %></td>
                    <td><input type ="submit" value="Edit" name="edit" onclick="editRecord(<%=s%>);"</td>
                    <td><input type ="submit" value="Delete" name="delete" onclick="deleteRecord(<%=s%>);"</td>
                    
                    <%}%>
                </tr>
                </tr>
            }
        </table>
    </body>
</html>
