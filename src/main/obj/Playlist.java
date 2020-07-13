package main.obj;

import com.google.gson.Gson;

public class Playlist {
	
	private int id;
	private String playlistname;
	private User user;
	private Song[] songs;
	
	public Playlist(int id, String name, User user, Song... songs) {
		super();
		this.id = id;
		this.playlistname = name;
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
		return playlistname;
	}
	public boolean isDefault() {
		return id == 0;
	}
	public void setName(String name) {
		this.playlistname = name;
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
	
	public String asJSON() {
		return new Gson().toJson(this);
	}
}
