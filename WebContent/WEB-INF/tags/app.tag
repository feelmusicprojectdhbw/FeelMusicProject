<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="title" fragment="true" %>
<%@attribute name="scripts" fragment="true" %>
<%@attribute name="styles" fragment="true" %>
<jsp:useBean id="Database" class="main.dao.Database" scope="application"/>
<jsp:useBean id="Genres" class="main.obj.Genres" scope="application"/>
<jsp:useBean id="Genre" class="main.obj.Genre" scope="application"/>
<jsp:useBean id="Styles" class="main.obj.Styles" scope="application"/>
<jsp:useBean id="Style" class="main.obj.Style" scope="application"/>
<jsp:useBean id="Feelings" class="main.obj.Feelings" scope="application"/>
<jsp:useBean id="Feeling" class="main.obj.Feeling" scope="application"/>
<jsp:useBean id="Languages" class="main.obj.Languages" scope="application"/>
<jsp:useBean id="Language" class="main.obj.Language" scope="application"/>
<jsp:useBean id="Usertype" class="main.obj.Usertype" scope="application"/>
<jsp:useBean id="Usertypes" class="main.obj.Usertypes" scope="application"/>
<jsp:useBean id="CounterBean" class="main.obj.CounterBean" scope="application"/>
<jsp:useBean id="HtmlDefaults" class="main.tools.HtmlDefaults" scope="application"/>


		<jsp:include page="/WEB-INF/templates/header.jsp"/>
		
		<link rel="stylesheet" href="css/styleplaylistcreation.css">
		<script src="js/playlistCreation.js" type="text/javascript" ></script>
        <jsp:invoke fragment="styles"/>

        <jsp:invoke fragment="title"/>

	</head>
	<body>	
	<jsp:include page="/WEB-INF/templates/navbar.jsp"/>
	<main>
		<jsp:doBody/>
	</main>
	<footer
		class="footer container-fluid text-center text-md-left bg-dark text-light py-2 bottom-0">
	<div class="container">
		<span class="text-muted">
			<p>&copy; Feel Music 2020 All rights reserved</p>
		</span>
	</div>
	</footer>
	</body>
</html>