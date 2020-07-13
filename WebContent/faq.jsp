<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="true"%>
<%@page import="main.*"%>
<%@page import="main.obj.*"%>
<%@page import="main.dao.*"%>
<%@page import="main.tools.*"%>
<%@page import="main.servlets.*"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:app>
 	<jsp:body> 
     	<div class="container">
        <div class="jumbotron">
        <h2>Wie kann ich eine Playlist erstellen? </h2>
        <p>
            Oben in der Kopfzeile findest du den Reiter 'Create Playlist'. Dort kannst du dann aus f�nf verschiedenen Kategorien deine pers�nliche Playlist zusammenstellen. 
			Wenn du die Kategorie ausgeklappt hast, w�hlst ein Kriterium durch einen Klick aus. Mit einem weiteren Klick schlie�t du dieses Kriterium aus und mit einem weiteren neutralisierst du deine Auswahl. Was genau nun aktivert, deaktivert oder neutral ist, erkennst du an den Farben (gr�n- aktiviert, rot- deaktiviert, grau- neutral).
			Wenn du mit deiner Auswahl zufrieden bist, musst du untern auf den Button 'Send' klicken. Danach wird dir eine Playlist erstellt und kannst sofort losh�ren. 
		</p>
        </div>

        <div class="jumbotron">
         <h2>Wie bediene ich die Playlist? </h2>
         <p>
           Wenn du eine Playlist erstellt hast wird direkt das erste Lied abgespielt. Falls ein Video vorhanden ist, siehst du es auf der linken Seite. 
           Darunter stehen der Titel, der K�nstler und zus�tzliche Informationen des aktuellen Liedes. 
           �ber dem Video kannst du zwischen den verschiedenen Platformen w�hlen. Aktuell bieten wir die Plattformen Youtube und Soundcloud an.
           Auf der rechten Seite wird dir die komplette Playlist in der Reihenfolge der Wiedergabe angezeigt. 
           Du kannst auch �ber die Buttons 'Previous' oder 'Next' ein Lied nocheinmal h�ren oder �berspringen.
           Durch einen Klick auf ein Lied in der Liste kannst du auch direkt dort hin springen. </br>
        </p>
       </div>

	    <div class="jumbotron">
	      <h2>Kann ich eine Playlist abspeichern?  </h2>	         
	      <p>
           Ja. Wenn du eine Playlist erstellt hast und die Daten abgeschickt hast, wird eine Playlist direkt f�r dich erstellt. Du wirst automatisch zu dieser geleitet und sie wird abgespielt. 
	       Wenn du auf unserer Seite mit deinem Account angemeldet bist, findest du oben rechts �ber der Warteschlange ein Eingabefeld. Dort kannst du den Namen der Playlist eingeben und mit dem Button abspeichern. 
	       Bei deiner Account�bersicht ('My Account') kannst du nun deine gespeicherten Playlists sehen und abspielen. </br>
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
	        Nein. Die Erstellung des Kontos ist freiwillig. Wenn du aber ein Konto erstellst, kannst du die Playlists speichern und immer wieder abspielen. Zus�tzlich stehen dir mehr Funktionen auf der Website zur Verf�gung. 
	        Du kannst dann ein Lied oder ein K�nstler erstellen, hinzuf�gen oder l�schen. </br>
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
	</jsp:body>
</t:app>