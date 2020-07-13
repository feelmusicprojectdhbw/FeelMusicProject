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
import main.obj.Playlist;
import main.obj.Styles;

/**
 * Servlet implementation class CreatePlaylist_Servlet
 */
@WebServlet("/CreatePlaylist")
public class CreatePlaylist_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Database db;
	private static CreatePlaylist_Servlet me;

	public CreatePlaylist_Servlet() {
		super();
		CreatePlaylist_Servlet.me = this;
		if (db == null) {
			try {
				db = Database.getDatabase();
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
	
	public static ServletContext getServerContext() {
		return me.getServletContext();
	}
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("createPlaylist.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String selectedGenres = request.getParameter("selectedGenres");
		String blockedGenres = request.getParameter("blockedGenres");
		String selectedFeelings = request.getParameter("selectedFeelings");
		String blockedFeelings = request.getParameter("blockedFeelings");
		String selectedStyles = request.getParameter("selectedStyles");
		String blockedStyles = request.getParameter("blockedStyles");
		String selectedLanguages = request.getParameter("selectedLanguages");
		String blockedLanguages = request.getParameter("blockedLanguages");
		String from = request.getParameter("fromdate");
		String until = request.getParameter("untildate");
		
		int ifrom = 0;
		int iuntil = Integer.MAX_VALUE;		
		String selGen[] = null;
		int iSelGen[] = null;		
		String bloGen[] = null;
		int iBloGen[] = null;		
		String selFee[] = null;
		int iSelFee[] = null;
		String bloFee[] = null;
		int iBloFee[] = null;
		String selSty[] = null;
		int iSelSty[] = null;
		String bloSty[] = null;
		int iBloSty[] = null;
		String selLan[] = null;
		int iSelLan[] = null;
		String bloLan[] = null;
		int iBloLan[] = null;
		
		
		if(selectedGenres != null && !selectedGenres.isBlank()) {
			selGen = selectedGenres.split(";");
			iSelGen = new int[selGen.length];
			for(int i = 0; i < iSelGen.length; i++) {
				iSelGen[i] = Genres.getGenreID(selGen[i]);
			}
		}
		
		if(blockedGenres != null && !blockedGenres.isBlank()) {
			bloGen = blockedGenres.split(";");
			iBloGen = new int[bloGen.length];
			for(int i = 0; i < iBloGen.length; i++) {
				iBloGen[i] = Genres.getGenreID(bloGen[i]);
			}
		}
		
		if(selectedFeelings != null && !selectedFeelings.isBlank()) {
			selFee = selectedFeelings.split(";");
			iSelFee = new int[selFee.length];
			for(int i = 0; i < iSelFee.length; i++) {
				iSelFee[i] = Feelings.getFeelingID(selFee[i]);
			}
		}
		
		if(blockedFeelings != null && !blockedFeelings.isBlank()) {
			bloFee = blockedFeelings.split(";");
			iBloFee = new int[bloFee.length];
			for(int i = 0; i < iBloFee.length; i++) {
				iBloFee[i] = Feelings.getFeelingID(bloFee[i]);
			}
		}
		
		if(selectedStyles != null && !selectedStyles.isBlank()) {
			selSty = selectedStyles.split(";");
			iSelSty = new int[selSty.length];
			for(int i = 0; i < iSelSty.length; i++) {
				iSelSty[i] = Styles.getStyleID(selSty[i]);
			}
		}
		
		if(blockedStyles != null && !blockedStyles.isBlank()) {
			bloSty = blockedStyles.split(";");
			iBloSty = new int[bloSty.length];
			for(int i = 0; i < iBloSty.length; i++) {
				iBloSty[i] = Styles.getStyleID(bloSty[i]);
			}
		}
		
		if(selectedLanguages != null && !selectedLanguages.isBlank()) {
			selLan = selectedLanguages.split(";");
			iSelLan = new int[selLan.length];
			for(int i = 0; i < iSelLan.length; i++) {
				iSelLan[i] = Languages.getLanguageID(selLan[i]);
			}
		}
		
		if(blockedLanguages != null && !blockedLanguages.isBlank()) {
			bloLan = blockedLanguages.split(";");
			iBloLan = new int[bloLan.length];	
			for(int i = 0; i < iBloLan.length; i++) {
				iBloLan[i] = Languages.getLanguageID(bloLan[i]);
			}
		}
				
		if(from != null && !from.isBlank()) {
			try {
				ifrom = Integer.parseInt(from.replace("-", ""));
			}catch(NumberFormatException e) {}
		}
		if(until != null && !until.isBlank()) {
			try {
				iuntil = Integer.parseInt(until.replace("-", ""));
			}catch(NumberFormatException e) {}
		}
		
		if(ifrom == 0 && iuntil == Integer.MAX_VALUE && iSelGen == null && iBloGen == null && iSelFee == null && iBloFee == null 
													 && iSelSty == null && iBloSty == null && iSelLan == null && iBloLan == null) {
			doGet(request, response);
		}else {
			try {
				request.setAttribute("playlist", new Playlist(0, "New Playlist", null, db.getPlaylistcreationSongs(new int[] {ifrom, iuntil}, iSelGen, iBloGen, iSelFee, iBloFee, iSelSty, iBloSty, iSelLan, iBloLan)));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("player.jsp").forward(request, response);
		}
	}
	
	public static CreatePlaylist_Servlet getMe() {
		return me;
	}
}
