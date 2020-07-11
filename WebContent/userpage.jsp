<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="true"%>
<%@page import="main.*"%>
<%@page import="main.obj.*"%>
<%@page import="main.dao.*"%>
<%@page import="main.tools.*"%>
<%@page import="main.servlets.*"%>
<%@page import="main.servlets.ajax.*"%>
<%=HtmlDefaults.generateHtmlHeader()%>
  <%	User user = (User) session.getAttribute("user");
  		if(user != null){%>
        	<title>Benutzer: <%=user.getUsername() %></title>
        <%}else{%>
        	 <title>Fehler!</title>
        <%}%>
  	    <link rel="stylesheet" href="css/styleuserpage.css">
    </head>
    
    <body>
    	
    	<% if(user != null){ %>   
			<%=HtmlDefaults.generateHtmlNavbar(user)%>		
		<div class="container">	
			<h2 class="form-signin-heading">Willkommen zur&uumlck,  <%= user.getUsername() %>!</h2>
			<label>Benutzername: <%= user.getUsername() %></label> <br>
			<label>E-Mail: <%= user.getEmailAddress() %></label> <br>
			<label>Geburtsdatum: <%= user.getBirthdate() %></label> <br>
			<label>Benutzertyp: <%= user.getUsertype().getType() %></label><br>
			<% Playlist[] pls = Database.loadPlaylistsByUser(user); 
			   if(pls != null && pls.length > 0){%>
			<div id="playlistdiv">
			<h4> Gespeicherte Playlists: </h4>
				<div id="playlistlistdiv">
				<% for(Playlist p : pls){%>
					<div id="pllstdiv<%= p.getId() %>">
						<label class="btn btn-link"onclick="focusPlaylist(<%= p.getId() %>)"><%= p.getName() %></label>
						<label><a class="btn btn-warning" onClick="playPlaylist(<%= p.getId() %>,<%= p.getUser().getId() %>)">Abspielen </a></label>
						<label><a class="btn btn-danger" onclick="deletePlaylist(<%= p.getId() %>,<%= p.getUser().getId() %>)">L&oumlschen</a></label>
					</div>
				<%}%>
				</div>
				<div id="playlistsongsdiv">
				</div>
			</div>
			<%}%>
 	 	<% }else{ %>
        	 <h2 class="form-signin-heading">Fehler! Sie sind nicht angemeldet!</h2>
        	 <label>Klicken Sie <a href="Login">hier</a> um sich anzumelden.</label>
        <% } %>	
	</div>
</body>
<%=HtmlDefaults.generateHtmlFooter()%>
<script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
<script src="js/userpage.js" type="text/javascript" ></script>
</html>