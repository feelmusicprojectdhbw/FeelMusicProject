package main.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.dao.Database;
import main.obj.User;
import main.obj.Usertype;

/**
 * Servlet implementation class Signup_Servlet
 */
@WebServlet("/Signup_Servlet")
public class Signup_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static Signup_Servlet me;      
	private Database db;

	public Signup_Servlet() {
        super();
        Signup_Servlet.me = this;
        if (db == null) {
			try {
				db = Database.getDatabase();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Ist bereits erstellt");
		}    
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("signup.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("inputusername");
		String mailaddress = request.getParameter("inputemail");
		String password = request.getParameter("inputpassword");
		String birthdate = request.getParameter("inputbirthdate");
		
		if( username != null && !username.isBlank() &&
			mailaddress != null && !mailaddress.isBlank() &&
			password != null && !password.isBlank() &&
			birthdate != null && !birthdate.isBlank()) {
			if(db != null) {	
				try {			
					boolean userexists = db.checkUsernameAlreadyExists(username);
					boolean mailaddressexists = db.checkUsermailAlreadyExists(mailaddress);
					if(userexists || mailaddressexists) {
						request.setAttribute("useralreadyexists", Boolean.valueOf(userexists));
						request.setAttribute("mailalreadyexists", Boolean.valueOf(mailaddressexists));
						request.setAttribute("username", username);
						request.setAttribute("mailaddress",mailaddress);
						request.setAttribute("birthdate", birthdate);
						request.getRequestDispatcher("signup.jsp").forward(request, response);
					}else {
						int iBirthdate = 0;
						try {
							iBirthdate = Integer.parseInt(birthdate.replace("-", ""));
						}catch(NumberFormatException e) {}
						String encrypedPassword = User.encryptPassword64Digit(password, mailaddress);
						int id = db.signUpUser(username, encrypedPassword, mailaddress, iBirthdate);
						if(id != -1) {
							User user = new User(id, username, mailaddress, iBirthdate, new Usertype(2, "Default"));
							HttpSession session=request.getSession();  
							session.setAttribute("user", user);
							response.sendRedirect("userpage.jsp");
						}else {
							request.setAttribute("loginfailed", Boolean.valueOf(true));
							request.getRequestDispatcher("signup.jsp").forward(request, response);
						}
						
					}									
				} catch (SQLException e1) {
					request.setAttribute("loginfailed", Boolean.valueOf(true));
					request.getRequestDispatcher("signup.jsp").forward(request, response);
				}
			}		
		}else{		
			doGet(request, response);
		}
	}
	
	public static Signup_Servlet getMe() {
		return me;
	}

	public static ServletContext getServerContext() {
		return me.getServletContext();
	}

}
