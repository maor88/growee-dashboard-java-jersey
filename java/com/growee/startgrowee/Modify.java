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

import com.google.gson.Gson;
import com.growee.database.mysql.daomysql.CurrentDeviceStateDAO;
import com.growee.database.mysql.daomysql.DeviceGrowSettingsDAO;
import com.growee.database.mysql.daomysql.ECCalibrationDAO;
import com.growee.database.mysql.daomysql.TargetDeviceStateDAO;
import com.growee.objects.calibration.CalibrationData;
import com.growee.objects.devices.DeviceCurrentState;
import com.growee.objects.modify.*;


/**
 * @author Maor
 */
@Path("/modify")
public class Modify extends AppBaseClass {


    private static TargetAndCurrentLevel getTargetAndCurrentLevel(String userId, String deviceId) {
        DeviceCurrentState devicesCurrentData = CurrentDeviceStateDAO.getDeviceCurrentData(userId, deviceId);
        CurrentPHAndEC currentPHAndEC = new CurrentPHAndEC(devicesCurrentData.getEC(), devicesCurrentData.getPH(),
                devicesCurrentData.is_ph_module_connected(), devicesCurrentData.is_ec_module_connected(),
                devicesCurrentData.getEc_pumps_connected());
        TargetECBottelsNames targetECBottelsNames = new TargetECBottelsNames(DeviceGrowSettingsDAO.getTargetBottlesNamesAndWaterQuantity(deviceId));
        TargetECMililiters targetECMililiters = new TargetECMililiters(TargetDeviceStateDAO.getLastModifyBottelsMililiters(deviceId));
        TargetECAndPH targetECAndPH = new TargetECAndPH(TargetDeviceStateDAO.getTargetPHAndECValueAndLastTimeChange(deviceId));
        TargetPHBottelsAndEC targetPHBottelsAndEC = new TargetPHBottelsAndEC(targetECBottelsNames, targetECMililiters, targetECAndPH);
        CalibrationData calibrationData = ECCalibrationDAO.getCalibrationLastUpdate(deviceId);
        return new TargetAndCurrentLevel(currentPHAndEC, targetPHBottelsAndEC, calibrationData);
    }

    @POST
    @Path("userDeviceTargetAndCurrent")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String userDeviceTargetAndCurrent(@Context HttpServletRequest req) {
        System.out.println("in Server Side: userDeviceTargetAndCurrent");
        session = req.getSession(false);
        if (session != null) {
            String userId = (String) session.getAttribute("userId");
            String deviceId = (String) session.getAttribute("deviceId");
            TargetAndCurrentLevel targetBottlesAndCurrentLevel = getTargetAndCurrentLevel(userId, deviceId);
            return new Gson().toJson(targetBottlesAndCurrentLevel);
        } else {
            return MassagesFromServer.False.getMassage();
        }
    }

    @POST
    @Path("updateDeviceEC")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateDeviceEC(@Context HttpServletRequest req, String json) {
        System.out.println("in Server Side: updateDeviceEC");
        session = req.getSession(false);
        String deviceId = (String) session.getAttribute("deviceId");
        Gson gson = new Gson();
        TargetPHBottelsAndEC targetPHBottelsAndEC = gson.fromJson(json, TargetPHBottelsAndEC.class);
        targetPHBottelsAndEC.computeTargetrEcRelations(DeviceGrowSettingsDAO.getDeviceWaterQuantity(deviceId));
        TargetDeviceStateDAO.setNewECBottelsMilliliterAndTarget(targetPHBottelsAndEC, deviceId);
        DeviceGrowSettingsDAO.setBottelsNames(targetPHBottelsAndEC, deviceId);
    }

    @POST
    @Path("pauseTargetEC")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void pauseTargetEC(@Context HttpServletRequest req) {
        System.out.println("in Server Side: pauseTargetEC");
        session = req.getSession(false);
        String deviceId = (String) session.getAttribute("deviceId");
        TargetDeviceStateDAO.stopTargetEC(deviceId);
    }

    @GET
    @Path("userDeviceBottelsDetails")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getUserDeviceBottelsDetails(@Context HttpServletRequest req) {
        System.out.println("in Server Side: userDeviceBottelsDetails");
        session = req.getSession(false);
        if (session != null) {
            String userId = (String) session.getAttribute("userId");
            String deviceId = (String) session.getAttribute("deviceId");
            TargetAndCurrentLevel targerBottlesAndCurrentLevel = getTargetAndCurrentLevel(userId, deviceId);
            return new Gson().toJson(targerBottlesAndCurrentLevel);
        } else {
            return MassagesFromServer.False.getMassage();
        }
    }

    @POST
    @Path("updateDevicePH")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateDevicePH(@Context HttpServletRequest req, String json) {
        System.out.println("in Server Side: updateDevicePH");
        session = req.getSession(false);
        String deviceId = (String) session.getAttribute("deviceId");
        Gson gson = new Gson();
        TargetPH targetPH = gson.fromJson(json, TargetPH.class);
        TargetDeviceStateDAO.setPHTargetFromUser(targetPH, deviceId);
    }

    @POST
    @Path("pauseTargetPH")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void pauseTargetPH(@Context HttpServletRequest req, String json) {
        System.out.println("in Server Side: pauseTargetPH");
        session = req.getSession(false);
        String deviceId = (String) session.getAttribute("deviceId");
        Gson gson = new Gson();
        TargetPH targetPH = gson.fromJson(json, TargetPH.class);
        TargetDeviceStateDAO.pausePHTarget(targetPH, deviceId);
    }

    @POST
    @Path("flushPumps")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void flushPumps(@Context HttpServletRequest req, String json) {
        System.out.println("in Server Side: flushPumps");
        session = req.getSession(false);
        String deviceId = (String) session.getAttribute("deviceId");
        Gson gson = new Gson();
        FlushPumps flushPumps = gson.fromJson(json, FlushPumps.class);
        TargetDeviceStateDAO.setFlushPumps(flushPumps, deviceId);
    }

}
