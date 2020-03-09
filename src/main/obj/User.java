package main.obj;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
	
	private int id;
	private String username;
	private String password;
	private String emailAddress;
	private int birthdate;
	private Usertype usertype;
	
	public User(int id, String username, String password, String emailAddress, int birthdate, Usertype usertype) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.emailAddress = emailAddress;
		this.birthdate = birthdate;
		this.usertype = usertype;
	}

	public int getId() {
		return id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public void setBirthdate(int birthdate) {
		this.birthdate = birthdate;
	}

	public Usertype getUsertype() {
		return usertype;
	}

	public void setUsertype(Usertype usertype) {
		this.usertype = usertype;
	}

	public String encryptPassword64Digit(String password) {
		return get_SHA_256_SecurePassword(password, generateSalt(username));
	}
	
	private String get_SHA_256_SecurePassword(String passwordToHash, byte[] salt)
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
	
	private byte[] generateSalt(String s) {
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

}
