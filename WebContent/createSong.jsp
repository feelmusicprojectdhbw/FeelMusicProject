<%=HtmlDefaults.generateHtmlHeader()%>

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
	   <%=HtmlDefaults.generateHtmlNavbar()%>
<div class="container">
	


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
		<label> Feelings </label> 
		</summary>
			<div>
	 <% 
      	if(Feelings.getFeelings() != null){
			for (Feeling f : Feelings.getFeelings()) {
				if(f.getId() == 1)continue;
				%><input class="orechts"type="checkbox" name="feeling" value="<%= f.getName() %>"><%= f.getName() %><br>
					<%
			}			
		}%>
		</div>
		</details>
		<details>
		<summary>
		<label class=" "> Styles </label> 
		</summary>
			<div>
	 <% 
      	if(Styles.getStyles() != null){
			for (Style s : Styles.getStyles()) {
				if(s.getId() == 1)continue;
				%><input class="orechts" type="checkbox" name="style" value="<%= s.getName() %>"><%= s.getName() %><br>
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
	</div>
</body>
<%=HtmlDefaults.generateHtmlFooter()%>


</html>