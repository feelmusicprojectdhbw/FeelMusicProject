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
        	<title>Benutzerverwaltung</title>
   	    <link rel="stylesheet" href="css/styleuserpage.css">
    </head>
	<body>
    	<% if(user != null){ %>   
			<%=HtmlDefaults.generateHtmlNavbar(user)%>
			<div class="container">
			<%if (user.getUsertype().getId() == 1 || user.getUsertype().getId() == 3){%>
				<h1 class="form-signin-heading">Benutzerverwaltung</h1>
				<h2>Suche nach einer E-Mail Addresse oder einer Id</h2>
				<p><label for="inputSearch"><b>Search:</b></label>	
				<input type="text" name="inputSearch" id="inputSearch" class="form-control" placeholder="E-Mail oder Benutzer ID" autofocus>
				<button class="btn btn-success btn-lg" onclick="searchUser(<%=user.getUsertype().getId()%>)">Suchen</button></p>
				<div id="userpagewrapper" style="display: none">
					<form id="usercontrol" class=" form-signin " method="post" action="UserControl">
						<label>Benutzer ID: <span id="userIdSpan"></span> <input type="text" id="hiddenid" name=hiddenid style="display:none"/></label> <br>					
						<label>Benutzername: <span id="usernameSpan"></span></label> <br>
						<label>E-Mail: <span id="emailSpan"></span></label> <br>
						<label>Geburtsdatum: <span id="birthdateSpan"></span></label> <br>
						<label>Benutzertyp: 
						<select id="usertypeselector" name="usertypeselector" onchange="changedUsertype(0, 0)">
							<% for(Usertype ut : Usertypes.getAllUsertypes()){%>							
								<option value="<%=ut.getId() %>"><%=ut.getType()%></option>								
							<% } %>
						</select>
						</label><br>					
						<div id="zuordnugsdiv" style="display: none">
							<label> Verbunden mit: 
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
						<button class="btn btn-primary btn-lg" type="submit">&Aumlnderungen speichern</button>
					</form>
				</div>				
			<%}else{%>
				<!-- Kein zugriff auf die Seite (User hat keine Berechtigung benutzer zu verwalten) -->
			<%}%>
 	 	<% }else{ %>
			<!-- Kein zugriff auf die Seite (User ist nicht angemeldet) -->
			<%=HtmlDefaults.generateNotLoggedInMessage()%>
        <% } %>
	</div>
	<%=HtmlDefaults.generateHtmlFooter()%>
</body>

<script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
<script src="js/usercontrol.js" type="text/javascript" ></script>
</html>