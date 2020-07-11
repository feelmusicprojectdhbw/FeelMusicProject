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
				// dump(db);
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

	
//	  private void dump(Database db2) throws IllegalAccessException,
//	  ClassNotFoundException, InstantiationException, SQLException {
//	  
//	  
//	  try { db.getConnection().createStatement().execute("DROP TABLE albums"); }
//	  catch (SQLException e) { if
//	  (!e.getSQLState().equals("proper SQL-state for table does not exist"))
//	  {}else{throw e;} }
//	  
//	  db.getConnection().createStatement().execute("CREATE TABLE albums (\r\n" +
//	  "  id int  NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\r\n"
//	  + "  name varchar(50) NOT NULL DEFAULT '0',\r\n" +
//	  "  releasedate int DEFAULT NULL,\r\n" + "  PRIMARY KEY (id)\r\n" + ")");
//	  db.getConnection().createStatement().execute("DELETE FROM albums");
//	  db.getConnection().createStatement().
//	  execute("INSERT INTO albums (name, releasedate) VALUES\r\n" +
//	  "	('Single', 0)"); try {
//	  db.getConnection().createStatement().execute("DROP TABLE  artists"); } catch
//	  (SQLException e) { if
//	  (!e.getSQLState().equals("proper SQL-state for table does not exist"))
//	  {}else{throw e;} }
//	  db.getConnection().createStatement().execute("CREATE TABLE artists (\r\n" +
//	  "  id int  NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\r\n"
//	  + "  name varchar(50) DEFAULT NULL,\r\n" +
//	  "  link varchar(100) DEFAULT NULL,\r\n" + "  PRIMARY KEY (id)\r\n" + ")");
//	  db.getConnection().createStatement().execute("DELETE FROM artists");
//	  db.getConnection().createStatement().
//	  execute("INSERT INTO artists (name, link) VALUES\r\n" +
//	  "	('non', NULL),\r\n" + "	('Wildstylez', NULL),\r\n" +
//	  "	('Sound Rush', NULL),\r\n" + "	('Ruby Prophet', NULL),\r\n" +
//	  "	('Sub Zero Project', NULL),\r\n" + "	('Dr. Peacock', NULL),\r\n" +
//	  "	('Audiotricz', NULL),\r\n" + "	('TNT', NULL),\r\n" +
//	  "	('Wasted Penguinz', NULL),\r\n" + "	( 'Headhunterz', NULL),\r\n" +
//	  "	( 'Project One', NULL),\r\n" + "	( 'Noisecontrollers', NULL),\r\n" +
//	  "	( 'KiFi', NULL),\r\n" + "	( 'Eurielle', NULL)"); try {
//	  db.getConnection().createStatement().execute("DROP TABLE  feelings"); } catch
//	  (SQLException e) { if
//	  (!e.getSQLState().equals("proper SQL-state for table does not exist"))
//	  {}else{throw e;} }
//	  db.getConnection().createStatement().execute("CREATE TABLE feelings (\r\n" +
//	  "  id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\r\n"
//	  + "  name varchar(50) NOT NULL,\r\n" + "  PRIMARY KEY (id)\r\n" + ") ");
//	  db.getConnection().createStatement().execute("DELETE FROM feelings");
//	  db.getConnection().createStatement().
//	  execute("INSERT INTO feelings (name) VALUES\r\n" + "	( 'angry'),\r\n" +
//	  "	( 'anxious'),\r\n" + "	( 'brave'),\r\n" + "	('courageous'),\r\n" +
//	  "	( 'dreaming'),\r\n" + "	( 'drinkmode'),\r\n" + "	( 'energetic'),\r\n"
//	  + "	('free'),\r\n" + "	( 'grooving'),\r\n" + "	( 'group'),\r\n" +
//	  "	('happy'),\r\n" + "	( 'hate'),\r\n" + "	( 'hopeful'),\r\n" +
//	  "	('in love'),\r\n" + "	( 'lonely'),\r\n" + "	( 'lost'),\r\n" +
//	  "	('motivating (fitness)'),\r\n" + "	('motivating (life)'),\r\n" +
//	  "	( 'neutral'),\r\n" + "	( 'optimistic'),\r\n" + "	( 'painful'),\r\n" +
//	  "	('partymode'),\r\n" + "	( 'pessimistic'),\r\n" + "	('rebellious'),\r\n"
//	  + "	('sad'),\r\n" + "	( 'thankful')"); try {
//	  db.getConnection().createStatement().execute("DROP TABLE  genres"); } catch
//	  (SQLException e) { if
//	  (!e.getSQLState().equals("proper SQL-state for table does not exist"))
//	  {}else{throw e;} }
//	  db.getConnection().createStatement().execute("CREATE TABLE genres (\r\n" +
//	  "  id int  NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\r\n"
//	  + "  name varchar(50) NOT NULL DEFAULT '0',\r\n" +
//	  "  parent int  DEFAULT NULL,\r\n" + "  PRIMARY KEY (id)\r\n" + ")");
//	  db.getConnection().createStatement().execute("DELETE FROM genres");
//	  db.getConnection().createStatement().
//	  execute("INSERT INTO genres (name, parent) VALUES\r\n" +
//	  "	('EDM (Electronic Dance Music)', NULL),\r\n" +
//	  "	('Hardstyle', 1),\r\n" + "	('Psystyle', 2),\r\n" +
//	  "	('Euphoric Hardstyle', 2),\r\n" + "	('Rawstyle', 2),\r\n" +
//	  "	('Rawphoric', 2),\r\n" + "	('Classic Hardstyle', 2),\r\n" +
//	  "	('Early Hardstyle', 2),\r\n" + "	('Dubstyle', 2),\r\n" +
//	  "	( 'Subground', 2),\r\n" + "	( 'Pop', NULL)"); try {
//	  db.getConnection().createStatement().execute("DROP TABLE  labels"); } catch
//	  (SQLException e) { if
//	  (!e.getSQLState().equals("proper SQL-state for table does not exist"))
//	  {}else{throw e;} }
//	  db.getConnection().createStatement().execute("CREATE TABLE labels (\r\n" +
//	  "  id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\r\n"
//	  + "  name varchar(50) DEFAULT '0',\r\n" +
//	  "  link varchar(100) DEFAULT NULL,\r\n" + "  PRIMARY KEY (id)\r\n" + ")");
//	  db.getConnection().createStatement().execute("DELETE FROM labels");
//	  db.getConnection().createStatement().
//	  execute("INSERT INTO labels (name, link) VALUES\r\n" +
//	  "	('DJS Records', NULL),\r\n" + "	('Fusion Records', NULL),\r\n" +
//	  "	('Scantraxx', NULL),\r\n" + "	('Seismin Records', NULL),\r\n" +
//	  "	('X-Rate Records', NULL),\r\n" + "	('ID&T', NULL),\r\n" +
//	  "	('Sensation Black', NULL),\r\n" + "	('Dutch Master Works', NULL),\r\n" +
//	  "	('Minus is More', NULL),\r\n" + "	( 'TiLLT Records', NULL),\r\n" +
//	  "	( 'Theracords', NULL),\r\n" + "	( 'Keep It Up Music', NULL),\r\n" +
//	  "	( 'Digital Age', NULL),\r\n" + "	( 'WE R', NULL),\r\n" +
//	  "	( 'Q-Dance Records', NULL),\r\n" +
//	  "	( 'Spirit Of Hardstyle', NULL),\r\n" +
//	  "	( 'Art of Creation', NULL),\r\n" + "	( 'Roughstate', NULL),\r\n" +
//	  "	( 'Free Release', NULL),\r\n" + "	( 'Dirty Workz', NULL),\r\n" +
//	  "	( 'Blutonium Records', NULL),\r\n" +
//	  "	( 'Hardnation Records', NULL),\r\n" + "	( 'Tunnel Records', NULL),\r\n"
//	  + "	( 'Hardbeatz Records', NULL),\r\n" +
//	  "	( 'Harder Stylez Records', NULL),\r\n" +
//	  "	( 'Davaro Records', NULL),\r\n" + "	( 'Neptun Records', NULL),\r\n" +
//	  "	( 'Musical Awareness Records', NULL),\r\n" +
//	  "	( 'Infected Section Records', NULL),\r\n" +
//	  "	( 'EDM-Unity', NULL),\r\n" + "	( 'Spoontech Records', NULL),\r\n" +
//	  "	( 'Activa', NULL),\r\n" + "	( 'BLQ Records', NULL),\r\n" +
//	  "	( 'Dance Pollution', NULL),\r\n" + "	( 'Saifam', NULL),\r\n" +
//	  "	( 'Titanic Records', NULL),\r\n" + "	( 'Zanzalabs', NULL),\r\n" +
//	  "	( 'Scantraxx Italy', NULL),\r\n" +
//	  "	( 'Italian Hardstyle', NULL),\r\n" + "	( 'Unite Records', NULL),\r\n" +
//	  "	( 'White Blood Records', NULL),\r\n" +
//	  "	( 'Bionic Digital Recordings', NULL),\r\n" +
//	  "	( 'Gearbox Digital', NULL),\r\n" + "	( 'Darkstyle Traxx', NULL),\r\n"
//	  + "	( 'Hot Score Records', NULL),\r\n" + "	( 'Austrocore', NULL),\r\n" +
//	  "	( 'Hardfusion', NULL),\r\n" + "	( 'Hard Music Records', NULL),\r\n" +
//	  "	( 'D3-Structive Records', NULL),\r\n" + "	( 'Sikkdome', NULL),\r\n" +
//	  "	( 'ETX Editiontraxx', NULL),\r\n" + "	( 'Sector Beatz', NULL),\r\n" +
//	  "	( 'Swiss Masterworks', NULL),\r\n" +
//	  "	( 'Hexablast Records', NULL),\r\n" + "	( 'Invaders Records', NULL)");
//	  try { db.getConnection().createStatement().execute("DROP TABLE  languages");
//	  } catch (SQLException e) { if
//	  (!e.getSQLState().equals("proper SQL-state for table does not exist"))
//	  {}else{throw e;} }
//	  db.getConnection().createStatement().execute("CREATE TABLE languages (\r\n" +
//	  "  id int  NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\r\n"
//	  + "  Name varchar(50) DEFAULT NULL,\r\n" + "  PRIMARY KEY (id)\r\n" + ")");
//	  db.getConnection().createStatement().execute("DELETE FROM languages");
//	  db.getConnection().createStatement().
//	  execute("INSERT INTO languages (Name) VALUES\r\n" + "	('English'),\r\n" +
//	  "	('French'),\r\n" + "	('German'),\r\n" + "	('Italian'),\r\n" +
//	  "	('Spanish')"); try {
//	  db.getConnection().createStatement().execute("DROP TABLE  playlists"); }
//	  catch (SQLException e) { if
//	  (!e.getSQLState().equals("proper SQL-state for table does not exist"))
//	  {}else{throw e;} }
//	  db.getConnection().createStatement().execute("CREATE TABLE playlists (\r\n" +
//	  "  id int  NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\r\n"
//	  + "  name varchar(50) NOT NULL DEFAULT 'New Playlist',\r\n" +
//	  "  userid int  NOT NULL DEFAULT 0,\r\n" + "  PRIMARY KEY (id)\r\n" + "  )");
//	  db.getConnection().createStatement().execute("DELETE FROM playlists"); try {
//	  db.getConnection().createStatement().execute("DROP TABLE  songs"); } catch
//	  (SQLException e) { if
//	  (!e.getSQLState().equals("proper SQL-state for table does not exist"))
//	  {}else{throw e;} }
//	  db.getConnection().createStatement().execute("CREATE TABLE songs (\r\n" +
//	  "  id bigint  NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\r\n"
//	  + "  releasedate int DEFAULT NULL,\r\n" +
//	  "  name varchar(50) DEFAULT NULL,\r\n" + "  artist int  DEFAULT NULL,\r\n" +
//	  "  album int  DEFAULT NULL,\r\n" + "  genre int  DEFAULT NULL,\r\n" +
//	  "  label int  DEFAULT NULL,\r\n" + "  language int  DEFAULT NULL,\r\n" +
//	  "  yt_link varchar(50) DEFAULT NULL,\r\n" +
//	  "  sf_link varchar(50) DEFAULT NULL,\r\n" +
//	  "  sc_link varchar(50) DEFAULT NULL,\r\n" + "  PRIMARY KEY (id)\r\n" + ") ");
//	  try { db.getConnection().createStatement().execute("DELETE FROM songs"); }
//	  catch (SQLException e) { if
//	  (!e.getSQLState().equals("proper SQL-state for table does not exist"))
//	  {}else{throw e;} } db.getConnection().createStatement().
//	  execute("INSERT INTO songs (releasedate, name, artist, album, genre, label, language, yt_link, sf_link, sc_link) VALUES\r\n"
//	  +
//	  "	(20190829, 'Untamable', 2, 1, 2, 17, 2, 'NCPAwdRvNRc', '6VQye74oX8FYIXUKI7feiG', '672606881'),\r\n"
//	  +
//	  "	(20191004, 'Army of Fire', 3, 1, 4, 15, 2, 'O2PLv1Fn004', NULL, NULL),\r\n"
//	  + "	(20191010, 'Into The Wild', 2, 1, 4, 17, 2, '3Fu73ZT_e6U', NULL, NULL)"
//	  ); try { db.getConnection().createStatement().execute("DROP TABLE  style"); }
//	  catch (SQLException e) { if
//	  (!e.getSQLState().equals("proper SQL-state for table does not exist"))
//	  {}else{throw e;} }
//	  db.getConnection().createStatement().execute("CREATE TABLE style (\r\n" +
//	  "  id int  NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\r\n"
//	  + "  name varchar(50) NOT NULL DEFAULT '',\r\n" + "  PRIMARY KEY (id)\r\n" +
//	  ")"); db.getConnection().createStatement().execute("DELETE FROM style");
//	  db.getConnection().createStatement().
//	  execute("INSERT INTO style (name) VALUES\r\n" + "	('Electronic'),\r\n" +
//	  "	('Melodic'),\r\n" + "	('Instrumental'),\r\n" + "	('Intro'),	\r\n" +
//	  "	('deleted'),\r\n" + "	('Middle Intro'),\r\n" + "	('Outro'),\r\n" +
//	  "	('Anti-Climax'),\r\n" + "	('Euphoric-Kick'),\r\n" +
//	  "	('Nu-Kick'),\r\n" + "	('Raw-Kick'),\r\n" + "	('Psy-Kick'),\r\n" +
//	  "	('Reverse Bass'),\r\n" + "	('Distorted Kick'),\r\n" +
//	  "	('deleted'),\r\n" + "	('deleted'),\r\n" + "	('Atmospheric')");
//	  db.getConnection().createStatement().
//	  execute("Delete from style where name = 'deleted'"); try {
//	  db.getConnection().createStatement().execute("DROP TABLE usertbl"); } catch
//	  (SQLException e) { if
//	  (!e.getSQLState().equals("proper SQL-state for table does not exist"))
//	  {}else{throw e;} }
//	  db.getConnection().createStatement().execute("CREATE TABLE usertbl (\r\n" +
//	  "  id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\r\n"
//	  + "  name varchar(50) NOT NULL DEFAULT '0',\r\n" +
//	  "  password varchar(256) NOT NULL DEFAULT '0',\r\n" +
//	  "  email varchar(50) DEFAULT NULL,\r\n" + "  birthday int DEFAULT NULL,\r\n"
//	  + "  usertype int  NOT NULL,\r\n" + "  PRIMARY KEY (id)\r\n" + ")");
//	  db.getConnection().createStatement().execute("DELETE FROM usertbl"); try {
//	  db.getConnection().createStatement().execute("DROP TABLE  usertype"); } catch
//	  (SQLException e) { if
//	  (!e.getSQLState().equals("proper SQL-state for table does not exist"))
//	  {}else{throw e;} }
//	  db.getConnection().createStatement().execute("CREATE TABLE usertype (\r\n" +
//	  "  id int  NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\r\n"
//	  + "  name varchar(50) DEFAULT NULL,\r\n" + "  PRIMARY KEY (id)\r\n" + ")");
//	  db.getConnection().createStatement().execute("DELETE FROM usertype");
//	  db.getConnection().createStatement().
//	  execute("INSERT INTO usertype (name) VALUES\r\n" + "	('Administrator'),\r\n"
//	  + "	('User')"); try {
//	  db.getConnection().createStatement().execute("DROP TABLE  z_artist_label"); }
//	  catch (SQLException e) { if
//	  (!e.getSQLState().equals("proper SQL-state for table does not exist"))
//	  {}else{throw e;} } db.getConnection().createStatement().
//	  execute("CREATE TABLE z_artist_label (\r\n" +
//	  "  artist int  NOT NULL DEFAULT 0,\r\n" +
//	  "  label int  NOT NULL DEFAULT 0,\r\n" + "  PRIMARY KEY (artist, label)\r\n"
//	  + ")");
//	  db.getConnection().createStatement().execute("DELETE FROM z_artist_label");
//	  try {
//	  db.getConnection().createStatement().execute("DROP TABLE  z_feeling_song"); }
//	  catch (SQLException e) { if
//	  (!e.getSQLState().equals("proper SQL-state for table does not exist"))
//	  {}else{throw e;} } db.getConnection().createStatement().
//	  execute("CREATE TABLE z_feeling_song (\r\n" + "  feeling int  NOT NULL,\r\n"
//	  + "  song bigint  NOT NULL DEFAULT 0,\r\n" +
//	  "  upvoted int  NOT NULL DEFAULT 0,\r\n" +
//	  "  downvoted int  NOT NULL DEFAULT 0,\r\n" +
//	  "  PRIMARY KEY (feeling,song)\r\n" + ") ");
//	  db.getConnection().createStatement().execute("DELETE FROM z_feeling_song");
//	  db.getConnection().createStatement().
//	  execute("INSERT INTO z_feeling_song (feeling, song, upvoted, downvoted) VALUES\r\n"
//	  + "	(1, 1, 1, 0),\r\n" + "	(1, 2, 1, 0),\r\n" + "	(1, 3, 1, 0),\r\n" +
//	  "	(3, 1, 1, 0),\r\n" + "	(3, 3, 1, 0),\r\n" + "	(4, 2, 1, 0),\r\n" +
//	  "	(4, 3, 1, 0),\r\n" + "	(5, 1, 1, 0),\r\n" + "	(5, 2, 1, 0),\r\n" +
//	  "	(5, 3, 1, 0),\r\n" + "	(10, 1, 1, 0),\r\n" + "	(11, 3, 1, 0),\r\n" +
//	  "	(14, 2, 1, 0),\r\n" + "	(16, 1, 1, 0),\r\n" + "	(24, 2, 1, 0)"); try {
//	  db.getConnection().createStatement().execute("DROP TABLE  z_genre_style"); }
//	  catch (SQLException e) { if
//	  (!e.getSQLState().equals("proper SQL-state for table does not exist"))
//	  {}else{throw e;} } db.getConnection().createStatement().
//	  execute("CREATE TABLE z_genre_style (\r\n" + "  genre int  NOT NULL,\r\n" +
//	  "  style int  NOT NULL,\r\n" + "  PRIMARY KEY (genre,style)\r\n" + ")");
//	  db.getConnection().createStatement().execute("DELETE FROM z_genre_style");
//	  db.getConnection().createStatement().
//	  execute("INSERT INTO z_genre_style (genre, style) VALUES\r\n" +
//	  "	(1, 1),\r\n" + "	(2, 1),\r\n" + "	(2, 14),\r\n" + "	(3, 1),\r\n"
//	  + "	(3, 12),\r\n" + "	(4, 1),\r\n" + "	(4, 2),\r\n" + "	(4, 9),\r\n"
//	  + "	(4, 14),\r\n" + "	(5, 1),\r\n" + "	(5, 11),\r\n" +
//	  "	(5, 14),\r\n" + "	(6, 1),\r\n" + "	(6, 2),\r\n" +
//	  "	(6, 11),\r\n" + "	(6, 14)"); try{
//	  db.getConnection().createStatement().execute("DROP TABLE z_playlist_songs");
//	  } catch (SQLException e) { if
//	  (!e.getSQLState().equals("proper SQL-state for table does not exist"))
//	  {}else{} } db.getConnection().createStatement().
//	  execute("CREATE TABLE z_playlist_songs (\r\n" +
//	  "  playlist int  NOT NULL,\r\n" + "  song bigint  NOT NULL DEFAULT 0,\r\n" +
//	  "  ordered int NOT NULL,\r\n" + "  PRIMARY KEY (playlist,song)\r\n" + ") ");
//	  db.getConnection().createStatement().execute("DELETE FROM z_playlist_songs");
//	  try {
//	  db.getConnection().createStatement().execute("DROP TABLE  z_song_co_artist");
//	  } catch (SQLException e) { if
//	  (!e.getSQLState().equals("proper SQL-state for table does not exist"))
//	  {}else{} } db.getConnection().createStatement().
//	  execute("CREATE TABLE z_song_co_artist (\r\n" +
//	  "  song_id bigint  NOT NULL,\r\n" + "  co_artist_id int  NOT NULL,\r\n" +
//	  "  PRIMARY KEY (song_id,co_artist_id)\r\n" + ")");
//	  db.getConnection().createStatement().execute("DELETE FROM z_song_co_artist");
//	  db.getConnection().createStatement().
//	  execute("INSERT INTO z_song_co_artist (song_id, co_artist_id) VALUES\r\n" +
//	  "	(1, 3),\r\n" + "	(1, 4),\r\n" + "	(2, 14),\r\n" + "	(3, 13)");
//	  try {
//	  db.getConnection().createStatement().execute("DROP TABLE  z_song_style"); }
//	  catch (SQLException e) { if
//	  (!e.getSQLState().equals("proper SQL-state for table does not exist"))
//	  {}else{} } db.getConnection().createStatement().
//	  execute("CREATE TABLE z_song_style (\r\n" + "  song bigint  NOT NULL,\r\n" +
//	  "  style int  NOT NULL,\r\n" + "  PRIMARY KEY (song,style)\r\n" + ")");
//	  db.getConnection().createStatement().execute("DELETE FROM z_song_style");
//	  db.getConnection().createStatement().
//	  execute("INSERT INTO z_song_style (song, style) VALUES\r\n" + "	(1, 1),\r\n"
//	  + "	(1, 2),\r\n" + "	(1, 14),\r\n" + "	(2, 1),\r\n" + "	(2, 2),\r\n"
//	  + "	(2, 9),\r\n" + "	(2, 14),\r\n" + "	(3, 1),\r\n" + "	(3, 2),\r\n"
//	  + "	(3, 9),\r\n" + "	(3, 14)"); try {
//	  db.getConnection().createStatement().
//	  execute("DROP TABLE  z_user_voted_songfeeling"); } catch (SQLException e) {
//	  if (!e.getSQLState().equals("proper SQL-state for table does not exist"))
//	  {}else{} } db.getConnection().createStatement().
//	  execute("CREATE TABLE z_user_voted_songfeeling (\r\n" +
//	  "  userid int  NOT NULL DEFAULT 0,\r\n" +
//	  "  song bigint  NOT NULL DEFAULT 0,\r\n" + "  PRIMARY KEY (userid,song)\r\n"
//	  + ")"); db.getConnection().createStatement().
//	  execute("DELETE FROM z_user_voted_songfeeling");
//	  
//	  }

}
