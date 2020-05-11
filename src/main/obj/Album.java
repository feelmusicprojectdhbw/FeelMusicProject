 package main.obj;

public class Album {
	private int id;
	private String name;
	private int releasedate;
	
	public Album(int id, String name, int releasedate) {
		super();
		this.id = id;
		this.name = name;
		this.releasedate = releasedate;
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
	public int getReleasedate() {
		return releasedate;
	}
	public void setReleasedate(int releasedate) {
		this.releasedate = releasedate;
	}
	
	public String toString() {
		return "ID: " + id + ", " + name + " Released: " + releasedate;
		
	}
	
}
