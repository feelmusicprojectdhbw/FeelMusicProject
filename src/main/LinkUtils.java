package main;

public class LinkUtils {
	
	public static void main(String args[]) {
		System.out.println(parseSoundCloudLink("<iframe width=\"500\" height=\"300\" scrolling=\"no\" frameborder=\"no\" allow=\"autoplay\" \r\n" + 
				"		src=\"https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/672606881&color=%23ff5500&auto_play=true&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true\">\r\n" + 
				"		</iframe>"));
	}
	
	public static String parseLinkFromEmbeddedCode(String embedded) {
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
	}
	
	public static String parseYoutubeLink(String link) {
		String parsed = parseLinkFromEmbeddedCode(link);
		if(parsed.contains("www.youtube.com")) {
			String idEnd = parsed.split("v=")[1];
			String ret = "";
			for(char c : idEnd.toCharArray()) {
				if(c != '&' && c != '/') {
					ret += c;
				}else {
					return ret;
				}
			}
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
		if(parsed.contains("open.spotify.com/embed/track/")) {
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
