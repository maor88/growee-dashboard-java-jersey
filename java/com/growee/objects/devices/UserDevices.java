/**
 * 
 */
package com.growee.objects.devices;

import java.util.List;

/**
 * @author Maor
 *
 */
public class UserDevices {

	List<Device> devices;
	String currentUsedDeviceNumber;
	
	public UserDevices(List<Device> userDevices, String currentUsedDeviceNumber) {
		this.devices = userDevices;
		this.currentUsedDeviceNumber = currentUsedDeviceNumber;
	}
}
