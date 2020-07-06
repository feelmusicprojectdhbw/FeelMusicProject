<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="true"%>
<%@page import="main.*"%>
<%@page import="main.obj.*"%>
<%@page import="main.dao.*"%>
<%@page import="main.tools.*"%>
<%@page import="main.servlets.*"%>
<%=HtmlDefaults.generateHtmlHeader()%>
        <title>createArtist</title>
    </head>
    
    <body>
     <%	User user = (User) session.getAttribute("user");%>
     <%=HtmlDefaults.generateHtmlNavbar(user)%>
        <div class="container py-2">

     <form class=" form-signin " method="post" action="CreateArtist">
      <h2 class=" form-signin-heading ">K&uumlnstler anlegen</h2>
      <label for=" inputArtist " class=" sr-only ">K&uumlnstler</label>
      <input type="text" name = "inputArtist" id=" inputArtist " class=" form-control " placeholder="Künstler" required autofocus>

      <label for=" inputLink " class=" sr-only ">Link zum K&uumlnstler</label>
      <input type="url" name = "inputLink" id=" inputLink " class=" form-control " placeholder="Link" required>

      <button class=" btn btn-block btn-outline-success btn-lg " type="submit">Senden</button>
     </form>
 
     </div>
    <%=HtmlDefaults.generateHtmlFooter()%>
    </body>
</html>