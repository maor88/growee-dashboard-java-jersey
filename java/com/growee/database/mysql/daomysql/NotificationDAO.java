package com.growee.database.mysql.daomysql;

import com.growee.objects.notification.DeviceNotification;

public class NotificationDAO extends DAOBaseClass {

	public static final String tableName = "notification";
	public static final String primaryKey = "device_id";

	public static void setNotificationData(DeviceNotification notification) {

		int doNotification = notification.isSetNotification() ? 1 : 0;
		String query = "insert into " + tableName + " (" + NotificationColumns.DeviceId.getColumnName() + ","
				+ NotificationColumns.UserId.getColumnName() + ","
				+ NotificationColumns.NotificationEmail.getColumnName() + ","
				+ NotificationColumns.DoNotification.getColumnName() + ") values('" + notification.getDevcieId() + "','"
				+ notification.getUserId() + "','" + notification.getEmail() + "'," + doNotification
				+ ") on duplicate key update  " + NotificationColumns.NotificationEmail.getColumnName() + "= '"
				+ notification.getEmail() + "', " + NotificationColumns.DoNotification.getColumnName() + " = "
				+ doNotification + ", " + NotificationColumns.AlreadyNotifyToUser.getColumnName() + " = 0";
		try {
			connect();
			st.executeUpdate(query);
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}

	}

	public static boolean isDeviceNotificationOn(String deviceId) {

		String query = "select * from  " + tableName + "  where "+ primaryKey +"='" + deviceId +"'";
		boolean res = false;
		try {
			connect();
			rs = st.executeQuery(query);
			if (rs.next()) {
				res = rs.getBoolean(NotificationColumns.DoNotification.getColumnName());
		}
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
		return res;
	}

	public static String getNotificationEmail(String deviceId) {
		String query = "select * from  " + tableName + "  where "+ primaryKey +"='" + deviceId +"'";
		String res = null;
		try {
			connect();
			rs = st.executeQuery(query);
			if (rs.next()) {
				res = rs.getString(NotificationColumns.NotificationEmail.getColumnName());
			}

		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
		return res;
	}
	
	public static void initilizeAlreadyNotify(String deviceId , String userId , String email) {
	
		int doNotification =  0;
		String query = "insert into " + tableName + " (" + NotificationColumns.DeviceId.getColumnName() + ","
				+ NotificationColumns.UserId.getColumnName() + ","
				+ NotificationColumns.NotificationEmail.getColumnName() + ","
				+ NotificationColumns.DoNotification.getColumnName() + ") values('" + deviceId + "','"
				+ userId + "','" + email + "'," + doNotification
				+ ") on duplicate key update  "  + NotificationColumns.AlreadyNotifyToUser.getColumnName() + " = 0";
		try {
			connect();
			st.executeUpdate(query);
		} catch (Exception ex) {
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

	
	
	public enum NotificationColumns {
		DeviceId("device_id"), UserId("user_id"), NotificationEmail("email"), DoNotification(
				"do_notification"), DeviceName(
						"device_name"), UserName("user_name"), AlreadyNotifyToUser("already_notify_to_user");

		private String column;

		NotificationColumns(String column) {
			this.column = column;
		}

		public String getColumnName() {
			return column;
		}
	}





}
