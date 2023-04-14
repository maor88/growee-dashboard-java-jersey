package com.growee.objects.users;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class UserSettings {
	
	String userId;
	String userName;
	String email;
	String plantType;
	String plantStatus;
	String ecType;
	String waterLiter;

	
	public UserSettings () {}
	
	public UserSettings(String userId, String userName, String email, String plantType, String plantStatus, String ecType, String waterLiter) {
		super();
		this.userId =userId;
		this.userName = userName;
		this.email = email;
		this.plantType = plantType;
		this.plantStatus = plantStatus;
		this.ecType = ecType;
		this.waterLiter = waterLiter;
	}
	
	public void setSettingsByJson(JSONObject obj) throws JSONException {
		this.userName = obj.getString("name");
		this.email = obj.getString("email");
		this.plantType = obj.getString("plantType");
		this.plantStatus = obj.getString("palntStaus");
		this.ecType = obj.getString("ecType");
		this.waterLiter = obj.getString("waterLiter");
	}
	
	public String getUserName() {
		return userName;
	}

	public String getEmail() {
		return email;
	}

	public String getPlantType() {
		return plantType;
	}

	public String getPlantStatus() {
		return plantStatus;
	}

	public String getEcType() {
		return ecType;
	}

	public String getWaterLiter() {
		return waterLiter;
	}


}
