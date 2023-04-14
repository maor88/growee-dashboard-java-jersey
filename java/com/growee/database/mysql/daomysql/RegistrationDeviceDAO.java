package com.growee.database.mysql.daomysql;

import com.growee.codeGenerator.StringCodeCreator;
import com.growee.database.mysql.daomysql.DevicesDAO.DevicesColumns;

public class RegistrationDeviceDAO extends DAOBaseClass{

	private static final String tableName = "device_registration";
	private static final String primaryKey = "registration_code";
	
	
	public static boolean isRegistrationCodeExist(String registrationCode) {
		boolean res = false;
		String query = "select * from " + tableName + " where " + primaryKey + " = '"
				+ registrationCode + "'";
		try {
			connect();
			rs = st.executeQuery(query);
			if (rs.next()) {
				res = true;
			}
		} catch (Exception ex) {
			System.err.println("error to know if registration code");
		} finally {
			disconnect();
		}
		return res;
	}
	
	public static String getRegistrationCodeUserId(String registrationCode) {
		int userId = 0 ;
		String query = "select * from " + tableName + " where " + primaryKey + " = '"
				+ registrationCode + "'";
		try {
			connect();
			rs = st.executeQuery(query);
			
			if (rs.next()) {
				userId = rs.getInt(DevicesConfigurationColumns.UserId.getColumnName());
			}
		} catch (Exception ex) {
			System.err.println("error to know if registration code");
		} finally {
			disconnect();
		}
		return Integer.toString(userId);
	}
	
	
	public static void setUserIdToRegistrationCode(String registrationCode, String userId) {
		String query = "UPDATE " + tableName + " SET " + DevicesColumns.UserId.getColumnName() + " = '"
				+ userId + "',"+ DevicesConfigurationColumns.TimeUserRegistrated.getColumnName() + " = now()  where " + primaryKey + "= '" + registrationCode + "'";
		try {
			connect();
			st.executeUpdate(query);
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
		
	}
	
	public static void deleteDevice(String deviceId ,String userId) {
		String query = "UPDATE " + tableName + " SET " + DevicesConfigurationColumns.UserId.getColumnName() + " = -1  where " +  DevicesConfigurationColumns.PcbId.getColumnName() + "= " + deviceId + "";
		try {
			connect();
			st.executeUpdate(query);
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
		
	}
	
	public static String getDeviceId(String registartionCode) {
		String query = "select * from "+tableName+ "  where " +primaryKey + "='" +  registartionCode+ "'";
		String DeviceIde = "";
		try {
			connect();
			rs = st.executeQuery(query);
			while (rs.next()) {
				DeviceIde = rs.getString(DevicesConfigurationColumns.PcbId.getColumnName());
			}
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
		return DeviceIde;
	}
	
	
	public static void createdNewRegistrationsCode(int numberToCreate){
		String query;
		try {
			connect();
			for(int i =  0 ; i < numberToCreate ; i ++) {
				query = " insert into " + tableName + "("+DevicesConfigurationColumns.RegistrationCode.getColumnName() +") values ('" + StringCodeCreator.generateRegistrationCode() + "')";
				st.executeUpdate(query);
			}		
		} catch (Exception ex) {
			System.err.println(ex);
		}
		finally {
			disconnect();
		}
	}
	

	public enum DevicesConfigurationColumns {
		DeviceId("device_id"), UserId("user_id"), RegistrationCode("registration_code"), PcbId("pcb_id"), TimeCreated(
				"time_created") , TimeDeviceRegistrated("time_device_registrate"), TimeUserRegistrated("time_user_registrate");

		private String column;

		DevicesConfigurationColumns(String column) {
			this.column = column;
		}

		public String getColumnName() {
			return column;
		}
	}




	
}
