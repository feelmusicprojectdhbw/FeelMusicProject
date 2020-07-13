<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="true"%>
<%@page import="main.*"%>
<%@page import="main.obj.*"%>
<%@page import="main.dao.*"%>
<%@page import="main.tools.*"%>
<%@page import="main.servlets.*"%>
<%@page import="main.servlets.ajax.*"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:app>
 	<jsp:body>
 	<c:choose>
		<c:when test="${empty user}">
			<jsp:include page="/WEB-INF/templates/notLoggedIn.jsp"/>
		</c:when>
		<c:otherwise>  	
		<div class="container">	
			<h2 class="form-signin-heading">Willkommen zur&uumlck,  ${user.getUsername()}!</h2>
			<label>Benutzername: ${user.getUsername()}</label> <br>
			<label>E-Mail: ${user.getEmailAddress()}</label> <br>
			<label>Geburtsdatum: ${user.getFormattedBirthdate()}</label> <br>
			<label>Benutzertyp: ${user.getUsertype().getType()}</label><br>
			<div id="playlistdiv">
			<h4> Gespeicherte Playlists: </h4>
				<div id="playlistlistdiv">
				<c:forEach var="p" items="${user.loadUserplaylists()}"> 
					<div id="pllstdiv${p.getId()}">
						<label class="btn btn-link"onclick="focusPlaylist(${p.getId()})">${p.getName()}</label>
						<label><a class="btn btn-warning" onClick="playPlaylist(${p.getId()},${p.getUser().getId()})">Abspielen </a></label>
						<label><a class="btn btn-danger" onclick="deletePlaylist(${p.getId()},${p.getUser().getId()})">L&oumlschen</a></label>
					</div>
				</c:forEach>
				</div>
				<div id="playlistsongsdiv">
				</div>
			</div>
		</div>
 	 </c:otherwise>
 	 </c:choose>
	</jsp:body>
</t:app>
<link rel="stylesheet" href="css/styleuserpage.css">
<script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
<script src="js/userpage.js" type="text/javascript" ></script>
</html>