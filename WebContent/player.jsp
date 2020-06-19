<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="main.*"%>
<!doctype html>
<html lang="de">
<%@page import="main.obj.*"%>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <!--<link rel="stylesheet" href="css/style.css">-->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css">
    <title>Home</title>
    
</head>

<body>
   <%=HtmlDefaults.generateHtmlNavbar()%>

	<div class=" container">
      <!-- Example row of columns -->
      <div class="row">
        <div class="col-md">
          <h2>Player</h2>
          <div id="player" ></div>
          <p><button class="btn btn-outline-warning" id="previous">Previous</button>
            <button class="btn btn-outline-warning" id="next">Next</button></p>
            <p><label id="songname"> </label> - <a id="artist" href="" title="" target="_blank" rel="noopener noreferrer"> </a> <label id="coartists"> </label></p>
            <p>Label: <a id="label" href="" title="" target="_blank" rel="noopener noreferrer"> </a></p>
        </div>

        <div class="col-md">
          <h2>Vorschau</h2>
          <div class='table-responsive'>
            <!--Table-->
            <div id="table-wrapper">
 				<div id="table-scroll">
            		<table id="tablePreview" class="table table-hover">
		              <!--Table head-->
		              <thead>
		                <tr>
		                  <th>ID</th>
		                  <th>Title</th>
		                  <th>Artist</th>
		                </tr>
		              </thead>
		              <!--Table head-->
		              <!--Table body-->
		              <tbody>
		              
		              </tbody>
		              <!--Table body-->
		            </table>
		            <!--Table-->
		          </div>
				</div>
			</div>
        </div>
      </div>
      <hr>
      </div>

      <footer class="footer container-fluid text-center text-md-left bg-dark text-light py-2 bottom-0"
        style="position: fixed; bottom: 0;">
        <div class="container">
          <span class="text-muted">
            <p>&copy; Feel Music 2020 All rights reserved</p>
          </span>
        </div>
      </footer>
</body>
  <script>
  var playlistState = 0;
  var playlistMapObject = [
     <% Playlist p = (Playlist)request.getAttribute("playlist");
     if(p != null){
  		for (Song s : p.getSongs()) { %>
  		{ytlink: "<%= s.getLinks()[0] %>", sflink: "<%= s.getLinks()[1] %>", sclink: "<%= s.getLinks()[2] %>", title: "<%= s.getName() %>", 
  		 artist: "<%= s.getArtist().getName() %> %ARTISTLINK% <%= s.getArtist().getLink() %>", 
  		 coartists: "<% if(s.getCoArtists() != null && s.getCoArtists().getCoartists().length > 0){for(Artist a : s.getCoArtists().getCoartists()){ %><%= a.getName()%>%ARTISTLINK%<%= a.getLink()%>;<%}}%>",
  		 label: "<%=s.getLabel().getName()%>%LABELLINK%<%=s.getLabel().getLink()%>"},
     <% } 
  	} %>
   ]
  
  /*https://www.youtube.com/embed/VIDEOID/?enablejsapi=1&autoplay=1

  // playlist map
/*  let playlistMapObject = [
    {link: "https://open.spotify.com/embed/track/02HJo9cBRCO12opAKdcZcJ", title: "Spotify Lied", artist: "Someone"},
    {link: "https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/723315721&color=%23ff5500&auto_play=true&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true", title: "Soundcloud lied", artist: "Someone"},
    {link: "https://www.youtube.com/embed/aYJStepXdbc?&autoplay=1", title: "Youtube Lied", artist: "Someone"},
  ]*/

  // if player finished song -> next song
  
  
  
  	var tag = document.createElement('script');
      tag.src = "https://www.youtube.com/iframe_api";
      var firstScriptTag = document.getElementsByTagName('script')[0];
      firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

      var player;
      function onYouTubeIframeAPIReady() {
    	  player = new YT.Player('player', {
    	    height: '360',
    	    width: '640',
    	    videoId: playlistMapObject[0].ytlink,
    	    events: {
    	      'onReady': onPlayerReady,
    	      'onStateChange': onPlayerStateChange
    	    }
    	  });
    	  }
    function onPlayerReady(event) {
    	event.target.playVideo();
    }
    
    function onPlayerStateChange(event) {
        if (event.data == 0) {
      	  playlistState = (playlistState + 1) % playlistMapObject.length;
      	  event.target.loadVideoById(playlistMapObject[playlistState].ytlink, 0, "large");
      	  refreshInfo(playlistState);
        }
      }
    function stopVideo() {
        player.stopVideo();
      }

 // let player = document.getElementById('playeriFrame');
 /*  player.addEventListener("onloadstart", function (state) {
	  console.log(state);
	  console.log(state.data);
    if (event.data === 0) {
      playlistState = (playlistState + 1) % playlistMapObject.length;
      player.src = playlistMapObject[playlistState].link;
    }
  }); */

  // add functionallity to prev and next buttons
  let previousButton = document.getElementById('previous');
  let nextBoutton = document.getElementById('next');
  previousButton.addEventListener('click', function (e) {
	  if(playlistState > 0){
	      playlistState = (playlistState - 1) % playlistMapObject.length;
	      player.loadVideoById(playlistMapObject[playlistState].ytlink, 0, "large");
	  }
  });
  nextBoutton.addEventListener('click', function (e) {
      playlistState = (playlistState + 1) % playlistMapObject.length;
      player.loadVideoById(playlistMapObject[playlistState].ytlink, 0, "large");
  });

  // construct preview table
  let previewTabel = document.getElementById('tablePreview');
  playlistMapObject.forEach((el, index) =>{
    let row = previewTabel.insertRow(index + 1);
    let cell = row.insertCell(0);
    let cell2 = row.insertCell(1);
    let cell3 = row.insertCell(2);
    
    cell.innerHTML = index;
    cell2.innerHTML = el.title;
    cell3.innerHTML = el.artist.split("%ARTISTLINK%")[0];

    row.style = 'cursor: pointer;';
    row.addEventListener('click', function () {
    	player.loadVideoById(playlistMapObject[index].ytlink, 0, "large");
    	playlistState = index;
    	refreshInfo(index);
    })
  });

  // autoplay first song in row
  (function () {
//    player.src = playlistMapObject[playlistState].link;
    refreshInfo(playlistState);
  })()
   
  function refreshInfo(n){
      document.getElementById("songname").innerHTML = playlistMapObject[n].title;
      let arti =  playlistMapObject[n].artist.split("%ARTISTLINK%");      
      if(arti[1]){
          document.getElementById("artist").innerHTML = "<a href=\"" + arti[1] + "\" title=\"" + arti[0] + "\" target=\"_blank\" rel=\"noopener noreferrer\">" + arti[0] + "</a>";	  
      }else{
    	  document.getElementById("artist").innerHTML = arti[0];
      }
      
      let coartistcell = "";
      let coart = playlistMapObject[n].coartists;
      if(coart !== ""){
    	  coart.split(";").forEach((item, index) =>{
  	    	let art = item.split("%ARTISTLINK%");
  	    	if(art[1] !== "" && art[0] !== ""){
  	    		coartistcell = coartistcell.concat(((index == 0)?"feat. ":", ") + "<a href=\"" + art[1] + "\" title=\"" + art[0] + "\" target=\"_blank\" rel=\"noopener noreferrer\">" + art[0] + "</a>");
  	    	}else if(art[0] !== ""){
  	    		coartistcell = coartistcell.concat(((index == 0)?"feat. ":", ") + art[0]);
  	    	}
  	    });
   	}
      
      document.getElementById("coartists").innerHTML = coartistcell;
      
      
      let lbl =  playlistMapObject[n].label.split("%LABELLINK%");      
      if(lbl[1]){
          document.getElementById("label").innerHTML = "<a href=\"" + lbl[1] + "\" title=\"" + lbl[0] + "\" target=\"_blank\" rel=\"noopener noreferrer\">" + lbl[0] + "</a>";	  
      }else{
    	  document.getElementById("label").innerHTML = lbl[0];
      }
	}
</script>
</html>