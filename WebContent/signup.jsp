<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="main.*"%>
<%@page import="main.obj.*"%>
<%@page import="main.dao.*"%>
<%=HtmlDefaults.generateHtmlHeader()%>
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
  <%=HtmlDefaults.generateHtmlFooter()%>

</body>

</html>