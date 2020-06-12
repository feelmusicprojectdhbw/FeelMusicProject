
   <!doctype html>
    <html lang="de">
    
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    
        <title>SignIn</title>
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
<div class=" container ">

    <form class=" form-signin ">
      <h2 class=" form-signin-heading ">Please sign in</h2>
      <label for=" inputEmail " class=" sr-only ">Email address</label>
      <input type=" email " id=" inputEmail " class=" form-control " placeholder=" Email address " required autofocus>
      <label for=" inputPassword " class=" sr-only ">Password</label>
      <input type=" password " id=" inputPassword " class=" form-control " placeholder=" Password " required>
      <div class=" checkbox ">
        <label>
          <input type=" checkbox " value=" remember-me " id=" rlabel "> Remember me
        </label>
      </div>
      <button class=" btn btn-lg btn-primary btn-block " type=" submit ">Sign in</button>
      <button class=" btn btn-lg btn-primary btn-block " herf="signup.html ">Register</button>
    </form>

  </div>
  <!-- /container -->
 
  
</body>

<footer class="footer container-fluid text-center text-md-left bg-dark text-light py-2 bottom-0">
  <div class="container">
      <span class="text-muted"> <p>&copy; Feel Music 2020    All rights reserved</p></span>
  </div>
</footer>

</html>