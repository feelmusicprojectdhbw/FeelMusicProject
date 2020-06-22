<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="main.*"%>
<%@page import="main.obj.*"%>
<%@page import="main.dao.*"%>
<%=HtmlDefaults.generateHtmlHeader()%>

<title>createSong</title>
<link rel="stylesheet" href="css/stylesongcreation.css">
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
	   <%=HtmlDefaults.generateHtmlNavbar()%>
<div class="container">
<h2 class=" form-signin-heading ">Create Song</h2>

			<form id="songcreation" class="form-signin " method="post" action="CreateSong_Servlet">
			

			<label for=" inputSong" ><b>Song</b></label> 	
			<input type="text" name="inputSong" id="inputSong " class="form-control" placeholder=" Song " required autofocus>
	
	<div class="songmetadatawrapperdiv">
		<div class="artistsdiv">
			<label for="artist" > <b>Artist</b> </label>		
			<select class=" form-control "id="artist" name="artist">
			<% Artist[] artists = Database.getAllArtists();
     	 	if(artists != null){
			for (Artist a : artists) {
				if(a.getId() == 1)continue;
				%><option><%= a.getName() %>;<%= a.getId() %></option>
					<%
			}			
			}%>
			</select>
	
			
			
			
			
			
			<label for="coartist1 coartist2 coartist3 coartist4 " ><b>Co-Artists</b></label>
			<div class="coartistsdiv">
			<select class=" form-control " id="coartist1" name="coartist1">
	 		<% if(artists != null){
			for (Artist a : artists) {
				%><option><%= a.getName() %><span style="display: hidden;">;<%= a.getId() %></span></option>
					<%
			}			
			}%>
			</select>
			</div>
			<div class="coartistsdiv">
			<select class=" form-control " id="coartist2" name="coartist2">
	 		<% if(artists != null){
			for (Artist a : artists) {
			%><option><%= a.getName() %><span style="display: hidden;">;<%= a.getId() %></span></option>
					<%
			}			
		}%>
			</select>
			</div>
			<div class="coartistsdiv">
			<select class=" form-control " id="coartist3" name="coartist3">
			<% if(artists != null){
			for (Artist a : artists) {
				%><option><%= a.getName() %><span style="display: hidden;">;<%= a.getId() %></span></option>
					<%
			}			
		}%>
			</select>
			</div>
			<div class="coartistsdiv">
			<select class=" form-control " id="coartist4" name="coartist4">
			<% if(artists != null){
			for (Artist a : artists) {
				%><option><%= a.getName() %><span style="display: hidden;">;<%= a.getId() %></span></option>
					<%
			}			
		}%>
			</select>
			</div>
		</div>
		<div class="songmetadatadiv">	
			<label for="releaseDate"> <b>Releasedate</b></label> <br>
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
			
			<label for=" inputLanguage " ><b>Language</b></label> 
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
		<label><b>Feelings</b></label> 
	
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

			<button class=" btn btn-lg btn-primary btn-block " type="submit" onClick="processValidations()">Send</button>
			<button class=" btn btn-lg btn-primary btn-block " type="reset">Delete</button>
		</form>
	</div>

</body>
<%=HtmlDefaults.generateHtmlFooter()%>


</html>