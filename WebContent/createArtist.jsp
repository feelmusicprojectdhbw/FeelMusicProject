<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="true"%>
<%@page import="main.*"%>
<%@page import="main.obj.*"%>
<%@page import="main.dao.*"%>
<%@page import="main.tools.*"%>
<%@page import="main.servlets.*"%>
<%=HtmlDefaults.generateHtmlHeader()%>
        <title>createArtist</title>
    </head>
    
    <body>
     <%	User user = (User) session.getAttribute("user");%>
     <%=HtmlDefaults.generateHtmlNavbar(user)%>
        <div class="container py-2">

     <form class=" form-signin " method="post" action="CreateArtist">
      <select class="form-control" name="artistToDelete">  
      <% Artist[] artists = Database.getAllArtists();
      	if(artists != null){
			for (Artist a : artists) {
				%><option value="<%= a.getId() %>"><%= a.getName() %>;<%= a.getId() %></option><%
			}			
		}%> 
        </select>
      <h2 class=" form-signin-heading ">Create Artists</h2>
      <label for=" inputArtist " class=" sr-only ">Artist</label>
      <input type="text" name = "inputArtist" id=" inputArtist " class=" form-control " placeholder=" Artist " required autofocus>

      <label for=" inputLink " class=" sr-only ">Link of the artist</label>
      <input type="url" name = "inputLink" id=" inputLink " class=" form-control " placeholder=" Link " required>

      <input type="radio" name="createOrDelete" class=" form-control " value="create" checked/>Create
      <input type="radio" name="createOrDelete" class=" form-control "value="delete" />Delete
      <button class=" btn btn-block btn-outline-success btn-lg " type="submit">Send</button>
     </form>
 
     </div>
    <%=HtmlDefaults.generateHtmlFooter()%>
    </body>
</html>