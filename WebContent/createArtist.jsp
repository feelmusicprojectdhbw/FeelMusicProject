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
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    
        <title>createArtist</title>
    </head>
    
    <body>
        <%=HtmlDefaults.generateHtmlHeader()%>
        <div class="container py-2">

     <form class=" form-signin " method="post" action="CreateArtist_Servlet">
      <select name="artistToDelete">  
      <% Artist[] artists = Database.getAllArtists();
      	if(artists != null){
			for (Artist a : artists) {
				%><option><%= a.getName() %>;<%= a.getId() %></option><%
			}			
		}%> 
        </select>
      <h2 class=" form-signin-heading ">Create Artists</h2>
      <label for=" inputArtist " class=" sr-only ">Artist</label>
      <input type=" text " name = "inputArtist" id=" inputArtist " class=" form-control " placeholder=" Artist " required autofocus>

      <label for=" inputLink " class=" sr-only ">Link of the artist</label>
      <input type=" url " name = "inputLink" id=" inputLink " class=" form-control " placeholder=" Link " required>

      <input type="radio" name="createOrDelete" class=" form-control " value="create" checked/>Create
      <input type="radio" name="createOrDelete" class=" form-control "value="delete" />Delete
      <button class=" btn btn-lg btn-primary btn-block " type=" submit ">Send</button>
     </form>
 
     </div>
    </body>
  <footer class="footer container-fluid text-center text-md-left bg-dark text-light py-2 bottom-0">
    <div class="container">
        <span class="text-muted"> <p>&copy; Feel Music 2020    All rights reserved</p></span>
    </div>
</footer>



</html>