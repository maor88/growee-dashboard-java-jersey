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
import com.growee.emails.EmailSender;
import com.growee.objects.contactUs.ContactUsEmail;

/**
 * @author Maor
 *
 */
@Path("/contactUs")
public class ContactUs extends AppBaseClass{
	
	
	@POST
	@Path("sendEmail")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void sendEmail(@Context HttpServletRequest req, String json) throws JSONException {
		System.out.println("in Server Side: sendEmail");
		session = req.getSession(false);
		String deviceId = (String) session.getAttribute("deviceId");
		String userId = (String) session.getAttribute("userId");
		Gson gson = new Gson();
		ContactUsEmail contactUsEmail = gson.fromJson(json, ContactUsEmail.class);
		contactUsEmail.setDeviceId(deviceId);
		contactUsEmail.setUserId(userId);
		EmailSender.sendContactUsMail(contactUsEmail);
	}
	
	

}
