package main.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.dao.Database;
import main.obj.Feelings;
import main.obj.Genres;
import main.obj.Languages;
import main.obj.Song;
import main.obj.Styles;
import main.tools.HtmlDefaults;

/**
 * Servlet implementation class SearchSong_Servlet
 */
@WebServlet("/SearchSong")
public class SearchSong_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private Database db;
	private static SearchSong_Servlet me;      

    public SearchSong_Servlet() {
    	  super();
    	  SearchSong_Servlet.me = this;
          if (db == null) {
  			try {
  				db = Database.getDatabase();
  				if(Genres.getGenres() == null || Genres.getGenres().length == 0) {
  					Genres.requery(db);
  				}
  				if(Languages.getLanguages() == null || Languages.getLanguages().length == 0) {
  					Languages.reloadLanguages(db);
  				}
  				if(Feelings.getFeelings() == null || Feelings.getFeelings().length == 0) {
  					Feelings.reloadFeelings(db);
  				}
  				if(Styles.getStyles() == null || Styles.getStyles().length == 0) {
  					Styles.reloadStyles(db);
  				}
  			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
  				e.printStackTrace();
  			}
  		}   
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchSong = request.getParameter("inputSearch");
		String artist = request.getParameter("artist");
		
		if (searchSong != null && artist != null && !artist.isBlank()){
			response.setContentType("text/plain");
			try {
				if(db != null) {
						Song[] songs = null;
						if(Integer.parseInt(artist.split(";")[1]) != 1) {
							songs = db.searchForSongByNameAndArtist(searchSong, Integer.parseInt(artist.split(";")[1]));							
							response.getWriter().write(HtmlDefaults.generateSongSearchResults(songs));
						}else {
							songs = db.searchForSongByName(searchSong);
							response.getWriter().write(HtmlDefaults.generateSongSearchResults(songs));
						}
				
				}else {	
					response.getWriter().write("failed");
				}
			}catch(Exception e) {
				e.printStackTrace();
				response.getWriter().write("failed");
			}
		}else {
			response.sendRedirect("searchSong.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String searchSong = request.getParameter("inputSearch");
//		String artist = request.getParameter("artist");
//		
//		if (searchSong != null && artist != null && !artist.isBlank()){
//			try {
//				if(db != null) {
//						Song[] songs = null;
//						if(Integer.parseInt(artist.split(";")[1]) != 1) {
//							songs = db.searchForSongByNameAndArtist(searchSong, Integer.parseInt(artist.split(";")[1]));
//							request.setAttribute("results", songs);
//							request.getRequestDispatcher("searchSong.jsp").forward(request, response);
//						}else {
//							songs = db.searchForSongByName(searchSong);
//							request.setAttribute("results", songs);
//							request.getRequestDispatcher("searchSong.jsp").forward(request, response);
//						}
//				
//				}else {	
//					response.sendRedirect("searchSong.jsp");
//				}
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
	}
	
	public static ServletContext getServerContext() {
		return me.getServletContext();
	}
	
	public static SearchSong_Servlet getMe() {
		return me;
	}

}
