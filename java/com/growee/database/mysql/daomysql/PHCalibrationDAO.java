package com.growee.database.mysql.daomysql;

import com.growee.objects.calibration.PHCalibration;

public class PHCalibrationDAO extends DAOBaseClass {

	public static final String tableName = "calibration_ph";
	public static final String primaryKey = "device_id";


	public static void addDeviceToTableIfNotExist(String deviceId) {
		String query = "insert into " + tableName + " (" + CalibrationPHColumns.DeviceId.getColumnName() + ","
				+ CalibrationPHColumns.DoCalibrationPH_4.getColumnName() + " ) values('" + deviceId
				+ "',0 ) on duplicate key update  " + CalibrationPHColumns.DoCalibrationPH_4.getColumnName() + "= 0";
		try {
			connect();
			st.executeUpdate(query);
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
	}

	public static void startPH4Calibration(String deviceId) {

		String query = "insert into " + tableName + " (" + CalibrationPHColumns.DeviceId.getColumnName() + ","
				+ CalibrationPHColumns.DoCalibrationPH_4.getColumnName() + " ) values('" + deviceId
				+ "',1 ) on duplicate key update  " + CalibrationPHColumns.DoCalibrationPH_4.getColumnName() + "= 1";
		try {
			connect();
			st.executeUpdate(query);
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
	}

	public static void startPH7Calibration(String deviceId) {

		String query = "insert into " + tableName + " (" + CalibrationPHColumns.DeviceId.getColumnName() + ","
				+ CalibrationPHColumns.DoCalibrationPH_4.getColumnName() + ","
				+ CalibrationPHColumns.DoCalibrationPH_7.getColumnName() + " ) values('" + deviceId
				+ "',0,1 ) on duplicate key update  " + CalibrationPHColumns.DoCalibrationPH_4.getColumnName() + "= 0 ,"
				+ CalibrationPHColumns.DoCalibrationPH_7.getColumnName() + "= 1";
		try {
			connect();
			st.executeUpdate(query);
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
	}

	public static void initlizeCalibration(String deviceId) {
		String query = "UPDATE " + tableName + " SET " + CalibrationPHColumns.DoCalibrationPH_4.getColumnName()
				+ " = 0, " + CalibrationPHColumns.PH_4_1_Raw.getColumnName() + " ='0', "
				+ CalibrationPHColumns.PH_4_2_Raw.getColumnName() + " ='0', "
				+ CalibrationPHColumns.PH_4_3_Raw.getColumnName() + " ='0', "
				+ CalibrationPHColumns.PH_4_Average.getColumnName() + " ='0', "
				+ CalibrationPHColumns.DoCalibrationPH_7.getColumnName() + " = 0, "
				+ CalibrationPHColumns.PH_7_1_Raw.getColumnName() + " ='0', "
				+ CalibrationPHColumns.PH_7_2_Raw.getColumnName() + " ='0', "
				+ CalibrationPHColumns.PH_7_3_Raw.getColumnName() + " ='0', "
				+ CalibrationPHColumns.PH_7_Average.getColumnName() + " ='0'  WHERE " + primaryKey + "='" + deviceId
				+ "'";
		try {
			connect();
			st.executeUpdate(query);
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
	}

	public static void initlize7Calibration(String deviceId) {
		String query = "UPDATE " + tableName + " SET "
				+ CalibrationPHColumns.DoCalibrationPH_7.getColumnName() + " = 0, "
				+ CalibrationPHColumns.PH_7_1_Raw.getColumnName() + " ='0', "
				+ CalibrationPHColumns.PH_7_2_Raw.getColumnName() + " ='0', "
				+ CalibrationPHColumns.PH_7_3_Raw.getColumnName() + " ='0', "
				+ CalibrationPHColumns.PH_7_Average.getColumnName() + " ='0'  WHERE " + primaryKey + "='" + deviceId
				+ "'";
		try {
			connect();
			st.executeUpdate(query);
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
	}

	public static void initlize4Calibration(String deviceId) {
		String query = "UPDATE " + tableName + " SET "
				+ CalibrationPHColumns.DoCalibrationPH_4.getColumnName() + " = 0, "
				+ CalibrationPHColumns.PH_4_1_Raw.getColumnName() + " ='0', "
				+ CalibrationPHColumns.PH_4_2_Raw.getColumnName() + " ='0', "
				+ CalibrationPHColumns.PH_4_3_Raw.getColumnName() + " ='0', "
				+ CalibrationPHColumns.PH_4_Average.getColumnName() + " ='0'  WHERE " + primaryKey + "='" + deviceId
				+ "'";
		try {
			connect();
			st.executeUpdate(query);
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
	}

	public static float getFourCalibrationAvergate(String deviceId) {
		float fourPHRowAverage = 0;
		try {
			String query = "select * from " + tableName + " where " + primaryKey + "= '" + deviceId + "'";
			connect();
			rs = st.executeQuery(query);
			rs.next();
			if (!rs.wasNull()) {
				float ph_4_2_raw = rs.getFloat(CalibrationPHColumns.PH_4_2_Raw.getColumnName());
				float ph_4_3_raw = rs.getFloat(CalibrationPHColumns.PH_4_3_Raw.getColumnName());
				fourPHRowAverage = (ph_4_3_raw + ph_4_2_raw) / 2;
				String quary2 = "UPDATE " + tableName + " SET " + CalibrationPHColumns.PH_4_Average.getColumnName()
						+ " =  '" + Float.toString(fourPHRowAverage) + "'," +CalibrationPHColumns.DoCalibrationPH_4.getColumnName()+ " = 0  WHERE " + primaryKey + "='" + deviceId + "'";
				st.executeUpdate(quary2);
			}
		} catch (Exception ex) {
			System.err.println("error getting ph 4 average raw");
		} finally {
			disconnect();
		}
		return fourPHRowAverage;
	}

	public static float getSevenCalibrationAvergate(String deviceId) {
		float sevenPHRowAverage = 0;
		try {
			String query = "select * from " + tableName + " where " + primaryKey + "= '" + deviceId + "'";
			connect();
			rs = st.executeQuery(query);
			rs.next();
			if (!rs.wasNull()) {
				float ph_7_2_raw = rs.getFloat(CalibrationPHColumns.PH_7_2_Raw.getColumnName());
				float ph_7_3_raw = rs.getFloat(CalibrationPHColumns.PH_7_3_Raw.getColumnName());
				sevenPHRowAverage = (ph_7_2_raw + ph_7_3_raw) / 2;
				String quary2 = "UPDATE " + tableName + " SET " + CalibrationPHColumns.PH_7_Average.getColumnName()
						+ " =  '" + Float.toString(sevenPHRowAverage) + "' ," +CalibrationPHColumns.DoCalibrationPH_7.getColumnName()+ " = 0  WHERE " + primaryKey + "='" + deviceId + "'";
				st.executeUpdate(quary2);
			}
		} catch (Exception ex) {
			System.err.println("error getting ph 7 average raw");
		} finally {
			disconnect();
		}
		return sevenPHRowAverage;
	}

	public static PHCalibration getPHAverageRaw(String deviceId) {
		PHCalibration phRowAverage = null;
		try {
			String query = "select *  from " + tableName + " where " + CalibrationPHColumns.DeviceId.getColumnName()
					+ "= '" + deviceId + "'";
			connect();
			rs = st.executeQuery(query);
			if (rs.next()) {
				phRowAverage = new PHCalibration(rs.getFloat(CalibrationPHColumns.PH_4_2_Raw.getColumnName()),
						rs.getFloat(CalibrationPHColumns.PH_4_3_Raw.getColumnName()),
						rs.getFloat(CalibrationPHColumns.PH_4_Average.getColumnName()),
						rs.getFloat(CalibrationPHColumns.PH_7_2_Raw.getColumnName()),
						rs.getFloat(CalibrationPHColumns.PH_7_3_Raw.getColumnName()),
						rs.getFloat(CalibrationPHColumns.PH_7_Average.getColumnName()));
			}
		} catch (Exception ex) {
			System.err.println("error getting ph raws average ");
		} finally {
			disconnect();
		}
		return phRowAverage;
	}

	public static void storePHCalibrationParameters(String deviceId, PHCalibration phCalibration) {
		String query = "UPDATE " + tableName + " SET " + CalibrationPHColumns.A_PH.getColumnName() + " =  "
				+ Float.toString(phCalibration.getA_ph()) + "," + CalibrationPHColumns.B_PH.getColumnName() + " = "
				+ Float.toString(phCalibration.getB_ph()) + "  WHERE " + primaryKey + "='" + deviceId + "'";
		try {
			connect();
			st.executeUpdate(query);
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
	}

	public enum CalibrationPHColumns {
		DeviceId("device_id"), DoCalibrationPH_4("do_calibration_ph_4"), PH_4_1_Raw("4_1_raw"), PH_4_2_Raw("4_2_raw"), 
		PH_4_3_Raw("4_3_raw"), PH_4_Average("4_raw_avarage"), DoCalibrationPH_7("do_calibration_ph_7"), PH_7_1_Raw("7_1_raw"), 
		PH_7_2_Raw("7_2_raw"), PH_7_3_Raw("7_3_raw"), PH_7_Average("7_raw_avarage"), A_PH( "a_ph"), B_PH("b_ph"),
		Last_A_PH("last_a_ph"),Last_B_PH("last_b_ph"),LastPHCalibration("last_ph_calibration");

		private String column;

		CalibrationPHColumns(String column) {
			this.column = column;
		}

		public String getColumnName() {
			return column;
		}
	}

}
