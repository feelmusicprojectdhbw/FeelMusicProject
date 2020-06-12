package main.obj;

import main.dao.Database;

public class Languages {
	private static Language[] languages;
	
	public static void reloadLanguages(Database db) {
		setLanguages(db.loadLanguages());
	}
	
	public static Language getLanguageById(int id) {
		if(languages != null) {
			for(Language l : languages) {
				if(l.getId() == id) {
					return l;
				}
			}
		}
		return null;
	}

	public static Language[] getLanguages() {
		return languages;
	}

	public static void setLanguages(Language[] languages) {
		Languages.languages = languages;
	}

	public static int getLanguageID(String inputLanguage) {
		if(languages != null) {
			for(Language l : languages) {
				if(l.getName().equals(inputLanguage)) {
					return l.getId();
				}
			}
		}
		return 0;
	}
}
