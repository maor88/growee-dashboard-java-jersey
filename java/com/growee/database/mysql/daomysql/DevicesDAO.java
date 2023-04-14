/**
 * 
 */
package com.growee.database.mysql.daomysql;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.growee.database.mysql.QueryGenerator;
import com.growee.database.mysql.daomysql.CurrentDeviceStateDAO.CurrenStateColumns;
import com.growee.database.mysql.daomysql.DeviceGrowSettingsDAO.DeviceGrowSettingsColumns;
import com.growee.objects.devices.Device;
import com.growee.startgrowee.Dashboard;

/**
 * @author Maor
 *
 */
public class DevicesDAO extends DAOBaseClass {

	public static final String tableName = "devices";
	public static final String primaryKey = "device_id";

	public static List<Device> getUserDevices(String userId) {
		String query = "select devices.* , device_grow_settings.is_initilized , device_current_state.last_update  from devices left join device_grow_settings on devices.device_id = device_grow_settings.device_id left join  device_current_state on  device_current_state.device_id = device_grow_settings.device_id  where devices.user_id ="
				+ userId + " order by devices.device_number ASC";
		String time;
		Timestamp timeStamp;
		boolean isConnected = false;
		List<Device> devices = new ArrayList<Device>();
		try {
			connect();
			rs = st.executeQuery(query);
			while (rs.next()) {
				time = rs.getString(CurrenStateColumns.LastUpdate.getColumnName());
				if (time != null) {
					timeStamp = Timestamp.valueOf(time);
					isConnected = Dashboard.isDeviceLastUpdateInTime(timeStamp, Dashboard.MAX_GAP_TIME_IN_MIN);
				} else {
					isConnected = false;
				}
				devices.add(new Device(rs.getString(DevicesColumns.DeviceId.getColumnName()),
						rs.getString(DevicesColumns.deviceName.getColumnName()),
						rs.getString(DevicesColumns.deviceNumber.getColumnName()),
						rs.getBoolean(DeviceGrowSettingsColumns.IsInitilized.getColumnName()), isConnected));
			}
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
		return devices;
	}

	public static String getFirstUserDeviceId(String userId) {
		String query = "select device_id, min(" + DevicesColumns.deviceNumber.getColumnName() + ") from " + tableName
				+ "  where " + DevicesColumns.UserId.getColumnName() + "= ? ";
		String deviceId = null;
		try {
			connect(query);
			ps.setString(1,userId);
			rs = ps.executeQuery();
			if (rs.next()) {
				deviceId = String.valueOf(rs.getInt(DevicesColumns.DeviceId.getColumnName()));
			}
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
		return deviceId;
	}

	public static String getUserDeviceIdByDeviceName(String userId, String deviceName) {
		String query = "select * from  " + tableName + "  where " + DevicesColumns.UserId.getColumnName() + "= '"
				+ userId + "' and " + DevicesColumns.deviceName.getColumnName() + " =" + deviceName;
		String deviceId = null;
		try {
			connect();
			rs = st.executeQuery(query);
			if (rs.next()) {
				deviceId = String.valueOf(rs.getInt(DevicesColumns.DeviceId.getColumnName()));
			}
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
		return deviceId;
	}

	public static void setDeviceName(String device_name, String deviceId) {
		String query = "UPDATE " + tableName + " SET " + DevicesColumns.deviceName.getColumnName() + " = '"
				+ device_name + "'  WHERE " + primaryKey + "= '" + deviceId + "'";
		try {
			connect();
			st.executeUpdate(query);
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
	}

	public static String getCreatedTime(String deviceId) {
		Date date = null;
		try {
			connect();
			rs = st.executeQuery(QueryGenerator.selectRowByPrimatyKey(tableName, primaryKey, deviceId));
			rs.next();
			if (!rs.wasNull()) {
				date = rs.getDate(DevicesColumns.TimeCreated.getColumnName());
			}
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
		return date.toString().replaceAll("-", "/");
	}

	public static void addNewDevice(String deviceId, String userId) {

		String deviceName = "device" + deviceId;
		String query = " insert into " + tableName + "(" + DevicesColumns.DeviceId.getColumnName() + ","
				+ DevicesColumns.UserId.getColumnName() + ", " + DevicesColumns.deviceName.getColumnName()
				+ " ) values ('" + deviceId + "' , '" + userId + "', '" + deviceName + "')";
		try {
			connect();
			st.executeUpdate(query);
		} catch (

		Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
	}

	public static void deleteDevice(String deviceId) {
		String query = "DELETE FROM " +tableName +" WHERE " +primaryKey +"='" +deviceId +"'";
		try {
			connect();
			st.executeUpdate(query);
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
	}

	public enum DevicesColumns {
		DeviceId("device_id"), UserId("user_id"), deviceName("device_name"), deviceNumber("device_number"), TimeCreated(
				"time_created");

		private String column;

		DevicesColumns(String column) {
			this.column = column;
		}

		public String getColumnName() {
			return column;
		}
	}

}
