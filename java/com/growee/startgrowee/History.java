package com.growee.startgrowee;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;

import com.google.gson.Gson;
import com.growee.database.mysql.daomysql.DevicesDAO;
import com.growee.database.redis.HistoryDeviceDataRedis;

@Path("/history")
public class History extends AppBaseClass{


	@GET
	@Path("getDeviceHistory")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getDeviceHistory(@Context HttpServletRequest req) throws JSONException {
		System.out.println("in Server Side: getDeviceHistory");
		session = req.getSession(false);
		String deviceId = (String) session.getAttribute("deviceId");
		String json = new Gson().toJson(HistoryDeviceDataRedis.getHistory(deviceId,DevicesDAO.getCreatedTime(deviceId)));
		return json;
	}
	
}

