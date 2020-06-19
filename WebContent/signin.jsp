<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="main.*"%>
   <!doctype html>
    <html lang="de">
    
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    
        <title>SignIn</title>
    </head>
    
    <body>
     <%=HtmlDefaults.generateHtmlHeader()%>
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

<footer class="footer container-fluid text-center text-md-left bg-dark text-light py-2 bottom-0">
  <div class="container">
      <span class="text-muted"> <p>&copy; Feel Music 2020    All rights reserved</p></span>
  </div>
</footer>

</html>