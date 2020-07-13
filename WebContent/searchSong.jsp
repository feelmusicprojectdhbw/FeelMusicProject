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
	<div class="wholewrapper">
	<div class="formwrapper">
		<h2>Suche nach einem Songtitel und einem K&uumlnstler</h2>
			<p><label for=" inputSearch" ><b>Suchen:</b></label>	
			<input type="text" name="inputSearch" id="inputSearch" class="form-control" placeholder=" Songname " autofocus></p>
			<br>	 
			<p><label for="artist" > <b>K&uumlnstler</b> </label>		
			<select class=" form-control "id="artist" name="artist">
				<option value="1">?</option>
				<c:forEach var="a" items="${Database.getAllArtists()}"> 
					<c:if test="${a.isNoDefault()}">
					<option value="${a.getId()}">${a.getName()}</option>
					</c:if>			
				</c:forEach>
			</select> </p>
			<p>
			<button class=" btn btn-success btn-lg " onClick="searchSongs()">Suchen</button> </p>
			<br>
			<br>
		</div>
		<div id ="resultwrapper" class="resultwrapper">			
		 			 
		 </div>
	 </div>
	 </div>
</jsp:body>
</t:app>


<script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
<script src="js/searchSong.js" type="text/javascript"></script>

</html>