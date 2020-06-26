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

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login_Servlet")
public class Login_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static Login_Servlet me;      
	private Database db;

    public Login_Servlet() {
        super();
        Login_Servlet.me = this;
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mailaddress = request.getParameter("inputmailaddress");
		String password = request.getParameter("inputpassword");
//		String remember = request.getParameter("rlabel");
		
		if (mailaddress != null && !mailaddress.isEmpty() &&
			password != null && !password.isEmpty()) {
			
			if(db != null) {
				try {
					User user = db.checkUser(mailaddress, User.encryptPassword64Digit(password, mailaddress));
					if(user == null) {
						request.setAttribute("loginfailed", Boolean.valueOf(true));
						request.getRequestDispatcher("login.jsp").forward(request, response);
						return;
					}else {
						HttpSession session=request.getSession();  
						session.setAttribute("user",user); 
						request.getRequestDispatcher("userpage.jsp").forward(request, response);
						//wenn remember null, dann nicht gecheckt, sonst value "remember-me"
				}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}else{
			doGet(request, response);			
		}				
	}

	
	public static Login_Servlet getMe() {
		return me;
	}

	public static ServletContext getServerContext() {
		return me.getServletContext();
	}

}
