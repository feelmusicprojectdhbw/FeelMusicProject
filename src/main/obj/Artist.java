package main.obj;

public class Artist {
	private int id;
	private String name;
	private String link;
	
	public Artist(int id, String name, String link) {
		super();
		this.setId(id);
		this.setName(name);
		this.setLink(link);
	}

	public String getName() {
		return name;
	}
	
	public boolean isNoDefault() {
		return id != 1;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String toString() {
		return "ID: " + id + ", " + name + " " + link;
	}
}
