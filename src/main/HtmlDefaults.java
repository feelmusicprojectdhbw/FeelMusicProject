package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import main.obj.User;

public class HtmlDefaults {
	
	private static String navBar;
	private static String header;
	private static String footer;

	
	public static String generateHtmlNavbar(User user) {
		StringBuilder addition = new StringBuilder(""); 
		if(user != null) {
			if(user.getUsertype().getId() == 1) { //Admin
				addition.append("<a class=\"nav-link nav-item text-light\" href=\"CreateSong_Servlet\">Create Song</a>");
				addition.append("<a class=\"nav-link nav-item text-light\"href=\"CreateArtist_Servlet\">Create Artist</a>");
				addition.append("<a class=\"nav-link nav-item text-light\"href=\"CreateLabel_Servlet\">Create Label</a>");
			}else if(user.getUsertype().getId() == 3) { //Moderator
				addition.append("<a class=\"nav-link nav-item text-light\" href=\"CreateSong_Servlet\">Create Song</a>");
				addition.append("<a class=\"nav-link nav-item text-light\"href=\"CreateArtist_Servlet\">Create Artist</a>");
				addition.append("<a class=\"nav-link nav-item text-light\"href=\"CreateLabel_Servlet\">Create Label</a>");				
			}else if(user.getUsertype().getId() == 4) { //Artist
				addition.append("<a class=\"nav-link nav-item text-light\" href=\"CreateSong_Servlet\">Create Song</a>");				
			}else if(user.getUsertype().getId() == 5) { //Label
				
			}
			addition.append("<a href=\"Logout_Servlet\"><button class=\"btn btn-outline-success px-2 px-3 mx-3 my-2 my-sm-0\">LogOut</button></a>");
		}else {
			addition.append("<a href=\"Login_Servlet\"><button class=\"btn btn-outline-success px-2 px-3 mx-3 my-2 my-sm-0\">LogIn</button></a>");
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
	
}
