<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="true"%>
<%@page import="main.*"%>
<%@page import="main.obj.*"%>
<%@page import="main.dao.*"%>
<%=HtmlDefaults.generateHtmlHeader()%>
    <title>SingUp</title>
	<link rel="stylesheet" href="css/login.css">
</head>

<body>
     <%	User user = (User) session.getAttribute("user");%>
     <%=HtmlDefaults.generateHtmlNavbar(user != null)%>
	<div class=" container ">
	
	<%
	Boolean useralreadyexists = (Boolean) request.getAttribute("useralreadyexists");
	Boolean mailalreadyexists = (Boolean) request.getAttribute("mailalreadyexists");
	Boolean loginfailed = (Boolean) request.getAttribute("loginfailed");
	String username = (String) request.getAttribute("username");
	String mailaddress = (String) request.getAttribute("mailaddress");
	String birthdate = (String) request.getAttribute("birthdate");
	%>

    <form class=" form-signin " method="post" action="Signup_Servlet">
		<h2 class=" form-signin-heading ">Please Sign up</h2>
                                       
		<label for="username">Username</label>
		<input type="text" required autocomplete="username" id="username" name="inputusername" class=" form-control " placeholder="Username" minlength="4" maxlength="20" value="<%=((username!=null)?username:"") %>" required autofocus>                              
                                       
		<label for="inputEmail">Email address</label>
		<input type="email" required  autocomplete="email" autocorrect="on" id="inputEmail" name="inputemail" class=" form-control " placeholder="name@example.com" value="<%=((mailaddress!=null)?mailaddress:"") %>" required>
		
		<label for="inputPassword">Password</label>
		<input type="password" id="inputPassword" name="inputpassword" class="form-control" placeholder="Password" minlength="8" required>
      
		<label for="birthdate">Birthdate</label> 
		<input class="form-control"type="date" id="birthdate" name="inputbirthdate" value="<%=((birthdate!=null)?birthdate:"") %>" required>                                                                                     
     <% if(useralreadyexists != null && useralreadyexists == Boolean.TRUE){%>
			<div class="loginfaildiv">
				<label class="loginfaillabel"><b>Username does already exist!</b></label>
			</div>
		<%}%>
		<% if(mailalreadyexists != null && mailalreadyexists == Boolean.TRUE){%>
			<div class="loginfaildiv">
				<label class="loginfaillabel"><b>Mailaddress is already in use!</b></label>
			</div>
		<%}%>
		<% if(loginfailed != null && loginfailed == Boolean.TRUE){%>
			<div class="loginfaildiv">
				<label class="loginfaillabel"><b>Error in Database, try again later!</b></label>
			</div>
		<%}%>
      <button class=" btn btn-lg btn-primary btn-block " type="submit">Sign up</button>     
    </form>
  </div>
  <%=HtmlDefaults.generateHtmlFooter()%>
</body>
</html>