package com.growee.database.mysql.daomysql;

import java.util.ArrayList;
import java.util.List;

import com.growee.database.mysql.QueryGenerator;
import com.growee.objects.users.UserDetails;
import com.growee.objects.users.UserSignUp;

/**
 * @author Maor
 *
 */
public class UsersDAO extends DAOBaseClass {

	public static final String tableName = "users";
	public static final String primaryKey = "user_id";

	public static String getUserId(UserDetails user) {
		String query = "select * from " + tableName + " where  user_password= ? and (user_name = ? or user_email = ?)";
		String userId = "";
		try {
			connect(query);
			ps.setString(1, user.getPass());
			ps.setString(2, user.getUserName());
			ps.setString(3, user.getUserName());
			rs = ps.executeQuery();
			while (rs.next()) {
				userId = rs.getString("user_id");
			}
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
		return userId;

	}

	public static String getUserIdByEmail(String email) {
		String query = "select * from " + tableName + " where user_email= ?";
		String userId = "";
		try {
			connect(query);
			ps.setString(1,email);
			rs = ps.executeQuery();
			while (rs.next()) {
				userId = rs.getString("user_id");
			}
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
		return userId;

	}

	public static boolean isRegularUser(UserDetails user) {
		String query = "select * from  " + tableName + "  where  user_password=? and (user_name = ? or user_email = ? )";
		boolean res = false;
		try {
			connect(query);
			ps.setString(1, user.getPass());
			ps.setString(2, user.getUserName());
			ps.setString(3, user.getUserName());
			rs = ps.executeQuery();
			if (rs.next()) {
				res = true;
			}
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
		return res;
	}

	public static String getUserNameByEmail(String email) {
		String query = "select * from  " + tableName + "  where user_email = '" + email + "'";
		String userName = "";
		try {
			connect();
			rs = st.executeQuery(query);
			if (rs.next() && !rs.wasNull()) {
				userName = rs.getString("user_name");
			}
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
		return userName;
	}

	public static String getUserEmail(String userId) {
		String query = "select * from  " + tableName + "  where " + primaryKey + "= '" + userId + "'";
		String userEmail = "";
		try {
			connect();
			rs = st.executeQuery(query);
			if (rs.next() && !rs.wasNull()) {
				userEmail = rs.getString(UsersColumns.UserEmail.getColumnName());
			}
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
		return userEmail;
	}

	public static String getUserNameById(String userId) {
		String query = "select * from  " + tableName + "  where user_id = " + userId;
		String userName = "";
		try {
			connect();
			rs = st.executeQuery(query);
			if (rs.next() && !rs.wasNull()) {
				userName = rs.getString("user_name");
			}
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
		return userName;
	}

	public static UserDetails getUserDetailsById(String userId) {
		String query = "select * from  " + tableName + "  where user_id = " + userId;
		UserDetails userDetails = new UserDetails();
		try {
			connect();
			rs = st.executeQuery(query);
			if (rs.next() && !rs.wasNull()) {
				userDetails.setUserName(rs.getString(UsersColumns.UserName.getColumnName()));
				userDetails.setUserEmail(rs.getString(UsersColumns.UserEmail.getColumnName()));
			}
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
		return userDetails;
	}

	public static List<UserDetails> getAllUserTable() {
		List<UserDetails> usersDetails = new ArrayList<UserDetails>();
		try {
			connect();
			rs = st.executeQuery(QueryGenerator.selectAllTable(tableName));
			while (rs.next()) {
				usersDetails.add(new UserDetails(rs.getString("user_id"), rs.getString("user_email"),
						rs.getString("user_password"), rs.getString("growee_device_id"), rs.getString("user_code")));
			}
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
		return usersDetails;
	}

	public static boolean setNewPassword(String userEmail, String newPass) {
		String query = "UPDATE " + tableName + " SET " + UsersColumns.Password.getColumnName() + "='" + newPass
				+ "' WHERE " + UsersColumns.UserEmail.getColumnName() + "='" + userEmail + "'";
		try {
			connect();
			st.executeUpdate(query);
			return true;
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
		return false;
	}

	public static boolean setNewUserName(String userId, String userName) {
		String query = "UPDATE " + tableName + " SET " + UsersColumns.UserName.getColumnName() + "='" + userName
				+ "' WHERE " + UsersColumns.UserID.getColumnName() + "=" + userId + "";
		try {
			connect();
			st.executeUpdate(query);
			return true;
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
		return false;
	}

	public static boolean isUserSetFirstTimePassword(UserDetails user) {
		String query = "select * from  " + tableName + "  where (" + UsersColumns.UserName.getColumnName() + "= ? or " + UsersColumns.UserEmail.getColumnName() + " = ?) and "+ UsersColumns.Password.getColumnName()+ "= ?";
		boolean res = false;
		try {
			connect(query);
			ps.setString(1,user.getUserName());
			ps.setString(2,user.getUserName());
			ps.setString(3,user.getPass());
			rs = ps.executeQuery();

			if (rs.next()) {
				res = rs.getBoolean(UsersColumns.InitPass.getColumnName());
			}
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
		return !res;
	}

	public static boolean isUserNameExist(String userName) {
		String query = "select * from  " + tableName + "  where user_name ='" + userName + "'";
		boolean res = false;
		try {
			connect();
			rs = st.executeQuery(query);

			if (rs.next()) {
				res = true;
			}
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
		return res;
	}

	public static boolean isUserEmailExist(String email) {
		String query = "select * from  " + tableName + "  where " + UsersColumns.UserEmail.getColumnName() + "='"
				+ email + "'";
		boolean res = false;
		try {
			connect();
			rs = st.executeQuery(query);
			if (rs.next()) {
				res = true;
			}
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
		return res;
	}

	public static boolean isUserSawWelcomePage(UserDetails user) {
		String query = "select * from  " + tableName + "  where " + UsersColumns.UserID.getColumnName() + "='"
				+ user.getUserId() + "'";
		boolean res = false;
		try {
			connect();
			rs = st.executeQuery(query);

			if (rs.next()) {
				res = rs.getBoolean(UsersColumns.AfterWelcome.getColumnName());
			}
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
		return res;
	}

	public static List<String> getAllUserNames() {
		List<String> userNames = new ArrayList<String>();
		String query = "select * from  " + tableName;
		try {
			connect();
			rs = st.executeQuery(query);
			while (rs.next()) {
				userNames.add(rs.getString(UsersColumns.UserName.getColumnName()));
			}
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
		return userNames;
	}

	public static List<String> getAllUserEmailes() {
		List<String> userEmailes = new ArrayList<String>();
		String query = "select * from  " + tableName;
		try {
			connect();
			rs = st.executeQuery(query);
			while (rs.next()) {
				userEmailes.add(rs.getString(UsersColumns.UserEmail.getColumnName()));
			}
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
		return userEmailes;
	}

	public static void setNewPasswordAndUserName(UserDetails user, String userId) {
		String query = "UPDATE " + tableName + " SET " + UsersColumns.Password.getColumnName() + "='" + user.getPass()
				+ "' , " + UsersColumns.UserName.getColumnName() + "= '" + user.getUserName() + "' WHERE "
				+ UsersColumns.UserID.getColumnName() + "='" + userId + "'";
		try {
			connect();
			st.executeUpdate(query);
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
	}

	public static void addNewUser(UserSignUp user) {
		String query = "insert into " + tableName + " (" + UsersColumns.UserEmail.getColumnName() + ", "
				+ UsersColumns.Password.getColumnName() + "," + UsersColumns.UserName.getColumnName() + ","+UsersColumns.InitPass.getColumnName()+") values ('"
				+ user.getNewUserEmail() + "','" + user.getNewPassword() +"','" +user.getUserName()+ "',1)";
		try {
			connect();
			st.executeUpdate(query);
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}

	}

	public static void setInitPasswordDone(String userId) {
		String query = "UPDATE " + tableName + " SET " + UsersColumns.InitPass.getColumnName() + "= 1  WHERE "
				+ UsersColumns.UserID.getColumnName() + "='" + userId + "'";
		try {
			connect();
			st.executeUpdate(query);
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
	}

	public static void setUserSawWelcomePopup(String userId) {
		String query = "UPDATE " + tableName + " SET " + UsersColumns.AfterWelcome.getColumnName() + "= 1  WHERE "
				+ UsersColumns.UserID.getColumnName() + "='" + userId + "'";
		try {
			connect();
			st.executeUpdate(query);
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}

	}

	public static void updateLastLogin(String userId) {
		String query = "UPDATE " + tableName + " SET " + UsersColumns.LastLogin.getColumnName() + "= now()  WHERE "
				+ UsersColumns.UserID.getColumnName() + "='" + userId + "'";
		try {
			connect();
			st.executeUpdate(query);
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
	}

	public enum UsersColumns {
		UserID("user_id"), UserEmail("user_email"), Password("user_password"), UserName("user_name"), InitPass(
				"init_Pass"), AfterWelcome("after_welcome"),LastLogin("last_login");

		private String column;

		UsersColumns(String column) {
			this.column = column;
		}

		public String getColumnName() {
			return column;
		}
	}

}
