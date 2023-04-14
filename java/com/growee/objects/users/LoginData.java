/**
 * 
 */
package com.growee.objects.users;

/**
 * @author Maor
 *
 */
public class LoginData {

	boolean isAdmin = false;
	boolean isUser = false;
	boolean isFirstTime = false;
	boolean isUserNameSet = false;
	String session_id;

	
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public boolean isUser() {
		return isUser;
	}
	public void setUser(boolean isUser) {
		this.isUser = isUser;
	}
	public boolean isFirstTime() {
		return isFirstTime;
	}
	public void setFirstTime(boolean isFirstTime) {
		this.isFirstTime = isFirstTime;
	}
	public boolean isUserNameSet() {
		return isUserNameSet;
	}

	public void setUserNameSet(boolean result) {
		this.isUserNameSet = result;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	
	
	
}
