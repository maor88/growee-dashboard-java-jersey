package com.growee.database.mysql.daomysql;

import java.util.ArrayList;
import java.util.List;

import com.growee.objects.users.UserSettings;

/**
 * @author Maor
 *
 */
public class UserSettingsDAO extends DAOBaseClass {

	public static void setNewUserSettings(UserSettings userSettings, String userId) {
		String query = "UPDATE user_settings SET user_name ='" + userSettings.getUserName() + "', email='" + userSettings.getEmail()+
				"', plant_type='" + userSettings.getPlantType()+"', plant_status='" + userSettings.getPlantStatus() + 
				"', ec_type='" + userSettings.getEcType() + "', water_quantity='" + userSettings.getWaterLiter() + "' WHERE user_id='" + userId + "'";
		try {
			connect();
			st.executeUpdate(query);
			disconnect();
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}
	
	public static List<UserSettings> getAllUsrtSettingsTable() {
		List<UserSettings> usersSettings = new ArrayList<UserSettings>();
		String query = "select * from user_settings";
		try {
			connect();
			rs = st.executeQuery(query);
			while (rs.next()) {
				usersSettings.add(new UserSettings(rs.getString("user_id"), rs.getString("user_name"),rs.getString("email"),rs.getString("plant_type")
						,rs.getString("plant_status"),rs.getString("ec_type"),rs.getString("water_quantity")));
			}
			disconnect();
		} catch (Exception ex) {
			System.err.println(ex);
		}
		return usersSettings;
	}

	public static String isUserSignedUp(String userId) {
		String query = "select * from user_settings where user_id = '" + userId + "'";
		String userLogedIn ="false";
		try {
			connect();
			rs = st.executeQuery(query);
			rs.next();
			String name = rs.getString("user_name");
			if (name!=null && !name.isEmpty()) {
				userLogedIn = "true";
			}
			disconnect();
		} catch (Exception ex) {
			System.err.println(ex);
		}
		
		return userLogedIn;
	}

	public static String getUserName(String userId) {
		String query = "select * from user_settings where user_id = '" + userId + "'";
		String name = null;
		try {
			connect();
			rs = st.executeQuery(query);
			rs.next();
			name = rs.getString("user_name");
			if (name==null || name.isEmpty()) {
				name = " user name not set!";
			}
			disconnect();
		} catch (Exception ex) {
			System.err.println(ex);
		}
		
		return name;
	}


}
