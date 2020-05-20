<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="main.*"%>
<%@page import="main.obj.*"%>
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

<title>createPlaylist</title>
</head>
<nav class="navbar navbar-expand-md navbar-dark bg-dark">
	<a class="navbar-brand" href="player.jsp">FeelMusic</a> <a
		class="nav-link nav-item text-light " href="player.jsp">Home</a> <a
		class="nav-link nav-item text-light" href="createSong.jsp">Create
		Song</a> <a class="nav-link nav-item text-light" href="createPlaylist.jsp">Create
		Playlist</a> <a class="nav-link nav-item text-light "
		href="createArtist.jsp">Create Artist</a> <a
		class="nav-link nav-item text-light" href="impressum.jsp">Impressum</a>
	<a class="nav-link nav-item text-light" href="datenschutz.jsp">Datenschutz</a>



	<button class="btn btn-outline-success px-2 px-3 mx-3 my-2 my-sm-0"">LogIn</button>

</nav>
<div class=" container ">

	<div class="treeview-animated w-20 border mx-4 my-4">
		<h6 class="pt-3 pl-3">Musicstyles</h6>
		<hr>
		<ul class="treeview-animated-list mb-3">
			
		<% 	
		if(Genres.getGenres() != null){%>
			<li class="treeview-animated-items">
			<% for (Genre g : Genres.getGenres()) { 
				%>
				<a class="closed"> <span><%= g.getName() %></span>
				<% 
	    		if(g.getSubgenres() != null){%>
	    		<ul class="nested">
	    		<%
	   			 	for(Genre g1 : g.getSubgenres()){ %>
	   			 	<li>
						<div class="treeview-animated-element">
							<i class="far  ic-w mr-1"></i><%= g1.getName() %>	       		 	
					<% if(g1.getSubgenres() != null){
						%>
						<ul class="nested">
						<%
			   			 	for(Genre g2 : g1.getSubgenres()){ %>
			   			 	<li>
								<div class="treeview-animated-element">
									<i class="far  ic-w mr-1"></i><%= g2.getName() %>
							</li>
			       		 	
							<%}%>
							</ul>
	    				<%}%>
	    				</li>
					<%}%>
					</ul>
	    		<%}%>
	    		</a>
	    	<%}%> 
	    	 </li>	
		<%}%> 
		</ul>
	<div class="form-group">
		<label for="exampleFormControlSelect2">Core Emotions</label> <select
			multiple class="form-control" id="exampleFormControlSelect2">
			<% 	
		if(Feelings.getFeelings() != null){
			for (Feeling f : Feelings.getFeelings()) {
				%><option><%= f.getName() %></option><%
			}			
		}%>
		</select>
	</div>


	<p class="checkcaption">Choose a decade</p>


	<form>
		<label for="form">From:</label> <input type="text" id="iform"
			name="iform"><br>
		<br> <label for="until">Until:</label> <input type="text"
			id="iuntil" name="iuntil"><br>
		<br> <input type="submit" value="Submit">
	</form>



</div>
</body>
</div>
<footer
	class="footer container-fluid text-center text-md-left bg-dark text-light py-2 bottom-0">
	<div class="container">
		<span class="text-muted">
			<p>&copy; Feel Music 2020 All rights reserved</p>
		</span>
	</div>
</footer>

</body>

</html>