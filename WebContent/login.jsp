<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="true"%>
<%@page import="main.*"%>
<%@page import="main.obj.*"%>
<%@page import="main.dao.*"%>
<%@page import="main.tools.*"%>
<%@page import="main.servlets.*"%>
<%=HtmlDefaults.generateHtmlHeader()%>
        <title>Anmelden</title>
		<link rel="stylesheet" href="css/login.css">
    </head>
    
    <body>
     <%	User user = (User) session.getAttribute("user");%>
     <%=HtmlDefaults.generateHtmlNavbar(user)%>
	<div class=" container ">

    <form class="form-signin" method="post" action="Login">
      <h2 class="form-signin-heading">Please sign in</h2>
      <label for="inputEmail" class="sr-only">Email address</label>
      <input type="email" id="inputmailaddress" class="form-control" placeholder="Email address" name="inputmailaddress" required autofocus>
      <label for="inputPassword" class="sr-only">Password</label>
      <input type="password" id="inputpassword" class="form-control" placeholder="Password" name="inputpassword" required>
   <!--     <div class="checkbox">
       <label>
          <input type="checkbox" name="rlabel" value="remember-me" id="rlabel"> Remember me
        </label>
      </div>-->
      <% Boolean fail = (Boolean)request.getAttribute("loginfailed");
        if(fail != null && fail == Boolean.TRUE){%>
			<div class="loginfaildiv">
				<label class="loginfaillabel"><b>Benutzername oder Kennwort falsch!</b></label>
			</div>
		<%}%>
      	<button class="btn btn-block btn-outline-success btn-lg " type="submit">Anmelden</button>
		<label>You don't have a Account yet? Klick here to <a style="margin: 1px;" href="Signup">Registrieren</a></label>
    </form>

  </div>
	<%=HtmlDefaults.generateHtmlFooter()%>
</body>
</html>