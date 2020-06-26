<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="true"%>
<%@page import="main.*"%>
<%@page import="main.obj.*"%>
<%@page import="main.dao.*"%>
<%@page import="main.tools.*"%>
<%@page import="main.servlets.*"%>
<%=HtmlDefaults.generateHtmlHeader()%>
<title>createPlaylist</title>

<script>
function doGenre(genre) {
	var radios = document.getElementsByClassName(genre + "radio");
	var newChecked = -1;
	for(var index = 0; index < radios.length; index++){
	if(radios[index].checked == true){
		radios[index].checked = false;
		newChecked = index + 1;
		break;
		}
	} 
	if(newChecked == radios.length){
		newChecked = 0;
	}
	radios[newChecked].checked = true;
	document.getElementById(genre + "btn").style.backgroundColor = radios[newChecked].getAttribute("value");
	document.getElementById(genre + "btn").setAttribute("selection", newChecked);
	
	var subs = document.getElementById(genre + "div").getElementsByTagName("genre");
	for(var i = 0; i < subs.length; i++){
	doSubgenres(subs[i].getAttribute("value"), newChecked);
	}
}

function doSubgenres(genre, newChecked){
	var radios = document.getElementsByClassName(genre + "radio");
	for(var index = 0; index < radios.length; index++){
		radios[index].checked = false;
	}
	radios[newChecked].checked = true;
	document.getElementById(genre + "btn").style.backgroundColor = radios[newChecked].getAttribute("value");
	document.getElementById(genre + "btn").setAttribute("selection", newChecked);
}

function processValidations(){
	processGenres();
	processFeelings();
	processStyles();
	processLanguages();
}

function processGenres()
{
    var genres = document.getElementsByName("genre");
    var selectedGenres = [];
    var blockedGenres = [];
    for (var i = 0; i < genres.length; i++) {
        if (genres[i].getAttribute("selection") == 1) {
        	selectedGenres.push(genres[i].textContent);
        }else if (genres[i].getAttribute("selection") == 2) {
        	blockedGenres.push(genres[i].textContent);
        }
    }
    var hiddenSelectedGenres = document.getElementById("selectedGenres");
    hiddenSelectedGenres.value = selectedGenres.join(";");
    var hiddenBlockedGenres = document.getElementById("blockedGenres");
    hiddenBlockedGenres.value = blockedGenres.join(";");
}

function doFeeling(feeling) {
	var radios = document.getElementsByClassName(feeling + "radio");
	var newChecked = -1;
	for(var index = 0; index < radios.length; index++){
	if(radios[index].checked == true){
		radios[index].checked = false;
		newChecked = index + 1;
		break;
		}
	} 
	if(newChecked == radios.length){
		newChecked = 0;
	}
	radios[newChecked].checked = true;
	document.getElementById(feeling + "btn").style.backgroundColor = radios[newChecked].getAttribute("value");
	document.getElementById(feeling + "btn").setAttribute("selection", newChecked);
}

function processFeelings()
{
    var feelings = document.getElementsByName("feeling");
    var selectedFeelings = [];
    var blockedFeelings = [];
    for (var i = 0; i < feelings.length; i++) {
        if (feelings[i].getAttribute("selection") == 1) {
        	selectedFeelings.push(feelings[i].textContent);
        }else if (feelings[i].getAttribute("selection") == 2) {
        	blockedFeelings.push(feelings[i].textContent);
        }
    }
    var hiddenSelectedFeelings = document.getElementById("selectedFeelings");
    hiddenSelectedFeelings.value = selectedFeelings.join(";");
    var hiddenBlockedFeelings = document.getElementById("blockedFeelings");
    hiddenBlockedFeelings.value = blockedFeelings.join(";");
}

function doStyle(style) {
	var radios = document.getElementsByClassName(style + "radio");
	var newChecked = -1;
	for(var index = 0; index < radios.length; index++){
	if(radios[index].checked == true){
		radios[index].checked = false;
		newChecked = index + 1;
		break;
		}
	} 
	if(newChecked == radios.length){
		newChecked = 0;
	}
	radios[newChecked].checked = true;
	document.getElementById(style + "btn").style.backgroundColor = radios[newChecked].getAttribute("value");
	document.getElementById(style + "btn").setAttribute("selection", newChecked);
}

function processStyles()
{
    var styles = document.getElementsByName("style");
    var selectedStyles = [];
    var blockedStyles = [];
    for (var i = 0; i < styles.length; i++) {
        if (styles[i].getAttribute("selection") == 1) {
        	selectedStyles.push(styles[i].textContent);
        }else if (styles[i].getAttribute("selection") == 2) {
        	blockedStyles.push(styles[i].textContent);
        }
    }
    var hiddenSelectedStyles = document.getElementById("selectedStyles");
    hiddenSelectedStyles.value = selectedStyles.join(";");
    var hiddenBlockedStyles = document.getElementById("blockedStyles");
    hiddenBlockedStyles.value = blockedStyles.join(";");
}

function doLanguage(language) {
	var radios = document.getElementsByClassName(language + "radio");
	var newChecked = -1;
	for(var index = 0; index < radios.length; index++){
	if(radios[index].checked == true){
		radios[index].checked = false;
		newChecked = index + 1;
		break;
		}
	} 
	if(newChecked == radios.length){
		newChecked = 0;
	}
	radios[newChecked].checked = true;
	document.getElementById(language + "btn").style.backgroundColor = radios[newChecked].getAttribute("value");
	document.getElementById(language + "btn").setAttribute("selection", newChecked);
}

function processLanguages()
{
    var languages = document.getElementsByName("language");
    var selectedLanguages = [];
    var blockedLanguages = [];
    for (var i = 0; i < languages.length; i++) {
        if (languages[i].getAttribute("selection") == 1) {
        	selectedLanguages.push(languages[i].textContent);
        }else if (languages[i].getAttribute("selection") == 2) {
        	blockedLanguages.push(languages[i].textContent);
        }
    }
    var hiddenSelectedLanguages = document.getElementById("selectedLanguages");
    hiddenSelectedLanguages.value = selectedLanguages.join(";");
    var hiddenBlockedLanguages = document.getElementById("blockedLanguages");
    hiddenBlockedLanguages.value = blockedLanguages.join(";");
}
</script>
<link rel="stylesheet" href="css/styleplaylistcreation.css">
</head>
<body>
     <%	User user = (User) session.getAttribute("user");%>
     <%=HtmlDefaults.generateHtmlNavbar(user)%>
	
<div class=" container ">
	<form id="playlistcreation" class=" form-signin " method="post" action="FeelMusic_Servlet">
  		<h4 class="pt-3 pl-3"><b>Genres</b></h4>
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
			
			<button class=" btn btn-lg btn-primary btn-block " type="submit" onClick="processValidations()">Send</button>
	</form>



</div>
<%=HtmlDefaults.generateHtmlFooter()%>

</body>

</html>