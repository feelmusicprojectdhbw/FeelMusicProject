<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-md navbar-dark bg-dark mb-2">
	<a class="navbar-brand" href="FeelMusic">Feel Music</a>
	<a class="nav-link nav-item text-light" href="FeelMusic">Home</a>
	<a class="nav-link nav-item text-light" href="CreatePlaylist">Playlist erstellen</a>
	<a class="nav-link nav-item text-light" href="SearchSong">Song suchen</a>
	<a class="nav-link nav-item text-light"href="FAQ">FAQs</a>
    <a class="nav-link nav-item text-light" href="impressum.jsp">Impressum</a>
	<a class="nav-link nav-item text-light" href="datenschutz.jsp">Datenschutz</a>
	
	<c:choose>
		<c:when test="${empty user}">
			<a href="Login"><button class="btn btn-outline-success px-2 px-3 mx-3 my-2 my-sm-0">Anmelden</button></a>
		</c:when>
		<c:otherwise>
			<c:if test="${user.isAdmin()}">		
				<a class="nav-link nav-item text-light" href="CreateSong">Song hinzuf&uumlgen</a>
				<a class="nav-link nav-item text-light" href="CreateArtist">K&uumlnstler anlegen</a>
				<a class="nav-link nav-item text-light" href="CreateLabel">Label anlegen</a>
				<a class="nav-link nav-item text-light" href="UserControl">Benutzerverwaltung</a>
			</c:if>
			<c:if test="${user.isModerator()}">
	
					<a class="nav-link nav-item text-light" href="CreateSong">Song hinzuf&uumlgen</a>
					<a class="nav-link nav-item text-light" href="CreateArtist">K&uumlnstler anlegen</a>
					<a class="nav-link nav-item text-light" href="CreateLabel">Label anlegen</a>				
					<a class="nav-link nav-item text-light" href="UserControl">Benutzerverwaltung</a>
			</c:if>
			<c:if test="${user.isArtist()}">
					<a class="nav-link nav-item text-light" href="CreateSong">Song hinzuf&uumlgen</a>				
			</c:if>
			<c:if test="${user.isLabel()}">
			</c:if>	
			<a class="nav-link nav-item text-light" href="userpage.jsp">Mein Konto</a>
			<a href="Logout"><button class="btn btn-outline-success px-2 px-3 mx-3 my-2 my-sm-0\">Abmelden</button></a>
				
		</c:otherwise>
	</c:choose>
</nav>	
	