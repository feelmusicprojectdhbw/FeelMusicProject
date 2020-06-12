package main.tools;

import java.util.ArrayList;

public class Validations {

	public static int[] entferneDoppelte(int... ids) {
		ArrayList<Integer> idslist = new ArrayList<Integer>();
		for(int n : ids) {
			boolean schondrin = false;
			for(int drin : idslist) {
				if(n == drin) {
					schondrin = true;
				}
			}
			if(!schondrin) {
				idslist.add(n);
			}
		}
		int[] ret = new int[idslist.size()];
		for(int i = 0; i < ret.length; i++) {
			ret[i] = idslist.get(i).intValue();

		}
		return ret;
	}

	public static int[] entferneDoppelte(int[] styleIDs, int[] defaultStyles) {
		int[] ret = new int[styleIDs.length + defaultStyles.length];
		int i = 0;
		for(int n : styleIDs) {
			ret[i] = n;
			i++;
		}
		for(int n : defaultStyles) {
			ret[i] = n;
			i++;
		}
		return entferneDoppelte(ret);
	}
}
