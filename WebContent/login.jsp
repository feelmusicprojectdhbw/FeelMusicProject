<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="true"%>
<%@page import="main.*"%>
<%@page import="main.obj.*"%>
<%@page import="main.dao.*"%>
<%@page import="main.tools.*"%>
<%@page import="main.servlets.*"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:app>
 	<jsp:body>
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
      	<c:if test="${not empty loginfailed}">
      		<div class="loginfaildiv">
				<label class="loginfaillabel"><b>Benutzername oder Kennwort falsch!</b></label>
			</div>
      	</c:if>
      	<button class="btn btn-block btn-outline-success btn-lg " type="submit">Anmelden</button>
		<label>You don't have a Account yet? Klick here to <a style="margin: 1px;" href="Signup">Registrieren</a></label>
    </form>

  </div>
	</jsp:body>
</t:app>