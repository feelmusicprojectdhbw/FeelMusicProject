<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="main.*"%>
<%@page import="main.obj.*"%>
<%@page import="main.dao.*"%>
<!doctype html>
<html lang="de">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<!--own css-->
	<link rel="stylesheet" href="css/style.css">

<title>createSong</title>
<script>
	function processValidations(){
		processFeelings();
		processStyles();
	}

    function processFeelings()
    {
        var feelings = document.getElementsByName("feeling");
        var selectedFeelings = [];
        for (var i = 0; i < feelings.length; i++) {
            if (feelings[i].checked) {
            	selectedFeelings.push(feelings[i].value);
            }
        }
        
        var hiddenSelectedFeelings = document.getElementById("selectedFeelings");
        hiddenSelectedFeelings.value = selectedFeelings.join(";");
    }
    
    function processStyles()
    {
        var styles = document.getElementsByName("style");
        var selectedStyles = [];
        for (var i = 0; i < styles.length; i++) {
            if (styles[i].checked) {
            	selectedStyles.push(styles[i].value);
            }
        }
        
        var hiddenSelectedStyles = document.getElementById("selectedStyles");
        hiddenSelectedStyles.value = selectedStyles.join(";");
        
    }
</script>
</head>

<body>

	<nav class="navbar navbar-expand-md navbar-dark bg-dark">
		<a class="navbar-brand" href="player.jsp">FeelMusic</a> <a
			class="nav-link nav-item text-light " href="player.jsp">Home</a> <a
			class="nav-link nav-item text-light" href="createSong.jsp">Create
			Song</a> <a class="nav-link nav-item text-light"
			href="createPlaylist.jsp">Create Playlist</a> <a
			class="nav-link nav-item text-light " href="createArtist.jsp">Create
			Artist</a> <a class="nav-link nav-item text-light" href="impressum.jsp">Impressum</a>
		<a class="nav-link nav-item text-light" href="datenschutz.jsp">Datenschutz</a>



		<button class="btn btn-outline-success px-2 px-3 mx-3 my-2 my-sm-0"">LogIn</button>

	</nav>
		<form id="songcreation" class=" form-signin " method="post" action="CreateSong_Servlet">
			<h2 class=" form-signin-heading ">Create Song</h2>

			<label for=" inputSong " class=" sr-only ">Song</label> 
			<input type="text" name="inputSong" id=" inputSong " class=" form-control " placeholder=" Song " required autofocus>
			<label class=" "> Artist 
			<select class=" form-control " name="artist">
	 <% Artist[] artists = Database.getAllArtists();
      	if(artists != null){
			for (Artist a : artists) {
				if(a.getId() == 1)continue;
				%><option><%= a.getName() %>;<%= a.getId() %></option>
					<%
			}			
		}%>
			</select>
			</label> 
			<label> Co-Artists
			<select class=" form-control " name="coartist1">
	 <% if(artists != null){
			for (Artist a : artists) {
				%><option><%= a.getName() %><span style="display: hidden;">;<%= a.getId() %></span></option>
					<%
			}			
		}%>
			</select>
			<select class=" form-control " name="coartist2">
	 <% if(artists != null){
			for (Artist a : artists) {
				%><option><%= a.getName() %><span style="display: hidden;">;<%= a.getId() %></span></option>
					<%
			}			
		}%>
			</select>
			<select class=" form-control " name="coartist3">
	 <% if(artists != null){
			for (Artist a : artists) {
				%><option><%= a.getName() %><span style="display: hidden;">;<%= a.getId() %></span></option>
					<%
			}			
		}%>
			</select>
			<select class=" form-control " name="coartist4">
	 <% if(artists != null){
			for (Artist a : artists) {
				%><option><%= a.getName() %><span style="display: hidden;">;<%= a.getId() %></span></option>
					<%
			}			
		}%>
			</select>
			</label> <label for=" releaseDate " class=" sr-only ">Releasedate</label> 
			
			<input type="date" id="releaseDate" name="releaseDate" required> 
			
			<label for=" genre " class=" sr-only ">Genre</label> 
			<select class=" form-control " name="inputGenre">
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
			<label for=" inputLabel " class=" sr-only ">Label</label> 
			<select class=" form-control " name="inputLabel">
			 <% MusicLabel[] labels = Database.getAllLabels();
			 if(labels != null){
					for (MusicLabel l : labels) {
						%><option><%= l.getName() %>;<%= l.getId() %></option>
							<%
					}			
				}%>
			</select>
			
			<label for=" inputLanguage " class=" sr-only ">Language</label> 
			<select class=" form-control " name = inputLanguage>
			 <% 
			 if(Languages.getLanguages() != null && Languages.getLanguages().length > 0){
					for (Language l : Languages.getLanguages()) {
						%><option><%= l.getName() %></option>
							<%
					}			
				}%>
			</select>
			
		<details>
		<summary>
		<label class=" "> Feelings </label> 
		</summary>
			<div>
	 <% 
      	if(Feelings.getFeelings() != null){
			for (Feeling f : Feelings.getFeelings()) {
				if(f.getId() == 1)continue;
				%><input type="checkbox" name="feeling" value="<%= f.getName() %>"><%= f.getName() %><br>
					<%
			}			
		}%>
		</div>
		</details>
		<details>
		<summary>
		<label class=" "> Styles </label> 
		</summary>
			<div class="orechts">
	 <% 
      	if(Styles.getStyles() != null){
			for (Style s : Styles.getStyles()) {
				if(s.getId() == 1)continue;
				%><input type="checkbox" name="style" value="<%= s.getName() %>"><%= s.getName() %><br>
					<%
			}			
		}%>
		</div>
		</details>
			<input type="hidden" name="selectedFeelings" id="selectedFeelings" />
			<input type="hidden" name="selectedStyles" id="selectedStyles" />
			
			<label for=" inputytLink " class=" sr-only ">Youtube Link</label> 
			<input type=" url " name="inputYtLink" id=" inputLink " class=" form-control " placeholder=" ytLink "> 
			
			<label for=" inputSfLink " class=" sr-only ">Spotify Link</label> 
			<input type=" url " name="inputSfLink" id=" inputLink " class=" form-control " placeholder=" sfLink ">
			
			<label for=" inputScLink " class=" sr-only ">Soundcloud Link</label>
			<input type=" url " name="inputScLink" id=" inputLink " class=" form-control " placeholder=" scLink ">

			<button class=" btn btn-lg btn-primary btn-block " type="submit" onClick="processValidations()">Send</button>
			<button class=" btn btn-lg btn-primary btn-block " type="reset">Delete</button>
		</form>
</body>

<footer
	class="footer container-fluid text-center text-md-left bg-dark text-light py-2 bottom-0">
	<div class="container">
		<span class="text-muted">
			<p>&copy; Feel Music 2020 All rights reserved</p>
		</span>
	</div>
</footer>

</html>