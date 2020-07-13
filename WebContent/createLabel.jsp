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
		<div class="container">
			<form class=" form-signin " method="post" action="CreateLabel">
				<h2 class=" form-signin-heading ">Label erstellen</h2>
				<label for="inputLabel" class=" sr-only "> Label</label>
				<input type="text" name="inputLabel" id="inputLabel" class=" form-control " placeholder=" Label " required autofocus>
  
				<label for=" inputLink " class=" sr-only ">Link zum Label</label>
				<input type="url" name="inputLink" id=" inputLink " class=" form-control " placeholder=" Link " required>
				<button class="  btn btn-block btn-outline-success btn-lg " type="submit">Senden</button>
   			</form>
		</div>  
	</jsp:body>
</t:app>