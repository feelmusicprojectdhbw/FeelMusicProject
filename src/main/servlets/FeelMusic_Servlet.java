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
import main.obj.Styles;

/**
 * Servlet implementation class FeelMusic_Servlet
 */
@WebServlet("/FeelMusic")
public class FeelMusic_Servlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private Database db;
	private static FeelMusic_Servlet me;

	public FeelMusic_Servlet() {
		super();
		FeelMusic_Servlet.me = this;
		if (db == null) {
			try {
				db = Database.getDatabase();
				// dump(db);
				Genres.requery(db);
				Languages.reloadLanguages(db);
				Feelings.reloadFeelings(db);
				Styles.reloadStyles(db);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Ist bereits erstellt");
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		response.sendRedirect("landingpage.jsp");  		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public static FeelMusic_Servlet getMe() {
		return me;
	}
	
	public static ServletContext getServerContext() {
		return me.getServletContext();
	}

}
