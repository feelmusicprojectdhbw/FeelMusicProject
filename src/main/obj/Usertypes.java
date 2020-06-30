package main.obj;

import java.util.ArrayList;

import main.dao.Database;

public class Usertypes {
	
	private static Usertype[] allUsertypes;
	
	public static ArrayList<Usertype> getFilteredUsertypes(int requestingUsertypeId) {	
		ArrayList<Usertype> ret = new ArrayList<Usertype>();
		if(requestingUsertypeId == 1) {
			for(Usertype ut : getAllUsertypes()) { //Admins
				ret.add(ut);
			}
		}else if(requestingUsertypeId == 3) { //Moderators
			for(Usertype ut : getAllUsertypes()) {
				if(ut.getId() != 1 && ut.getId() != 3) {
					ret.add(ut);
				}
			}
		}
		return ret;
	}

	public static Usertype[] getAllUsertypes() {
		if(allUsertypes != null) {
			return allUsertypes;
		}else{
			return allUsertypes = Database.getAllUsertypes();
		}
	}

	public static Usertype getUsertypeById(int searchedId) {
		if(allUsertypes == null) {
			allUsertypes = Database.getAllUsertypes();		
		}
		for(Usertype u : allUsertypes) {
			if(u.getId() == searchedId) {
				return u;
			}
		}
		return null;
	}
}
