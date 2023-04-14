package com.growee.database.mysql.daomysql;

import java.util.ArrayList;
import java.util.List;

import com.growee.database.mysql.QueryGenerator;
import com.growee.objects.users.UserDetails;

/**
 * @author Maor
 *
 */
public class AdminUserDAO extends DAOBaseClass {

	public static final String tableName = "user_admin";


	public static String getAdminId(UserDetails user) {
		String query = "select * from user_admin where user_name ='" + user.getUserName() + "' and user_password='"
				+ user.getPass() + "'";
		String userId = "";
		try {
			connect();
			rs = st.executeQuery(query);
			while (rs.next()) {
				userId = rs.getString("user_id");
			}
			disconnect();
		} catch (Exception ex) {
			System.err.println(ex);
		}
		return userId;

	}
	
	
	public static boolean isUserAdmin(UserDetails user) {
		String query = "select * from user_admin where user_code ='" + user.getUserName() + "' and user_password='"
				+ user.getPass() + "'";
		boolean res = false ;
		try {
			connect();
			rs = st.executeQuery(query);
			rs.next();
			if (!rs.wasNull()) {
				res = true;
			}
			disconnect();
		} catch (Exception ex) {
			System.err.println("the user is not an admin");
		}
		return res;
	}
	
	public static boolean isUserAdmin(String userId) {
		String query = "select * from user_admin where user_id ='" + userId + "'";
		boolean res = false;
		try {
			connect();
			rs = st.executeQuery(query);
			rs.next();
			if (!rs.wasNull()) {
				res = true;
			}
			disconnect();
		} catch (Exception ex) {
			System.err.println(ex);
		}
		return res;
	}

	public static List<UserDetails> getAllAdminUserTable() {
		List<UserDetails> usersDetails = new ArrayList<UserDetails>();
		try {
			connect();
			rs = st.executeQuery(QueryGenerator.selectAllTable(tableName));
			while (rs.next()) {
				usersDetails.add(new UserDetails(rs.getString("user_code"), rs.getString("user_password")));
			}
			disconnect();
		} catch (Exception ex) {
			System.err.println(ex);
		}
		return usersDetails;
	}
}
