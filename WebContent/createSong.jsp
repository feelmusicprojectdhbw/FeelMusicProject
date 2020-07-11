<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="true"%>
<%@page import="main.*"%>
<%@page import="main.obj.*"%>
<%@page import="main.dao.*"%>
<%@page import="main.tools.*"%>
<%@page import="main.servlets.*"%>
<%=HtmlDefaults.generateHtmlHeader()%>

<title>createSong</title>
<link rel="stylesheet" href="css/stylesongcreation.css">
<script type="text/javascript" src="js/songCreation.js"></script>
</head>

<body>
     <%	User user = (User) session.getAttribute("user");%>
     <%=HtmlDefaults.generateHtmlNavbar(user)%>
<div class="container">
<%if(user != null){ %>
	<h2 class=" form-signin-heading ">Song hinzuf&uumlgen</h2>

			<form id="songcreation" class="form-signin " method="post" action="CreateSong">
			

			<label for=" inputSong" ><b>Song</b></label> 	
			<input type="text" name="inputSong" id="inputSong " class="form-control" placeholder=" Song " required autofocus>
	
	<div class="songmetadatawrapperdiv">
		<div class="artistsdiv">
			<label for="artist" > <b>K&uumlnstler</b> </label>		
			<select class=" form-control "id="artist" name="artist">
		<% 	Artist[] artists = null; 
			if(user.getUsertype().getId() == 1 || user.getUsertype().getId() == 3 ||user.getUsertype().getId() == 4){
				artists = Database.getAllArtists();
	     	}else{
	     		artists = Database.getNoLinkedArtists();
	     	}
     	 	if(artists != null){
     	 		
				for (Artist a : artists) {
					if(a.getId() == 1)continue;
					%><option><%= a.getName() %>;<%= a.getId() %></option>
						<%
				}			
			}%>
			</select>		
			
			<label for="coartist1 coartist2 coartist3 coartist4 " ><b>Co-Produzenten</b></label>
			<div class="coartistsdiv">
			<select class=" form-control " id="coartist1" name="coartist1">
	 		<% if(artists != null){
			for (Artist a : artists) {
				%><option><%= a.getName() %>;<%= a.getId() %></option>
					<%
			}			
			}%>
			</select>
			</div>
			<div class="coartistsdiv">
			<select class=" form-control " id="coartist2" name="coartist2">
	 		<% if(artists != null){
			for (Artist a : artists) {
			%><option><%= a.getName() %>;<%= a.getId() %></option>
					<%
			}			
		}%>
			</select>
			</div>
			<div class="coartistsdiv">
			<select class=" form-control " id="coartist3" name="coartist3">
			<% if(artists != null){
			for (Artist a : artists) {
				%><option><%= a.getName() %>;<%= a.getId() %></option>
					<%
			}			
		}%>
			</select>
			</div>
			<div class="coartistsdiv">
			<select class=" form-control " id="coartist4" name="coartist4">
			<% if(artists != null){
			for (Artist a : artists) {
				%><option><%= a.getName() %>;<%= a.getId() %></option>
					<%
			}			
		}%>
			</select>
			</div>
		</div>
		<div class="songmetadatadiv">	
			<label for="releaseDate"> <b>Erscheinungsdatum</b></label> <br>
			<input type="date" id="releaseDate" name="releaseDate" class=" metadata-control " required> 
			
			<label for="genre" ><b>Genre</b></label> 
			<select class=" metadata-control " id="genre" name="inputGenre">
			<% for (Genre g : Genres.getGenres()) { 
				%><option><%= g.getName() %></option>
				<%	
	    			if(g.getSubgenres() != null){    		
	   			 		for(Genre g1 : g.getSubgenres()){ 
	   			 		%><option><%= g1.getName() %></option>
						<%
							if(g1.getSubgenres() != null){
					   			for(Genre g2 : g1.getSubgenres()){
					   				%><option><%= g2.getName() %></option>
									<%
								}
			    			}
			    				
						}
	   			 	}
	    		}%> 
			</select>
			
			
			<label for=" inputLabel " ><b>Label</b></label> 
			<select class=" metadata-control " name="inputLabel">
			 <% MusicLabel[] labels = Database.getAllLabels();
			 if(labels != null){
					for (MusicLabel l : labels) {
						%><option><%= l.getName() %>;<%= l.getId() %></option>
							<%
					}			
				}%>
			</select>
			
			<label for=" inputLanguage " ><b>Sprache</b></label> 
			<select class=" metadata-control " name = inputLanguage>
			 <% 
			 if(Languages.getLanguages() != null && Languages.getLanguages().length > 0){
					for (Language l : Languages.getLanguages()) {
						%><option><%= l.getName() %></option>
							<%
					}			
				}%>
			</select>
		</div>	
	</div>
	<div class="linksdiv">	
		<label><b>Gef&uumlhle</b></label> 
	
		<div class="tlayoutdiv">
			<table class="tlayout">
			<tbody>
				<tr>		
			<% if(Feelings.getFeelings() != null){
				int i = 0;
				for (Feeling f : Feelings.getFeelings()) {
					if(f.getId() == 1)continue;
					if(i < ((Feelings.getFeelings().length / 5) + ((Feelings.getFeelings().length%5==0)?0:1))){
					i += 1;%>
					<td class="table-elements"><input class="selectionboxes" type="checkbox" name="feeling" value="<%= f.getName() %>"><%= f.getName() %><br></td>
					<%}else{
					i = 1;%>
					</tr>
					<tr>
					<td class="table-elements"><input class="selectionboxes" type="checkbox" name="feeling" value="<%= f.getName() %>"><%= f.getName() %><br></td>
					<%
				  	}
				}			
			}%>	</tr>
			</tbody>
			
			</table>
		</div>	
		<div class="tlayoutdiv">
		<label><b>Styles</b></label> 	
				 
			<table class="tlayout">
			<tbody>
				<tr>		
			<% if(Styles.getStyles() != null){
				int i = 0;
				for (Style s : Styles.getStyles()) {
					if(s.getId() == 1)continue;
					if(i < ((Styles.getStyles().length / 5) + ((Styles.getStyles().length%5==0)?0:1))){
					i += 1;%>
					<td class="table-elements"><input class="selectionboxes" type="checkbox" name="style" value="<%= s.getName() %>"><%= s.getName() %><br></td>
					<%}else{
					i = 1;%>
					</tr>
					<tr>
					<td class="table-elements"><input class="selectionboxes" type="checkbox" name="style" value="<%= s.getName() %>"><%= s.getName() %><br></td>
					<%
				  	}
				}			
			}%>	</tr>
			</tbody>
			
			</table>
		</div>	
	</div>
			<input type="hidden" name="selectedFeelings" id="selectedFeelings" />
			<input type="hidden" name="selectedStyles" id="selectedStyles" />
			
			<label for=" inputytLink " ><b>Youtube Link</b></label> 
			<input type=" url " name="inputYtLink" id=" inputLink " class=" form-control " placeholder=" ytLink "> 
			
			<label for=" inputSfLink " ><b>Spotify Link</b></label> 
			<input type=" url " name="inputSfLink" id=" inputLink " class=" form-control " placeholder=" sfLink ">
			
			<label for=" inputScLink " ><b>Soundcloud Link</b></label>
			<input type=" url " name="inputScLink" id=" inputLink " class=" form-control " placeholder=" scLink ">

			<button class="  btn btn-block btn-outline-success btn-lg " type="submit" onClick="processValidations()">Senden</button>
		</form>
		<%}else{%>
			<%=HtmlDefaults.generateNotLoggedInMessage()%>
		<%}%>
	</div>

<%=HtmlDefaults.generateHtmlFooter()%>
</body>

</html>