
    <!doctype html>
    <html lang="de">
    
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    
        <title>createLabel</title>
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
        <h2 class=" form-signin-heading ">Create Label</h2>
        <label for=" inputLabel" class=" sr-only "> Label</label>
        <input type=" text " id=" inputLabel " class=" form-control " placeholder=" Artist " required autofocus>
  
        <label for=" inputLink " class=" sr-only ">Link of the label</label>
        <input type=" url " id=" inputLink " class=" form-control " placeholder=" Link " required>
  
         
      
      <button class=" btn btn-lg btn-primary btn-block " type=" submit ">Send</button>
      <button class=" btn btn-lg btn-primary btn-block " type=" reset ">Delete</button>
    </form>
  
    <footer class="footer container-fluid text-center text-md-left bg-dark text-light py-2 bottom-0">
      <div class="container">
          <span class="text-muted"> <p>&copy; Feel Music 2020    All rights reserved</p></span>
      </div>
  </footer>

</body>

</html>