package com.growee.database.mysql.daomysql;

import java.sql.*;

/**
 * @author Maor
 *
 */
public class DAOBaseClass {

	protected static Connection con;
	protected static Statement st;
	protected static PreparedStatement ps;
	protected static ResultSet rs;

	private static String productionSQL = "";
	private static String prodectionUserName = "growee";
	private static String productionPass = "";


	public static void connect() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = DriverManager.getConnection(productionSQL, prodectionUserName, productionPass);
			System.out.println("connect To Driver Success");
			st = con.createStatement();
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
	}

	public static void connect(String quary) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = DriverManager.getConnection(productionSQL, prodectionUserName, productionPass);
			System.out.println("connect To Driver Success");
			ps = con.prepareStatement(quary);
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
	}

	public static void disconnect()  {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {}
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {}
		}

		System.out.println("disconenct SQL");
	}

}
