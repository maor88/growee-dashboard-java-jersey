package com.growee.objects.users;

/**
 * @author Maor
 *
 */
public class UserDetails {

	String userId;
	String userEmail;
	String groweeDeviceId;
	String userName;
	String pass;
	boolean isAdmin;

	public UserDetails(String userName, String userPasswor) {
		this.userName = userName;
		this.pass = userPasswor;
	}
	
	public UserDetails(){};
	
	public UserDetails(String userId){
		this.userId = userId;
	};
	
	public UserDetails(String id, String email, String userPasswor, String groweeDevice, String userCode ) {
		this.userId = id;
		this.userEmail = email;
		this.groweeDeviceId = groweeDevice;
		this.userName = userCode;
		this.pass = userPasswor;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setGroweeDeviceId(String groweeDeviceId) {
		this.groweeDeviceId = groweeDeviceId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserCode(String userCode) {
		this.userName = userCode;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String getUserId() {
		return this.userId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public String getGroweeDeviceId() {
		return groweeDeviceId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean admin) {
		isAdmin = admin;
	}
}
