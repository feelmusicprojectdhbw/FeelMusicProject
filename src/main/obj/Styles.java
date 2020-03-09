package main.obj;

import main.Database;

public class Styles {
private static Style[] styles;
	
	public static boolean areStylesLoaded() {
		return styles != null;
	}
	
	public static Style getStyle(String style) {
		if(styles != null) {
			for(Style f : styles) {
				if(f.getName().equals(style)) {
					return f;
				}
			}
		}
		return null;
	}
	
	public static Style getStyle(int n) {
		if(styles != null) {
			for(Style f : styles) {
				if(f.getId() == n) {
					return f;
				}
			}
		}
		return null;
	}
	
	public static void reloadStyles(Database db) {
		setStyles(db.loadStyles());
	}
	
	public static Style[] getStyles() {
		return styles;
	}

	public static void setStyles(Style[] styles) {
		Styles.styles = styles;
	}
}
