package com.growee.objects.devices;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class DeviceTargetState {

	String deviceId;
	String temperature;
	String PH;
	String EC;
	String userCode;
	String userId;
	String lastUpdateTimeStamp;
	String literOfWater;
	
	// add in the future
	String targetEC;
	String ECA;
	String ECB;
	String ECC;
	
	public String getDeviceId() {
		return deviceId;
	}

	public String getTemperature() {
		return temperature;
	}

	public String getPH() {
		return PH;
	}

	public String getEC() {
		return EC;
	}

	public DeviceTargetState(){}
	
	
	public DeviceTargetState(String userId,String deviceId,String PH,String EC, String lastUpdate) {
		this.userId = userId;
		this.deviceId = deviceId;
		this.PH = PH;
		this.EC = EC;
		this.lastUpdateTimeStamp = lastUpdate;
	}
	
	public void setDeviceData(String deviceId, String temperature,String PH,String EC ){
		this.deviceId = deviceId;
		this.temperature = temperature;
		this.PH = PH;
		this.EC = EC;
	}

	public void setDeviceDataByJson(JSONObject obj) throws JSONException {
		this.PH =  obj.getString("PH");
		this.EC =  obj.getString("EC");
	}
	
	public void setLiterOfWater(String liter) {
		this.literOfWater = liter;
	}
	
	public String getWaterLiter() {
		return this.literOfWater;
	}
	

}
