package main.obj;

public class CoArtists {
	
	private Artist[] coartists;
	
	public CoArtists(Artist... artists) {
		this.setCoartists(artists);
	}

	public Artist[] getCoartists() {
		return coartists;
	}

	public void setCoartists(Artist[] coartists) {
		this.coartists = coartists;
	}
	
	public String toString() {
		String ret = "";
		for(Artist a : coartists) {
			ret += "<t>" + a + "<br>";
		}
		return ret;
	}

}
