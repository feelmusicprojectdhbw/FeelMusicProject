<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="true"%>
<%@page import="main.*"%>
<%@page import="main.obj.*"%>
<%@page import="main.dao.*"%>
<%=HtmlDefaults.generateHtmlHeader()%>
    <title>Home</title>
    <link rel="stylesheet" href="css/styleplayer.css">
</head>

<body>
     <%	User user = (User) session.getAttribute("user");%>
     <%=HtmlDefaults.generateHtmlNavbar(user)%>

	<div class=" container">
	<p>
	    <label for="chooseYtPlayer"> Youtube</label>
		<input type="radio" id="chooseYtPlayer" name="playerChooser" value="YoutubePlayer"checked>
	    <label for="chooseScPlayer"> Soundcloud</label> 
	    <input type="radio" id="chooseScPlayer" name="playerChooser" value="SoundcloudPlayer">
    </p>
      <!-- Example row of columns -->
      <div class="row">
        <div class="col-md">
          <h2>Player</h2>
          <div id="ytplayerdiv">
          	<div id="ytplayer"></div>
          </div>
          <div id="scplayerdiv">
           <iframe id="scplayer" width="640px" height="360px" scrolling="no" frameborder="no" allow="autoplay"src="https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/152316976&color=%23ff5500&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true"></iframe>
      	  </div>
        <!--    <div id="sfplayer" style="visibility: hidden;">
          <iframe src="https://open.spotify.com/embed/track/1CFv4FqqeaZkNViwWSdk47" width="300" height="380" frameborder="0" allowtransparency="true" allow="encrypted-media"></iframe>
          </div>-->
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
      <%=HtmlDefaults.generateHtmlFooter()%>
</body>
<!--<script type="text/javascript" src="js/scApi.js"></script>-->
<script src="https://w.soundcloud.com/player/api.js" type="text/javascript"></script>
<script>
var playlistMapObject = [
	<% Playlist p = (Playlist)request.getAttribute("playlist");
 	if(p != null){
		for (Song s : p.getSongs()) { %>
  			{ytlink: "<%= s.getLinks()[0] %>", sflink: "<%= s.getLinks()[1] %>", sclink: "<%= s.getLinks()[2] %>", title: "<%= s.getName() %>", 
	  		 artist: "<%= s.getArtist().getName() %> %ARTISTLINK% <%= s.getArtist().getLink() %>", 
	  		 coartists: "<% if(s.getCoArtists() != null && s.getCoArtists().getCoartists().length > 0){for(Artist a : s.getCoArtists().getCoartists()){ %><%= a.getName()%>%ARTISTLINK%<%= a.getLink()%>;<%}}%>",
	  		 label: "<%=s.getLabel().getName()%>%LABELLINK%<%=s.getLabel().getLink()%>"
	  		},
     <% } 
	  	} %>
   ]
var playlistState = 0;  
var tag = document.createElement('script');
    tag.src = "https://www.youtube.com/iframe_api";
var firstScriptTag = document.getElementsByTagName('script')[0];
    firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

var player;
function onYouTubeIframeAPIReady() {
	player = new YT.Player('ytplayer', {
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
	if(document.getElementById('chooseYtPlayer').checked) {
		hideAllPlayers();
		document.getElementById("ytplayerdiv").style.visibility = "visible";
		document.getElementById("ytplayerdiv").style.display = "block";
		event.target.playVideo();
		refreshInfo(playlistState);
	}
}
  
function onPlayerStateChange(event) {
	if (event.data == 0) {
		playlistState = (playlistState + 1) % playlistMapObject.length;
		loadNextSong(playlistState, 0);
	}
}
function stopVideo() {
	player.stopVideo();
}
  
var soundcloud = SC.Widget(document.getElementById('scplayer'));
	soundcloud.bind(SC.Widget.Events.FINISH, function() {
		playlistState = (playlistState + 1) % playlistMapObject.length;
		loadNextSong(playlistState, 0);
	});


// add functionallity to prev and next buttons
let previousButton = document.getElementById('previous');
	previousButton.addEventListener('click', function (e) {
		if(playlistState > 0){
			playlistState = (playlistState - 1) % playlistMapObject.length;
			loadNextSong(playlistState, 0);
		}
	});
let nextBoutton = document.getElementById('next');
	nextBoutton.addEventListener('click', function (e) {
		playlistState = (playlistState + 1) % playlistMapObject.length;
		loadNextSong(playlistState, 0);
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
		loadNextSong(index, 0);
		playlistState = index;
  	});
});

// autoplay first song in row
(function () {
	loadNextSong(playlistState, 0);
})()

function loadNextSong(index, trycounter){
	hideAllPlayers();
	if(!isEmpty(player)){
		player.pauseVideo();
	}
	soundcloud.pause();
	if(document.getElementById('chooseYtPlayer').checked) {
		if(!isEmpty(playlistMapObject[index].ytlink)){
			document.getElementById("ytplayerdiv").style.visibility = "visible";
			document.getElementById("ytplayerdiv").style.display = "block";
	 		player.loadVideoById(playlistMapObject[index].ytlink, 0, "large");
		}else{
			trycounter++;
			if(trycounter >= document.getElementsByName('playerChooser').length){
				playlistState = (playlistState + 1) % playlistMapObject.length;
	  			loadNextSong(playlistState, 0);
			}else{				
	  			document.getElementById('chooseScPlayer').checked = true;
	  			loadNextSong(index, trycounter);
			}
		}
	}else if(document.getElementById('chooseScPlayer').checked) {
		if(!isEmpty(playlistMapObject[index].sclink)){
			document.getElementById("scplayerdiv").style.visibility = "visible";
			document.getElementById("scplayerdiv").style.display = "block";
			soundcloud.load('https%3A//api.soundcloud.com/tracks/' + playlistMapObject[index].sclink);
			soundcloud.bind(SC.Widget.Events.READY, function() {
				soundcloud.play();
				soundcloud.unbind(SC.Widget.Events.READY);
			});
		}else{
			trycounter++;
			if(trycounter >= document.getElementsByName('playerChooser').length){
				playlistState = (playlistState + 1) % playlistMapObject.length;
	  			loadNextSong(playlistState, 0);
			}else{				
	  			document.getElementById('chooseYtPlayer').checked = true;
	  			loadNextSong(index, trycounter);
			}
		}
	}
	refreshInfo(index);
}

function hideAllPlayers(){
	document.getElementById("ytplayerdiv").style.visibility = "hidden";
	document.getElementById("scplayerdiv").style.visibility = "hidden";
	document.getElementById("ytplayerdiv").style.display = "none";
	document.getElementById("scplayerdiv").style.display = "none";
}

function isEmpty(e) {
	switch (e) {
		case "":
	    	return true;
	    case "null":
	    	return true;
	    case 0:
	    	return true;
	    case "0":
	    	return true;
	    case null: 
	    	return true;
	    case false:
	    	return true;
	    case typeof e == "undefined":
	    	return true;
	    case typeof e === "undefined":
	      	return true;
	    default:
	      	return false;
	}
}
 
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