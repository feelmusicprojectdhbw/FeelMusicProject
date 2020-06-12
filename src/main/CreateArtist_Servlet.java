package main;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.dao.Database;

/**
 * Servlet implementation class CreateArtist_Servlet
 */
@WebServlet("/CreateArtist_Servlet")
public class CreateArtist_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Database db;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateArtist_Servlet() {
        super();
        if (db == null) {
			try {
				db = Database.getDatabase();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
        }
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("createArtist.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String creation = request.getParameter("createOrDelete");
		String artistToDelete = request.getParameter("artistToDelete");
		String name = request.getParameter("inputArtist");
		String link = request.getParameter("inputLink");
		
		if(creation != null) {
			if(creation.equalsIgnoreCase("create")) {
				
				try {
					if(!db.checkArtistAlreadyExists(name)) {
						db.insertArtist(name, link);
					}else {
						System.out.println("Artist with that Name already exists.");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(creation.equalsIgnoreCase("delete")){			
				if(artistToDelete != null && db != null) {
					String[] spl = artistToDelete.split(";");
					if(spl.length == 2) {
						try {
							db.deleteArtist(Integer.parseInt(spl[1]), spl[0]);
						}catch(Exception e) {
							e.printStackTrace();
						};
						
					}
				}
			}
		}
		doGet(request, response);
	}

}
