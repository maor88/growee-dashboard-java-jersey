package com.growee.generalActions;

import com.growee.database.mysql.daomysql.CurrentDeviceStateDAO;
import com.growee.database.mysql.daomysql.DeviceGrowSettingsDAO;
import com.growee.database.mysql.daomysql.RegistrationDeviceDAO;
import com.growee.database.mysql.daomysql.TargetDeviceStateDAO;
import com.growee.database.mysql.daomysql.DevicesDAO;
import com.growee.database.mysql.daomysql.NotificationDAO;
import com.growee.database.redis.HistoryDeviceDataRedis;

public class GeneralActions {
	
	public static void createdNewRegistrationsCode(int numberToCreate) {
		RegistrationDeviceDAO.createdNewRegistrationsCode(numberToCreate);
	}
	
	public static void deleteDeviceFromAllDB(String deviceId,String userId,String timeDeviceCreate){
		CurrentDeviceStateDAO.deleteDevice(deviceId);
		TargetDeviceStateDAO.deleteDevice(deviceId);
		DeviceGrowSettingsDAO.deleteDevice(deviceId);
		DevicesDAO.deleteDevice(deviceId);
		RegistrationDeviceDAO.deleteDevice(deviceId,userId);
		NotificationDAO.deleteDevice(deviceId);
		HistoryDeviceDataRedis.deleteDeviceHistory(deviceId, timeDeviceCreate);
	}

}
