package main.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout_Servlet
 */
@WebServlet("/Logout")
public class Logout_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static Logout_Servlet me;      

    public Logout_Servlet() {
        super();
        Logout_Servlet.me = this;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();  
		session.invalidate(); 
		response.sendRedirect("logout.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public static Logout_Servlet getMe() {
		return me;
	}
	
	public static ServletContext getServerContext() {
		return me.getServletContext();
	}

}
