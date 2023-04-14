package com.growee.startgrowee;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import com.google.gson.Gson;
import com.growee.database.mysql.daomysql.NotificationDAO;
import com.growee.objects.notification.DeviceNotification;


/**
 * @author Maor
 *
 */

@Path("/notification")

public class Nottification extends AppBaseClass{

	@POST
	@Path("setDeviceNotification")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateDeviceEC(@Context HttpServletRequest req, String json)  {
		System.out.println("in Server Side: setDeviceNotification");
		session = req.getSession(false);
		String deviceId = (String) session.getAttribute("deviceId");
		String userId = (String) session.getAttribute("userId");
		Gson gson = new Gson();
		DeviceNotification notification = gson.fromJson(json, DeviceNotification.class);
		notification.setDevcieId(deviceId);
		notification.setUserId(userId);
		NotificationDAO.setNotificationData(notification);
	}
}
