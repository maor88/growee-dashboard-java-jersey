package com.growee.objects.contactUs;

public class ContactUsEmail {

	String userName;
	String emailToSend;
	String emailContent;
	String deviceId;
	String userId;
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailToSend() {
		return emailToSend;
	}

	public void setEmailToSend(String emailToSend) {
		this.emailToSend = emailToSend;
	}

	public String getEmailContent() {
		return emailContent;
	}

	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String toString() { 
	    return "	User-name: " + this.userName + ",	User-id: " + this.userId + ",	Device-id: " + this.deviceId + ",	User-Email: " + this.emailToSend + "\n\n Message:\n" + this.emailContent;
	} 
}
