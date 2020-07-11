package main.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import main.obj.Artist;
import main.obj.Song;
import main.obj.User;
import main.servlets.CreateArtist_Servlet;
import main.servlets.CreateLabel_Servlet;
import main.servlets.CreatePlaylist_Servlet;
import main.servlets.CreateSong_Servlet;
import main.servlets.FeelMusic_Servlet;
import main.servlets.Login_Servlet;
import main.servlets.Logout_Servlet;
import main.servlets.SearchSong_Servlet;
import main.servlets.Signup_Servlet;
import main.servlets.UserControl_Servlet;

public class HtmlDefaults {
	
	private static String navBar;
	private static String header;
	private static String footer;
	private static String notLoggedIn;

	
	public static String generateHtmlNavbar(User user) {
		StringBuilder addition = new StringBuilder(""); 
		if(user != null) {
			if(user.getUsertype().getId() == 1) { //Admin
				addition.append("<a class=\"nav-link nav-item text-light\" href=\"CreateSong\">Song hinzuf&uumlgen</a>");
				addition.append("<a class=\"nav-link nav-item text-light\"href=\"CreateArtist\">K&uumlnstler anlegen</a>");
				addition.append("<a class=\"nav-link nav-item text-light\"href=\"CreateLabel\">Label anlegen</a>");
				addition.append("<a class=\"nav-link nav-item text-light\"href=\"UserControl\">Benutzerverwaltung</a>");
			}else if(user.getUsertype().getId() == 3) { //Moderator
				addition.append("<a class=\"nav-link nav-item text-light\" href=\"CreateSong\">Song hinzuf&uumlgen</a>");
				addition.append("<a class=\"nav-link nav-item text-light\"href=\"CreateArtist\">K&uumlnstler anlegen</a>");
				addition.append("<a class=\"nav-link nav-item text-light\"href=\"CreateLabel\">Label anlegen</a>");				
				addition.append("<a class=\"nav-link nav-item text-light\"href=\"UserControl\">Benutzerverwaltung</a>");
			}else if(user.getUsertype().getId() == 4) { //Artist
				addition.append("<a class=\"nav-link nav-item text-light\" href=\"CreateSong\">Song hinzuf&uumlgen");				
			}else if(user.getUsertype().getId() == 5) { //Label
			}
			addition.append("<a class=\"nav-link nav-item text-light\" href=\"userpage.jsp\">Mein Konto</a>");
			addition.append("<a href=\"Logout\"><button class=\"btn btn-outline-success px-2 px-3 mx-3 my-2 my-sm-0\">Abmelden</button></a>");
		}else {
			addition.append("<a href=\"Login\"><button class=\"btn btn-outline-success px-2 px-3 mx-3 my-2 my-sm-0\">Anmelden</button></a>");
		}
		
		addition.append("</nav>");		
		if(navBar != null && !navBar.isBlank()) {
			return navBar + addition.toString();
		}else {
			return (navBar = readFile("html\\navBar.html")) + addition.toString();
		}

	}
	public static String generateHtmlHeader() {
		if(header != null && !header.isBlank()) {
			return header;
		}else {
			return header = readFile("html\\header.html");
		}
	}
	public static String generateHtmlFooter() {
		if(footer != null && !footer.isBlank()) {
			return footer;
		}else {
			return footer = readFile("html\\footer.html");
		}
	}

	
	public static String readFile(String path) {
		
		File f = null;
		if(FeelMusic_Servlet.getMe() != null) {
			f = new File(FeelMusic_Servlet.getServerContext().getRealPath(path));			
		}else if(CreatePlaylist_Servlet.getMe() != null) {
			f = new File(CreatePlaylist_Servlet.getServerContext().getRealPath(path));
		}else if(CreateArtist_Servlet.getMe() != null) {
			f = new File(CreateArtist_Servlet.getServerContext().getRealPath(path));
		}else if(CreateLabel_Servlet.getMe() != null) {
			f = new File(CreateLabel_Servlet.getServerContext().getRealPath(path));
		}else if(CreateSong_Servlet.getMe() != null) {
			f = new File(CreateSong_Servlet.getServerContext().getRealPath(path));
		}else if(SearchSong_Servlet.getMe() != null) {
			f = new File(SearchSong_Servlet.getServerContext().getRealPath(path));
		}else if(Login_Servlet.getMe() != null) {
			f = new File(Login_Servlet.getServerContext().getRealPath(path));
		}else if(Signup_Servlet.getMe() != null) {
			f = new File(Signup_Servlet.getServerContext().getRealPath(path));
		}else if(Logout_Servlet.getMe() != null) {
			f = new File(Logout_Servlet.getServerContext().getRealPath(path));
		}else if(UserControl_Servlet.getMe() != null) {
			f = new File(UserControl_Servlet.getServerContext().getRealPath(path));
		}
		String s = "";		
		if(f != null) {
			try(BufferedReader br = new BufferedReader(new FileReader(f))) {
			    StringBuilder sb = new StringBuilder();
			    String line = br.readLine();
			    
			    int linesWritten = 0;
	
			    while (line != null) {
			    	if(linesWritten++ > 0) {
			    		sb.append(System.lineSeparator());
			    	}
			        sb.append(line);
			        line = br.readLine();
			    }
			    s = sb.toString();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		return s;
	}
	
	public static String createSongtable(Song[] songs) {     		
		StringBuilder sb = new StringBuilder();
		sb.append("<div id=\"table-wrapper\">");
		sb.append("<div id=\"table-scroll\">");
		sb.append("<table id=\"tablePreview\" class=\"table table-hover\">");
		sb.append("<table>");
		for(Song s : songs)	{
			sb.append("<tr>");
			sb.append("<td>");
			sb.append(s.getId());
			sb.append("</td>");
			sb.append("<td>");			
			sb.append(s.getName());
			sb.append("</td>");
			sb.append("<td>");			
			sb.append(s.getArtist().getName());
			int i = 0;
			for(Artist a : s.getCoArtists().getCoartists()) {
				if(i++ == 0) {
					sb.append(" feat. ");
				}else {
					sb.append(", ");
				}
				sb.append(a.getName());
			}
			sb.append("</td>");
			sb.append("</tr>");
		}
		sb.append("</table>");
		sb.append("</div>");
		sb.append("</div>");
		sb.append("</div>");
		return sb.toString();
	}
	
	public static String generateSongSearchResults(Song[] songs) {
		StringBuilder strb = new StringBuilder();
		strb.append("<p>");
		strb.append("<h3>Suchergebnisse: </h3>");
		strb.append("</p>");
		 if(songs.length > 0){
			 for(Song s : songs){
			 	strb.append("<div class=\"resultsongdiv\">");
			 	strb.append("<label>" + s.getName() + " - ");
		 		if(s.getArtist().getLink() != null && s.getArtist().getLink() != "null"){ 
		 			strb.append("<a href=\"" + s.getArtist().getLink() + "\" title=\"" + s.getArtist().getName() + "\" target=\"_blank\" rel=\"noopener noreferrer\">" + s.getArtist().getName() + "</a>");
	 			}else{
	 				strb.append(s.getArtist().getName());
 				}
		 		  if(s.getCoArtists().getCoartists() != null && s.getCoArtists().getCoartists().length != 0){	
		 			int i = 0;
		 			for(Artist a : s.getCoArtists().getCoartists()){
			 			if(i == 0){
			 				i++;
			 				strb.append(" feat. "); 
			 			}else{ 
			 				strb.append(", "); 
			 			}
			 			if(a.getLink() != null && a.getLink() != "null"){ 
			 				strb.append("<a href=\"" + a.getLink() + "\" title=\"" + a.getName() +"\" target=\"_blank\" rel=\"noopener noreferrer\">" + a.getName() + "</a>");
			 			}else{
			 				strb.append(a.getName());				 			}
						}
		 		  }					 		
		 		 strb.append("</label>");
		 		strb.append("</div>");
			 }
			 
		 }else {
			 //No songs found!
		 }
		 //strb.append("<label>Don\'t we have your favourite songs yet? Add them <a href=\"createSong.jsp\" title=\"Add Song\">here</a>!</label>");
		 strb.append("<label> Song nicht gefunden? <a href=\"createSong.jsp\" title=\"Add Song\">Hier</a> kannst du ihn hinzuf&uumlgen!</label>");
		 return strb.toString();

	}
	
	public static String generateNotLoggedInMessage() {
		if(notLoggedIn != null && !notLoggedIn.isBlank()) {
			return notLoggedIn;
		}else {
			return notLoggedIn = readFile("html\\notLoggedIn.html");
		}
	}
	
	
	
}
