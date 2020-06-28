<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="true"%>
<%@page import="main.*"%>
<%@page import="main.obj.*"%>
<%@page import="main.dao.*"%>
<%@page import="main.tools.*"%>
<%@page import="main.servlets.*"%>
<%@page import="main.servlets.ajax.*"%>
<%=HtmlDefaults.generateHtmlHeader()%>
  <%	User user = (User) session.getAttribute("user"); %>
        	<title>Usercontrol</title>
   	    <link rel="stylesheet" href="css/styleuserpage.css">
  	    <link rel="stylesheet" href="css/styleusercontrol.css">
    </head>
	<body>
    	<% if(user != null){ %>   
			<%=HtmlDefaults.generateHtmlNavbar(user)%>
			<%if (user.getUsertype().getId() == 1 || user.getUsertype().getId() == 3){%>
				<h1 class="form-signin-heading">Benutzerverwaltung</h1>
				<h2>Search for E-Mail Address</h2>
				<p><label for="inputSearch"><b>Search:</b></label>	
				<input type="text" name="inputSearch" id="inputSearch" class="form-control" placeholder="Users mailaddress or UserId" autofocus>
				<button onclick="searchUser(<%=user.getUsertype().getId()%>)">Suchen</button></p>
				<div id="userpagewrapper" style="display: none">
					<form id="usercontrol" class=" form-signin " method="post" action="UserControl_Servlet">
						<label>User ID: <span id="userIdSpan"></span> <input type="text" id="hiddenid" name=hiddenid style="display:none"/></label> <br>					
						<label>Username: <span id="usernameSpan"></span></label> <br>
						<label>E-Mail: <span id="emailSpan"></span></label> <br>
						<label>Birthdate: <span id="birthdateSpan"></span></label> <br>
						<label>Type: 
						<select id="usertypeselector" name="usertypeselector" onchange="changedUsertype(0, 0)">
							<% for(Usertype ut : Usertypes.getAllUsertypes()){%>							
								<option value="<%=ut.getId() %>"><%=ut.getType()%></option>								
							<% } %>
						</select>
						</label><br>					
						<div id="zuordnugsdiv" style="display: none">
							<label> Connection to: 
								<select id="labelToConnect" name="labelToConnect">  
							     <% MusicLabel[] labels = Database.getAllLabels();
							      	if(labels != null){
										for (MusicLabel a : labels) {
											%><option value="<%= a.getId() %>"><%= a.getName() %>;<%= a.getId() %></option><%
										}			
									}%> 
								</select>
								<select id="artistToConnect" name="artistToConnect">  
							     <% Artist[] artists = Database.getAllArtists();
							      	if(artists != null){
										for (Artist a : artists) {
											if(a.getId() != 1){%>
												<option value="<%= a.getId() %>"><%= a.getName() %>;<%= a.getId() %></option>
										  <%}
										}			
									}%> 
						        </select>
					        </label>
						</div>
						<button class=" btn btn-lg btn-primary btn-block " type="submit">Save Changes</button>
					</form>
				</div>				
			<%}else{%>
				<!-- Kein zugriff auf die Seite (User hat keine Berechtigung benutzer zu verwalten) -->
			<%}%>
 	 	<% }else{ %>
			<!-- Kein zugriff auf die Seite (User ist nicht angemeldet) -->
			<%=HtmlDefaults.generateNotLoggedInMessage()%>
        <% } %>
	<%=HtmlDefaults.generateHtmlFooter()%>
</body>
<script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
<script src="js/usercontrol.js" type="text/javascript" ></script>
</html>