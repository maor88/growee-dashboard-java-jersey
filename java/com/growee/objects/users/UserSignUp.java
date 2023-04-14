package com.growee.objects.users;

public class UserSignUp {

	String newUserEmail;
	String newPassword;
	String userName;
	
	
	public UserSignUp(String newUserEmail, String newPassword, String userName) {
		super();
		this.newUserEmail = newUserEmail;
		this.newPassword = newPassword;
		this.userName = userName;
	}

	public String getNewUserEmail() {
		return newUserEmail;
	}

	public void setNewUserEmail(String newUserEmail) {
		this.newUserEmail = newUserEmail;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
	
}
