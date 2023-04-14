package com.growee.startgrowee;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;

import com.growee.database.mysql.daomysql.ECCalibrationDAO;
import com.growee.database.mysql.daomysql.PHCalibrationDAO;
import com.growee.objects.calibration.ECCalibration;
import com.growee.objects.calibration.PHCalibration;

/**
 * @author Maor
 *
 */
@Path("/calibration")


public class Calibration extends AppBaseClass {


	static final float MIN_PH_4 = 0;
	static final float MAX_PH_4 = 10000;
	static final float MIN_PH_7 = 0;
	static final float MAX_PH_7 = 10000;
	static final float MIN_EC = 0;
	static final float MAX_EC = 10000;

	
	@POST
	@Path("startPH_7")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void startPH_7(@Context HttpServletRequest req){
		System.out.println("in Server Side: startPH_7");
		session = req.getSession(false);
		String deviceId = (String) session.getAttribute("deviceId");
		PHCalibrationDAO.initlize7Calibration(deviceId);
		PHCalibrationDAO.startPH7Calibration(deviceId);
		ECCalibrationDAO.addDeviceToTableIfNotExist(deviceId);
	}
	
	@POST
	@Path("checkSevenCalibration")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String checkSevenCalibration(@Context HttpServletRequest req)  {
		System.out.println("in Server Side: checkSevenCalibration");
		session = req.getSession(false);
		String deviceId = (String) session.getAttribute("deviceId");
		float phRawFourAverage = PHCalibrationDAO.getSevenCalibrationAvergate(deviceId);
		if (MIN_PH_7 < phRawFourAverage &&  phRawFourAverage < MAX_PH_7) {
			return MassagesFromServer.True.getMassage();
		} else {
			return MassagesFromServer.False.getMassage();
		}
	}


	@POST
	@Path("startPH_4")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void startPH_4(@Context HttpServletRequest req)  {
		System.out.println("in Server Side: startPH_4");
		session = req.getSession(false);
		String deviceId = (String) session.getAttribute("deviceId");
		PHCalibrationDAO.initlize4Calibration(deviceId);
		PHCalibrationDAO.startPH4Calibration(deviceId);
	}

	@POST
	@Path("stopPHCalibration")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void stopPHCalibration(@Context HttpServletRequest req)  {
		System.out.println("in Server Side: stopPHCalibration");
		session = req.getSession(false);
		String deviceId = (String) session.getAttribute("deviceId");
		PHCalibrationDAO.initlizeCalibration(deviceId);
	}

	@POST
	@Path("checkFourCalibration")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String checkFourCalibration(@Context HttpServletRequest req)  {
		System.out.println("in Server Side: checkFourCalibration");
		session = req.getSession(false);
		String deviceId = (String) session.getAttribute("deviceId");
		float phRawFourAverage = PHCalibrationDAO.getFourCalibrationAvergate(deviceId);
		if (MIN_PH_4 < phRawFourAverage &&  phRawFourAverage < MAX_PH_4) {
			storePHCalibrationParameters(deviceId);
			return MassagesFromServer.True.getMassage();
		} else {
			return MassagesFromServer.False.getMassage();
		}

	}

	private void storePHCalibrationParameters(String deviceId) {
		PHCalibration phCalibration = PHCalibrationDAO.getPHAverageRaw(deviceId);
		PHCalibrationDAO.storePHCalibrationParameters(deviceId , phCalibration);
	}


//	EC

	
	@POST
	@Path("startEC_1400")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void startEC_1400(@Context HttpServletRequest req) throws JSONException {
		System.out.println("in Server Side: startEC_1400");
		session = req.getSession(false);
		String deviceId = (String) session.getAttribute("deviceId");
		ECCalibrationDAO.initlize1400ECCalibration(deviceId);
		ECCalibrationDAO.startEC1400Calibration(deviceId);
		PHCalibrationDAO.addDeviceToTableIfNotExist(deviceId);

	}

	@POST
	@Path("startEC_2700")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void startEC_2700(@Context HttpServletRequest req) throws JSONException {
		System.out.println("in Server Side: startEC_2700");
		session = req.getSession(false);
		String deviceId = (String) session.getAttribute("deviceId");
		ECCalibrationDAO.initlize2700ECCalibration(deviceId);
		ECCalibrationDAO.startEC2700Calibration(deviceId);
	}
	
	@POST
	@Path("stopECCalibration")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void stopECCalibration(@Context HttpServletRequest req) throws JSONException {
		System.out.println("in Server Side: stopECCalibration");
		session = req.getSession(false);
		String deviceId = (String) session.getAttribute("deviceId");
		ECCalibrationDAO.initlizeECCalibration(deviceId);
	}

	
	@POST
	@Path("checkEcOnePointFourCalibration")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String checkEcOnePointFourCalibration(@Context HttpServletRequest req) throws JSONException {
		System.out.println("in Server Side: checkEcOnePointFourCalibration");
		session = req.getSession(false);
		String deviceId = (String) session.getAttribute("deviceId");
		float ecRawFourAverage = ECCalibrationDAO.get1400CalibrationAvergate(deviceId);
		if (MIN_EC < ecRawFourAverage &&  ecRawFourAverage < MAX_EC) {
			return MassagesFromServer.True.getMassage();
		} else {
			return MassagesFromServer.False.getMassage();
		}
	}

	@POST
	@Path("checkEcTwoPointSevenCalibration")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String checkEcTwoPointSevenCalibration(@Context HttpServletRequest req) throws JSONException {
		System.out.println("in Server Side: checkEcTwoPointSevenCalibration");
		session = req.getSession(false);
		String deviceId = (String) session.getAttribute("deviceId");
		float ecRawFourAverage = ECCalibrationDAO.get2700CalibrationAvergate(deviceId);
		if (MIN_EC < ecRawFourAverage &&  ecRawFourAverage < MAX_EC) {
			storeECCalibrationParameters(deviceId);
			return MassagesFromServer.True.getMassage();
		} else {
			return MassagesFromServer.False.getMassage();
		}
	}

	
	private void storeECCalibrationParameters(String deviceId) {
		ECCalibration ecCalibration = ECCalibrationDAO.getECAverageRaw(deviceId);
		ECCalibrationDAO.storeECCalibrationParameters(deviceId , ecCalibration);	
	}
	

}
