/**
 * 
 */
package com.growee.startgrowee;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.growee.database.mysql.daomysql.AdminUserDAO;
import com.growee.database.mysql.daomysql.CurrentDeviceStateDAO;
import com.growee.database.mysql.daomysql.TargetDeviceStateDAO;
import com.growee.database.mysql.daomysql.UserSettingsDAO;
import com.growee.database.mysql.daomysql.UsersDAO;
import com.growee.objects.devices.DeviceCurrentState;
import com.growee.objects.devices.DeviceTargetState;
import com.growee.objects.users.UserDetails;
import com.growee.objects.users.UserSettings;

public class Admin {
	
	HttpSession session;
	
	@GET
	@Path("adminPage")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getAllAdmins(@Context HttpServletRequest req) {
		System.out.println("in Server Side: adminPage");
		session = req.getSession(false);
		if (session != null) {
			String userId = (String) session.getAttribute("userId");
			if (AdminUserDAO.isUserAdmin(userId)) {
				return getAllGroweeTablesToJson();
			}
		}
		return MassagesFromServer.False.getMassage();
	}
	
	
	private static String getAllGroweeTablesToJson() {
		List<UserDetails> usersAdmin = AdminUserDAO.getAllAdminUserTable();
		List<UserDetails> groweeUsers = UsersDAO.getAllUserTable();
		List<DeviceCurrentState> devicesCurrentData = CurrentDeviceStateDAO.getAllDevicesTable();
		List<UserSettings> userSettings = UserSettingsDAO.getAllUsrtSettingsTable();
		List<DeviceTargetState> deviceTargetState = TargetDeviceStateDAO.getAllDevicesTable();
		List<String> jsons = new ArrayList<String>();
		jsons.add(new Gson().toJson(usersAdmin));
		jsons.add(new Gson().toJson(groweeUsers));
		jsons.add(new Gson().toJson(userSettings));
		jsons.add(new Gson().toJson(devicesCurrentData));
		jsons.add(new Gson().toJson(deviceTargetState));
		return new Gson().toJson(jsons);
	}


}
