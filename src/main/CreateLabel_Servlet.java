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
 * Servlet implementation class CreateLabel_Servlet
 */
@WebServlet("/CreateLabel_Servlet")
public class CreateLabel_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Database db;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateLabel_Servlet() {
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
		response.sendRedirect("createLabel.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String creation = request.getParameter("createOrDelete");
		String labelToDelete = request.getParameter("labelToDelete");
		String name = request.getParameter("inputLabel");
		String link = request.getParameter("inputLink");
		
		if(creation != null) {
			if(creation.equalsIgnoreCase("create")) {
				try {
					db.insertLabel(name, link);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(creation.equalsIgnoreCase("delete")){			
				if(labelToDelete != null && db != null) {
					String[] spl = labelToDelete.split(";");
					if(spl.length == 2) {
						try {
							db.deleteLabel(Integer.parseInt(spl[1]), spl[0]);
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
