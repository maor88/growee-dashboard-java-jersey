/**
 * 
 */
package com.growee.startgrowee;

import com.growee.database.mysql.daomysql.SessionConnectionDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author Maor
 *
 */
public class AppBaseClass  implements HttpSessionListener {

	public HttpSession session;


	@Override
	public void sessionCreated(HttpSessionEvent arg0) {

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		String userId = (String) arg0.getSession().getAttribute("userId");
		SessionConnectionDAO.decreaseDeviceIdOpenSession(userId);
	}

	protected void setDeviceSession(HttpServletRequest req, String deviceId) {
		session = req.getSession(true);
		session.setAttribute("deviceId", deviceId);
	}

	
	protected void setUserWelcomePopup(HttpServletRequest req, boolean res) {
		session = req.getSession(true);
		session.setAttribute("isSawWelcomePage", res);
	}


	protected void setUserSession(HttpServletRequest req, String userId) {
		session = req.getSession(true);
		session.setAttribute("userId", userId);
		SessionConnectionDAO.increaseDeviceIdOpenSession(userId);
	}

	protected void updateValidSession(String userId) {
		if(SessionConnectionDAO.isNoSessionForUser(userId)) {
			SessionConnectionDAO.increaseDeviceIdOpenSession(userId);
		}
	}





}
