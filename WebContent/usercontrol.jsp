<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="true"%>
<%@page import="main.*"%>
<%@page import="main.obj.*"%>
<%@page import="main.dao.*"%>
<%@page import="main.tools.*"%>
<%@page import="main.servlets.*"%>
<%@page import="main.servlets.ajax.*"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:app>
 	<jsp:body>
 	<c:choose>
		<c:when test="${empty user}">
			<jsp:include page="/WEB-INF/templates/notLoggedIn.jsp"/>
		</c:when>
		<c:otherwise>  
			<div class="container">
			<c:if test="${user.hasElevatedPermissions() }">
				<h1 class="form-signin-heading">Benutzerverwaltung</h1>
				<h2>Suche nach einer E-Mail Addresse oder einer Id</h2>
				<p><label for="inputSearch"><b>Search:</b></label>	
				<input type="text" name="inputSearch" id="inputSearch" class="form-control" placeholder="E-Mail oder Benutzer ID" autofocus>
				<button class="btn btn-success btn-lg" onclick="searchUser(${user.getUsertype().getId()})">Suchen</button></p>
				<div id="userpagewrapper" style="display: none">
					<form id="usercontrol" class=" form-signin " method="post" action="UserControl">
						<label>Benutzer ID: <span id="userIdSpan"></span> <input type="text" id="hiddenid" name=hiddenid style="display:none"/></label> <br>					
						<label>Benutzername: <span id="usernameSpan"></span></label> <br>
						<label>E-Mail: <span id="emailSpan"></span></label> <br>
						<label>Geburtsdatum: <span id="birthdateSpan"></span></label> <br>
						<label>Benutzertyp: 
						<select id="usertypeselector" name="usertypeselector" onchange="changedUsertype(0, 0)">
						<c:forEach var="ut" items="${Usertypes.getAllUsertypes()}"> 
								<option value="${ut.getId() }">${ut.getType()}</option>								
						</c:forEach>
						</select>
						</label><br>					
						<div id="zuordnugsdiv" style="display: none">
							<label> Verbunden mit: 
								<select id="labelToConnect" name="labelToConnect">  
								<c:forEach var="l" items="${Database.getAllLabels()}">
							    	<option value="${l.getId()}">${l.getName()}</option>
							    </c:forEach>
								</select>
								<select id="artistToConnect" name="artistToConnect">  
								<c:forEach var="a" items="${Database.getAllArtists()}">
							    	<option value="${a.getId()}">${a.getName()}</option>
							    </c:forEach>
								</select>
					        </label>
						</div>
						<button class="btn btn-primary btn-lg" type="submit">&Aumlnderungen speichern</button>
					</form>
				</div>	
				</c:if>
				</div>
			</c:otherwise>
		</c:choose>
	</jsp:body>
</t:app>

<script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
<script src="js/usercontrol.js" type="text/javascript" ></script>
</html>