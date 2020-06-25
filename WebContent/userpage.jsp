<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="true"%>
<%@page import="main.*"%>
<%@page import="main.obj.*"%>
<%@page import="main.dao.*"%>
<%=HtmlDefaults.generateHtmlHeader()%>
  <%	User user = (User) session.getAttribute("user");
  		if(user != null){%>
        	<title>User: <%=user.getUsername() %></title>
        <%}else{%>
        	 <title>Error!</title>
        <%}%>
		<link rel="stylesheet" href="css/login.css">
    </head>
    
    <body>
    	<% if(user != null){ %>   
		     <%=HtmlDefaults.generateHtmlNavbar(true)%>
		     <h2 class="form-signin-heading">Welcome back,  <%= user.getUsername() %>!</h2>
		     
		     <label>Username: <%= user.getUsername() %></label> <br>
		     <label>E-Mail: <%= user.getEmailAddress() %></label> <br>
		     <label>Birthdate: <%= user.getBirthdate() %></label> <br>
		     <label>Type: <%= user.getUsertype().getType() %></label>
			
 	 	<% }else{ %>
        	 <h2 class="form-signin-heading">Error! you are not logged in!</h2>
        	 <label>Klick <a href="Login_Servlet">here </a> to log in.</label>
        <% } %>
	<%=HtmlDefaults.generateHtmlFooter()%>
</body>
</html>