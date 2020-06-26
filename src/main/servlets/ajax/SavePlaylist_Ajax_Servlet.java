package main.servlets.ajax;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.dao.Database;

/**
 * Servlet implementation class SavePlaylist_Servlet
 */
@WebServlet("/SavePlaylist_Ajax_Servlet")
public class SavePlaylist_Ajax_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Database db;

    public SavePlaylist_Ajax_Servlet() {
        super();
        if (db == null) {
			try {
				db = Database.getDatabase();				
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		} 
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("userid");
		String playlistname = request.getParameter("playlistname");				
		String playlist = request.getParameter("playlist");	
		
		String ajaxRet = "failed";		
		
		if(playlist != null && user != null && !user.trim().isEmpty()
		&& playlistname != null && !playlistname.trim().isEmpty()) {
		
			String splitted[] = playlist.split(";");
			if(splitted.length > 0) {
				int userid = Integer.parseInt(user);
				
				int songs[] = new int[splitted.length];
				for(int i = 0; i < splitted.length; i++) {
					songs[i] = Integer.parseInt(splitted[i]);
				}
				
				if(db != null) {
					try {
						ajaxRet = Integer.toString(db.insertPlaylist(playlistname, userid, songs));
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
			}		
		}
		response.setContentType("text/plain");
		response.getWriter().write(ajaxRet);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
