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
	<form id="playlistcreation" class=" form-signin " method="post" action="CreatePlaylist">
  		<!-- <h4 class="pt-3 pl-3"><b>Genres</b></h4> -->
  		<label for="exampleFormControlSelect2">Genres</label>
 		<hr>		
		<div class="genrediv">
		<c:if test="${Genres.hasGenres()}">
			<table>
			<tbody>
			<tr>
			<c:forEach var="g" items="${Genres.getGenres()}">
			<td class="genretd">
			<ul class="treeview-animated-list mb-3" id="genreTreeUlCheckbox">

				<li class="treeview-animated-items">
					<input class="${g.getTrimmedName()}radio" type="radio" value="#6c757d" checked hidden>
					<input class="${g.getTrimmedName()}radio" type="radio" value="green" hidden>
					<input class="${g.getTrimmedName()}radio" type="radio" value="red" hidden>
					<div id="${g.getTrimmedName()}div">
						<genre id="${g.getTrimmedName()}btn" name="genre" class="btn btn-secondary" onclick="doGenre('${g.getTrimmedName()}')" value="${g.getTrimmedName()}" selection="0">${g.getName()}</genre>
						<c:if test="${g.hasSubgenres()}">
	    				<ul class="nested"> 
	    				<c:forEach var="g1" items="${g.getSubgenres()}">   				
	   			 			<li class="genreli">
	   			 			<input class="${g1.getTrimmedName()}radio" type="radio" value="#6c757d" checked hidden>
							<input class="${g1.getTrimmedName()}radio" type="radio" value="green" hidden>
							<input class="${g1.getTrimmedName()}radio" type="radio" value="red" hidden>
							<div id="${g1.getTrimmedName()}div">
	   			 			<genre id="${g1.getTrimmedName()}btn" name="genre" class="btn btn-secondary" onclick="doGenre('${g1.getTrimmedName()}')" value="${g1.getTrimmedName()}" selection="0">${g1.getName()}</genre>	       		 	
							<c:if test="${g1.hasSubgenres()}">
								<ul class="nested">
								<c:forEach var="g2" items="${g1.getSubgenres()}">
			   			 			<li class="genreli">
			   			 				<input class="${g2.getTrimmedName()}radio" type="radio" value="#6c757d" checked hidden>
										<input class="${g2.getTrimmedName()}radio" type="radio" value="green" hidden>
										<input class="${g2.getTrimmedName()}radio" type="radio" value="red" hidden>
										<div id="${g2.getTrimmedName()}div">
			   			 					<genre id="${g2.getTrimmedName()}btn" name="genre" class="btn btn-secondary" onclick="doGenre('${g2.getTrimmedName()}')" value="${g2.getTrimmedName()}" selection="0">${g2.getName()}</genre>
				   			 				<c:if test="${g2.hasSubgenres()}">
												<ul class="nested">
												<c:forEach var="g3" items="${g2.getSubgenres()}">
							   			 			<li class="genreli">
							   			 				<input class="${g3.getTrimmedName()}radio" type="radio" value="#6c757d" checked hidden>
														<input class="${g3.getTrimmedName()}radio" type="radio" value="green" hidden>
														<input class="${g3.getTrimmedName()}radio" type="radio" value="red" hidden>
														<div id="${g3.getTrimmedName()}div">
							   			 					<genre id="${g3.getTrimmedName()}btn" name="genre" class="btn btn-secondary" onclick="doGenre('${g3.getTrimmedName()}')" value="${g3.getTrimmedName()}" selection="0">${g3.getName()}</genre>
							   			 				</div>
													</li>		       		 	
												</c:forEach>
												</ul>
											</c:if>		
										</div>
									</li>		       		 	
								</c:forEach>
								</ul>						
		    				</c:if>	    
							</div>						
		    				</li>
						</c:forEach>
						</ul>					
	    		</c:if>
	    		</div>
	    		</li>
	    		</ul>
	    		</td>    		
	    	</c:forEach>
	    	</tr>
	    	</tbody>
			</table>
		</c:if>
		<input type="hidden" name="selectedGenres" id="selectedGenres" />
		<input type="hidden" name="blockedGenres" id="blockedGenres" />
		</div>
	
	<div class="form-group">
 		<hr>
		<label for="exampleFormControlSelect2">Gef&uumlhle</label> 
		<div class="scrollContainer">
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
						<td class="table-elements">
						 	<div id="${f.getName()}div">
								<input class="${f.getTrimmedName()}radio" type="radio" value="#6c757d" checked hidden>
								<input class="${f.getTrimmedName()}radio" type="radio" value="green" hidden>
								<input class="${f.getTrimmedName()}radio" type="radio" value="red" hidden>
						 		<div id="${f.getTrimmedName()}btn" name="feeling" class="btn btn-secondary" onclick="doFeeling('${f.getTrimmedName()}')" value="${f.getTrimmedName()}" selection="0">${f.getName()}</div>
						 	</div>
					 	</td>
						 	</c:when>

							<c:otherwise>	
						 	${i.reset()}
						 	${i.increment()}					
					</tr>
					<tr>
						<td class="table-elements">
						 	<div id="${f.getName()}div">
								<input class="${f.getTrimmedName()}radio" type="radio" value="#6c757d" checked hidden>
								<input class="${f.getTrimmedName()}radio" type="radio" value="green" hidden>
								<input class="${f.getTrimmedName()}radio" type="radio" value="red" hidden>
						 		<div id="${f.getTrimmedName()}btn" name="feeling" class="btn btn-secondary" onclick="doFeeling('${f.getTrimmedName()}')" value="${f.getTrimmedName()}" selection="0">${f.getName()}</div>
						 	</div>
					 	</td>
						</c:otherwise>
						</c:choose>
					</c:forEach>	

					</c:forEach>
			</c:if>	
		</tr>
		</tbody>
			
		</table>	
			
		</div>
		<input type="hidden" name="selectedFeelings" id="selectedFeelings" />
		<input type="hidden" name="blockedFeelings" id="blockedFeelings" />
	</div>

	<div class="form-group">
 		<hr>
		<details>
		<summary>
			<label for="exampleFormControlSelect3">Styles</label> 
		</summary>
		<div class="scrollContainer">
		<table class="tlayout">
			<tbody>
				<tr>
				<c:if test="${Styles.hasStyles()}">
					<c:forEach var="i" items="${CounterBean.newArrayInstance()}"> 				
					${i.reset()}
					<c:forEach var="s" items="${Styles.getStyles()}"> 
							<c:choose>
							<c:when test="${i.isSmaller(Styles.calc())}">
							${i.increment()}
						<td class="table-elements">
						 	<div id="${s.getName()}div">
								<input class="${s.getTrimmedName()}radio" type="radio" value="#6c757d" checked hidden>
								<input class="${s.getTrimmedName()}radio" type="radio" value="green" hidden>
								<input class="${s.getTrimmedName()}radio" type="radio" value="red" hidden>
						 		<div id="${s.getTrimmedName()}btn" name="style" class="btn btn-secondary" onclick="doStyle('${s.getTrimmedName()}')" value="${s.getTrimmedName()}" selection="0">${s.getName()}</div>
						 	</div>
					 	</td>
						 	</c:when>
							<c:otherwise>	
						 	${i.reset()}
						 	${i.increment()}					
							</tr>
							<tr>
								<td class="table-elements">
								 	<div id="${s.getName()}div">
										<input class="${s.getTrimmedName()}radio" type="radio" value="#6c757d" checked hidden>
										<input class="${s.getTrimmedName()}radio" type="radio" value="green" hidden>
										<input class="${s.getTrimmedName()}radio" type="radio" value="red" hidden>
								 		<div id="${s.getTrimmedName()}btn" name="style" class="btn btn-secondary" onclick="doStyle('${s.getTrimmedName()}')" value="${s.getTrimmedName()}" selection="0">${s.getName()}</div>
								 	</div>
							 	</td>
						</c:otherwise>
						</c:choose>
					</c:forEach>	

					</c:forEach>
				</c:if>	
			</tr>
		</tbody>
			
		</table>
		</div>
		<input type="hidden" name="selectedStyles" id="selectedStyles" />
		<input type="hidden" name="blockedStyles" id="blockedStyles" />
		</details>
	</div>

	<div class="form-group">
 	<hr>		
	<details>
	<summary>
	<label for="exampleFromControlSelect4">Sprache</label>
	</summary>	
	<div id="smallContainer" >
		<table class="tlayout">
			<tbody>
				<tr>	
				<c:if test="${Languages.hasLanguages()}">
					<c:forEach var="i" items="${CounterBean.newArrayInstance()}"> 				
					${i.reset()}
					<c:forEach var="l" items="${Languages.getLanguages()}"> 
							<c:choose>
							<c:when test="${i.isSmaller(Languages.calc())}">
							${i.increment()}
						<td class="table-elements">
						 	<div id="${l.getName()}div">
								<input class="${l.getTrimmedName()}radio" type="radio" value="#6c757d" checked hidden>
								<input class="${l.getTrimmedName()}radio" type="radio" value="green" hidden>
								<input class="${l.getTrimmedName()}radio" type="radio" value="red" hidden>
						 		<div id="${l.getTrimmedName()}btn" name="language" class="btn btn-secondary" onclick="doLanguage('${l.getTrimmedName()}')" value="${l.getTrimmedName()}" selection="0">${l.getName()}</div>
						 	</div>
					 	</td>
						 	</c:when>

							<c:otherwise>	
						 	${i.reset()}
						 	${i.increment()}					
					</tr>
					<tr>
						<td class="table-elements">
						 	<div id="${l.getName()}div">
								<input class="${l.getTrimmedName()}radio" type="radio" value="#6c757d" checked hidden>
								<input class="${l.getTrimmedName()}radio" type="radio" value="green" hidden>
								<input class="${l.getTrimmedName()}radio" type="radio" value="red" hidden>
						 		<div id="${l.getTrimmedName()}btn" name="language" class="btn btn-secondary" onclick="doLanguage('${l.getTrimmedName()}')" value="${l.getTrimmedName()}" selection="0">${l.getName()}</div>
						 	</div>
					 	</td>
						</c:otherwise>
						</c:choose>
					</c:forEach>	

					</c:forEach>
				</c:if>
				</tr>
			</tbody>
			
		</table>
		</div>
		<input type="hidden" name="selectedLanguages" id="selectedLanguages" />
		<input type="hidden" name="blockedLanguages" id="blockedLanguages" />
	
	</details>
	</div>

	<p class="checkcaption">W&aumlhle den Erscheinungszeitraum</p>
	
			<label for="form">Von:</label> 
			<input  class="metadata-control"type="date" id="fromdate" name="fromdate"> 
			<br><br> 
			<label for="until">Bis:</label> 
			<input  class="metadata-control" type="date" id="untildate" name="untildate">
			<br><br> 
			
			<button class=" btn btn-block btn-outline-success btn-lg " type="submit" onClick="processValidations()">Senden</button>
			<br>
	</form>



</div>
</jsp:body>

</t:app>
