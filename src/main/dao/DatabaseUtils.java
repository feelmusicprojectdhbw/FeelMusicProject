package main.dao;

import java.util.ArrayList;

import main.obj.Album;
import main.obj.Artist;
import main.obj.CoArtists;
import main.obj.Genre;
import main.obj.Genres;
import main.obj.Language;
import main.obj.Languages;
import main.obj.MusicLabel;
import main.obj.Playlist;
import main.obj.Song;
import main.obj.User;

public class DatabaseUtils {

	public static Song[] parseSongs(ArrayList<String[]> arrlst, Database db) {
		Song[] ret = new Song[arrlst.size()];
		for(int i = 0; i < ret.length; i++) {
			String[] songArr = arrlst.get(i);
			int songID = Integer.parseInt(songArr[0]);
			int songReleasedate = Integer.parseInt(songArr[1]);
			
			Artist artist = new Artist(Integer.parseInt(songArr[3]), songArr[4], songArr[5]);
			CoArtists co = db.loadCoArtistsBySong(songID);
			
			Album album = new Album(Integer.parseInt(songArr[6]), songArr[7], Integer.parseInt(songArr[8]));
			Genre g = Genres.searchGenreById(Integer.parseInt(songArr[9]));
			MusicLabel mlbl = new MusicLabel(Integer.parseInt(songArr[10]), songArr[11], songArr[12]);
			Language lang = Languages.getLanguageById(Integer.parseInt(songArr[13]));
			String[] links = new String[songArr.length - 14];
			for (int j = 0; j < links.length; j++) {
				links[j] = songArr[14+j];
			}
			
			ret[i] = new Song(songID, songReleasedate, songArr[2], artist, co, album, g, mlbl, lang, links);
		}
		return ret;
	}
	
	public static CoArtists parseCoArtists(ArrayList<String[]> coArtistList) {
		Artist[] retArr = new Artist[coArtistList.size()];
		int i = 0;
		for(String[] s : coArtistList) {
			retArr[i++] = new Artist(Integer.parseInt(s[0]), s[1], s[2]);
		}
		return new CoArtists(retArr);
	}

	/**
	 * ^7 stück
	 * @return
	 */
	public static String[] getSongQueryColumns() {
		return new String[] {"SongID", "SongReleasedate", "Songname", "artistid", "artistname", "artistlink",
				"AlbumID", "AlbumName", "AlbumReleasedate", "genreID", "labelID", "labelName", "labelLink",
				"languageID", "yt_link", "sf_link", "sc_link"};
	}
	
	/**
	 * Returns a query, that selects all songinformation needed to parse the song.
	 * Tables for where clause: song alias a, artists alias b, co_artists alias c, 
	 * artists (from co_artists) alias zn (n = 1-9), albums alias d, labels alias e. 
	 * 
	 * @return
	 */
	public static String getSongQuery() {
		return "SELECT " + 
				"	songs.id AS SongID, songs.releasedate AS SongReleasedate, songs.name AS Songname, " + 
				"	artists.id AS artistid, artists.name AS artistname, artists.link AS artistlink, " + 
				"	albums.id AS AlbumID, albums.name AS AlbumName, albums.releasedate AS AlbumReleasedate, " + 
				"	songs.genre AS genreID, " + 
				"	labels.id AS labelID, labels.name AS labelName, labels.link AS labelLink, " + 
				"	songs.language AS languageID, songs.yt_link AS yt_link, songs.sf_link AS sf_link, songs.sc_link AS sc_link " + 
				"FROM songs " + 
				"LEFT JOIN artists ON songs.artist = artists.id " + 
				"LEFT JOIN albums ON songs.album = albums.id " + 
				"LEFT JOIN labels ON songs.label = labels.id ";
	}

	public static boolean checkArtistExists() {
		// TODO Auto-generated method stub
		return false;
	}

	public static Artist[] parseArtists(ArrayList<String[]> arr) {
		Artist[] ret = new Artist[arr.size()];
		int i = 0;
		for(String[] str : arr) {
			ret[i++] = new Artist(Integer.parseInt(str[0]), str[1], str[2]);
		}
				
		return ret;
	}

	public static Playlist[] parsePlaylists(ArrayList<String[]> arr, User user) {
		Playlist[] ret = new Playlist[arr.size()];
		int i = 0;
		for(String[] str : arr) {
			ret[i++] = new Playlist(Integer.parseInt(str[0]), str[1], user);
		}
		return ret;
	}

	public static String generateINqry(int[] arr) {
		String ret = "IN(";
		for(int i = 0; i < arr.length; i++) {
			if(i != 0) {
				ret += ",";
			}
			ret += arr[i];
		}
		return ret += ")";
	}

	public static MusicLabel[] parseLabels(ArrayList<String[]> arr) {
		MusicLabel[] ret = new MusicLabel[arr.size()];
		int i = 0;
		for(String[] str : arr) {
			ret[i++] = new MusicLabel(Integer.parseInt(str[0]), str[1], str[2]);
		}
				
		return ret;
	}
}
