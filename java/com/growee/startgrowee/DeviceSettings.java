/**
 * 
 */
package com.growee.startgrowee;


import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;

import com.google.gson.Gson;
import com.growee.database.mysql.daomysql.DeviceGrowSettingsDAO;
import com.growee.database.mysql.daomysql.DevicesDAO;
import com.growee.objects.devices.DeviceGrowSettings;
import org.codehaus.jettison.json.JSONObject;


/**
 * @author Maor
 *
 */
@Path("/deviceSettings")
public class DeviceSettings extends AppBaseClass{
	
	@GET
	@Path("getDeviceSettings")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getDeviceSettings(@Context HttpServletRequest req) {
		System.out.println("in Server Side: getDeviceSettings");
		session = req.getSession(false);
		if (session != null) {
			String deviceId = (String) session.getAttribute("deviceId");
			DeviceGrowSettings deviceGroweSettings = DeviceGrowSettingsDAO.getDeviceSettingData(deviceId);
			return new Gson().toJson(deviceGroweSettings);
		} else {
			return MassagesFromServer.False.getMassage();
		}
	}
	
	
	
	@POST
	@Path("updateDeviceSettings")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateDeviceSettings(@Context HttpServletRequest req, String json) {
		System.out.println("in Server Side: updateDeviceSettings");
		session = req.getSession(false);
		String deviceId = (String) session.getAttribute("deviceId");
		Gson gson = new Gson();
		json = addStartGrowDateIfNeeded(json);
		DeviceGrowSettings deviceGrowSettings = gson.fromJson(json, DeviceGrowSettings.class);
		DeviceGrowSettingsDAO.updateDeviceSettings(deviceGrowSettings, deviceId);
		DevicesDAO.setDeviceName(deviceGrowSettings.getDevice_name(),deviceId);
		// HistoryDeviceDataRedis.insertHistory(userId,
		// createJsonForHistoryRedis(deviceTargetData, deviceCurrentState));
	}

	/**
	 * To support new version (front don't send start_grow_date more
	 * but we don't want to remove the parameter start_grow_date from DeviceGrowSettings in the java back
	 * so we add start_grow_date to the requestbody before gson.fromJson(json, DeviceGrowSettings.class);
	 * (fromJson crash if one of the expected class fields is not present in the json string))
	 * @param json json requestBody
	 * @return
	 */
	private String addStartGrowDateIfNeeded(String json) {
		try {
			JSONObject jsonObject = new JSONObject(json);
			if (!jsonObject.has("start_grow_date")){
				jsonObject.put("start_grow_date", "2020-06-03 08:40:28");
				json = jsonObject.toString();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}


}
