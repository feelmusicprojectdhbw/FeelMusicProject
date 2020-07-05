<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="true"%>
<%@page import="main.*"%>
<%@page import="main.obj.*"%>
<%@page import="main.dao.*"%>
<%@page import="main.tools.*"%>
<%@page import="main.servlets.*"%>
<%=HtmlDefaults.generateHtmlHeader()%>
<title>createPlaylist</title>

<link rel="stylesheet" href="css/styleplaylistcreation.css">
<script src="js/playlistCreation.js" type="text/javascript" ></script>
</head>
<body>
     <%	User user = (User) session.getAttribute("user");%>
     <%=HtmlDefaults.generateHtmlNavbar(user)%>
	
<div class="container">
	<form id="playlistcreation" class=" form-signin " method="post" action="FeelMusic">
  		<!-- <h4 class="pt-3 pl-3"><b>Genres</b></h4> -->
  		<label for="exampleFormControlSelect2">Genres</label>
 		<hr>		
		<div class="genrediv">
		<% 	
		if(Genres.getGenres() != null){%>
			<table>
			<tbody>
			<tr>
			<%for (Genre g : Genres.getGenres()) {%>
			<td class="genretd">
			<ul class="treeview-animated-list mb-3" id="genreTreeUlCheckbox">

				<% String mainG = g.getName().replace(" ", "");
				%>
				<li class="treeview-animated-items">
					<input class="<%= mainG %>radio" type="radio" value="#6c757d" checked hidden>
					<input class="<%= mainG %>radio" type="radio" value="green" hidden>
					<input class="<%= mainG %>radio" type="radio" value="red" hidden>
					<div id="<%= mainG %>div">
						<genre id="<%= mainG %>btn" name="genre" class="btn btn-secondary" onclick="doGenre('<%= mainG %>')" value="<%= mainG %>" selection="0"><%= g.getName() %></genre>
						<% if(g.getSubgenres() != null){%>
	    				<ul class="nested">
    					<% for(Genre g1 : g.getSubgenres()){ 
	    					String secondG = g1.getName().replace(" ", "");%>
	   			 			<li class="genreli">
	   			 			<input class="<%= secondG %>radio" type="radio" value="#6c757d" checked hidden>
							<input class="<%= secondG %>radio" type="radio" value="green" hidden>
							<input class="<%= secondG %>radio" type="radio" value="red" hidden>
							<div id="<%= secondG %>div">
	   			 			<genre id="<%= secondG %>btn" name="genre" class="btn btn-secondary" onclick="doGenre('<%= secondG %>')" value="<%= secondG %>" selection="0"><%= g1.getName()%></genre>	       		 	
							<% if(g1.getSubgenres() != null){
								%>
								<ul class="nested">
								<%
					   			 	for(Genre g2 : g1.getSubgenres()){ 
					   			 		String thirdG = g2.getName().replace(" ", "");%>
			   			 			<li class="genreli">
			   			 				<input class="<%= thirdG %>radio" type="radio" value="#6c757d" checked hidden>
										<input class="<%= thirdG %>radio" type="radio" value="green" hidden>
										<input class="<%= thirdG %>radio" type="radio" value="red" hidden>
										<div id="<%= thirdG %>div">
			   			 					<genre id="<%= thirdG %>btn" name="genre" class="btn btn-secondary" onclick="doGenre('<%= thirdG %>')" value="<%= thirdG %>" selection="0"><%= g2.getName()%></genre>
			   			 				<% if(g2.getSubgenres() != null){
											%>
											<ul class="nested">
											<%
								   			 	for(Genre g3 : g2.getSubgenres()){ 
								   			 		String fourthG = g3.getName().replace(" ", "");%>
						   			 			<li class="genreli">
						   			 				<input class="<%= fourthG %>radio" type="radio" value="#6c757d" checked hidden>
													<input class="<%= fourthG %>radio" type="radio" value="green" hidden>
													<input class="<%= fourthG %>radio" type="radio" value="red" hidden>
													<div id="<%= fourthG %>div">
						   			 					<genre id="<%= fourthG %>btn" name="genre" class="btn btn-secondary" onclick="doGenre('<%= fourthG %>')" value="<%= fourthG %>" selection="0"><%= g3.getName()%></genre>
						   			 				</div>
												</li>		       		 	
												<%}%>
											</ul>
											</div>
					    					<%}%>
			   			 				
									</li>		       		 	
									<%}%>
								</ul>	
								</div>						
		    					<%}%>
		    				</li>
						<%}%>
					</ul>
					</div>
	    		<%}%>
	    		</li>
	    		</ul>
	    		</td>
	    		
	    	<%} %>
	    	</tr>
	    	</tbody>
			</table>
		<%}%> 
		<input type="hidden" name="selectedGenres" id="selectedGenres" />
		<input type="hidden" name="blockedGenres" id="blockedGenres" />
		</div>
	
	<div class="form-group">
 		<hr>
		<label for="exampleFormControlSelect2">Feelings</label> 
		<div class="scrollContainer">
		<table class="tlayout">
			<tbody>
			<tr>
		<% 	
		if(Feelings.getFeelings() != null){
			int i = 0;
			for (Feeling f : Feelings.getFeelings()) {
				if(f.getId() == 1)continue;
				String feel = f.getName().replace(" ", "");
				if(i < ((Feelings.getFeelings().length / 5) + ((Feelings.getFeelings().length%5==0)?0:1))){
				i += 1;%>
				<td class="table-elements">
			 	<div id="<%= f.getName() %>div">
					<input class="<%= feel %>radio" type="radio" value="#6c757d" checked hidden>
					<input class="<%= feel %>radio" type="radio" value="green" hidden>
					<input class="<%= feel %>radio" type="radio" value="red" hidden>
			 		<div id="<%= feel %>btn" name="feeling" class="btn btn-secondary" onclick="doFeeling('<%= feel %>')" value="<%= feel %>" selection="0"><%= f.getName() %></div>
			 	</div>
			 	</td><%
				}else{
					i = 1;%>
					</tr>
					<tr>
					<td class="table-elements">
					<div id="<%= f.getName() %>div">
					<input class="<%= feel %>radio" type="radio" value="#6c757d" checked hidden>
					<input class="<%= feel %>radio" type="radio" value="green" hidden>
					<input class="<%= feel %>radio" type="radio" value="red" hidden>
			 		<div id="<%= feel %>btn" name="feeling" class="btn btn-secondary" onclick="doFeeling('<%= feel %>')" value="<%= feel %>" selection="0"><%= f.getName() %></div>
			 		</div>
			 		</td>
				<%
				}
			}			
		}%>
		</tr>
		</tbody>
			
		</table>	
			
		</div>
		<input type="hidden" name="selectedFeelings" id="selectedFeelings" />
		<input type="hidden" name="blockedFeelings" id="blockedFeelings" />
	</div>

	<div class="form-group">
 		<hr>
		<details>
		<summary>
		<label for="exampleFormControlSelect3">Style</label> 
		</summary>
		<div class="scrollContainer">
		<table class="tlayout">
			<tbody>
				<tr>	
			<%
			if(Styles.getStyles()!= null) {
				int i = 0;
				for (Style s : Styles.getStyles()) {
					String style = s.getName().replace(" ", "");
					if(i < ((Styles.getStyles().length / 5) + ((Styles.getStyles().length%5==0)?0:1))){
						i += 1;%>
						<td class="table-elements">
				 	<div id="<%= s.getName() %>div">
						<input class="<%= style %>radio" type="radio" value="#6c757d" checked hidden>
						<input class="<%= style %>radio" type="radio" value="green" hidden>
						<input class="<%= style %>radio" type="radio" value="red" hidden>
				 		<div id="<%= style %>btn" name="style" class="btn btn-secondary" onclick="doStyle('<%= style %>')" value="<%= style %>" selection="0"><%= s.getName() %></div>
				 	</div>
				 	</td>
				<%}else{
					i = 1;%>
					</tr>
					<tr>
					<td class="table-elements">
					<div id="<%= s.getName()%> ">
						<input class="<%= style %>radio" type="radio" value="#6c757d" checked hidden>
						<input class="<%= style %>radio" type="radio" value="green" hidden>
						<input class="<%= style %>radio" type="radio" value="red" hidden>
				 		<div id="<%= style %>btn" name="style" class="btn btn-secondary" onclick="doStyle('<%= style %>')" value="<%= style %>" selection="0"><%= s.getName() %></div>
				 	</div>
					</td>
					<%
				  	}
				}	
			}
			%>
			</tr>
		</tbody>
			
		</table>
		</div>
		<input type="hidden" name="selectedStyles" id="selectedStyles" />
		<input type="hidden" name="blockedStyles" id="blockedStyles" />
		</details>
	</div>

	<div class="form-group">
 	<hr>		
	<details>
	<summary>
	<label for="exampleFromControlSelect4">Language</label>
	</summary>	
	<div id="smallContainer" >
		<table class="tlayout">
			<tbody>
				<tr>	
				<%
				if(Languages.getLanguages()!=null) {
					int i = 0;
					for (Language l : Languages.getLanguages()) {
						String language = l.getName().replace(" ", "");
						if(i < ((Languages.getLanguages().length / 2) + ((Languages.getLanguages().length%2==0)?0:1))){
							i += 1;%>
					<td class="table-elements">
					 	<div id="<%= l.getName() %>div">
							<input class="<%= language %>radio" type="radio" value="#6c757d" checked hidden>
							<input class="<%= language %>radio" type="radio" value="green" hidden>
							<input class="<%= language %>radio" type="radio" value="red" hidden>
					 		<div id="<%= language %>btn" name="language" class="btn btn-secondary" onclick="doLanguage('<%= language %>')" value="<%= language %>" selection="0"><%= l.getName() %></div>
					 	</div>
				 	</td>
					 	<%}else{
						i = 1;%>						
				</tr>
				<tr>
					<td class="table-elements">
					 	<div id="<%= l.getName() %>div">
							<input class="<%= language %>radio" type="radio" value="#6c757d" checked hidden>
							<input class="<%= language %>radio" type="radio" value="green" hidden>
							<input class="<%= language %>radio" type="radio" value="red" hidden>
					 		<div id="<%= language %>btn" name="language" class="btn btn-secondary" onclick="doLanguage('<%= language %>')" value="<%= language %>" selection="0"><%= l.getName() %></div>
					 	</div>
				 	</td>
						<%
					  	}
					}	
				}
				%>
				</tr>
			</tbody>
			
		</table>
		</div>
		<input type="hidden" name="selectedLanguages" id="selectedLanguages" />
		<input type="hidden" name="blockedLanguages" id="blockedLanguages" />
	
	</details>
	</div>

	<p class="checkcaption">Choose a decade</p>
	
			<label for="form">From:</label> 
			<input  class="metadata-control"type="date" id="fromdate" name="fromdate"> 
			<br><br> 
			<label for="until">Until:</label> 
			<input  class="metadata-control" type="date" id="untildate" name="untildate">
			<br><br> 
			
			<button class=" btn btn-block btn-outline-success btn-lg " type="submit" onClick="processValidations()">Send</button>
	</form>



</div>
<%=HtmlDefaults.generateHtmlFooter()%>
</body>

</html>