package com.growee.objects.users;

public class ConnectionStatus {
	
	String userId;
	boolean isSawWelcomePage;
	boolean isInitDevice;
	boolean isDeviceConnectedToWifi;
	String deviceId;
	String emailNotification;
	boolean isNotificationOn;
	
	public ConnectionStatus(){}
	
	public ConnectionStatus(boolean isSawWelcomePage, boolean isInitDevice, boolean isDeviceConnectedToWifi) {
		this.isSawWelcomePage = isSawWelcomePage;
		this.isInitDevice = isInitDevice;
		this.isDeviceConnectedToWifi = isDeviceConnectedToWifi;
	}
	
	
	public boolean getIsSignUp() {
		return isInitDevice;
	}
	public void setIsSignUp(boolean isInitDevice) {
		this.isInitDevice = isInitDevice;
	}
	public boolean getIsDeviceConnectedToWifi() {
		return isDeviceConnectedToWifi;
	}
	public void setIsDeviceConnectedToWifi(boolean isDeviceConnectedToWifi) {
		this.isDeviceConnectedToWifi = isDeviceConnectedToWifi;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isSawWelcomePage() {
		return isSawWelcomePage;
	}

	public void setSawWelcomePage(boolean isSawWelcomePage) {
		this.isSawWelcomePage = isSawWelcomePage;
	}

	public boolean isInitDevice() {
		return isInitDevice;
	}

	public void setInitDevice(boolean isInitDevice) {
		this.isInitDevice = isInitDevice;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getEmailNotification() {
		return emailNotification;
	}

	public void setEmailNotification(String emailNotification) {
		this.emailNotification = emailNotification;
	}

	public boolean isNotificationOn() {
		return isNotificationOn;
	}

	public void setNotificationOn(boolean isNotificationOn) {
		this.isNotificationOn = isNotificationOn;
	}

	public void setDeviceConnectedToWifi(boolean isDeviceConnectedToWifi) {
		this.isDeviceConnectedToWifi = isDeviceConnectedToWifi;
	}
	
	
	

}
