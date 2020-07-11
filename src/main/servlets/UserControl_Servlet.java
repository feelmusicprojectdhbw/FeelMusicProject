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
import main.obj.User;

/**
 * Servlet implementation class UserControl_Servlet
 */
@WebServlet("/UserControl")
public class UserControl_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	private Database db;
	private static UserControl_Servlet me;       

    public UserControl_Servlet() {
        super();
        UserControl_Servlet.me = this;
        if (db == null) {
			try {
				db = Database.getDatabase();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}   
    }

	/**
	 * AJAX Suchanfrage beantworten
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchUser = request.getParameter("inputSearch");
		if(searchUser != null && !searchUser.trim().isEmpty()) {
			response.setContentType("text/plain");
			try {
				if(db != null) {
					String userinfo = "failed";
					User foundUser = null;
					try {
						int foundUserId = Integer.parseInt(searchUser);
						foundUser = db.searchUserById(foundUserId);
					}catch(NumberFormatException e) {
						foundUser = db.searchUserByMail(searchUser);
					}
					if(foundUser != null) {
						StringBuilder sb = new StringBuilder();
						sb.append(foundUser.getId());
						sb.append(";");
						sb.append(foundUser.getUsername());
						sb.append(";");
						sb.append(foundUser.getEmailAddress());
						sb.append(";");
						sb.append(foundUser.getBirthdate());
						sb.append(";");
						sb.append(foundUser.getUsertype().getId());
						sb.append(";");
						if(foundUser.getUsertype().getId() == 4) {
							sb.append(db.getUserArtistZuordnung(foundUser.getId()));
						}else if(foundUser.getUsertype().getId() == 5) {
							sb.append(db.getUserLabelZuordnung(foundUser.getId()));
						}else {
							sb.append("0");
						}
						userinfo = sb.toString();
					}
					response.getWriter().write(userinfo);		
				}else {	
					response.getWriter().write("failed");
				}
			}catch(SQLException e) {
				response.getWriter().write("failed");
				e.printStackTrace();
			}
		}else{
			response.sendRedirect("usercontrol.jsp");
		}
	}

	/**
	 * Daten validieren und uebernehmen
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Änderungen übernehmen
		User angemeldeterUser = (User) request.getSession().getAttribute("user");
		if(angemeldeterUser != null) {
			if(angemeldeterUser.getUsertype().getId() == 1 || angemeldeterUser.getUsertype().getId() == 3) {
				int hiddenuserid = Integer.parseInt(request.getParameter("hiddenid"));		
				int neuerUsertype = Integer.parseInt(request.getParameter("usertypeselector"));
				int labelToConnect = Integer.parseInt(request.getParameter("labelToConnect"));	
				int artistToConnect = Integer.parseInt(request.getParameter("artistToConnect"));
				if(db != null) {
					try {
						if(angemeldeterUser.getUsertype().getId() == 3 && neuerUsertype != 1 && neuerUsertype != 3) {					
							db.deleteUserConnections(hiddenuserid);
							db.updateUsersUsertype(hiddenuserid, neuerUsertype);
							if(neuerUsertype == 4) {
								db.establishUserArtistConnection(hiddenuserid, artistToConnect);
							}else if(neuerUsertype == 5) {
								db.establishUserLabelConnection(hiddenuserid, labelToConnect);
							}
						}else if(angemeldeterUser.getUsertype().getId() == 1){
							db.deleteUserConnections(hiddenuserid);
							db.updateUsersUsertype(hiddenuserid, neuerUsertype);
							if(neuerUsertype == 4) {
								db.establishUserArtistConnection(hiddenuserid, artistToConnect);
							}else if(neuerUsertype == 5) {
								db.establishUserLabelConnection(hiddenuserid, labelToConnect);
							}
							doGet(request, response);
						}else {
							//Keine Berechtigung den User zu ändern.... Eigentlich gleich mal kicken weil er das Frontend umgangen hat XD 
							System.out.println();
							doGet(request, response);
						}
					} catch (SQLException e) {
						//Datenbankfehler
						e.printStackTrace();
						doGet(request, response);
					}
				}else {
					//Keine Datenbankverbindung 
					doGet(request, response);
				}
				
				
				
			}else {
				//Keine Berechtigung überhaupt user zu sehen.... Eigentlich gleich mal kicken XD 
				//Sollte aber produktiv nie erreicht werden;) 
				doGet(request, response);
			}
		}else {
			//Nicht angemeldet... Keine Berechtigung überhaupt user zu sehen.... Eigentlich gleich mal kicken XD 
			//Sollte aber produktiv nie erreicht werden;) 
			doGet(request, response);
		}
	}
	
	public static ServletContext getServerContext() {
		return me.getServletContext();
	}
	
	public static UserControl_Servlet getMe() {
		return me;
	}

}
