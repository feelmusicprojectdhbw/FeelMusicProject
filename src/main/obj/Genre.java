package main.obj;

public class Genre {
	private int id;
	private String name;
	private int parentID;
	
	private Genre[] subgenres;
	
	public Genre(int id, String name, int parentID) {
		this.name = name;
		this.id = id;
		this.setParentID(parentID);
		subgenres = new Genre[0];
	}
	
	public int getId() {
		return id;
	}

	public Genre[] getSubgenres() {
		return subgenres;
	}
	
	public void addSubgenre(Genre g) {
		if(subgenres == null) {
			subgenres = new Genre[0];
		}
		Genre[] arr = new Genre[subgenres.length + 1];
		for(int i = 0; i < subgenres.length; i++) {
			arr[i] = subgenres[i];
		}
		arr[arr.length-1] = g;
		subgenres = arr;
	}

	public String getName() {
		return name;
	}

	public Genre searchParentId(int n) {
		Genre g = null;
		if(this.id == n) {
			g = this;
		}else {
			if(subgenres != null) {
				for(Genre sg : subgenres) {
					g = sg.searchParentId(n);
				}
			}
		}
		return g;
	}

	public int getParentID() {
		return parentID;
	}

	public void setParentID(int parentID) {
		this.parentID = parentID;
	}
	
}
