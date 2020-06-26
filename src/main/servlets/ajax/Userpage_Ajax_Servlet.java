package main.servlets.ajax;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.dao.Database;
import main.obj.Song;
import main.tools.HtmlDefaults;

/**
 * Servlet implementation class Userpage_Ajax_Servlet
 */
@WebServlet("/Userpage_Ajax_Servlet")
public class Userpage_Ajax_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private Database db;

    public Userpage_Ajax_Servlet() {
        super();
        
    	try {
			db = Database.getDatabase();				
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String playlistid = request.getParameter("playlistid");
		String ajaxRet = "";
		if(playlistid != null && !playlistid.trim().isEmpty()) {
			if(db != null) {
				Song[] songs = db.loadSongsByPlaylist (Integer.parseInt(playlistid));
				ajaxRet = HtmlDefaults.createSongtable(songs);
			}
		}
		response.setContentType("text/plain");
		response.getWriter().write(ajaxRet);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
