package com.growee.database.mysql.daomysql;


public class LoginCookiesDAO extends DAOBaseClass{

	public static final String tableName = "login_cookies";
	public static final String primaryKey = "session_id";
	
	public static void setNewCookieSessionId(String sessionId, String userId) {

		String query = "insert into " + tableName + " (" +primaryKey +","+LoginCookiesColumns.UserId.getColumnName()+") values(?,?) on duplicate key update  " + primaryKey + "= ?" ;
		try {
			connect(query);
			ps.setString(1,sessionId);
			ps.setString(2,userId);
			ps.setString(3,sessionId);
			ps.executeUpdate();
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
	}

	public static boolean isSessionIdExist(String sessionId) {
		String query = "select * from "+tableName + " where " + primaryKey + "= ?";
		boolean res = false;
		try {
			connect(query);
			ps.setString(1,sessionId);
			rs = ps.executeQuery();
			if (rs.next() && !rs.wasNull()) {
				if(rs.getString(LoginCookiesColumns.UserId.getColumnName()) != null){
					res = true;
				}
			}
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
		return res;
	}
	
	public static String getUserIdSession(String sessionId) {
		String query = "select * from "+tableName+ "  where " +LoginCookiesColumns.SessionId.getColumnName() + "=?";
		String userId = "";
		try {
			connect(query);
			ps.setString(1,sessionId);
			rs = ps.executeQuery();
			rs.next();
			if (!rs.wasNull()) {
				userId = rs.getString(LoginCookiesColumns.UserId.getColumnName());
			}
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
		return userId;
	}
	
	
	public static void removeSessionId(String sessionId) {
		String query = "DELETE FROM " +tableName +" WHERE " +primaryKey +"='" +sessionId +"'";
		try {
			connect();
			st.executeUpdate(query);
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
		
	}
	
	public enum LoginCookiesColumns {
		SessionId("session_id"),
		UserId("user_id");

		private String column;

		LoginCookiesColumns(String column) {
			this.column = column;
		}

		public String getColumnName() {
			return column;
		}
	}


}
