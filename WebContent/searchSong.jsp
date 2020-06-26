<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="true"%>
<%@page import="main.*"%>
<%@page import="main.obj.*"%>
<%@page import="main.dao.*"%>
<%@page import="main.tools.*"%>
<%@page import="main.servlets.*"%>
<%=HtmlDefaults.generateHtmlHeader()%>

<title>Search Song</title>
<link rel="stylesheet" href="css/stylesongsearch.css">
</head>

<body>
     <%	User user = (User) session.getAttribute("user");%>
     <%=HtmlDefaults.generateHtmlNavbar(user)%>
	<div class="wholewrapper">
	<div class="formwrapper">
		<h2>Search for a Songname or Artist</h2>
		<form id="songcreation" class="form-signin " method="post" action="SearchSong_Servlet">		
			<p><label for=" inputSearch" ><b>Search:</b></label>	
			<input type="text" name="inputSearch" id="inputSearch " class="form-control" placeholder=" Songname " autofocus></p>
			<br>	 
			<p><label for="artist" > <b>Artist</b> </label>		
			<select class=" form-control "id="artist" name="artist">
			<% Artist[] artists = Database.getAllArtists();
	   	 	if(artists != null){
	   	 		%><option>?;1</option><%
				for (Artist a : artists) {
					if(a.getId() == 1){
						continue;
					}else{
						%><option><%= a.getName() %>;<%= a.getId() %></option><%
					}			
				}
			}%>
			</select> </p>
			<p>
			<button class=" btn btn-lg btn-primary btn-block " type="submit" onClick="processValidations()">Search</button> </p>
			<br>
			<br>
		</form>  
		</div>
		
	 <%
	 Song[] songs = (Song[])request.getAttribute("results");
	 if(songs != null){
		%>		
		<div class="resultwrapper">			
		<p>
			<h3>Results: </h3>
		</p><%
		 if(songs.length > 0){
			 for(Song s : songs){
				 %>
			 	<div class="resultsongdiv">
			 		<label><%=s.getName()%> - 
			 		<%if(s.getArtist().getLink() != null && s.getArtist().getLink() != "null"){ %>
			 			<a href="<%=s.getArtist().getLink()%>" title="<%=s.getArtist().getName()%>" target="_blank" rel="noopener noreferrer"><%=s.getArtist().getName()%></a>
			 		<%}else{%>
			 			<%=s.getArtist().getName()%>
			 		<%}
			 		  if(s.getCoArtists().getCoartists() != null && s.getCoArtists().getCoartists().length != 0){	
			 			int i = 0;
			 			for(Artist a : s.getCoArtists().getCoartists()){
				 			if(i == 0){
				 				i++;%>
				 				<%=" feat. "%> 
				 			<%}else{ %>
				 				<%=", "%> 
				 			<%}%>
				 			<%if(a.getLink() != null && a.getLink() != "null"){ %>
				 				<a href="<%=a.getLink()%>" title="<%=a.getName()%>" target="_blank" rel="noopener noreferrer"><%=a.getName()%></a>
				 			<%}else{%>
				 				<%=a.getName() %>
				 			<%}
							}
			 		  }%>					 		
			 		</label>
			 	</div>
			 <%}
			 
		 }%>
		 <label>Don't we have your favourite songs yet? Add them <a href="createSong.jsp" title="Add Song">here</a>!</label>	 			 
		 </div>
	 <%}%>	 
	 </div>
</body>
<%=HtmlDefaults.generateHtmlFooter()%>

<script>
function processValidations(){

}
</script>

</html>