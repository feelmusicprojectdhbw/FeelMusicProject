package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.obj.Artist;
import main.obj.Genres;
import main.obj.Languages;
import main.obj.Song;

/**
 * Servlet implementation class FeelMusic_Servlet
 */
@WebServlet("/FeelMusic_Servlet")
public class FeelMusic_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private Database db;
    public FeelMusic_Servlet() {
        super();
        if(db == null) {
        	try {
				db = Database.connectToMySQL("localhost", "feelmusic", "root", "");
				Genres.requery(db);
				Languages.reloadLanguages(db);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
        }else {
        	System.out.println("Ist bereits erstellt");
        }
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		writer.println("<html>");
		writer.println("<head><title>Hello World Servlet</title></head>");
		writer.println("<body>");
		writer.println("	<h1>Hello World from a Sevlet!</h1>");
		try {
			ResultSet rs = db.getConnection().createStatement().executeQuery("select * from genres");
			while(rs.next()) {
				writer.println("<p>" + rs.getString("name") + "</p>");
			}
			writer.println("<h2>Song aus Datenbank </h2>");
			for(Song n : db.getAllSongsByArtist(new Artist(2, "Wildstylez", null))) {
				writer.println("<p>id: " + n.getId() + "<br>");
				writer.println("<p>Name: " + n.getName() + "<br>");
				writer.println("<p>Releasedate: " + n.getReleasedate() + "<br>");
				writer.println("<p>Album: " + n.getAlbum() + "<br>");
				writer.println("<p>Artist: " + n.getArtist() + "<br>");
				writer.println("<p>Coartists: " + n.getCoArtists() + "<br>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		writer.println("<body>");
		writer.println("</html>");
			
		writer.close();	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
