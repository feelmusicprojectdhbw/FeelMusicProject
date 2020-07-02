package main.servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FAQ_Servlet
 */
@WebServlet("/FAQ")
public class FAQ_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FAQ_Servlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String downloadFilename = request.getParameter("d");
		if(downloadFilename != null && !downloadFilename.trim().isEmpty()) {
	        // This should send the file to browser
	        try(InputStream in = request.getServletContext().getResourceAsStream("/downloads/" + downloadFilename);OutputStream out = response.getOutputStream()) {
	        	try {	
	              	byte[] buffer = new byte[1024];
	               
	              	int numBytesRead;
	              	while ((numBytesRead = in.read(buffer)) > 0) {
	                  	out.write(buffer, 0, numBytesRead);
	              	}
	        	}catch(NullPointerException e) {	        		 
	        		response.sendRedirect("faq.jsp");
	        		return;
	        	}
          		in.close();
          		// for example application/pdf, text/plain, text/html, image/jpg
		        response.setContentType("text/plain");

		        response.setHeader("Content-disposition","attachment; filename=" + downloadFilename);
  	        	out.flush();
        	}
		}else {
			response.sendRedirect("faq.jsp");  
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
