<%-- 
    Document   : AgentAdd
    Created on : 11-Apr-2018, 3:12:02 PM
    Author     : RATHA
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
        <title>Create Marketing Agent</title>
        <link href="CSS/GlobalFont.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/GlobalNav.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/GlobalAdd.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/GlobalSiteBack.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/GlobalTextboxLayout.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/GlobalBody.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <!--<form action="insert" method="POST" name="addAgentForm">
            <input type="hidden" name="agent" value="add"/>
            <label>First Name: </label><input type="text" name="Fname"/> <br/>
            <label>Last Name: </label><input type="text" name="Lname"/> <br/>
            <label>Phone Number: </label><input type="text" name="phone"/> <br/>
            <label>Email: </label><input type="email" name="email"/> <br/>
            <label>Username: </label><input type="text" name="Uname"/> <br/>
            <label>Password: </label><input type="password" name="pass"/> <br/>
            <label>Re-enter password: </label><input type="password" name="conf"/> <br/>
            <input type="submit" name="submit" value="Create"/> <br/>
        </form>-->
        <div id="bgPage"></div>
        <div id="wrapper">
            <div id="title">
                <h1>List of Agents</h1>
                <div class="navBar">
                    <a href="SiteHome.jsp">Home</a>
                    <a href="LocationIndex.jsp">Locations</a>
                    <a href="AgentIndex.jsp" class="active">Agents</a>
                    <a href="ClientIndex.jsp">Clients</a>
                    <a href="OrderIndex.jsp">Orders</a>
                    <a href="SiteError.jsp">Error</a>
                </div>
            </div>
            <div id="form">
                <form action="${pageContext.request.contextPath}/AgentServlet" method="POST" name="addAgentForm">
                    <input type="hidden" name="agent" value="add"/>

                    <div id="body">
                        <label class="left">First Name:</label>
                        <input type="text" name="id" class="right" />
                        </br>

                        <label class="left">Last Name:</label>
                        <input type="text" name="agentId" class="right" />
                        </br>

                        <label class="left">First Name:</label>
                        <input type="text" name="firstName" class="right" />
                        </br>

                        <label class="left">Phone Number:</label>
                        <input type="text" name="lastName" class="right" />
                        </br>

                        <label class="left">Email:</label>
                        <input type="text" name="streetNumber" class="right" />
                        </br>

                        <label class="left">Username:</label>
                        <input type="text" name="streetName" class="right" />
                        </br>

                        <label class="left">Password:</label>
                        <input type="password" name="city" class="right" />
                        </br>

                        <label class="left">Re-enter password:</label>
                        <input type="password" name="province" class="right" />
                        </br>



                        <label class="left">Rad:</label>
                        <div class="rads right">
                            <input type="radio" name="gender" value="male"> Male
                            <input type="radio" name="gender" value="female"> Female
                            <input type="radio" name="gender" value="other">Other
                        </div>
                        </br>

                        <label class="left">Rad:</label>
                        <div class="rads right">
                            <input type="radio" name="genders" value="male"> Male
                            <input type="radio" name="genders" value="female"> Female
                            <input type="radio" name="genders" value="other">Other </br>
                            <input type="radio" name="genders" value="other">Apache Attack Helicopter
                            <input type="radio" name="genders" value="other">Genderfluid
                            <input type="radio" name="genders" value="other">Otherkin
                        </div>
                        </br>

                        <label class="left">drop:</label>
                        <select class="right">
                            <option value="volvo">Volvo</option>
                            <option value="saab">Saab</option>
                            <option value="opel">Opel</option>
                            <option value="audi">Audi</option>
                        </select>
                        </br>

                        <input type="Submit" name="submit" value="Create" />
                    </div>
                    <div id="foot"><p>Distribution Assignment</p></div>
                </form>
            </div>
        </div>
    </body>
</html>
