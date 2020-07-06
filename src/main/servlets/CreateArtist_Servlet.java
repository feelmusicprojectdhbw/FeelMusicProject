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

/**
 * Servlet implementation class CreateArtist_Servlet
 */
@WebServlet("/CreateArtist")
public class CreateArtist_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Database db;
	private static CreateArtist_Servlet me;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateArtist_Servlet() {
		super();
		CreateArtist_Servlet.me = this;
		if (db == null) {
			try {
				db = Database.getDatabase();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("createArtist.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("inputArtist");
		String link = request.getParameter("inputLink");

		if (name != null && !name.trim().isEmpty()) {
			try {
				if (!db.checkArtistAlreadyExists(name)) {
					db.insertArtist(name.trim(), link);
				} else {
					System.out.println("Artist with that Name already exists.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		doGet(request, response);
	}

	public static ServletContext getServerContext() {
		return me.getServletContext();
	}

	public static CreateArtist_Servlet getMe() {
		return me;
	}

}
