package main.obj;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import main.dao.Database;
import main.tools.Validations;

public class User {
	
	private int id;
	private String username;
	private String emailAddress;
	private int birthdate;
	private Usertype usertype;
	
	public User(int id, String username, String emailAddress, int birthdate, Usertype usertype) {
		super();
		this.id = id;
		this.username = username;
		this.emailAddress = emailAddress;
		this.birthdate = birthdate;
		this.usertype = usertype;
	}

	public int getId() {
		return id;
	}
	
	public boolean isAdmin() {
		return usertype.getId() == 1;
	}
	
	public boolean isDefault() {
		return usertype.getId() == 2;
	}
	
	public boolean isModerator() {
		return usertype.getId() == 3;
	}
	
	public boolean isArtist() {
		return usertype.getId() == 4;
	}
	
	public boolean hasElevatedPermissions() {
		return isAdmin() || isModerator();
	}
	
	public boolean isLabel() {
		return usertype.getId() == 5;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public int getBirthdate() {
		return birthdate;
	}
	
	public String getFormattedBirthdate() {
		return Validations.formatBirthdate(birthdate);
	}

	public void setBirthdate(int birthdate) {
		this.birthdate = birthdate;
	}

	public Usertype getUsertype() {
		return usertype;
	}

	public void setUsertype(Usertype usertype) {
		this.usertype = usertype;
	}

	public static String encryptPassword64Digit(String password, String mail) {
		return get_SHA_256_SecurePassword(password, generateSalt(mail));
	}
	
	private static String get_SHA_256_SecurePassword(String passwordToHash, byte[] salt)
    {
        String generatedPassword = null;
        try {
        	MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }
	
	private static byte[] generateSalt(String s) {
		byte[] salt = new byte[16];
		int n = 0;
		for (int i = 0; i < salt.length; i++) {
			if(i < s.length()) {
				salt[i] = (byte)((int)s.charAt(i) & 0xFF);
			}else {
				salt[i] = (byte)((int)'A'+ n++ + i & 0xFF);
			}
			
		}
		return salt;
	}
	
	public static User getCurrentUser(HttpSession ses) {
		return null;
		
	}
	
	public Playlist[] loadUserplaylists() {
		return Database.loadPlaylistsByUser(this);
	}

	public Optional<User> findByLoginToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

}
