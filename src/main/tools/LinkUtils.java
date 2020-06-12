package main.tools;

public class LinkUtils {
	
	public static String parseLinkFromEmbeddedCode(String embedded) {
		if(embedded.contains("src=\"")) {
			String arr[] = embedded.split("src=\"");
			if(arr.length > 1) {
				return arr[1].split("\"")[0];
			}else {
				if(!embedded.startsWith("\"")) {
					return embedded.split("\"")[0];
				}else {
					return embedded.replaceFirst("\"", "").split("\"")[0];
				}
			}
		}else {
			return embedded;
		}
	}
	
	public static String parseYoutubeLink(String link) {
		String parsed = parseLinkFromEmbeddedCode(link);
		if(parsed.contains("youtube.com")) {
			String idEnd = null;
			try {
				idEnd = parsed.split("v=")[1];
			}catch(ArrayIndexOutOfBoundsException e) {
				idEnd = parsed.split("embed/")[1].split("\"")[0];
			}
			
			String ret = "";
			for(char c : idEnd.toCharArray()) {
				if(c != '&' && c != '/') {
					ret += c;
				}else {
					return ret;
				}
			}
			return ret;
		}
		return null;
	}
	
	public static String parseSoundCloudLink(String link) {
		String parsed = parseLinkFromEmbeddedCode(link);
		if(parsed.contains("api.soundcloud")) {
			if(parsed.contains("?url=")) {
				return parsed.split("url=")[1].split("/tracks/")[1].replaceFirst("&", "HIERTRENNEN").split("HIERTRENNEN")[0];
			}else {
				if(parsed.contains("api.soundcloud.com/tracks/")) {
					parsed = parsed.split("/tracks/")[1];
				}
				return parsed.replaceFirst("&", "HIERTRENNEN").split("HIERTRENNEN")[0];
			}
		}else {
			return null;	
		}
	}
	
	public static String parseSpotifyLink(String link) {
		String parsed = parseLinkFromEmbeddedCode(link);
		if(parsed.contains("spotify.com")) {
			String idEnd = parsed.split("/track/")[1];
			String ret = "";
			for(char c : idEnd.toCharArray()) {
				if(c != '&' && c != '/') {
					ret += c;
				}else {
					break;
				}
			}
			if(ret != "") {
				return ret;
			}
		}
		return null;
	}
}
