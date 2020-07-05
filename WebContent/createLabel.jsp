<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="true"%>
<%@page import="main.*"%>
<%@page import="main.obj.*"%>
<%@page import="main.dao.*"%>
<%@page import="main.tools.*"%>
<%@page import="main.servlets.*"%>
<%=HtmlDefaults.generateHtmlHeader()%>
        <title>createLabel</title>
	</head>
    
	<body>
     	<% User user = (User) session.getAttribute("user");%>
     	<%=HtmlDefaults.generateHtmlNavbar(user)%>
		<div class="container">
			<form class=" form-signin " method="post" action="CreateLabel">
				<h2 class=" form-signin-heading ">Create Label</h2>
				<label for=" inputLabel" class=" sr-only "> Label</label>
				<input type="text" name="inputLabel" id=" inputLabel " class=" form-control " placeholder=" Label " required autofocus>
  
				<label for=" inputLink " class=" sr-only ">Link of the label</label>
				<input type="url" name="inputLink" id=" inputLink " class=" form-control " placeholder=" Link " required>
				<button class="  btn btn-block btn-outline-success btn-lg " type="submit">Send</button>
   			</form>
		</div>  
    	<%=HtmlDefaults.generateHtmlFooter()%>
	</body>

</html>