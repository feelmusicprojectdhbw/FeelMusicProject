package main.obj;

public class Playlist {
	
	private int id;
	private String name;
	private User user;
	private Song[] songs;
	
	public Playlist(int id, String name, User user, Song... songs) {
		super();
		this.id = id;
		this.name = name;
		this.user = user;
		this.songs = songs;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Song[] getSongs() {
		return songs;
	}
	public void setSongs(Song[] songs) {
		this.songs = songs;
	}	
}
