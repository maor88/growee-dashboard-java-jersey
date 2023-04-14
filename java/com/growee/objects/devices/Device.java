/**
 * 
 */
package com.growee.objects.devices;

/**
 * @author Maor
 *
 */
public class Device {

	String device_id;
	String user_id;
	String device_name;
	String device_number;
	boolean isSetupDone;
	boolean isDeviceConnectedToWifi;

	
	public Device(String deviceId, String deviceName, String deviceNumber , boolean isSetupDone, boolean isDeviceConnectedToWifi) {
		this.device_id = deviceId;
		this.device_name = deviceName;
		this.device_number = deviceNumber;
		this.isSetupDone = isSetupDone;
		this.isDeviceConnectedToWifi = isDeviceConnectedToWifi;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public String getDevice_number() {
		return device_number;
	}

	public void setDevice_number(String device_number) {
		this.device_number = device_number;
	}

}
