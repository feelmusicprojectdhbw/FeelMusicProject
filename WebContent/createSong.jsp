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
	<c:choose>
		<c:when test="${empty user}">
			<jsp:include page="/WEB-INF/templates/notLoggedIn.jsp"/>
		</c:when>
		<c:otherwise>
		<h2 class=" form-signin-heading ">Song hinzuf&uumlgen</h2>
	
				<form id="songcreation" class="form-signin " method="post" action="CreateSong">
				
	
				<label for=" inputSong" ><b>Song</b></label> 	
				<input type="text" name="inputSong" id="inputSong " class="form-control" placeholder=" Song " required autofocus>
		<c:choose>
			<c:when test="${user.isAdmin()}">
				<c:set var="artists" value="${Database.getAllArtists()}" scope="page" />	
			</c:when>
			<c:when test="${user.isModerator()}">
				<c:set var="artists" value="${Database.getAllArtists()}" scope="page" />	
			</c:when>
			<c:when test="${user.isArtist()}">
				<c:set var="artists" value="${Database.getAllArtists()}" scope="page" />	
			</c:when>
			<c:otherwise>
				<c:set var="artists" value="${Database.getNoLinkedArtists()}" scope="page" />	
			</c:otherwise>
		</c:choose>
		<div class="songmetadatawrapperdiv">
			<div class="artistsdiv">
				<label for="artist" > <b>K&uumlnstler</b> </label>	
				
				<select class=" form-control "id="artist" name="artist">
				<c:forEach var="a" items="${artists}">
					<option>${a.getName()};${a.getId()}</option>
				</c:forEach>
				</select>		
				
				<label for="coartist1 coartist2 coartist3 coartist4 " ><b>Co-Produzenten</b></label>
				<div class="coartistsdiv">
				<select class=" form-control " id="coartist1" name="coartist1">
				
			<jsp:include page="/WEB-INF/templates/notLoggedIn.jsp"/>
			<c:choose>
			<c:when test="${empty user}">
			</c:when>
			<c:otherwise>
			</c:otherwise>
			</c:choose>
		 		<c:forEach var="a" items="${artists}">
					<option>${a.getName()};${a.getId()}</option>
				</c:forEach>
				</select>
				</div>
				<div class="coartistsdiv">
				<select class=" form-control " id="coartist2" name="coartist2">
		 		<c:forEach var="a" items="${artists}">
					<option>${a.getName()};${a.getId()}</option>
				</c:forEach>
				</select>
				</div>
				<div class="coartistsdiv">
				<select class=" form-control " id="coartist3" name="coartist3">
				<c:forEach var="a" items="${artists}">
					<option>${a.getName()};${a.getId()}</option>
				</c:forEach>
				</select>
				</div>
				<div class="coartistsdiv">
				<select class=" form-control " id="coartist4" name="coartist4">
				<c:forEach var="a" items="${artists}">
					<option>${a.getName()};${a.getId()}</option>
				</c:forEach>
				</select>
				</div>
			</div>
			<div class="songmetadatadiv">	
				<label for="releaseDate"> <b>Erscheinungsdatum</b></label> <br>
				<input type="date" id="releaseDate" name="releaseDate" class=" metadata-control " required> 
				
				<label for="genre" ><b>Genre</b></label> 
				<select class=" metadata-control " id="genre" name="inputGenre">
				<c:forEach var="g" items="${Genres.getGenres()}">
					<option>${g.getName()}</option>
					<c:if test="${g.hasSubgenres()}">
					<c:forEach var="g1" items="${g.getSubgenres()}">
						<option>${g1.getName()}</option>
						<c:if test="${g1.hasSubgenres()}">
						<c:forEach var="g2" items="${g1.getSubgenres()}">
							<option>${g2.getName()}</option>
							<c:if test="${g2.hasSubgenres()}">
							<c:forEach var="g3" items="${g2.getSubgenres()}">
								<option>${g3.getName()}</option>
							</c:forEach>
							</c:if>
						</c:forEach>
						</c:if>
					</c:forEach>
					</c:if>
				</c:forEach> 
				</select>				
				
				<label for=" inputLabel " ><b>Label</b></label> 
				<select class=" metadata-control " name="inputLabel">
					<c:forEach var="l" items="${Database.getAllLabels()}">
						<option>${l.getName()};${l.getId()}</option>
					</c:forEach>
				</select>
				
				<label for=" inputLanguage " ><b>Sprache</b></label> 
				<select class=" metadata-control " name = inputLanguage>
					<c:forEach var="l" items="${Languages.getLanguages()}">
						<option>${l.getName()}</option>
					</c:forEach>
				</select>
			</div>	
		</div>
		<div class="linksdiv">	
			<label><b>Gef&uumlhle</b></label> 
		
			<div class="tlayoutdiv">
				<table class="tlayout">
				<tbody>
					<tr>	
						<c:if test="${Feelings.hasFeelings()}">
						<c:forEach var="i" items="${CounterBean.newArrayInstance()}"> 				
						${i.reset()}
						<c:forEach var="f" items="${Feelings.getFeelings()}"> 
								<c:choose>
									<c:when test="${i.isSmaller(Feelings.calc())}">
									${i.increment()}
										<td class="table-elements"><input class="selectionboxes" type="checkbox" name="feeling" value="${f.getName()}">${f.getName()}<br></td>
								 	</c:when>
									<c:otherwise>	
								 	${i.reset()}
								 	${i.increment()}					
										</tr>
										<tr>
										<td class="table-elements"><input class="selectionboxes" type="checkbox" name="feeling" value="${f.getName()}">${f.getName()}<br></td>
									</c:otherwise>
								</c:choose>
							</c:forEach>	
						</c:forEach>
						</c:if>	
					</tr>
				</tbody>
				
				</table>
			</div>	
			<div class="tlayoutdiv">
			<label><b>Styles</b></label> 	
					 
				<table class="tlayout">
				<tbody>
					<tr>		
					<c:if test="${Styles.hasStyles()}">
						<c:forEach var="i" items="${CounterBean.newArrayInstance()}"> 				
						${i.reset()}
						<c:forEach var="f" items="${Styles.getStyles()}"> 
								<c:choose>
									<c:when test="${i.isSmaller(Feelings.calc())}">
									${i.increment()}
										<td class="table-elements"><input class="selectionboxes" type="checkbox" name="style" value="${f.getName()}">${f.getName()}<br></td>
								 	</c:when>
									<c:otherwise>	
								 	${i.reset()}
								 	${i.increment()}					
										</tr>
										<tr>
										<td class="table-elements"><input class="selectionboxes" type="checkbox" name="style" value="${f.getName()}">${f.getName()}<br></td>
									</c:otherwise>
								</c:choose>
							</c:forEach>	
						</c:forEach>
					</c:if>	
					</tr>
				</tbody>
				
				</table>
			</div>	
		</div>
				<input type="hidden" name="selectedFeelings" id="selectedFeelings" />
				<input type="hidden" name="selectedStyles" id="selectedStyles" />
				
				<label for=" inputytLink " ><b>Youtube Link</b></label> 
				<input type=" url " name="inputYtLink" id=" inputLink " class=" form-control " placeholder=" ytLink "> 
				
				<label for=" inputSfLink " ><b>Spotify Link</b></label> 
				<input type=" url " name="inputSfLink" id=" inputLink " class=" form-control " placeholder=" sfLink ">
				
				<label for=" inputScLink " ><b>Soundcloud Link</b></label>
				<input type=" url " name="inputScLink" id=" inputLink " class=" form-control " placeholder=" scLink ">
	
				<button class="  btn btn-block btn-outline-success btn-lg " type="submit" onClick="processValidations()">Senden</button>
			</form>
			</c:otherwise>
		</c:choose>
		</div>
	</jsp:body>
</t:app>