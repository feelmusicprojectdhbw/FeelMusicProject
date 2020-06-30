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
        	<title>User: <%=user.getUsername() %></title>
        <%}else{%>
        	 <title>Error!</title>
        <%}%>
  	    <link rel="stylesheet" href="css/styleuserpage.css">
    </head>
    
    <body>
    
    	<% if(user != null){ %>   
			<%=HtmlDefaults.generateHtmlNavbar(user)%>
			<div class="container">	
			<h2 class="form-signin-heading">Welcome back,  <%= user.getUsername() %>!</h2>
		
			<label>Username: <%= user.getUsername() %></label> <br>
			<label>E-Mail: <%= user.getEmailAddress() %></label> <br>
			<label>Birthdate: <%= user.getBirthdate() %></label> <br>
			<label>Type: <%= user.getUsertype().getType() %></label><br>
			<% Playlist[] pls = Database.loadPlaylistsByUser(user); 
			   if(pls != null && pls.length > 0){%>
			<div id="playlistdiv">
			<h4> Saved Playlists: </h4>
				<div id="playlistlistdiv">
				<% for(Playlist p : pls){%>
					<div id="pllstdiv<%= p.getId() %>">
						<label onclick="focusPlaylist(<%= p.getId() %>)"><%= p.getName() %></label>
						<label><a onClick="playPlaylist(<%= p.getId() %>,<%= p.getUser().getId() %>)"> Play </a></label>
						<label><a onclick="deletePlaylist(<%= p.getId() %>,<%= p.getUser().getId() %>)">Delete</a></label>
					</div>
				<%}%>
				</div>
				<div id="playlistsongsdiv">
				</div>
			</div>
			</div>
			<%}%>
 	 	<% }else{ %>
        	 <h2 class="form-signin-heading">Error! you are not logged in!</h2>
        	 <label>Klick <a href="Login_Servlet">here </a> to log in.</label>
        <% } %>
	
</div>
</body>
<%=HtmlDefaults.generateHtmlFooter()%>
<script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
<script src="js/userpage.js" type="text/javascript" ></script>
</html>