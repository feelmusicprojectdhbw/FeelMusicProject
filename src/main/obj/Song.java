package main.obj;

public class Song {
	
	private int id;
	private int releasedate;
	private String songname;
	private Artist artist;
	private CoArtists coArtists;
	private Album album;
	private Genre genre;
	private MusicLabel label;
	private Language language;
	private String[] links;
	
	public Song(int id, int releasedate, String name, Artist artist, CoArtists coArtists, Album album, Genre genre,
			MusicLabel label, Language language, String... links) {
		super();
		this.id = id;
		this.releasedate = releasedate;
		this.songname = name;
		this.artist = artist;
		this.coArtists = coArtists;
		this.album = album;
		this.genre = genre;
		this.label = label;
		this.language = language;
		this.links = links;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getReleasedate() {
		return releasedate;
	}

	public void setReleasedate(int releasedate) {
		this.releasedate = releasedate;
	}

	public String getName() {
		return songname;
	}

	public void setName(String name) {
		this.songname = name;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public CoArtists getCoArtists() {
		return coArtists;
	}

	public void setCoArtists(CoArtists coArtists) {
		this.coArtists = coArtists;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public MusicLabel getLabel() {
		return label;
	}

	public void setLabel(MusicLabel label) {
		this.label = label;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public String[] getLinks() {
		return links;
	}

	public void setLinks(String[] links) {
		this.links = links;
	}
	
	
	
}
