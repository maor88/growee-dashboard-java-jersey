/**
 * 
 */
package com.growee.database.mysql.daomysql;


/**
 * @author Maor
 *
 */
public class ForgotPasswordDAO extends DAOBaseClass {

	public static final String tableName = "forgot_password";
	public static final String primaryKey = "user_email";

	public static void insertGeneratedUrlCode(String email, String urlCode) {
		String query = "insert into " + tableName + " (" + ForgotPassColumns.UserEmail.getColumnName() + " ) values('"
				+ email + "' ) on duplicate key update  " + ForgotPassColumns.UserEmail.getColumnName() + "= '" + email
				+ "', " + ForgotPassColumns.UrlCode.getColumnName() + " = '" + urlCode + "'";
		
		try {
			connect();
			st.executeUpdate(query);
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}

	}
	
	
	public static boolean isCodeUrlExist(String urlCode) {
		String query = "select * from "+tableName + " where " + ForgotPassColumns.UrlCode.getColumnName() + "= '" + urlCode + "'"; 
		boolean res = false;
		try {
			connect();
			rs = st.executeQuery(query);
			if (rs.next() && !rs.wasNull()) {
				res = true;
			}
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
		return res;
	}

	public static String getUserEmail(String urlCode) {
		String query = "select * from "+tableName+ "  where " +ForgotPassColumns.UrlCode.getColumnName() + "='" +  urlCode+ "'";
		String userEmail = "";
		try {
			connect();
			rs = st.executeQuery(query);
			while (rs.next()) {
				userEmail = rs.getString("user_email");
			}
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
		return userEmail;
	}

	public static void removeURLCode(String urlCode) {
		String query = "UPDATE " + tableName + " SET " + ForgotPassColumns.UrlCode.getColumnName() + "=''" +  " WHERE " +ForgotPassColumns.UrlCode.getColumnName() + "='" + urlCode + "'";
		try {
			connect();
			st.executeUpdate(query);
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
	}

	public enum ForgotPassColumns {
		UserEmail("user_email"),
		UrlCode("url_code");

		private String column;

		ForgotPassColumns(String column) {
			this.column = column;
		}

		public String getColumnName() {
			return column;
		}
	}



}
