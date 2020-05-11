package main;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import main.obj.Album;
import main.obj.Artist;
import main.obj.CoArtists;
import main.obj.Feeling;
import main.obj.Genre;
import main.obj.Language;
import main.obj.Playlist;
import main.obj.Song;
import main.obj.Style;
import main.obj.User;
import main.obj.Usertype;

public class Database {
	
//	public static void main(String args[]) {
//		Database testdb = null;
//		try {
//			testdb = getDatabase();
//		} catch (IllegalAccessException | ClassNotFoundException | InstantiationException | SQLException e) {
//			e.printStackTrace();
//		}
//		if(testdb != null) {
//			Statement stmt;
//			try {
//				stmt = testdb.getConnection().createStatement();
//			
//				stmt.executeUpdate("CREATE TABLE test (\r\n" + 
//						"  test int  NOT NULL DEFAULT 0,\r\n" + 
//						"  PRIMARY KEY (userid)\r\n" + 
//						")");
//			
//				stmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}

	private static Database thisDB;
	private Connection connection;

	private String host, database, user, pw;

	private static boolean isInstantiated() {
		return thisDB != null;
	}

	public static Database getDatabase()
			throws IllegalAccessException, ClassNotFoundException, SQLException, InstantiationException {
		if (isInstantiated()) {
			return thisDB;
		} else {
			return connectToMySQL("localhost", "feelmusic", "root", "");
		}
	}

	private Database(String host, String database, String user, String pw)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException, SQLException {
		//Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver").getDeclaredConstructor().newInstance();
		this.host = host;
		this.database = database;
		this.user = user;
		this.pw = pw;
		connection = connect();
	}

	private Connection connect() throws SQLException {
		//String connectionCommand = "jdbc:mysql://" + host + "/" + database + "?user=" + user + "&password=" + pw;
		String connectionCommand = "jdbc:derby:" + database + ";create=true";
		return DriverManager.getConnection(connectionCommand);
	}

	private static Database connectToMySQL(String host, String database, String user, String passwd)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException { // Klasse zum
																											// Verbinden
																											// mit der
																											// Datenbank
		try {
			thisDB = new Database(host, database, user, passwd);
			return thisDB;
		} catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			System.out.println("Konnte keine Verbindung herstellen!");
			e.printStackTrace();
			return null;
		}
	}

	public Connection getConnection() {
		return connection;
	}

	private ArrayList<String[]> doQuery(String sql, String... columnNames) throws SQLException {
		ArrayList<String[]> arr = new ArrayList<String[]>();
		Statement stmt = getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			String[] str = new String[columnNames.length];
			for (int i = 0; i < columnNames.length; i++) {
				str[i] = rs.getString(columnNames[i]);
			}
			arr.add(str);
		}
		rs.close();
		stmt.close();
		return arr;
	}

	/**
	 * Loads all Languages found in the Database
	 * 
	 * @return Array of Language
	 */
	public Language[] loadLanguages() {
		ArrayList<String[]> arrlst;
		try {
			arrlst = doQuery("Select id, name from languages", "id", "name");
		} catch (SQLException e) {
			e.printStackTrace();
			return new Language[0];
		}
		Language[] ret = new Language[arrlst.size()];
		for (int i = 0; i < arrlst.size(); i++) {
			ret[i] = new Language(Integer.parseInt(arrlst.get(i)[0]), arrlst.get(i)[1]);
		}

		return ret;
	}

	/**
	 * Loads all Genres found in the Database
	 * 
	 * @return Array of Genre
	 */
	public Genre[] loadGenres() {
		ArrayList<String[]> arrlst;
		try {
			arrlst = doQuery("SELECT * FROM genres ORDER BY id", "id", "name", "parent");
		} catch (SQLException e) {
			e.printStackTrace();
			return new Genre[0];
		}
		Genre[] ret = new Genre[arrlst.size()];
		for (int i = 0; i < arrlst.size(); i++) {
			ret[i] = new Genre(Integer.parseInt(arrlst.get(i)[0]), arrlst.get(i)[1],
					(arrlst.get(i)[2] == null) ? 0 : Integer.parseInt(arrlst.get(i)[2]));
		}
		return ret;
	}

	/**
	 * Loads all Feelings found in the Database
	 * 
	 * @return Array of Feeling
	 */
	public Feeling[] loadFeelings() {
		ArrayList<String[]> arrlst;
		try {
			arrlst = doQuery("select * from feelings", "id", "name");
		} catch (SQLException e) {
			e.printStackTrace();
			return new Feeling[0];
		}
		Feeling[] ret = new Feeling[arrlst.size()];
		for (int i = 0; i < arrlst.size(); i++) {
			ret[i] = new Feeling(Integer.parseInt(arrlst.get(i)[0]), arrlst.get(i)[1]);
		}
		return ret;
	}

	/**
	 * Loads all Styles found in the Database
	 * 
	 * @return Array of Style
	 */
	public Style[] loadStyles() {
		ArrayList<String[]> arrlst;
		try {
			arrlst = doQuery("select * from styles", "id", "name");
		} catch (SQLException e) {
			e.printStackTrace();
			return new Style[0];
		}
		Style[] ret = new Style[arrlst.size()];
		for (int i = 0; i < arrlst.size(); i++) {
			ret[i] = new Style(Integer.parseInt(arrlst.get(i)[0]), arrlst.get(i)[1]);
		}
		return ret;
	}

	/**
	 * Returns all Co-Artists found by SongID
	 * 
	 * @param songID
	 * @return
	 */
	public CoArtists loadCoArtistsBySong(int songID) {
		String query = "select artists.id, artists.name, artists.link FROM z_song_co_artist "
				+ "JOIN artists ON z_song_co_artist.co_artist_id = artists.id WHERE z_song_co_artist.song_id = " + songID;
		try {
			return DatabaseUtils.parseCoArtists(doQuery(query, "id", "name", "link"));
		} catch (SQLException e) {
			e.printStackTrace();
			return new CoArtists();
		}
	}

	/**
	 * Searchs for a Songname with LIKE 'SONGNAME%' LIMIT on 25
	 * 
	 * @param name Part of a Name to search for
	 * @return Array Of Songs that were found
	 */
	public Song[] searchForSongByName(String name) {
		return searchForSongByName(name, 25);
	}

	/**
	 * Searchs for a Songname with LIKE 'SONGNAME%'
	 * 
	 * @param name  Part of a Name to search for
	 * @param limit of max search results
	 * @return Array Of Songs that were found
	 */
	public Song[] searchForSongByName(String name, int limit) {
		String query = DatabaseUtils.getSongQuery() + " WHERE songs.name LIKE '" + name + "%' ORDER BY songs.name fetch first" /*LIMIT "*/
				+ limit + "rows only";
		try {
			return DatabaseUtils.parseSongs(doQuery(query, DatabaseUtils.getSongQueryColumns()), this);
		} catch (SQLException e) {
			e.printStackTrace();
			return new Song[0];
		}
	}

	/**
	 * Returns all Songs found by a Artist OR Co_Artist
	 * 
	 * @param a Artist to search for
	 * @return Array of Songs
	 */
	public Song[] getAllSongsByArtist(Artist a) {
		String query = DatabaseUtils.getSongQuery() + " JOIN z_song_co_artist ON z_song_co_artist.song_id = songs.id "
				+ "JOIN artists ON z_song_co_artist.co_artist_id = artists.id " + "WHERE artists.id = '" + a.getId() + "' OR artists.id = '"
				+ a.getId() + "' " + "GROUP BY SongID";

		try {
			return DatabaseUtils.parseSongs(doQuery(query, DatabaseUtils.getSongQueryColumns()), this);
		} catch (SQLException e) {
			e.printStackTrace();
			return new Song[0];
		}
	}

	/**
	 * Returns all Artists by Name or Part of a Name
	 * 
	 * @param name
	 * @return
	 */
	public Artist[] searchArtistByName(String name) {
		String query = "select * from artists where name LIKE '" + name + "%' ORDER BY name fetch first 3 rows only"/*LIMIT 3"*/;
		try {
			return DatabaseUtils.parseArtists(doQuery(query, "id", "name", "link"));
		} catch (SQLException e) {
			e.printStackTrace();
			return new Artist[0];
		}
	}

	public Playlist[] loadPlaylistsByUser(User user) {

		String query = "select * from playlists where userid = '" + user.getId() + "'";
		try {
			return DatabaseUtils.parsePlaylists(doQuery(query, "id", "name"), user);
		} catch (SQLException e) {
			e.printStackTrace();
			return new Playlist[0];
		}

	}

	/**
	 * 
	 * @param p
	 */
	public void loadSongsIntoPlaylist(Playlist p) {
		String query = DatabaseUtils.getSongQuery() + " RIGHT JOIN z_playlist_songs AS z ON z_playlist_songs.song = songs.id "
				+ "WHERE z_playlist_songs.playlist = '" + p.getId() + "' ORDER BY z_playlist_songs.ordered";
		try {
			p.setSongs(DatabaseUtils.parseSongs(doQuery(query, DatabaseUtils.getSongQueryColumns()), this));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Inserts an Album and Returns a Album Object with the generated ID
	 * 
	 * @param name
	 * @param releasedate
	 * @return null, if the Album could not be inserted
	 * @throws SQLException
	 */
	public Album insertAlbum(String name, int releasedate) throws SQLException {
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("INSERT INTO albums (name, releasedate) " + "values ('" + name + "', " + releasedate + ")",
				Statement.RETURN_GENERATED_KEYS);

		int autoIncAlbumIDFromApi = -1;

		ResultSet rs = stmt.getGeneratedKeys();

		if (rs.next()) {
			autoIncAlbumIDFromApi = rs.getInt(1);
		}
		rs.close();
		stmt.close();
		if (autoIncAlbumIDFromApi != -1) {
			return new Album(autoIncAlbumIDFromApi, name, releasedate);
		} else {
			return null;
		}
	}

	/**
	 * Inserts a artist and returns a Artist with the generated ID
	 * 
	 * @param name
	 * @param link
	 * @return if returns null, no Artist has been created
	 * @throws SQLException
	 */
	public Artist insertArtist(String name, String link) throws SQLException {
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("INSERT INTO artists (name, link) " + "values ('" + name + "', '" + link + "')",
				Statement.RETURN_GENERATED_KEYS);

		int autoIncArtistIDFromApi = -1;

		ResultSet rs = stmt.getGeneratedKeys();

		if (rs.next()) {
			autoIncArtistIDFromApi = rs.getInt(1);
		}
		rs.close();
		stmt.close();
		if (autoIncArtistIDFromApi != -1) {
			return new Artist(autoIncArtistIDFromApi, name, link);
		} else {
			return null;
		}
	}

	/**
	 * Inserts a new playlist and adds the Songs into it
	 * 
	 * @param pllst
	 * @return
	 * @throws SQLException
	 */
	public Playlist insertPlaylist(Playlist pllst) throws SQLException {
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("INSERT INTO playlists (name, userid) " + "values ('" + pllst.getName() + "', "
				+ pllst.getUser().getId() + ")", Statement.RETURN_GENERATED_KEYS);

		int autoIncPlaylistIDFromApi = -1;

		ResultSet rs = stmt.getGeneratedKeys();

		if (rs.next()) {
			autoIncPlaylistIDFromApi = rs.getInt(1);
		}
		rs.close();
		pllst.setId(autoIncPlaylistIDFromApi);
		int n = 0;
		for (Song s : pllst.getSongs()) {
			stmt.executeUpdate("INSERT INTO z_playlist_songs (playlist, song, ordered) " + "VALUES ("
					+ autoIncPlaylistIDFromApi + ", " + s.getId() + ", " + n++ + ")");
		}
		stmt.close();
		return pllst;
	}

	/**
	 * Inserts a Song by its params. Returns the generated Song id. Keep in Mind
	 * that the given IDs have to exist before, because they're foreign keys.
	 * 
	 * @param releasedate
	 * @param name
	 * @param artistID
	 * @param albumID
	 * @param genreID
	 * @param labelID
	 * @param languageID
	 * @param ytLink
	 * @param sfLink
	 * @param scLink
	 * @return returns -1 or throws Exceptpion, when Song could not be inserted.
	 * @throws SQLException
	 */
	public long insertSong(int releasedate, String name, int artistID, int albumID, int genreID, int labelID,
			int languageID, String ytLink, String sfLink, String scLink) throws SQLException {
		Statement stmt = connection.createStatement();
		stmt.executeUpdate(
				"INSERT INTO song (releasedate, name, artist, album, genre, label, language, yt_link, sf_link, sc_link) "
						+ "values (" + releasedate + ", '" + name + "', " + artistID + ", " + albumID + ", " + genreID
						+ ", " + labelID + ", " + languageID + ", '" + ytLink + "', '" + sfLink + "', '" + scLink
						+ "')",
				Statement.RETURN_GENERATED_KEYS);

		long autoIncArtistIDFromApi = -1;

		ResultSet rs = stmt.getGeneratedKeys();

		if (rs.next()) {
			autoIncArtistIDFromApi = rs.getLong(1);
		}
		rs.close();
		stmt.close();
		return autoIncArtistIDFromApi;
	}

	public int insertUser(String name, String password, String email, int birthday, int usertype) throws SQLException {
		return insertUser(new User(-1, name, password, email, birthday, new Usertype(usertype, "egal")));
	}

	public int insertUser(User u) throws SQLException {
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("INSERT INTO usertbl (name, password, email, birthday, usertype) " + "values ('"
				+ u.getUsername() + "', '" + u.encryptPassword64Digit(u.getPassword()) + "', '" + u.getEmailAddress()
				+ "', " + u.getBirthdate() + ", " + u.getUsertype().getId() + ")", Statement.RETURN_GENERATED_KEYS);

		int autoIncArtistIDFromApi = -1;

		ResultSet rs = stmt.getGeneratedKeys();

		if (rs.next()) {
			autoIncArtistIDFromApi = rs.getInt(1);
		}
		rs.close();
		stmt.close();
		return autoIncArtistIDFromApi;
	}

	public void insertUserVotedForSong(int userID, long songID) throws SQLException {

		Statement stmt = connection.createStatement();
		stmt.executeUpdate("INSERT INTO z_user_voted_songfeeling (userid, song) VALUES (" + userID + ", " + songID + ")");
		stmt.close();

	}

	/**
	 * Inserts OR Updates if exists the upvote value
	 * 
	 * @param feelingID
	 * @param songID
	 * @throws SQLException
	 */
	public void upvoteFeeling(int feelingID, int songID) throws SQLException {
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("INSERT INTO z_feeling_song (feeling, song) " + "VALUES (" + feelingID + ", " + songID + ") "
				+ "ON DUPLICATE KEY UPDATE upvoted = upvoted + 1");

		stmt.close();
	}

	/**
	 * Updates if exists the downvote value
	 * 
	 * @param feelingID
	 * @param songID
	 * @throws SQLException
	 */
	public void downvoteFeeling(int feelingID, long songID) throws SQLException {
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("UPDATE z_feeling_song SET downvoted = downvoted + 1 " + "WHERE feeling = " + feelingID
				+ " AND song = " + songID);
		stmt.close();
	}

	public void insertCoartistsForSong(long songID, int... coArtistIDs) throws SQLException {
		if (coArtistIDs != null && coArtistIDs.length > 0) {
			Statement stmt = connection.createStatement();
			for (int n : coArtistIDs) {
				stmt.executeUpdate(
						"INSERT INTO z_song_co_artist (song_id, co_artist_id) VALUES (" + songID + ", " + n + ")");
			}
			stmt.close();
		}
	}

	public int[] getStyleIDsByGenre(int genreID) throws SQLException {

		String qry = "Select style FROM z_genre_style AS z" + " WHERE z.genre = " + genreID;
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(qry);
		ArrayList<Integer> arr = new ArrayList<Integer>();
		while (rs.next()) {
			arr.add(rs.getInt("style"));
		}
		rs.close();
		stmt.close();
		int[] ret = new int[arr.size()];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = arr.get(i);
		}
		return ret;
	}

	public void insertStylesForSong(int songID, int... styleIDs) throws SQLException {
		if (styleIDs != null && styleIDs.length > 0) {
			Statement stmt = connection.createStatement();
			for (int n : styleIDs) {
				stmt.executeUpdate("INSERT INTO z_song_style (song, style) VALUES (" + songID + ", " + n + ")");
			}
			stmt.close();
		}
	}

	public Song[] getPlaylistcreationSongs(int releasedateArr[], int[] includedLanguages, int[] excludedLanguages,
			int[] includedGenres, int[] excludedGenres, int[] includedStyles, int[] excludedStyles,
			int[] includedFeelings, int[] excludedFeelings) throws SQLException {
		String qry = DatabaseUtils.getSongQuery();
		String joinQry = "";
		String whereClause = " WHERE";
		if (releasedateArr != null) {
			if (releasedateArr.length == 2) {
				whereClause += " songs.releasedate > " + releasedateArr[0] + " AND songs.releasedate < " + releasedateArr[1]
						+ " AND";
			}
		}

		if (includedLanguages != null) {
			whereClause += " songs.language " + DatabaseUtils.generateINqry(includedLanguages) + " AND";
		}
		if (excludedLanguages != null) {
			whereClause += " songs.language NOT " + DatabaseUtils.generateINqry(excludedLanguages) + " AND";
		}

		if (includedGenres != null) {
			whereClause += " songs.genre " + DatabaseUtils.generateINqry(includedGenres) + " AND";
		}
		if (excludedGenres != null) {
			whereClause += " songs.genre NOT " + DatabaseUtils.generateINqry(excludedGenres) + " AND";
		}

		if (includedStyles != null || excludedStyles != null) {
			if (includedStyles != null) {
				whereClause += " (SELECT COUNT(*) FROM z_song_style WHERE z_song_style.song = songs.id AND z_song_style.style "
						+ DatabaseUtils.generateINqry(includedStyles) + ") = " + includedStyles.length + " AND";
			}
			if (excludedStyles != null) {
				whereClause += " (SELECT COUNT(*) FROM z_song_style WHERE z_song_style.song = songs.id AND z_song_style.style "
						+ DatabaseUtils.generateINqry(excludedStyles) + ") = 0 AND";
			}
		}

		if (includedFeelings != null || excludedFeelings != null) {
			if (includedFeelings != null) {
				whereClause += " (SELECT COUNT(*) FROM z_feeling_song WHERE z_feeling_song.song = songs.id AND z_feeling_song.feeling "
						+ DatabaseUtils.generateINqry(includedFeelings) + ") = " + includedFeelings.length + " AND";
			}
			if (excludedFeelings != null) {
				whereClause += " (SELECT COUNT(*) FROM z_feeling_song WHERE z_feeling_song.song = songs.id AND z_feeling_song.feeling "
						+ DatabaseUtils.generateINqry(excludedFeelings) + ") = 0 AND";
			}
		}

		if (whereClause.endsWith("AND")) {
			whereClause = whereClause.substring(0, whereClause.length() - 3);
		}
		qry += joinQry + ((whereClause != " WHERE") ? whereClause : "") + /*" GROUP BY songs.id*/" ORDER BY RANDOM() fetch first 25 rows only"/*LIMIT 25"*/;
		return DatabaseUtils.parseSongs(doQuery(qry, DatabaseUtils.getSongQueryColumns()), this);

	}

}
