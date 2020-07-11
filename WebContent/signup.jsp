<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="true"%>
<%@page import="main.*"%>
<%@page import="main.obj.*"%>
<%@page import="main.dao.*"%>
<%@page import="main.tools.*"%>
<%@page import="main.servlets.*"%>
<%=HtmlDefaults.generateHtmlHeader()%>
    <title>Account erstellen</title>
	<link rel="stylesheet" href="css/login.css">
</head>

<body>
     <%	User user = (User) session.getAttribute("user");%>
     <%=HtmlDefaults.generateHtmlNavbar(user)%>
	<div class=" container ">
	
	<%
	Boolean useralreadyexists = (Boolean) request.getAttribute("useralreadyexists");
	Boolean mailalreadyexists = (Boolean) request.getAttribute("mailalreadyexists");
	Boolean loginfailed = (Boolean) request.getAttribute("loginfailed");
	String username = (String) request.getAttribute("username");
	String mailaddress = (String) request.getAttribute("mailaddress");
	String birthdate = (String) request.getAttribute("birthdate");
	%>

    <form class=" form-signin " method="post" action="Signup">
		<h2 class=" form-signin-heading ">Bitte geben Sie ihre Daten ein</h2>
                                       
		<label for="username">Benutzername</label>
		<input type="text" required autocomplete="username" id="username" name="inputusername" class=" form-control " placeholder="Benutzername" minlength="4" maxlength="20" value="<%=((username!=null)?username:"") %>" required autofocus>                              
                                       
		<label for="inputEmail">E--Mail Addresse</label>
		<input type="email" required  autocomplete="email" autocorrect="on" id="inputEmail" name="inputemail" class=" form-control " placeholder="name@example.com" value="<%=((mailaddress!=null)?mailaddress:"") %>" required>
		
		<label for="inputPassword">Password</label>
		<input type="password" id="inputPassword" name="inputpassword" class="form-control" placeholder="Password" minlength="8" required>
      
		<label for="birthdate">Geburtsdatum</label> 
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
				<label class="loginfaillabel"><b>Fehler in der Datenbank, bitte versuchen Sie es zu einem sp&aumlteren Zeitpunkt noch einmal.</b></label>
			</div>
		<%}%>
      <button class=" btn btn-lg btn-primary btn-block " type="submit">Registrieren</button>     
    </form>
  </div>
  <%=HtmlDefaults.generateHtmlFooter()%>
</body>
</html>