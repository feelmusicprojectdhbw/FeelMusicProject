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

    <title>SingUp</title>
</head>

<body>
    <%=HtmlDefaults.generateHtmlNavbar()%>
<div class=" container ">

    <form class=" form-signin ">
      <h2 class=" form-signin-heading ">Please Sign up</h2>
                                       
      <label for=" inputName " class=" sr-only ">Frist -Lastname</label>
     <input type="text" required autocomplete="name" id=" inputName " class=" form-control " placeholder=" Name " required autofocus>                              
                                       
      <label for=" inputEmail " class=" sr-only ">Email address</label>
      <input type=" email " required  autocomplete="email" autocorrect="on" id=" inputEmail " class=" form-control " placeholder="name@example.com" required autofocus>
      
      <label for=" inputPassword " autocorrect="off" class=" sr-only ">Password</label>
      <input type=" password " id=" inputPassword " class=" form-control " placeholder=" Password " required>
      <input type="hidden"  id=" fristName " class=" form-control " placeholder=" Name " required autofocus> <!-- Spam Schutz muss erweitert werden.-->
      
      <button class=" btn btn-lg btn-primary btn-block " type="reset">Delete</button>
                                                                                     
      <button class=" btn btn-lg btn-primary btn-block " type=" submit ">Sign up</button>
     
    </form>
  </body>
  </div>
  <footer class="footer container-fluid text-center text-md-left bg-dark text-light py-2 bottom-0">
    <div class="container">
        <span class="text-muted"> <p>&copy; Feel Music 2020    All rights reserved</p></span>
    </div>
</footer>

</body>

</html>