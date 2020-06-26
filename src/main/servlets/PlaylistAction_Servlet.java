package main.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.dao.Database;
import main.obj.Playlist;
import main.obj.User;

/**
 * Servlet implementation class LoadPlaylist_Servlet
 */
@WebServlet("/PlaylistAction_Servlet")
public class PlaylistAction_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Database db;
	
    public PlaylistAction_Servlet() {
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
		String action = request.getParameter("a");
		String playlistId = request.getParameter("p");
		String requireduserId = request.getParameter("u");	
		if(action.equals("play")) {
			if(playlistId != null && requireduserId != null) {
				User angemeldeterUser = (User) request.getSession().getAttribute("user");
				int reqUserId = Integer.parseInt(requireduserId);
				if(angemeldeterUser != null && angemeldeterUser.getId() == reqUserId) {
					if(db != null) {
						try {
							Playlist p = db.loadPlaylistByIDs(Integer.parseInt(playlistId), angemeldeterUser.getId());
							p.setUser(angemeldeterUser);
							request.setAttribute("playlist", p);
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					request.getRequestDispatcher("player.jsp").forward(request, response);
				}else {
					//Kein Zugriff auf die Playlist
				}
				
			}
		}else if(action.equals("delete")){
			if(playlistId != null && requireduserId != null) {
				User angemeldeterUser = (User) request.getSession().getAttribute("user");
				int reqUserId = Integer.parseInt(requireduserId);
				if(angemeldeterUser != null && angemeldeterUser.getId() == reqUserId) {
					String ret = "failed";
					if(db != null) {
						try {
							if(db.deletePlaylist(Integer.parseInt(playlistId), angemeldeterUser.getId())) {
								ret = "deleted";
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					response.setContentType("text/plain");
					response.getWriter().write(ret);
				}else {
					//Kein Zugriff auf die Playlist
				}
				
			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
