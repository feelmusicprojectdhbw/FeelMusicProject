<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="true"%>
<%@page import="main.*"%>
<%@page import="main.obj.*"%>
<%@page import="main.dao.*"%>
<%@page import="main.tools.*"%>
<%@page import="main.servlets.*"%>
<%=HtmlDefaults.generateHtmlHeader()%>
        <title>Feel Music</title>
   		<link rel="stylesheet" href="css/stylelandingpage.css">
    </head>
    
    <body>
     <%	User user = (User) session.getAttribute("user");%>
     <%=HtmlDefaults.generateHtmlNavbar(user)%>
 	<div class="bigWrapper, container">
        <div class="headerContainerImage">
            <div class="headerContainerContent">
                <h1 class="text-center">Feel Music </h1><br>
                <h5 class="text-center">Musik für dich</h5><br><br>
            </div>
        </div>

        <div class="informationContainer">
            <div class="informationContainerChild">
                <h3>Alle* Lieder auf einer Plattform </h3><br>
                <p> Du kennst es, dass nicht alle Lieder auf einer Plattfom verfügbar sind? <br>
                	Bei uns kannst du ganz einfach deine Lieblingssongs in einer Playlist abspielen lassen. </p>
            </div>
            <div class="informationContainerChild">
            <h3>Deine Entscheidung, was du hörst</h3><br>
                <p>
                	Durch die individuelle Zusammenstellung der Playlist werden dir auch nur deine Lieder, die zu deiner Suche passen angezeigt. Je nach Gernre, Gefühlslage, Sprache oder Veröffentlichzeitpunkt.  <br>
               </p>
            </div>
            <div class="informationContainerChild">
            <h3>Immer wann und wie oft du willst</h3><br>
                <p>
                	Wenn du dir einen Account erstellst, kannst du deine erstellte Playlist speichern. <br>
                	Somit kannst du sie immer wieder abspielen, wie oft du willst.
                </p>
            </div>
        </div>
        
        <div class="explanationContainer">
        	<div class="explanationContainerChild">
            <h3>Starte deine Musikkarriere!</h3><br>
                <p>
                 Mit einem Account kannst du dir einen eigenen Artist Account erstellen und deine eigenen Songs hochladen. Nachdem kann jeder deine Lieder hören und feiern. Starte direkt <a href ="CreateSong">hier</a>!
                </p>
            </div>
            <div class="explanationContainerChild">
        	</div>
        </div>
        <div class="explanationContainer">
        	<div class="explanationContainerChild">
        	</div>
        	<div class="explanationContainerChild" >
            <h3>Ganz einfaches erweitern deiner Playlist</h3><br>
                <p>
                 Wenn ein Lied nicht bei deiner Playlist dabei sein sollte, kannst du es suchen. Falls es kein Ergebnis geben sollte, kannst du dieses Lied mit wenigen Klicks es hinzufügen.  
                </p>
            </div>
        </div>
        
        <div class="informationContainer">
        <p>Hast du weitere Fragen? <a href="FAQ">Hier</a> gehts zum FAQ, wo du viele beantwortete Fragen findest! <br>
        
        *Aktuell nur mit Soundcloud und Youtube kompatibel. </p>
        </div>
        
    </div>
    <%=HtmlDefaults.generateHtmlFooter()%>
</body>

</html>