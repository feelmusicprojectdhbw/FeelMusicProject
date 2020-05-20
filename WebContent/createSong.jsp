
 <!doctype html>
<html lang="de">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <title>createSong</title>
</head>

<body>
  <nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <a class="navbar-brand" href="player.jsp">FellMusic</a>
    <a class="nav-link nav-item text-light " href="player.jsp">Home</a>
    <a class="nav-link nav-item text-light" href="createSong.jsp">Create Song</a>  
    <a class="nav-link nav-item text-light" href="createPlaylist.jsp">Create Playlist</a>
    <a class="nav-link nav-item text-light " href="createArtist.jsp">Create Artist</a> 
    <a class="nav-link nav-item text-light" href="impressum.jsp">Impressum</a>
    <a class="nav-link nav-item text-light"  href="datenschutz.jsp">Datenschutz</a>
                  
       

        <button class="btn btn-outline-success px-2 px-3 mx-3 my-2 my-sm-0" ">LogIn</button>

</nav>

<div class=" container ">

    <form class=" form-signin ">
      <h2 class=" form-signin-heading ">Create Song</h2>

      <label for=" inputSong " class=" sr-only ">Song</label>
      <input type=" text " id=" inputSong " class=" form-control " placeholder=" Song " required autofocus>
      <label  class=" "> Artist
        <select  class=" form-control ">   
             <option>Heino</option>
          <option>Michael Jackson</option>
          <option>Tom Waits</option>
          <option>Nina Hagen</option>
          <option>Marianne Rosenberg</option>
        </select>
      </label>
      <label > Co-Artist
        <select   class=" form-control " >    
          <option>Heino</option>
          <option>Michael Jackson</option>
          <option>Tom Waits</option>
          <option>Nina Hagen</option>
          <option>Marianne Rosenberg</option>
        </select>
      </label>


      <label for=" releaseDate " class=" sr-only ">Releasedate</label>
      <input type=" datetime " id=" releaseDate " class=" form-control " placeholder=" releaseDate " required>

      <label for=" inputAlbum " class=" sr-only ">Song</label>
      <input type=" text " id=" inputAlbum " class=" form-control " placeholder=" Album " required autofocus>

      <label for=" inputLabel " class=" sr-only ">Label</label>
      <input type=" text " id=" inputLabel " class=" form-control " placeholder=" Label " required autofocus>
      <label for=" inputLanguage " class=" sr-only ">Language</label>
      <input type=" text " id=" inputLanguage " class=" form-control " placeholder=" Language " required autofocus>

      <label for=" inputytLink " class=" sr-only ">Youtube Link</label>
      <input type=" url " id=" inputLink " class=" form-control " placeholder=" ytLink " >
      <label for=" inputSfLink " class=" sr-only ">Spotify Link</label>
      <input type=" url " id=" inputLink " class=" form-control " placeholder=" sfLink " >
      <label for=" inputScLink " class=" sr-only ">Soundcloud Link</label>
      <input type=" url " id=" inputLink " class=" form-control " placeholder=" scLink " >


      
      <button class=" btn btn-lg btn-primary btn-block " type=" submit ">Send</button>
      <button class=" btn btn-lg btn-primary btn-block " type=" reset ">Delete</button>
    </form>
  </body>
  
  <footer class="footer container-fluid text-center text-md-left bg-dark text-light py-2 bottom-0">
    <div class="container">
        <span class="text-muted"> <p>&copy; Feel Music 2020    All rights reserved</p></span>
    </div>
</footer>

</html>