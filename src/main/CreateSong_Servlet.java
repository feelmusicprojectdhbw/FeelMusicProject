package main;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.dao.Database;
import main.obj.Album;
import main.obj.Feelings;
import main.obj.Genres;
import main.obj.Languages;
import main.obj.Styles;
import main.tools.LinkUtils;
import main.tools.Validations;

/**
 * Servlet implementation class CreateSong_Servlet
 */
@WebServlet("/CreateSong_Servlet")
public class CreateSong_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private Database db;
    public CreateSong_Servlet() {
        super();
        if (db == null) {
			try {
				db = Database.getDatabase();
				if(Genres.getGenres() == null || Genres.getGenres().length == 0) {
					Genres.requery(db);
				}
				if(Languages.getLanguages() == null || Languages.getLanguages().length == 0) {
					Languages.reloadLanguages(db);
				}
				if(Feelings.getFeelings() == null || Feelings.getFeelings().length == 0) {
					Feelings.reloadFeelings(db);
				}
				if(Styles.getStyles() == null || Styles.getStyles().length == 0) {
					Styles.reloadStyles(db);
				}
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
		response.sendRedirect("createSong.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String inputSong = request.getParameter("inputSong");
		String artist = request.getParameter("artist");
		Album album = new Album(1, "Single", 0);
		String releasedate = request.getParameter("releaseDate");
		String genre = request.getParameter("inputGenre");
		String inputLabel = request.getParameter("inputLabel");
		String inputLanguage = request.getParameter("inputLanguage");
		String feeling = request.getParameter("selectedFeelings");
		String style = request.getParameter("selectedStyles");
		
		String ytLink = LinkUtils.parseYoutubeLink(request.getParameter("inputYtLink"));
		String sfLink = LinkUtils.parseSpotifyLink(request.getParameter("inputSfLink"));
		String scLink =  LinkUtils.parseSoundCloudLink(request.getParameter("inputScLink"));
		
		String coartist1 = request.getParameter("coartist1");
		String coartist2 = request.getParameter("coartist2");
		String coartist3 = request.getParameter("coartist3");
		String coartist4 = request.getParameter("coartist4");
		
		if (inputSong != null && !inputSong.isBlank() &&
			artist != null && !artist.isBlank() &&
			coartist1 != null && !coartist1.isBlank() &&
			coartist2 != null && !coartist2.isBlank() &&
			coartist3 != null && !coartist3.isBlank() &&
			coartist4 != null && !coartist4.isBlank() &&
			releasedate != null && !releasedate.isBlank() &&
			inputLabel != null && !inputLabel.isBlank() &&
			inputLanguage != null && !inputLanguage.isBlank() &&
			feeling != null && !feeling.isBlank() &&
			(ytLink != null && !ytLink.isBlank()||
			 sfLink != null && !sfLink.isBlank()||
			 scLink != null && !scLink.isBlank())) {
			
			int artistId = Integer.parseInt(artist.split(";")[1]);
			int date;
			try {
				date = Integer.parseInt(releasedate.replace("-", ""));
			}catch(NumberFormatException e) {
				date = Integer.parseInt(new SimpleDateFormat("yyyyMMdd").format(new GregorianCalendar().getTime()));
			}
			int labelID = Integer.parseInt(inputLabel.split(";")[1]);
			
			int coArtist1ID = Integer.parseInt(coartist1.split(";")[1]);
			int coArtist2ID = Integer.parseInt(coartist2.split(";")[1]);
			int coArtist3ID = Integer.parseInt(coartist3.split(";")[1]);
			int coArtist4ID = Integer.parseInt(coartist4.split(";")[1]);
			
			int genreID = Genres.getGenreID(genre);
			int languageID = Languages.getLanguageID(inputLanguage);
			
			String[] feelings = feeling.split(";");
			int[] feelingIDs = new int[feelings.length];
			for(int i = 0; i < feelings.length; i++) {
				int id = Feelings.getFeelingID(feelings[i]);
				feelingIDs[i] = id;
			}
			
			String[] styles = style.split(";");
			int[] styleIDs = new int[styles.length];
			for(int i = 0; i < styles.length; i++) {
				int id = Styles.getStyleID(styles[i]);
				styleIDs[i] = id;
			}
			
				if(db != null) {
					long generatedSongId = 0;
					try {
						generatedSongId = db.insertSong(date, inputSong, artistId, album.getId(), genreID, labelID, languageID, ytLink, sfLink, scLink);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					if(generatedSongId != 0) {
						int[] defaultStyles = null;
						try {
							defaultStyles = db.getStyleIDsByGenre(genreID);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						if(defaultStyles != null) {
							try {
								db.insertStylesForSong(generatedSongId, Validations.entferneDoppelte(styleIDs, defaultStyles));
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}else {
							System.out.println("No default Styles loaded.");
						}
						try {
							db.insertFeelingsForSong(generatedSongId, Validations.entferneDoppelte(feelingIDs));
						} catch (SQLException e) {
							e.printStackTrace();
						}
						
						try {
							db.insertCoartistsForSong(generatedSongId, Validations.entferneDoppelte(coArtist1ID, coArtist2ID, coArtist3ID, coArtist4ID));
						} catch (SQLException e) {
							e.printStackTrace();
						}						
						
					}else {
						System.out.println("Song SongID generated.");
					}
				}			
		}
		
		
		doGet(request, response);
	}

}
