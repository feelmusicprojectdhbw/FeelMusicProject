package main.obj;

import main.dao.Database;

public class Feelings {
	private static Feeling[] feelings;
	
	public static boolean areFeelingsLoaded() {
		return feelings != null;
	}
	
	public static int getFeelingID(String feeling) {
		if(feelings != null) {
			for(Feeling f : feelings) {
				if(f.getName().equals(feeling)) {
					return f.getId();
				}
			}
		}
		return 0;
	}
	
	public static Feeling getFeeling(String feeling) {
		if(feelings != null) {
			for(Feeling f : feelings) {
				if(f.getName().equals(feeling)) {
					return f;
				}
			}
		}
		return null;
	}
	
	public static Feeling getFeeling(int n) {
		if(feelings != null) {
			for(Feeling f : feelings) {
				if(f.getId() == n) {
					return f;
				}
			}
		}
		return null;
	}
	
	public static void reloadFeelings(Database db) {
		setFeelings(db.loadFeelings());
	}
	
	public static Feeling[] getFeelings() {
		return feelings;
	}

	public static void setFeelings(Feeling[] feelings) {
		Feelings.feelings = feelings;
	}

}
