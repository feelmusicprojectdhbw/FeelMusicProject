<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="true"%>
<%@page import="main.*"%>
<%@page import="main.obj.*"%>
<%@page import="main.dao.*"%>
<%@page import="main.tools.*"%>
<%@page import="main.servlets.*"%>
        <%=HtmlDefaults.generateHtmlHeader()%>  
        <title>FAQ</title>
    </head>
    
    <body>
     <%	User user = (User) session.getAttribute("user");%>
     <%=HtmlDefaults.generateHtmlNavbar(user)%>
     	<div class="container">
        <div class="jumbotron">
        <h2>Wie kann ich eine Playlist erstellen? </h2>
        <p>
            Oben in der Kopfzeile findest du den Reiter 'Create Playlist'. Dort kannst du dann aus fünf verschiedenen Kategorien deine persönliche Playlist zusammenstellen. 
			Wenn du die Kategorie ausgeklappt hast, wählst ein Kriterium durch einen Klick aus. Mit einem weiteren Klick schließt du dieses Kriterium aus und mit einem weiteren neutralisierst du deine Auswahl. Was genau nun aktivert, deaktivert oder neutral ist, erkennst du an den Farben (grün- aktiviert, rot- deaktiviert, grau- neutral).
			Wenn du mit deiner Auswahl zufrieden bist, musst du untern auf den Button 'Send' klicken. Danach wird dir eine Playlist erstellt und kannst sofort loshören. 
		</p>
        </div>

        <div class="jumbotron">
         <h2>Wie bediene ich die Playlist? </h2>
         <p>
           Wenn du eine Playlist erstellt hast wird direkt das erste Lied abgespielt. Falls ein Video vorhanden ist, siehst du es auf der linken Seite. 
           Darunter stehen der Titel, der Künstler und zusätzliche Informationen des aktuellen Liedes. 
           Über dem Video kannst du zwischen den verschiedenen Platformen wählen. Aktuell bieten wir die Plattformen Youtube und Soundcloud an.
           Auf der rechten Seite wird dir die komplette Playlist in der Reihenfolge der Wiedergabe angezeigt. 
           Du kannst auch über die Buttons 'Previous' oder 'Next' ein Lied nocheinmal hören oder überspringen.
           Durch einen Klick auf ein Lied in der Liste kannst du auch direkt dort hin springen. </br>
        </p>
       </div>

	    <div class="jumbotron">
	      <h2>Kann ich eine Playlist abspeichern?  </h2>	         
	      <p>
           Ja. Wenn du eine Playlist erstellt hast und die Daten abgeschickt hast, wird eine Playlist direkt für dich erstellt. Du wirst automatisch zu dieser geleitet und sie wird abgespielt. 
	       Wenn du auf unserer Seite mit deinem Account angemeldet bist, findest du oben rechts über der Warteschlange ein Eingabefeld. Dort kannst du den Namen der Playlist eingeben und mit dem Button abspeichern. 
	       Bei deiner Accountübersicht ('My Account') kannst du nun deine gespeicherten Playlists sehen und abspielen. </br>
      	 </p>
	    </div>
    
	    <div class="jumbotron">
          <h2>Wie kann ich mich einloggen?  </h2>	         
          <p>
            Du findest in der Kopfzeile einen Button mit der Aufschrift 'LogIn'. Dort kannst du dich mit deinen Daten einloggen. Wenn du noch kein Konto hast, kannst du dich ganz einfach unter 'Register' registrieren.  </br>
         </p>
	    </div>
    
	    <div class="jumbotron">
	      <h2>Muss ich mir ein Konto anlegen?  </h2>	         
	      <p>
	        Nein. Die Erstellung des Kontos ist freiwillig. Wenn du aber ein Konto erstellst, kannst du die Playlists speichern und immer wieder abspielen. Zusätzlich stehen dir mehr Funktionen auf der Website zur Verfügung. 
	        Du kannst dann ein Lied oder ein Künstler erstellen, hinzufügen oder löschen. </br>
	     </p>
	    </div>
	         
		<div class="jumbotron">
	       <h2>Deine Frage wurde nicht beantwortet?   </h2>		        
	       <p>
	        Wenn du noch weitere Fragen hast kannst du uns gerne kontaktieren. Die Kontaktdaten findest du <a href="impressum.jsp">hier</a>.  </br>
	        Du willst wissen, wie unsere Seite aufgebaut ist? In der <a href="FAQ?d=TechnischeDokuFeelMusic.pdf" download> Technischen Dokumentation </a> kannst du den gesamten Aufbau nachlesen. 
	      </p>
		</div>
        </div>

    <%=HtmlDefaults.generateHtmlFooter()%>
    
</body>

</html>