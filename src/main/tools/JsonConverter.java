package main.tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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

public class JsonConverter {
	
	private final Gson gson;
    
    public JsonConverter() {     
        gson = new GsonBuilder().create();
    }
    
	public String convertToJson(Album a) {    
    	return convertAlbum(a).toString();
    }
	
	public String convertToJson(Artist a) {    
    	return convertArtist(a).toString();
    }
	
	public String convertToJson(CoArtists ca) {    
    	return convertCoArtists(ca).toString();
    }
	
	public String convertToJson(Feeling[] fs) {
		return convertFeelings(fs).toString();
	}

	public String convertToJson(Feeling f) {
		return convertFeeling(f).toString();
	}
	
	public String convertToJson(Genre[] gs) {
		return convertGenres(gs).toString();
	}
	
	public String convertToJson(Genre g) {
		return convertGenre(g).toString();
	}
	
	public String convertToJson(Language[] gs) {
		return convertLanguages(gs).toString();
	}

	public String convertToJson(Language g) {
		return convertLanguage(g).toString();
	}
	
	public String convertToJson(MusicLabel ml) {
		return convertMusicLabel(ml).toString();
	}
    
    public String convertToJson(Playlist p) {    
    	return convertPlaylist(p).toString();
    }

	public String convertToJson(Song[] s) { 	
    	return convertSongs(s).toString();
    }
	
	public String convertToJson(Song s) {	   	
    	return convertSong(s).toString();
    }	
	
	public String convertToJson(Style[] s) { 	
    	return convertStyles(s).toString();
    }
	
	public String convertToJson(Style s) {	   	
    	return convertStyle(s).toString();
    }
	
	public String convertToJson(User u) {
    	return convertUser(u).toString();
    }
	
	public String convertToJson(Usertype ut) {
    	return convertUsertype(ut).toString();
    }
	
	private JsonObject convertPlaylist(Playlist p) {
		JsonObject ret = new JsonObject();
    	ret.addProperty("id", p.getId());
    	ret.addProperty("name", p.getName());
    	ret.add("user", convertUser(p.getUser()));
    	ret.add("songs", convertSongs(p.getSongs()));
		return ret;
	}

	private JsonElement convertUser(User u) {
		JsonObject ret = new JsonObject();
		ret.addProperty("id", u.getId());
		ret.addProperty("username", u.getUsername());
		ret.addProperty("emailAddress", u.getEmailAddress());
		ret.addProperty("birthdate", u.getBirthdate());
		ret.add("usertype", convertUsertype(u.getUsertype()));
		return ret;
	}
	
	private JsonElement convertUsertype(Usertype ut) {
		JsonObject ret = new JsonObject();
		ret.addProperty("id", ut.getId());
		ret.addProperty("type", ut.getType());
		return ret;
	}

	private JsonElement convertSongs(Song[] sa) {
		JsonArray jarray = new JsonArray(); 
		if(sa != null) {
			for(Song s : sa) {
				jarray.add(convertSong(s));
			}
		}
		return jarray;
	}
	
	private JsonElement convertSong(Song s) {
		JsonObject ret = new JsonObject();
		ret.addProperty("id", s.getId());
		ret.addProperty("releasedate", s.getReleasedate());
		ret.addProperty("name", s.getName());
		ret.add("artist", convertArtist(s.getArtist()));
		ret.add("coArtists", convertCoArtists(s.getCoArtists()));
		ret.add("album", convertAlbum(s.getAlbum()));
		ret.add("genre", convertGenre(s.getGenre()));
		ret.add("musiclabel", convertMusicLabel(s.getLabel()));
		ret.add("language", convertLanguage(s.getLanguage()));		
		ret.add("links", gson.toJsonTree(s.getLinks()).getAsJsonArray());
		return ret;
	}

	private JsonElement convertArtist(Artist a) {
		JsonObject ret = new JsonObject();
		ret.addProperty("id", a.getId());
		ret.addProperty("name", a.getName());
		ret.addProperty("link", a.getLink());
		return ret;
	}
	
	private JsonElement convertCoArtists(CoArtists ca) {
		JsonArray jarray = new JsonArray(); 
		for(Artist a : ca.getCoartists()) {
			jarray.add(convertArtist(a));
		}
		return jarray;
	}
	
	private JsonElement convertAlbum(Album a) {
		JsonObject ret = new JsonObject();
		ret.addProperty("id", a.getId());
		ret.addProperty("name", a.getName());
		ret.addProperty("releasedate", a.getReleasedate());
		return ret;
	}
	
	private JsonElement convertGenre(Genre g) {
		JsonObject ret = new JsonObject();
		ret.addProperty("id", g.getId());
		ret.addProperty("name", g.getName());
		ret.addProperty("parentID", g.getParentID());
		ret.add("subgenres", convertGenres(g.getSubgenres()));
		return ret;
	}
	
	private JsonElement convertGenres(Genre[] gs) {
		JsonArray jarray = new JsonArray();
		if(gs != null) {
			for(Genre g : gs) {
				jarray.add(convertGenre(g));
			}
		}
		return jarray;
	}
	
	private JsonElement convertMusicLabel(MusicLabel ml) {
		JsonObject ret = new JsonObject();
		ret.addProperty("id", ml.getId());
		ret.addProperty("name", ml.getName());
		ret.addProperty("link", ml.getLink());
		return ret;
	}
	
	private JsonArray convertLanguages(Language[] ls) {
		JsonArray jarray = new JsonArray();
		if(ls != null) {
			for(Language l : ls) {
				jarray.add(convertLanguage(l));
			}
		}
		return jarray;
	}
	
	private JsonElement convertLanguage(Language l) {
		JsonObject ret = new JsonObject();
		ret.addProperty("id", l.getId());
		ret.addProperty("name", l.getName());
		return ret;
	}
	
	private Object convertFeelings(Feeling[] fs) {
		JsonArray jarray = new JsonArray();
		if(fs != null) {
			for(Feeling f : fs) {
				jarray.add(convertFeeling(f));
			}
		}
		return jarray;
	}

	private JsonElement convertFeeling(Feeling f) {
		JsonObject ret = new JsonObject();
		ret.addProperty("id", f.getId());
		ret.addProperty("name", f.getName());
		return ret;
	}
	
	private Object convertStyles(Style[] ss) {
		JsonArray jarray = new JsonArray();
		if(ss != null) {
			for(Style s : ss) {
				jarray.add(convertStyle(s));
			}
		}
		return jarray;
	}

	private JsonElement convertStyle(Style s) {
		JsonObject ret = new JsonObject();
		ret.addProperty("id", s.getId());
		ret.addProperty("name", s.getName());
		return ret;
	}
}
