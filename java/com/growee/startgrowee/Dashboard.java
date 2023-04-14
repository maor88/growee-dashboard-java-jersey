
package com.growee.startgrowee;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.growee.objects.modify.TargetECAndPH;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.google.gson.Gson;
import com.growee.database.mysql.daomysql.CurrentDeviceStateDAO;
import com.growee.database.mysql.daomysql.DeviceGrowSettingsDAO;
import com.growee.database.mysql.daomysql.TargetDeviceStateDAO;
import com.growee.database.mysql.daomysql.DevicesDAO;
import com.growee.database.mysql.daomysql.LoginCookiesDAO;
import com.growee.database.mysql.daomysql.NotificationDAO;
import com.growee.database.mysql.daomysql.UserSettingsDAO;
import com.growee.database.mysql.daomysql.UsersDAO;
import com.growee.generalActions.GeneralActions;
import com.growee.objects.devices.Device;
import com.growee.objects.devices.DeviceCurrentState;
import com.growee.objects.devices.DeviceGrowSettings;
import com.growee.objects.devices.DeviceTargetState;
import com.growee.objects.devices.UserDevices;
import com.growee.objects.users.ConnectionStatus;
import com.growee.objects.users.UserDetails;
import com.growee.objects.users.UserSettings;

/**
 * @author Maor
 *
 */

@Path("/dashboard")
public class Dashboard extends AppBaseClass {

	public static final long MAX_GAP_TIME_IN_MIN = 15;

	private static String createJsonForHistoryRedis(DeviceTargetState target, DeviceCurrentState current) {
		List<String> jsons = new ArrayList<String>();
		jsons.add(new Gson().toJson(target));
		jsons.add(new Gson().toJson(current));
		return new Gson().toJson(jsons);
	}

	@GET
	@Path("userDetails")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getUserData(@Context HttpServletRequest req) {
		System.out.println("in Server Side: getUserData");
		session = req.getSession(false);
		if (session != null) {
			String userId = (String) session.getAttribute("userId");
			UserDetails userDetails = UsersDAO.getUserDetailsById(userId);
			return new Gson().toJson(userDetails);
		} else {
			return MassagesFromServer.False.getMassage();
		}
	}


	@GET
	@Path("userDeviceDetails")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getUserDeviceCurrentData(@Context HttpServletRequest req) {
		System.out.println("in Server Side: userDeviceDetails");
		session = req.getSession(false);
		if (session != null) {
			String userId = (String) session.getAttribute("userId");
			String deviceId = (String) session.getAttribute("deviceId");
			if (!deviceId.equals("0")) {
				DeviceCurrentState devicesCurrentData = CurrentDeviceStateDAO.getDeviceCurrentData(userId, deviceId);
				TargetECAndPH  targetEcAndPh = TargetDeviceStateDAO.getTargetPHAndECValueAndLastTimeChange(deviceId);
 				devicesCurrentData.isTargetsProgramOn(targetEcAndPh);
				return new Gson().toJson(devicesCurrentData);
			} else {
				return new Gson().toJson(null);
			}
		} else {
			return MassagesFromServer.LogOut.getMassage();
		}
	}

	@POST
	@Path("userDeviceDetailsByName")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String userDeviceDetailsByName(@Context HttpServletRequest req, String userdeviceName) throws JSONException {
		System.out.println("in Server Side: userDeviceDetailsByName");
		session = req.getSession(false);
		if (session != null) {
			String userId = (String) session.getAttribute("userId");
			String deviceId = DevicesDAO.getUserDeviceIdByDeviceName(userId, userdeviceName);
			setDeviceSession(req, deviceId);
			DeviceCurrentState devicesCurrentData = CurrentDeviceStateDAO.getDeviceCurrentData(userId, deviceId);
			return new Gson().toJson(devicesCurrentData);
		} else {
			return MassagesFromServer.LogOut.getMassage();
		}
	}

	@GET
	@Path("userAndDeviceConnection")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String conectionStatus(@Context HttpServletRequest req) {
		System.out.println("in Server Side: userAndDeviceConnection");
		session = req.getSession(false);
		if (session != null) {
			UserDetails userId = new UserDetails((String) session.getAttribute("userId"));
			updateValidSession(userId.getUserId());
			if (userId != null) {
				setUserWelcomePopup(req, UsersDAO.isUserSawWelcomePage(userId));
				return new Gson().toJson(MassagesFromServer.LogIn.getMassage());
			}
		}
		return MassagesFromServer.LogOut.getMassage();
	}

	@GET
	@Path("deviceConnection")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String deviceConnection(@Context HttpServletRequest req) {
		System.out.println("in Server Side: deviceConnection");
		session = req.getSession(false);
		if (session != null) {
			UserDetails userId = new UserDetails((String) session.getAttribute("userId"));
			String deviceId = (String) session.getAttribute("deviceId");
			if (userId != null) {
				Timestamp lastUpdate = CurrentDeviceStateDAO.getLastUpdateTimeStamp(deviceId);
				boolean isDeviceConnect = false;
				if (lastUpdate != null) {
					isDeviceConnect = isDeviceLastUpdateInTime(lastUpdate, MAX_GAP_TIME_IN_MIN);
				}
				boolean isDeviceInitilized = DeviceGrowSettingsDAO.isDeviceInitilized(deviceId);
				boolean isSawWelcomePage = (Boolean) session.getAttribute("isSawWelcomePage");
				ConnectionStatus status = new ConnectionStatus(isSawWelcomePage, isDeviceInitilized, isDeviceConnect);
				status.setNotificationOn(NotificationDAO.isDeviceNotificationOn(deviceId));
				String emailNotification = NotificationDAO.getNotificationEmail(deviceId);
				if (emailNotification == null) {
					emailNotification = UsersDAO.getUserEmail(userId.getUserId());
				}
				if (!isDeviceConnect) {
					NotificationDAO.initilizeAlreadyNotify(deviceId, userId.getUserId(), emailNotification);
				}
				status.setEmailNotification(emailNotification);
				return new Gson().toJson(status);
			}
		}
		return MassagesFromServer.LogOut.getMassage();
	}

	@POST
	@Path("initDeviceSettings")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void initDeviceSettings(@Context HttpServletRequest req, String json) throws JSONException {
		System.out.println("in Server Side: initDeviceSettings");
		session = req.getSession(false);
		String deviceId = (String) session.getAttribute("deviceId");
		String userId = (String) session.getAttribute("userId");
		Gson gson = new Gson();
		DeviceGrowSettings deviceGrowSettings = gson.fromJson(json, DeviceGrowSettings.class);
		DeviceGrowSettingsDAO.setNewDeviceSettings(deviceGrowSettings, deviceId);
		DevicesDAO.setDeviceName(deviceGrowSettings.getDevice_name(), deviceId);
		UsersDAO.setUserSawWelcomePopup(userId);
		// HistoryDeviceDataRedis.insertHistory(userId,
		// createJsonForHistoryRedis(deviceTargetData, deviceCurrentState));
	}

	@GET
	@Path("userDeviceGrowSetting")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getUserDeviceGrowSetting(@Context HttpServletRequest req) throws JSONException {
		System.out.println("in Server Side: userDeviceGrowSetting");
		session = req.getSession(false);
		if (session != null) {
			String deviceId = (String) session.getAttribute("deviceId");
			DeviceGrowSettings devicesGrowSettings = DeviceGrowSettingsDAO.getDeviceSettingData(deviceId);
			return new Gson().toJson(devicesGrowSettings);
		} else {
			return MassagesFromServer.False.getMassage();
		}
	}

	public static boolean isDeviceLastUpdateInTime(Timestamp lastUpdate, long maxGapTimeInMinutes) {
		Calendar time = Calendar.getInstance();
		time.add(Calendar.MILLISECOND, -time.getTimeZone().getOffset(time.getTimeInMillis()));
		long milliseconds2 = time.getTimeInMillis();
		long milliseconds1 = lastUpdate.getTime();
		long diff = milliseconds2 - milliseconds1;
		long diffMinutes = diff / (60 * 1000);
		if (diffMinutes > maxGapTimeInMinutes) {
			return false;
		}
		return true;
	}

	@POST
	@Path("updateUserSettings")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void setUserSettings(@Context HttpServletRequest req, String json) throws JSONException {
		System.out.println("in Server Side: updateUserSettings");
		session = req.getSession(false);
		String userId = (String) session.getAttribute("userId");
		JSONObject obj = new JSONObject(json);
		UserSettings userSettings = new UserSettings();
		userSettings.setSettingsByJson(obj);
		UserSettingsDAO.setNewUserSettings(userSettings, userId);
		TargetDeviceStateDAO.setWaterQuantity(userSettings.getWaterLiter(), userId);
	}

	@GET
	@Path("getUserDevices")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getUserDevices(@Context HttpServletRequest req) throws JSONException {
		System.out.println("in Server Side: getUserDevices");
		session = req.getSession(false);
		if (session != null) {
			String userId = (String) session.getAttribute("userId");
			List<Device> devices = DevicesDAO.getUserDevices(userId);
			UserDevices userDevices = new UserDevices(devices,
					convertDeviceIdToUserDeviceNumber(devices, (String) session.getAttribute("deviceId")));
			return new Gson().toJson(userDevices);
		} else {
			return MassagesFromServer.False.getMassage();
		}
	}

	private String convertDeviceIdToUserDeviceNumber(List<Device> devices, String deviceId) {
		String defaultDeviceNumber = "1";
		for (Device device : devices) {
			if (device.getDevice_id().equals(deviceId)) {
				return device.getDevice_number();
			}
		}
		return defaultDeviceNumber;
	}

	@POST
	@Path("logout")
	@Produces(MediaType.TEXT_HTML)
	public void logOut(@Context HttpServletRequest req, String sessionId) {
		System.out.println("in Server Side: logout");
		session = req.getSession(false);
		if (session != null) {
			session.invalidate();
			LoginCookiesDAO.removeSessionId(sessionId);
		}

	}

	@POST
	@Path("userSawWelcomePopup")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void userSawWelcomePopup(@Context HttpServletRequest req) {
		System.out.println("in Server Side: userSawWelcomePopup");
		setUserWelcomePopup(req, true);
		String userId = (String) session.getAttribute("userId");
		UsersDAO.setUserSawWelcomePopup(userId);
	}

	@POST
	@Path("updateUserName")
	@Produces(MediaType.TEXT_HTML)
	public void updateUserName(@Context HttpServletRequest req, String newUserName) {
		System.out.println("in Server Side: updateUserName");
		session = req.getSession(false);
		String userId = (String) session.getAttribute("userId");
		UsersDAO.setNewUserName(userId,newUserName);
	}

	@POST
	@Path("deleteDeviceFromUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteDeviceFromUser(@Context HttpServletRequest req) {
		System.out.println("in Server Side: deleteDeviceFromUser");
		setUserWelcomePopup(req, true);
		String deviceId = (String) session.getAttribute("deviceId");
		String userId = (String) session.getAttribute("userId");
		GeneralActions.deleteDeviceFromAllDB(deviceId, userId, "2017/01/01");
	}


	@POST
	@Path("forgetWifi")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void forgetWifi(@Context HttpServletRequest req) {
		System.out.println("in Server Side: forget wifi");
		session = req.getSession(false);
		String deviceId = (String) session.getAttribute("deviceId");
		TargetDeviceStateDAO.forgetWifi(deviceId);
	}



}
