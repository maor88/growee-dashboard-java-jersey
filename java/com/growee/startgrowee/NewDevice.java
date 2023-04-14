package com.growee.startgrowee;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.google.gson.Gson;
import com.growee.database.mysql.daomysql.RegistrationDeviceDAO;
import com.growee.database.mysql.daomysql.CurrentDeviceStateDAO;
import com.growee.database.mysql.daomysql.DeviceGrowSettingsDAO;
import com.growee.database.mysql.daomysql.TargetDeviceStateDAO;
import com.growee.database.mysql.daomysql.DevicesDAO;
import com.growee.objects.registarion.RegistarionCode;

@Path("/newDevice")
public class NewDevice extends AppBaseClass {

	@POST
	@Path("isValidRC")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String isValidRC(@Context HttpServletRequest req, String json) throws JSONException {
		System.out.println("in Server Side: isValidRC");
		session = req.getSession(false);
		if (session != null) {
			JSONObject obj = new JSONObject(json);
			String registrationCode = obj.getString("rc");
			String userId = (String) session.getAttribute("userId");
			RegistarionCode registartion = new RegistarionCode();
			if (RegistrationDeviceDAO.isRegistrationCodeExist(registrationCode)) {
				registartion.setValidRegistrationCode(true);
				String userIdRC = RegistrationDeviceDAO.getRegistrationCodeUserId(registrationCode);
				if (userIdRC.equals("-1") || userIdRC.equals(userId)) {
					if (userIdRC.equals("-1")) {
						RegistrationDeviceDAO.setUserIdToRegistrationCode(registrationCode, userId);
						registartion.setRegistrationSuccees(true);
					} else {
						registartion.setUserAlreadyRegistered(true);
					}
				} else {
					registartion.setRegistarionCodeInUse(true);
				}
			}
			return new Gson().toJson(registartion);
		} else {
			return MassagesFromServer.False.getMassage();
		}
	}
	
	
	@POST
	@Path("createNewDevice")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String createNewDevice(@Context HttpServletRequest req, String json) throws JSONException {
		System.out.println("in Server Side: createNewDevice");
		session = req.getSession(false);
		if (session != null) {
			JSONObject obj = new JSONObject(json);
			String registrationCode = obj.getString("rc");
			String userId = (String) session.getAttribute("userId");
			String deviceId = RegistrationDeviceDAO.getDeviceId(registrationCode);
			setDeviceSession(req,deviceId);
			DevicesDAO.addNewDevice(deviceId,userId);
			DeviceGrowSettingsDAO.addNewDevice(deviceId,userId);
			CurrentDeviceStateDAO.addNewDevice(deviceId,userId);
			TargetDeviceStateDAO.addNewDevice(deviceId,userId);
			return new Gson().toJson(deviceId);
		} else {
			return MassagesFromServer.False.getMassage();
		}
	}
	

}
