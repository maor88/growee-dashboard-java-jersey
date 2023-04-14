package com.growee.startgrowee;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.growee.database.redis.StatisticUsersDataRedis;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.google.gson.Gson;
import com.growee.codeGenerator.StringCodeCreator;
import com.growee.database.mysql.daomysql.AdminUserDAO;
import com.growee.database.mysql.daomysql.DevicesDAO;
import com.growee.database.mysql.daomysql.ForgotPasswordDAO;
import com.growee.database.mysql.daomysql.LoginCookiesDAO;
import com.growee.database.mysql.daomysql.UsersDAO;
import com.growee.emails.EmailSender;
import com.growee.objects.users.LoginData;
import com.growee.objects.users.UserDetails;
import com.growee.objects.users.UserSignUp;

/**
 * @author Maor
 *
 */

@Path("/welcome")
public class Welcome extends AppBaseClass {

	final static private String serverBaseSuffix = "webresources";


	@POST
	@Path("login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String logIn(@Context HttpServletRequest req, @Context HttpServletResponse res, String json)  throws JSONException {
		JSONObject obj = new JSONObject(json);
		UserDetails user = new UserDetails(obj.getString("userName"), obj.getString("userPass"));
		System.out.println("in Server Side: login");
		LoginData loginData = new LoginData();
		if (UsersDAO.isRegularUser(user)) {
			String userId = UsersDAO.getUserId(user);
			loginData.setUser(true);
			loginData.setFirstTime(UsersDAO.isUserSetFirstTimePassword(user));
			String deviceId = DevicesDAO.getFirstUserDeviceId(userId);
			setDeviceSession(req, deviceId);
			setUserSession(req, userId);
			loginData.setSession_id(StringCodeCreator.generateCookiesSessionIdCode());
			LoginCookiesDAO.setNewCookieSessionId(loginData.getSession_id(),userId);
			UsersDAO.updateLastLogin(userId);
			StatisticUsersDataRedis.updateUserEnterancesPerDay(userId);
		} else if (AdminUserDAO.isUserAdmin(user)) {
			setUserSession(req, AdminUserDAO.getAdminId(user));
			loginData.setAdmin(true);
		}
		return new Gson().toJson(loginData);
	}

	@POST
	@Path("allreadyLogedIn")
	@Produces(MediaType.TEXT_HTML)
	public String allreadyLogedIn(@Context HttpServletRequest req ,String sessionId) {
		System.out.println("in Server Side : alreadyLogedIn");
		session = req.getSession(false);
		if (session != null) {
			return MassagesFromServer.True.getMassage();
		}
		else if(LoginCookiesDAO.isSessionIdExist(sessionId)){
			String userId= LoginCookiesDAO.getUserIdSession(sessionId);
			String deviceId = DevicesDAO.getFirstUserDeviceId(userId);
			setUserSession(req, userId);
			setDeviceSession(req, deviceId);
			UsersDAO.updateLastLogin(userId);
			StatisticUsersDataRedis.updateUserEnterancesPerDay(userId);
			return MassagesFromServer.True.getMassage();
		}
		return MassagesFromServer.False.getMassage();
	}

	@POST
	@Path("isUserNameExist")
	@Produces(MediaType.TEXT_HTML)
	public String isUserNameExist(@Context HttpServletRequest req, @FormParam("newUserName") String userName) {
		System.out.println("in Server Side : isUserNameExist");
		if (UsersDAO.isUserNameExist(userName)) {
			return MassagesFromServer.True.getMassage();
		} else {
			return MassagesFromServer.False.getMassage();
		}
	}

	@POST
	@Path("forgotPass")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String forgotPass(@Context HttpServletRequest req, @Context HttpServletResponse res, String json)
			throws JSONException {
		System.out.println("in Server Side: forgot pass");
		JSONObject obj = new JSONObject(json);
		String emailToSend = obj.getString("emailToSend");
		String userName = UsersDAO.getUserNameByEmail(emailToSend);
		if (UsersDAO.isUserEmailExist(emailToSend)) {
			String uniqCode = StringCodeCreator.generateForgotPassForURL();
			ForgotPasswordDAO.insertGeneratedUrlCode(emailToSend, uniqCode);
//			String domain = req.getRequestURL().toString();
//			domain = domain.substring(0, domain.indexOf(serverBaseSuffix));
			String domain = "https://www.mygrowee.com/";
			if ( (!userName.equals("")) && (userName != null)) {
				EmailSender.sendForgotPassMail(emailToSend, "Grower", uniqCode, domain);
			} else {
				EmailSender.sendForgotPassMail(emailToSend, userName, uniqCode, domain);
			}

			return MassagesFromServer.True.getMassage();
		}
		return MassagesFromServer.False.getMassage();
	}

	@POST
	@Path("setNewPassword")
	@Produces(MediaType.TEXT_HTML)
	public String setNewPassword(@Context HttpServletRequest req, @Context HttpServletResponse res, String json)
			throws JSONException {
		System.out.println("in Server Side: set new password");
		JSONObject obj = new JSONObject(json);
		String newPass = obj.getString("newPass");
		String urlCode = obj.getString("urlCode");
		if (ForgotPasswordDAO.isCodeUrlExist(urlCode)) {
			String userEmail = ForgotPasswordDAO.getUserEmail(urlCode);
			if (UsersDAO.setNewPassword(userEmail, newPass)) {
				ForgotPasswordDAO.removeURLCode(urlCode);
				return MassagesFromServer.True.getMassage();
			}
		}

		return MassagesFromServer.False.getMassage();
	}

	@GET
	@Path("getAllUserNames")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getAllUserNames(@Context HttpServletRequest req) {
		System.out.println("in Server Side: getAllUserNames");
		List<String> userNames = UsersDAO.getAllUserNames();
		return new Gson().toJson(userNames);
	}

	@GET
	@Path("getAllUsersEmails")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getAllUsersEmails(@Context HttpServletRequest req) {
		System.out.println("in Server Side: getAllUsersEmails");
		List<String> userEmailes = UsersDAO.getAllUserEmailes();
		return new Gson().toJson(userEmailes);
	}

	@POST
	@Path("setNewUserNameAndPassword")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String setNewUserNameAndPassword(@Context HttpServletRequest req, @Context HttpServletResponse res, String json) throws  JSONException {
		System.out.println("in Server Side: setNewUserNameAndPassword");
		JSONObject obj = new JSONObject(json);
		UserDetails user = new UserDetails(obj.getString("newUserName"), obj.getString("newPassword"));
		session = req.getSession(false);
		if (session != null) {
			String userId = (String) session.getAttribute("userId");
			UsersDAO.setNewPasswordAndUserName(user, userId);
			UsersDAO.setInitPasswordDone(userId);
			return MassagesFromServer.True.getMassage();
		} else {
			return MassagesFromServer.False.getMassage();
		}
	}
	

	@POST
	@Path("signNewUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void signNewUser(@Context HttpServletRequest req, @Context HttpServletResponse res, String json)
			throws JSONException {
		System.out.println("in Server Side: signNewUser");
		JSONObject obj = new JSONObject(json);
		UserSignUp user = new UserSignUp(obj.getString("newUserEmail"), obj.getString("newPassword"),obj.getString("signNewUser") );
		UsersDAO.addNewUser(user);
		EmailSender.sendUserWelcomMail(user.getNewUserEmail() , user.getUserName());
	}

}
