/**
 * 
 */
package com.growee.objects.modify;

/**
 * @author Maor
 *
 */
public class CurrentPHAndEC {

	String currentEC;
	String currentPH;
	boolean isPH_Module_connected;
	boolean isEC_Module_connected;
	int ec_pumps_connected;
	
	public CurrentPHAndEC(String currentEC, String currentPH, boolean isPH_Module_connected,
						  boolean isEC_Module_connected, int ec_pumps_connected) {
		this.currentEC = currentEC;
		this.currentPH = currentPH;
		this.isPH_Module_connected = isPH_Module_connected;
		this.isEC_Module_connected = isEC_Module_connected;
		this.ec_pumps_connected = ec_pumps_connected;
	}
	
	public String getCurrentEC() {
		return currentEC;
	}
	public void setCurrentEC(String currentEC) {
		this.currentEC = currentEC;
	}
	public String getCurrentPH() {
		return currentPH;
	}
	public void setCurrentPH(String currentPH) {
		this.currentPH = currentPH;
	}

	
}
