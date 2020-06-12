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
    
    <title>Home</title>
    
</head>

<body>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
        <a class="navbar-brand" href="player.jsp">FeelMusic</a>
        <a class="nav-link nav-item text-light " href="player.jsp">Home</a>
        <a class="nav-link nav-item text-light" href="createSong.jsp">Create Song</a>  
        <a class="nav-link nav-item text-light" href="createPlaylist.jsp">Create Playlist</a>
        <a class="nav-link nav-item text-light " href="createArtist.jsp">Create Artist</a> 
        <a class="nav-link nav-item text-light" href="impressum.jsp">Impressum</a>
        <a class="nav-link nav-item text-light"  href="datenschutz.jsp">Datenschutz</a>
                                
        <button class="btn btn-outline-success px-2 px-3 mx-3 my-2 my-sm-0" ">LogIn</button>
    
    </nav>

	<div class=" container">
      <!-- Example row of columns -->
      <div class="row">
        <div class="col-md">
          <h2>Player</h2>
          <iframe id="playeriFrame" frameborder="no" width="500" height="300"
            allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
          <p><button class="btn btn-outline-warning" id="previous">Previous</button>
            <button class="btn btn-outline-warning" id="next">Next</button></p>
        </div>

        <div class="col-md">
          <h2>Vorschau</h2>
          <div class='table-responsive'>
            <!--Table-->
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
  let playlistState = 0;
   let playlistMapObject = [
     <% Playlist p = (Playlist)request.getAttribute("playlist");
     if(p != null){
  		for (Song s : p.getSongs()) { %>
  		{link: "https://www.youtube.com/embed/<%= s.getLinks()[0] %>?&autoplay=1",  title: "<%= s.getName() %>", artist: "<%= s.getArtist().getName() %>"},
     <% } 
  	} %>
   ]

  // playlist map
/*  let playlistMapObject = [
    {link: "https://open.spotify.com/embed/track/02HJo9cBRCO12opAKdcZcJ", title: "Spotify Lied", artist: "Someone"},
    {link: "https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/723315721&color=%23ff5500&auto_play=true&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true", title: "Soundcloud lied", artist: "Someone"},
    {link: "https://www.youtube.com/embed/aYJStepXdbc?&autoplay=1", title: "Youtube Lied", artist: "Someone"},
  ]*/

  // if player finished song -> next song
  let player = document.getElementById('playeriFrame');
  player.addEventListener("onStateChange", function (state) {
    if (state === 0) {
      playlistState = (playlistState + 1) % playlistMapObject.length;
      player.src = playlistMapObject[playlistState].link;
    }
  });

  // add functionallity to prev and next buttons
  let previousButton = document.getElementById('previous');
  let nextBoutton = document.getElementById('next');
  previousButton.addEventListener('click', function (e) {
      playlistState = (playlistState - 1) % playlistMapObject.length;
      player.src = playlistMapObject[playlistState].link;
  });
  nextBoutton.addEventListener('click', function (e) {
      playlistState = (playlistState + 1) % playlistMapObject.length;
      player.src = playlistMapObject[playlistState].link;
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
    cell3.innerHTML = el.artist;

    row.style = 'cursor: pointer;';
    row.addEventListener('click', function () {
      player.src = playlistMapObject[index].link;
    })
  });

  // autoplay first song in row
  (function () {
    player.src = playlistMapObject[playlistState].link;
  })()
</script>
</html>