package main.dao;

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
import main.obj.MusicLabel;
import main.obj.Playlist;
import main.obj.Song;
import main.obj.Style;
import main.obj.User;
import main.obj.Usertype;

public class Database {
	
	private static Database thisDB;
	
	private String host, database, user, pw;
	private Connection connection;
	
	private boolean mySQL = true;

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
		if(mySQL) {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
		}else {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").getDeclaredConstructor().newInstance();
		}
		this.host = host;
		this.database = database;
		this.user = user;
		this.pw = pw;
		connection = connect();
	}

	private Connection connect() throws SQLException {
		String connectionCommand; 
		if(mySQL) {
			connectionCommand= "jdbc:mysql://" + host + "/" + database + "?user=" + user + "&password=" + pw;
		}else {
			connectionCommand = "jdbc:derby:" + database + ";create=true";
		}
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

	private static ArrayList<String[]> doQuery(String sql, String... columnNames) throws SQLException {
		ArrayList<String[]> arr = new ArrayList<String[]>();
		if(thisDB != null) {
			Statement stmt = thisDB.getConnection().createStatement();
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
		}
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
	private Song[] searchForSongByName(String name, int limit) {
		String query;
		if(mySQL) {
			query = DatabaseUtils.getSongQuery() + " WHERE songs.name LIKE '" + name + "%' ORDER BY songs.name LIMIT " + limit;
		}else {
			query = DatabaseUtils.getSongQuery() + " WHERE songs.name LIKE '" + name + "%' ORDER BY songs.name fetch first " + limit + " rows only";
		}
		try {
			return DatabaseUtils.parseSongs(doQuery(query, DatabaseUtils.getSongQueryColumns()), this);
		} catch (SQLException e) {
			e.printStackTrace();
			return new Song[0];
		}
	}
	
	public Song[] searchForSongByNameAndArtist(String name, int artistId) {
		return searchForSongByNameAndArtist(name, artistId, 25);
	}
	
	private Song[] searchForSongByNameAndArtist(String name, int artistId, int limit) {
		String query;
		if(mySQL) {
			query = DatabaseUtils.getSongQuery() + "LEFT JOIN z_song_co_artist ON songs.id = z_song_co_artist.song_id "
					+ "WHERE songs.name LIKE '" + name + "%' AND (songs.artist = " + artistId + " OR z_song_co_artist.co_artist_id = " + artistId + ") "
							+ "ORDER BY songs.name LIMIT " + limit;
		}else {
			query = DatabaseUtils.getSongQuery() + "LEFT JOIN z_song_co_artist ON songs.id = z_song_co_artist.song_id "
					+ "WHERE songs.name LIKE '" + name + "%' AND (songs.artist = " + artistId + " OR z_song_co_artist.co_artist_id = " + artistId + ") "
							+ "ORDER BY songs.name fetch first " + limit + " rows only";
		}
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
	
	public static Artist[] getAllArtists() {
		if(thisDB != null) {
			String query = "select * from artists ORDER BY name";
			try {
				return DatabaseUtils.parseArtists(doQuery(query, "id", "name", "link"));
			} catch (SQLException e) {
				e.printStackTrace();
				return new Artist[0];
			}
		}
		return new Artist[0];
	}
	
	public static Artist[] getNoLinkedArtists() {
		if(thisDB != null) {
			String query = "select * from artists WHERE id NOT IN(SELECT artist FROM z_userconnector WHERE artist > 0) ORDER BY name";
			try {
				return DatabaseUtils.parseArtists(doQuery(query, "id", "name", "link"));
			} catch (SQLException e) {
				e.printStackTrace();
				return new Artist[0];
			}
		}
		return new Artist[0];
	}
	
	public static MusicLabel[] getAllLabels() {
		if(thisDB != null) {
			String query = "select * from labels ORDER BY name";
			try {
				return DatabaseUtils.parseLabels(doQuery(query, "id", "name", "link"));
			} catch (SQLException e) {
				e.printStackTrace();
				return new MusicLabel[0];
			}
		}
		return new MusicLabel[0];
	}
	
	

	/**
	 * Returns all Artists by Name or Part of a Name
	 * 
	 * @param name
	 * @return
	 */
	public Artist[] searchArtistByName(String name) {
		String query;
		if(mySQL) {
			query = "select * from artists where name LIKE '" + name + "%' ORDER BY name LIMIT 3";	
		}else {
			query = "select * from artists where name LIKE '" + name + "%' ORDER BY name fetch first 3 rows only";
		}
		
		try {
			return DatabaseUtils.parseArtists(doQuery(query, "id", "name", "link"));
		} catch (SQLException e) {
			e.printStackTrace();
			return new Artist[0];
		}
	}

	public static Playlist[] loadPlaylistsByUser(User user) {

		String query = "select * from playlists where user = '" + user.getId() + "'";
		try {
			return DatabaseUtils.parsePlaylists(doQuery(query, "id", "name"), user);
		} catch (SQLException e) {
			e.printStackTrace();
			return new Playlist[0];
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
	public int insertPlaylist(String playlistname, int userid, int[] songids) throws SQLException {
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("INSERT INTO playlists (name, user) " + "values ('" + playlistname + "', "
				+ userid + ")", Statement.RETURN_GENERATED_KEYS);

		int autoIncPlaylistIDFromApi = -1;

		ResultSet rs = stmt.getGeneratedKeys();

		if (rs.next()) {
			autoIncPlaylistIDFromApi = rs.getInt(1);
		}
		rs.close();
		for (int i = 0; i < songids.length; i++) {
			stmt.executeUpdate("INSERT INTO z_playlist_songs (playlist, song, ordered) " + "VALUES ("
					+ autoIncPlaylistIDFromApi + ", " + songids[i] + ", " + i + ")");
		}
		stmt.close();
		return autoIncPlaylistIDFromApi;
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
				"INSERT INTO songs (releasedate, name, artist, album, genre, label, language, yt_link, sf_link, sc_link) "
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

	public int signUpUser(String name, String encryptedPasswordString, String email, int birthday) throws SQLException {
		return insertUser(new User(-1, name, email, birthday, new Usertype(2, "egal")), encryptedPasswordString);
	}

	private int insertUser(User u, String encryptedPasswordString) throws SQLException {
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("INSERT INTO user (name, password, email, birthday, usertype) " + "values ('"
				+ u.getUsername() + "', '" + encryptedPasswordString + "', '" + u.getEmailAddress()
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
	public void upvoteFeeling(int feelingID, long songID) throws SQLException {
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
				if(n != 1) {
				stmt.executeUpdate(
						"INSERT INTO z_song_co_artist (song_id, co_artist_id) VALUES (" + songID + ", " + n + ")");
				}
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

	public void insertStylesForSong(long generatedSongId, int... styleIDs) throws SQLException {
		if (styleIDs != null && styleIDs.length > 0) {
			Statement stmt = connection.createStatement();
			for (int n : styleIDs) {
				if(n != 0) {
					stmt.executeUpdate("INSERT INTO z_song_style (song, style) VALUES (" + generatedSongId + ", " + n + ")");
				}
			}
			stmt.close();
		}
	}
	
	public void insertFeelingsForSong(long generatedSongId, int... feelingIDs) throws SQLException {
		if (feelingIDs != null && feelingIDs.length > 0) {
			Statement stmt = connection.createStatement();
			for (int n : feelingIDs) {
				if(n != 0) {
					stmt.executeUpdate("INSERT INTO z_feeling_song (feeling, song, upvoted, downvoted) VALUES (" + n +", " + generatedSongId + ", 1, 0)");
				}
			}
			stmt.close();
		}
	}

	public Song[] getPlaylistcreationSongs(int releasedateArr[], int[] includedGenres, int[] excludedGenres, 
				int[] includedFeelings, int[] excludedFeelings,
				int[] includedStyles, int[] excludedStyles,
				int[] includedLanguages, int[] excludedLanguages) throws SQLException {
		
		StringBuilder qryBuilder = new StringBuilder();
		qryBuilder.append(DatabaseUtils.getSongQuery());
		StringBuilder whereClauseBuilder = new StringBuilder();
		whereClauseBuilder.append(" WHERE");
		if (releasedateArr != null) {
			if (releasedateArr.length == 2) {
				whereClauseBuilder.append(" songs.releasedate > " + releasedateArr[0] + " AND songs.releasedate < " + releasedateArr[1]
						+ " AND");
			}
		}

		if (includedLanguages != null) {
			whereClauseBuilder.append(" songs.language " + DatabaseUtils.generateINqry(includedLanguages) + " AND");
		}
		if (excludedLanguages != null) {
			whereClauseBuilder.append(" songs.language NOT " + DatabaseUtils.generateINqry(excludedLanguages) + " AND");
		}

		if (includedGenres != null) {
			whereClauseBuilder.append(" songs.genre " + DatabaseUtils.generateINqry(includedGenres) + " AND");
		}
		if (excludedGenres != null) {
			whereClauseBuilder.append(" songs.genre NOT " + DatabaseUtils.generateINqry(excludedGenres) + " AND");
		}

		if (includedStyles != null || excludedStyles != null) {
			if (includedStyles != null) {
				whereClauseBuilder.append(" (SELECT COUNT(*) FROM z_song_style WHERE z_song_style.song = songs.id AND z_song_style.style "
						+ DatabaseUtils.generateINqry(includedStyles) + ") = " + includedStyles.length + " AND");
			}
			if (excludedStyles != null) {
				whereClauseBuilder.append(" (SELECT COUNT(*) FROM z_song_style WHERE z_song_style.song = songs.id AND z_song_style.style "
						+ DatabaseUtils.generateINqry(excludedStyles) + ") = 0 AND");
			}
		}

		if (includedFeelings != null || excludedFeelings != null) {
			if (includedFeelings != null) {
				whereClauseBuilder.append(" (SELECT COUNT(*) FROM z_feeling_song WHERE z_feeling_song.song = songs.id AND z_feeling_song.feeling "
						+ DatabaseUtils.generateINqry(includedFeelings) + ") = " + includedFeelings.length + " AND");
			}
			if (excludedFeelings != null) {
				whereClauseBuilder.append(" (SELECT COUNT(*) FROM z_feeling_song WHERE z_feeling_song.song = songs.id AND z_feeling_song.feeling "
						+ DatabaseUtils.generateINqry(excludedFeelings) + ") = 0 AND");
			}
		}

		if (whereClauseBuilder.toString().endsWith("AND")) {
			whereClauseBuilder.replace(0, whereClauseBuilder.length(), whereClauseBuilder.substring(0, whereClauseBuilder.length() - 3));
		}
		if(mySQL) {
			qryBuilder.append(((whereClauseBuilder.toString() != " WHERE") ? whereClauseBuilder.toString() : "") + " GROUP BY songs.id ORDER BY RAND() LIMIT 25");		
		}else {
			qryBuilder.append(((whereClauseBuilder.toString() != " WHERE") ? whereClauseBuilder.toString() : "") + " ORDER BY RANDOM() fetch first 25 rows only");		
		}
		return DatabaseUtils.parseSongs(doQuery(qryBuilder.toString(), DatabaseUtils.getSongQueryColumns()), this);

	}
	
	public void deleteArtist(int id, String name) throws SQLException {
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("Delete from artists where id = " + id + " AND name = '" + name + "'");
		stmt.close();
	}

	public MusicLabel insertLabel(String name, String link) throws SQLException {
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("INSERT INTO labels (name, link) " + "values ('" + name + "', '" + link + "')",
				Statement.RETURN_GENERATED_KEYS);

		int autoIncArtistIDFromApi = -1;

		ResultSet rs = stmt.getGeneratedKeys();

		if (rs.next()) {
			autoIncArtistIDFromApi = rs.getInt(1);
		}
		rs.close();
		stmt.close();
		if (autoIncArtistIDFromApi != -1) {
			return new MusicLabel(autoIncArtistIDFromApi, name, link);
		} else {
			return null;
		}
	}

	public void deleteLabel(int id, String name) throws SQLException {
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("Delete from labels where id = " + id + " AND name = '" + name + "'");
		stmt.close();
	}
	
	public boolean checkArtistAlreadyExists(String name) throws SQLException {
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery("select * FROM artists where UPPER(name) = '" + name.toUpperCase() + "'");
		boolean ret = rs.next();				
		rs.close();
		stmt.close();
		
		return ret;
	}

	public User checkUser(String mailaddress, String encryptedPassword64Digit) throws SQLException {
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery("select user.id, user.name, user.email, user.birthday, user.usertype, usertype.name FROM user JOIN usertype on user.usertype = usertype.id"
				+ " where UPPER(email) = '" + mailaddress.toUpperCase() + "' AND password='" + encryptedPassword64Digit + "' LIMIT 1");
		User ret = null;
		if(rs.next()) {
			ret = new User(rs.getInt("user.id"), rs.getString("user.name"), rs.getString("user.email"), rs.getInt("user.birthday"), new Usertype(rs.getInt("user.usertype"), rs.getString("usertype.name")));		
		}
		rs.close();
		stmt.close();
		return ret;
		
	}

	public boolean checkUsernameAlreadyExists(String username) throws SQLException {
		if(username.length() < 4 || username.length() > 20) {
			return true;
		}
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery("select user.id FROM user where UPPER(user.name) = '" + username + "' LIMIT 1");
		boolean ret = rs.next();
		rs.close();
		stmt.close();
		return ret;
	}

	public boolean checkUsermailAlreadyExists(String mailaddress) throws SQLException {
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery("select user.id FROM user where UPPER(user.email) = '" + mailaddress + "' LIMIT 1");
		boolean ret = rs.next();
		rs.close();
		stmt.close();
		return ret;
	}

	public Song[] loadSongsByPlaylist(int playlistid) {
		String query = DatabaseUtils.getSongQuery() + " RIGHT JOIN z_playlist_songs ON z_playlist_songs.song = songs.id "
				+ "WHERE z_playlist_songs.playlist = " + playlistid + " ORDER BY z_playlist_songs.ordered";
		try {
			return DatabaseUtils.parseSongs(doQuery(query, DatabaseUtils.getSongQueryColumns()), this);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new Song[0];
	}

	public Playlist loadPlaylistByIDs(int playlistid, int userid) throws SQLException {
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery("select name FROM playlists where id = " + playlistid + " AND user = " + userid + " LIMIT 1");
		Playlist ret = null;
		if(rs.next()) {
			ret = new Playlist(playlistid, rs.getString("name"), null, loadSongsByPlaylist(playlistid));		
		}
		rs.close();
		stmt.close();
		return ret;
	}

	public boolean deletePlaylist(int playlistid, int userid) throws SQLException {
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery("select name FROM playlists where id = " + playlistid + " AND user = " + userid + " LIMIT 1");		
		boolean ret = false;
		if(rs.next()) {
			stmt.executeUpdate("delete from z_playlist_songs where playlist = " + playlistid);
			stmt.executeUpdate("delete from playlists where id = " + playlistid + " AND user = " + userid);		
			ret = true;
		}
		rs.close();
		stmt.close();
		return ret;
	}
	

}
