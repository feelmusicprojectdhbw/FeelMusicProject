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
 	<div class="bigWrapperl, container">
        <div class="headerContainerImagel">
            <div class="headerContainerContentl">
                <h1 class="text-center">Feel Music </h1><br>
                <h5 class="text-center">Musik für dich</h5><br><br>
            </div>
        </div>

        <div class="informationContainerl">
            <div class="informationContainerChildl">
                <h3>Alle* Lieder auf einer Plattform </h3><br>
                <p> Du kennst es, dass nicht alle Lieder auf einer Plattfom verfügbar sind? <br>
                	Bei uns kannst du ganz einfach deine Lieblingssongs in einer Playlist abspielen lassen. </p>
            </div>
            <div class="informationContainerChildl">
            <h3>Deine Entscheidung, was du hörst</h3><br>
                <p>
                	Durch die individuelle Zusammenstellung der Playlist werden dir auch nur deine Lieder, die zu deiner Suche passen angezeigt. Je nach Gernre, Gefühlslage, Sprache oder Veröffentlichzeitpunkt.  <br>
               </p>
            </div>
            <div class="informationContainerChildl">
            <h3>Immer wann und wie oft du willst</h3><br>
                <p>
                	Wenn du dir einen Account erstellst, kannst du deine erstellte Playlist speichern. <br>
                	Somit kannst du sie immer wieder abspielen, wie oft du willst.
                </p>
            </div>
        </div>
        
        <div class="explanationContainerl">
        	<div class="explanationContainerChildl" >
            <h3>Ganz einfaches erweitern deiner Playlist</h3><br>
                <p>
                 Wenn ein Lied nicht bei deiner Playlist dabei sein sollte, kannst du es suchen. Falls es kein Ergebnis geben sollte, kannst du dieses Lied mit wenigen Klicks es hinzufügen.  
                </p>
            </div>
            <div class="explanationContainerChildl">
        	</div>
        </div>
        <div class="explanationContainerl">
        	<div class="explanationContainerChildl">
        	</div>
        	<div class="explanationContainerChildl" >
            <h3>Unterstütze uns</h3><br>
                <p>
                 Du hast deine eigenen Lieder schon auf anderen* Plattformen? Dann registriere dich mit einem Artist Account und lade deine Musik hoch. Daduch hilfst du uns, unser Angebot zu erweitern.  
                </p>
            </div>
        </div>
        
        <div class="informationContainerl">
        <p>Hast du weitere Fragen? <a href="FAQ">Hier</a> gehts zum FAQ, wo du viele beantwortete Fragen findest! <br>
        
        *Aktuell nur mit Soundcloud und Youtube kompatibel. </p>
        </div>
        
    </div>
	</jsp:body>
</t:app>