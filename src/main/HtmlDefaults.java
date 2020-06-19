package main;

public class HtmlDefaults {

	public static String generateHtmlHeader() {
		return "<nav class=\"navbar navbar-expand-md navbar-dark bg-dark\">\r\n" + 
				"	<a class=\"navbar-brand\" href=\"player.jsp\">FeelMusic</a> \r\n" + 
				"	<a class=\"nav-link nav-item text-light \" href=\"player.jsp\">Home</a> \r\n" + 
				"	<a class=\"nav-link nav-item text-light\" href=\"createSong.jsp\">Create Song</a> \r\n" + 
				"	<a class=\"nav-link nav-item text-light\" href=\"createPlaylist.jsp\">Create Playlist</a> \r\n" + 
				"	<a class=\"nav-link nav-item text-light \"href=\"createArtist.jsp\">Create Artist</a> \r\n" + 
				"	<a class=\"nav-link nav-item text-light\" href=\"impressum.jsp\">Impressum</a>\r\n" + 
//				"	<a class=\"nav-link nav-item text-light\" href=\"datenschutz.jsp\">Datenschutz</a>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"	<button class=\"btn btn-outline-success px-2 px-3 mx-3 my-2 my-sm-0\"\">LogIn</button>\r\n" + 
				"\r\n" + 
				"</nav>";
	}
	
}
