package main;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import main.obj.Artist;
import main.obj.CoArtists;
import main.obj.Feeling;
import main.obj.Genre;
import main.obj.Language;
import main.obj.Playlist;
import main.obj.Song;
import main.obj.Style;
import main.obj.User;

public class Database {
	
	private Connection connection;
	
	private String host, database, user, pw;
	
	public Database(String host, String database, String user, String pw) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, SQLException {
		super();
		Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
		this.host = host;
		this.database = database;
		this.user = user;
		this.pw = pw;
		connection = connect();
	}

	private Connection connect() throws SQLException {
		String connectionCommand = "jdbc:mysql://"+host+"/"+database+"?user="+user+"&password="+pw;
		return DriverManager.getConnection(connectionCommand);
	}

	public static Database connectToMySQL(String host, String database, String user, String passwd) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{		// Klasse zum Verbinden mit der Datenbank		
		try{
			return new Database(host, database, user ,passwd);
		}catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e){
			System.out.println("Konnte keine Verbindung herstellen!");
			e.printStackTrace();	
			return null;
		}
	}

	public Connection getConnection() {
		return connection;
	}
	
	private ArrayList<String[]> doQuery(String sql, String... columnNames) throws SQLException{
		ArrayList<String[]> arr = new ArrayList<String[]>();
		Statement stmt = getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			String[] str = new String[columnNames.length];
			for(int i = 0; i < columnNames.length; i++) {
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
		for(int i = 0; i < arrlst.size(); i++) {
			ret[i] = new Language(Integer.parseInt(arrlst.get(i)[0]), arrlst.get(i)[1]);
		}
		
		return ret;
	}
	
	/**
	 * Loads all Genres found in the Database
	 * @return Array of Genre
	 */
	public Genre[] loadGenres(){
		ArrayList<String[]> arrlst;
		try {
			arrlst = doQuery("select * from genres order by id", "id", "name", "parent");
		} catch (SQLException e) {
			e.printStackTrace();
			return new Genre[0];
		}
		Genre[] ret = new Genre[arrlst.size()];
		for(int i = 0; i < arrlst.size(); i++) {
			ret[i] = new Genre(Integer.parseInt(arrlst.get(i)[0]), arrlst.get(i)[1],(arrlst.get(i)[2] == null)?0:Integer.parseInt(arrlst.get(i)[2]));
		}
		return ret;
	}
	
	/**
	 * Loads all Feelings found in the Database
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
		for(int i = 0; i < arrlst.size(); i++) {
			ret[i] = new Feeling(Integer.parseInt(arrlst.get(i)[0]), arrlst.get(i)[1]);
		}
		return ret;
	}
	
	/**
	 * Loads all Styles found in the Database
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
		for(int i = 0; i < arrlst.size(); i++) {
			ret[i] = new Style(Integer.parseInt(arrlst.get(i)[0]), arrlst.get(i)[1]);
		}
		return ret;
	}
	
	/**
	 * Returns all Co-Artists found by SongID
	 * @param songID
	 * @return
	 */
	public CoArtists loadCoArtistsBySong(int songID) {
		String query = "select b.id, b.name, b.link FROM z_song_co_artist as a "
				+ "JOIN artists as b ON a.co_artist_id = b.id WHERE a.song_id = '" + songID + "'";
		try {
			return DatabaseUtils.parseCoArtists(doQuery(query, "b.id", "b.name", "b.link"));
		} catch (SQLException e) {
			e.printStackTrace();
			return new CoArtists();
		}
	}
	
	/**
	 * Searchs for a Songname with LIKE 'SONGNAME%'
	 * @param name Part of a Name to search for
	 * @return Array Of Songs that were found
	 */
	public Song[] searchForSongByName(String name) {		
		String query = DatabaseUtils.getSongQuery() + " WHERE a.name Like '" + name + "%'";
		try {
			return DatabaseUtils.parseSongs(doQuery(query, DatabaseUtils.getSongQueryColumns()), this);
		} catch (SQLException e) {
			e.printStackTrace();
			return new Song[0];
		}
	}
	/**
	 * Returns all Songs found by a Artist OR Co_Artist
	 * @param a Artist to search for
	 * @return Array of Songs
	 */
	public Song[] getAllSongsByArtist(Artist a) {
		String query = DatabaseUtils.getSongQuery() + " JOIN z_song_co_artist AS c ON c.song_id = a.id " + 
				"JOIN artists AS z ON c.co_artist_id = z.id " + 
				"WHERE b.id = '" + a.getId() + "' OR z.id = '" + a.getId() + "' " + 
				"GROUP BY SongID";
		
		try {
			return DatabaseUtils.parseSongs(doQuery(query, DatabaseUtils.getSongQueryColumns()), this);
		} catch (SQLException e) {
			e.printStackTrace();
			return new Song[0];
		}
	}
	
	/**
	 * Returns all Artists by Name or Part of a Name
	 * @param name
	 * @return
	 */
	public Artist[] searchArtistByName(String name) {
		String query = "select * from artists where name LIKE '" + name + "%'";
		try {
			return DatabaseUtils.parseArtists(doQuery(query, "id", "name", "link"));
		} catch (SQLException e) {
			e.printStackTrace();
			return new Artist[0];
		}
	}
	
	public Playlist[] loadPlaylistsByUser(User user) {
		
		String query = "select * from playlists where user = '" + user.getId() + "'";
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
		String query = DatabaseUtils.getSongQuery() + " RIGHT JOIN z_playlist_songs AS z ON z.song = a.id "
				+ "WHERE z.playlist = '" + p.getId() + "' ORDER BY z.order";
		try {
			p.setSongs(DatabaseUtils.parseSongs(doQuery(query, DatabaseUtils.getSongQueryColumns()), this));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Inserts a new playlist and adds the Songs into it
	 * @param pllst
	 * @return
	 * @throws SQLException
	 */
	public Playlist insertPlaylist(Playlist pllst) throws SQLException {
		Statement stmt = connection.createStatement();
		stmt.executeUpdate(
	            "INSERT INTO playlists (name, user) "
	            + "values ('" + pllst.getName() + "', " + pllst.getUser().getId() + ")",
	            Statement.RETURN_GENERATED_KEYS);

	    int autoIncPlaylistIDFromApi = -1;

	    ResultSet rs = stmt.getGeneratedKeys();

	    if (rs.next()) {
	    	autoIncPlaylistIDFromApi = rs.getInt(1);
	    }
	    
	    pllst.setId(autoIncPlaylistIDFromApi);
	    int n = 0;
	    for(Song s : pllst.getSongs()) {
	    	stmt.executeUpdate("INSERT INTO z_playlist_songs (playlist, song, order) "
	    			+ "VALUES (" + autoIncPlaylistIDFromApi + ", " + s.getId() + ", " + n++ + ")");
	    }	    
	    stmt.close();
	    return pllst;		
	}
	
	
	
	
	
	
}
