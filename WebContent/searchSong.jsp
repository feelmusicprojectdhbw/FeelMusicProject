<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="true"%>
<%@page import="main.*"%>
<%@page import="main.obj.*"%>
<%@page import="main.dao.*"%>
<%@page import="main.tools.*"%>
<%@page import="main.servlets.*"%>
<%=HtmlDefaults.generateHtmlHeader()%>

<title>Song suchen</title>
<link rel="stylesheet" href="css/stylesongsearch.css">
</head>

<body>
     <%	User user = (User) session.getAttribute("user");%>
     <%=HtmlDefaults.generateHtmlNavbar(user)%>
     <div class="container">
	<div class="wholewrapper">
	<div class="formwrapper">
		<h2>Suche nach einem Songtitel und einem K&uumlnstler</h2>
			<p><label for=" inputSearch" ><b>Suchen:</b></label>	
			<input type="text" name="inputSearch" id="inputSearch" class="form-control" placeholder=" Songname " autofocus></p>
			<br>	 
			<p><label for="artist" > <b>K&uumlnstler</b> </label>		
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
			<button class=" btn btn-success btn-lg " onClick="searchSongs()">Suchen</button> </p>
			<br>
			<br>
		</div>
		<div id ="resultwrapper" class="resultwrapper">			
		 			 
		 </div>
	 </div>
	 </div>
	 <%=HtmlDefaults.generateHtmlFooter()%>
</body>


<script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
<script src="js/searchSong.js" type="text/javascript"></script>

</html>