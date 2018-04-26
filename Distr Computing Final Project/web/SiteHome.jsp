<%-- 
    Document   : SiteHome
    Created on : Apr 17, 2018, 11:04:27 AM
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
        <title>Home</title>
        <link href="CSS/GlobalFont.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/GlobalNav.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/GlobalBody.css" rel="stylesheet" type="text/css"/>
        <link href="CSS/GlobalSiteBack.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="bgPage"></div>
        <div id="wrapper">
            <div id="title">
                <h1>Home Page</h1>
                <div class="navBar">
                    <a href="SiteHome.jsp" class="active">Home</a>
                    <a href="LocationIndex.jsp">Locations</a>
                    <a href="AgentIndex.jsp">Agents</a>
                    <a href="ClientIndex.jsp">Clients</a>
                    <a href="OrderIndex.jsp">Orders</a>
                    <a href="SiteError.jsp">Error</a>
                </div>
            </div>
            <div id="body">
                <h2>Welcome! Use this site for any order related service</h2> <br /><br />
                <p>Augue homero nonumy eos te, malis sonet praesent sed ex, cum ei clita commodo aperiam? Corrumpit accommodare ei vix. Ad usu vitae accusamus. Placerat lobortis hendrerit sea ea, ignota audire eloquentiam ne sed, pri odio pertinax suavitate cu. Idque dictas menandri vim id. Mei nobis iracundia eu, ut putent vivendo definitiones vim, cu pro fierent luptatum conclusionemque. Euripidis argumentum te vim, vim abhorreant assueverit ne.</p>
                <p>Graece contentiones quo in, an alii expetendis mea, ex reque dicta volumus eum. Minim fabellas rationibus an est, ut rebum aperiri iudicabit nam. Purto liberavisse ei eam. Graecis albucius consulatu ad pri, ex volumus molestie disputando usu. Omnis dicit duo ea, mel cu melius fabulas, id vidit ullum sed!</p>
                <p>Clita reprehendunt est at, qui porro quando cu, an erat nusquam molestiae duo! Vim ei aperiam saperet ceteros. Te modo harum affert mel. Unum atomorum interpretaris in cum, tation dolores ut pro! Tantas facilis sit no, cum et facilisi antiopam, eros assentior mea ex!</p>
                <p>In alia albucius appellantur mei, elitr docendi adipiscing at eam, in quo congue viderer. Saepe accusamus eam ea, ne dicat virtute repudiare sit, novum vidisse oporteat eu qui. Mutat utinam nominavi ad vim, ut mollis suscipit abhorreant eos? Est id amet principes, malis contentiones id has. Eum honestatis reprehendunt ad!</p>
                <p>An pro modus saepe, has alienum accusata cu, sea ei idque nullam labitur. Mea cu causae fierent disputando, quis harum aliquando mei at, pro idque elaboraret in? Has propriae appetere ne, ius in erat consulatu reprehendunt. Habeo vituperatoribus pro ei. Vis pericula aliquando ex! Dolore legere admodum sed te, error voluptaria mnesarchum ad his.</p>
                <p>Eu per aliquam denique, omnis putant explicari ne mel? In cum dicunt eripuit, liber fastidii indoctum id pro. Vix fabellas similique ex, ea mei mundi singulis inciderint. Sed in choro percipit splendide? Erat adipisci vim ad?</p>
                <p>At vel dolore maiorum, sed copiosae honestatis complectitur ex. Modus eirmod te nec, vix prima phaedrum liberavisse te. Ridens aliquam vivendum sea ut. Nisl equidem contentiones eos in, dicit repudiandae necessitatibus eos et? Cu nam stet nostrud!</p>
                <p>Eu falli persius duo, hinc senserit nec et? At his dictas qualisque? Et vis integre philosophia. Mea an malorum definiebas, facer dictas periculis vix an! Sonet malorum ut qui.</p>
                <p>Regione invidunt vituperata te mei, laoreet appellantur eum eu, cum eu sonet essent. An vel elitr suscipit, at ceteros inimicus interpretaris mea? Scripta voluptaria in pro, voluptaria deseruisse ex mel. Et eam dicat harum, dicam nonumy mel ut? Fabulas labores nominavi eam no. Ea quo vidit solum placerat, vix hinc brute cu.</p>
                <p>In cibo falli comprehensam duo, his cu dolore prodesset vituperata, sit harum nonumy in! Te sea iudico debitis aliquando? Inermis consetetur ius in, ad sed ridens pertinax! Ad duo suas conceptam, quo eirmod dissentiet suscipiantur in, qui ei aperiri perfecto.</p>
                <p>Cu oratio veniam inermis duo. Cum splendide torquatos at? Sed et quem denique laboramus, et sea verear expetenda. Ad mea nobis disputationi! Id velit partem apeirian vis. Partiendo consectetuer eu nam, eirmod laoreet albucius mea eu? At quo veri vitae, id sea nisl convenire.</p>
            </div>
            <div id="foot"><p>Distribution Assignment</p></div>
        </div>
    </body>
</html>
