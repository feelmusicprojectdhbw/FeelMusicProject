<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="true"%>
<%@page import="main.*"%>
<%@page import="main.obj.*"%>
<%@page import="main.dao.*"%>
<%@page import="main.tools.*"%>
<%@page import="main.servlets.*"%>
<%=HtmlDefaults.generateHtmlHeader()%>
        <title>Datenschutz</title>
    </head>
    
    <body>
     <%	User user = (User) session.getAttribute("user");%>
     <%=HtmlDefaults.generateHtmlNavbar(user)%>
 <div class="jumbotron">
    <h1>Datenschutz </h1>
    <span><p>
        Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam volupt</br>
        vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor</br>
        sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et</br>
        accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet,</br>
        consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et</br>
        justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. </br>
    </p></span>
</div>



  </body>
  </div>
  <%=HtmlDefaults.generateHtmlFooter()%>

</body>

</html>