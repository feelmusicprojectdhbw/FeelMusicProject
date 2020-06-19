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
    
        <title>createLabel</title>
    </head>
    
    <body>
      <%=HtmlDefaults.generateHtmlHeader()%>
<div class=" container ">
      <form class=" form-signin " method="post" action="CreateLabel_Servlet">
      <select name="labelToDelete">  
      <% MusicLabel[] labels = Database.getAllLabels();
      	if(labels != null){
			for (MusicLabel a : labels) {
				%><option><%= a.getName() %>;<%= a.getId() %></option><%
			}			
		}%> 
        </select>
        <h2 class=" form-signin-heading ">Create Label</h2>
        <label for=" inputLabel" class=" sr-only "> Label</label>
        <input type=" text " name="inputLabel" id=" inputLabel " class=" form-control " placeholder=" Label " required autofocus>
  
        <label for=" inputLink " class=" sr-only ">Link of the label</label>
        <input type=" url " name="inputLink" id=" inputLink " class=" form-control " placeholder=" Link " required>
  
         
      
      <input type="radio" name="createOrDelete" class=" form-control " value="create" checked/>Create
      <input type="radio" name="createOrDelete" class=" form-control "value="delete" />Delete
      <button class=" btn btn-lg btn-primary btn-block " type=" submit ">Send</button>
    </form>
  
    <footer class="footer container-fluid text-center text-md-left bg-dark text-light py-2 bottom-0">
      <div class="container">
          <span class="text-muted"> <p>&copy; Feel Music 2020    All rights reserved</p></span>
      </div>
  </footer>

</body>

</html>