<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="main.*"%>
<%@page import="main.obj.*"%>
<%@page import="main.dao.*"%>
<%=HtmlDefaults.generateHtmlHeader()%>
        <title>SignIn</title>
    </head>
    
    <body>
     <%=HtmlDefaults.generateHtmlNavbar()%>
<div class=" container ">

    <form class=" form-signin ">
      <h2 class=" form-signin-heading ">Please sign in</h2>
      <label for=" inputEmail " class=" sr-only ">Email address</label>
      <input type=" email " id=" inputEmail " class=" form-control " placeholder=" Email address " required autofocus>
      <label for=" inputPassword " class=" sr-only ">Password</label>
      <input type=" password " id=" inputPassword " class=" form-control " placeholder=" Password " required>
      <div class=" checkbox ">
        <label>
          <input type=" checkbox " value=" remember-me " id=" rlabel "> Remember me
        </label>
      </div>
      <button class=" btn btn-lg btn-primary btn-block " type=" submit ">Sign in</button>
      <button class=" btn btn-lg btn-primary btn-block " herf="signup.html ">Register</button>
    </form>

  </div>
  <!-- /container -->
 
  
</body>

<%=HtmlDefaults.generateHtmlFooter()%>

</html>