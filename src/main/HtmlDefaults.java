package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HtmlDefaults {
	
	private static String navBar;
	private static String header;
	private static String footer;

	
	public static String generateHtmlNavbar() {
		if(navBar != null) {
			return navBar;
		}else {
			return navBar = readFile("html\\navBar.html");
		}

	}
	public static String generateHtmlHeader() {
		if(header != null) {
			return header;
		}else {
			return header = readFile("html\\header.html");
		}
	}
	public static String generateHtmlFooter() {
		if(footer != null) {
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
