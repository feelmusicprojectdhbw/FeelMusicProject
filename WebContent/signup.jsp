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
    <form class=" form-signin " method="post" action="Signup">
		<h2 class=" form-signin-heading ">Bitte geben Sie ihre Daten ein</h2>
                                       
		<label for="username">Benutzername</label>
		<input type="text" required autocomplete="username" id="username" name="inputusername" class=" form-control " placeholder="Benutzername" minlength="4" maxlength="20" value="${username}" required autofocus>                              
                                       
		<label for="inputEmail">E--Mail Addresse</label>
		<input type="email" required  autocomplete="email" autocorrect="on" id="inputEmail" name="inputemail" class=" form-control " placeholder="name@example.com" value="${mailaddress}" required>
		
		<label for="inputPassword">Password</label>
		<input type="password" id="inputPassword" name="inputpassword" class="form-control" placeholder="Password" minlength="8" required>
      
		<label for="birthdate">Geburtsdatum</label> 
		<input class="form-control"type="date" id="birthdate" name="inputbirthdate" value="${birthdate}" required> 
		<c:if test="${useralreadyexists}">
			<div class="loginfaildiv">
				<label class="loginfaillabel"><b>Username does already exist!</b></label>
			</div>
		</c:if>                                                                                    
		<c:if test="${mailalreadyexists}">
			<div class="loginfaildiv">
				<label class="loginfaillabel"><b>Mailaddress is already in use!</b></label>
			</div>
		</c:if> 
		<c:if test="${loginfailed}">
			<div class="loginfaildiv">
				<label class="loginfaillabel"><b>Fehler in der Datenbank, bitte versuchen Sie es zu einem sp&aumlteren Zeitpunkt noch einmal.</b></label>
			</div>
		</c:if> 
      <button class=" btn btn-lg btn-primary btn-block " type="submit">Registrieren</button>     
    </form>
  </div>
	</jsp:body>
</t:app>