package main.obj;

import main.dao.Database;

public class Genres {
	
	public static Genre[] genres;

	public static void requery(Database db) {
		genres = null;
		Genre[]arr = db.loadGenres();
		for(Genre g : arr) {
			if(g.getParentID() == 0) {
				addGenre(g);
			}else {
				try {
					searchGenreById(g.getParentID()).addSubgenre(g);
				}catch(NullPointerException e) {
					System.out.println("Fehler in der Genredatenbank");
				}
			}
		}
	}

	public static Genre searchGenreById(int n) {
		if(genres != null) {
			for(Genre g : genres) {
				if(g.getId() == n) {
					return g;
				}else {			
					Genre tmp = g.searchParentId(n);
					if(tmp == null) {
						return null;
					}else {
						return tmp;
					}				
				}
			}
		}
		return null;
	}

	public static Genre[] getGenres() {
		return genres;
	}
	
	private static void addGenre(Genre g) {
		if(genres == null) {
			genres = new Genre[0];
		}
		Genre[] arr = new Genre[genres.length + 1];
		for(int i = 0; i < genres.length; i++) {
			arr[i] = genres[i];
		}
		arr[arr.length-1] = g;
		genres = arr;
	}

	public static int getGenreID(String genre) {
		if(genres != null) {
			for(Genre g : genres) {
				int n = g.getGenreID(genre);
				if(n != 0) {
					return n;
				}
			}
		}
		return 0;
	}
}
