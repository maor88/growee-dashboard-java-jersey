package com.growee.database.mysql.daomysql;

import com.growee.database.mysql.daomysql.PHCalibrationDAO.CalibrationPHColumns;
import com.growee.objects.calibration.CalibrationData;
import com.growee.objects.calibration.ECCalibration;

public class ECCalibrationDAO extends DAOBaseClass {

	public static final String tableName = "calibration_ec";
	public static final String primaryKey = "device_id";

	public static void addDeviceToTableIfNotExist(String deviceId) {
		String query = "insert into " + tableName + " (" + CalibrationECColumns.DeviceId.getColumnName() + ","
				+ CalibrationECColumns.DoCalibrationEC_1400.getColumnName() + ","
				+ CalibrationECColumns.DoCalibrationEC_2700.getColumnName() + " ) values('" + deviceId
				+ "',0,0) on duplicate key update  " + CalibrationECColumns.DoCalibrationEC_2700.getColumnName()
				+ "= 0 ";
		try {
			connect();
			st.executeUpdate(query);
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
	}


	public static void startEC1400Calibration(String deviceId) {

		String query = "insert into " + tableName + " (" + CalibrationECColumns.DeviceId.getColumnName() + ","
				+ CalibrationECColumns.DoCalibrationEC_1400.getColumnName() + ","
				+ CalibrationECColumns.DoCalibrationEC_2700.getColumnName() + " ) values('" + deviceId
				+ "',1,0 ) on duplicate key update  "
				+ CalibrationECColumns.DoCalibrationEC_2700.getColumnName()
				+ "= 0 ," + CalibrationECColumns.DoCalibrationEC_1400.getColumnName() + "= 1";
		try {
			connect();
			st.executeUpdate(query);
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
	}
	
	public static void startEC2700Calibration(String deviceId) {

		String query = "insert into " + tableName + " (" + CalibrationECColumns.DeviceId.getColumnName() + ","
				+ CalibrationECColumns.DoCalibrationEC_1400.getColumnName() + ","
				+ CalibrationECColumns.DoCalibrationEC_2700.getColumnName() + " ) values('" + deviceId
				+ "',0,1 ) on duplicate key update  "
				+ CalibrationECColumns.DoCalibrationEC_1400.getColumnName()
				+ "= 0 ," + CalibrationECColumns.DoCalibrationEC_2700.getColumnName() + "= 1";
		try {
			connect();
			st.executeUpdate(query);
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
	}
	
	

	public static void initlizeECCalibration(String deviceId) {
		String query = "UPDATE " + tableName + " SET "
				+ CalibrationECColumns.DoCalibrationEC_1400.getColumnName() + " = 0, "
				+ CalibrationECColumns.EC_1400_1_Raw.getColumnName() + " ='0', "
				+ CalibrationECColumns.EC_1400_2_Raw.getColumnName() + " ='0', "
				+ CalibrationECColumns.EC_1400_3_Raw.getColumnName() + " ='0', "
				+ CalibrationECColumns.EC_1400_Average.getColumnName() + " ='0', "
				+ CalibrationECColumns.DoCalibrationEC_2700.getColumnName() + " = 0, "
				+ CalibrationECColumns.EC_2700_1_Raw.getColumnName() + " ='0', "
				+ CalibrationECColumns.EC_2700_2_Raw.getColumnName() + " ='0', "
				+ CalibrationECColumns.EC_2700_3_Raw.getColumnName() + " ='0', "
				+ CalibrationECColumns.EC_2700_Average.getColumnName() + " ='0'  WHERE " + primaryKey + "='" + deviceId
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

	public static void initlize1400ECCalibration(String deviceId) {
		String query = "UPDATE " + tableName + " SET "
				+ CalibrationECColumns.DoCalibrationEC_1400.getColumnName() + " = 0, "
				+ CalibrationECColumns.EC_1400_1_Raw.getColumnName() + " ='0', "
				+ CalibrationECColumns.EC_1400_2_Raw.getColumnName() + " ='0', "
				+ CalibrationECColumns.EC_1400_3_Raw.getColumnName() + " ='0', "
				+ CalibrationECColumns.EC_1400_Average.getColumnName() + " ='0'  WHERE " + primaryKey + "='" + deviceId
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

	public static void initlize2700ECCalibration(String deviceId) {
		String query = "UPDATE " + tableName + " SET "
				+ CalibrationECColumns.DoCalibrationEC_2700.getColumnName() + " = 0, "
				+ CalibrationECColumns.EC_2700_1_Raw.getColumnName() + " ='0', "
				+ CalibrationECColumns.EC_2700_2_Raw.getColumnName() + " ='0', "
				+ CalibrationECColumns.EC_2700_3_Raw.getColumnName() + " ='0', "
				+ CalibrationECColumns.EC_2700_Average.getColumnName() + " ='0'  WHERE " + primaryKey + "='" + deviceId
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


	public static float get1400CalibrationAvergate(String deviceId) {
		float ec1400RowAverage = 0;
		try {
			String query = "select * from " + tableName + " where " + primaryKey + "= '" + deviceId + "'";
			connect();
			rs = st.executeQuery(query);
			rs.next();
			if (!rs.wasNull()) {
				float ec_1400_2_raw = rs.getFloat(CalibrationECColumns.EC_1400_2_Raw.getColumnName());
				float ec_1400_3_raw = rs.getFloat(CalibrationECColumns.EC_1400_3_Raw.getColumnName());
				ec1400RowAverage = (ec_1400_2_raw + ec_1400_3_raw) / 2;
				String quary2 = "UPDATE " + tableName + " SET " + CalibrationECColumns.EC_1400_Average.getColumnName()
						+ " =  '" + Float.toString(ec1400RowAverage) + "' ,"
						+ CalibrationECColumns.DoCalibrationEC_1400.getColumnName() + " = 0  WHERE " + primaryKey + "='"
						+ deviceId + "'";
				st.executeUpdate(quary2);
			}
		} catch (Exception ex) {
			System.err.println("error getting ec 1400 average raw");
		} finally {
			disconnect();
		}
		return ec1400RowAverage;
	}
	
	public static float get2700CalibrationAvergate(String deviceId) {
		float ec1400RowAverage = 0;
		try {
			String query = "select * from " + tableName + " where " + primaryKey + "= '" + deviceId + "'";
			connect();
			rs = st.executeQuery(query);
			rs.next();
			if (!rs.wasNull()) {
				float ec_1400_2_raw = rs.getFloat(CalibrationECColumns.EC_2700_2_Raw.getColumnName());
				float ec_1400_3_raw = rs.getFloat(CalibrationECColumns.EC_2700_3_Raw.getColumnName());
				ec1400RowAverage = (ec_1400_2_raw + ec_1400_3_raw) / 2;
				String quary2 = "UPDATE " + tableName + " SET " + CalibrationECColumns.EC_2700_Average.getColumnName()
						+ " =  '" + Float.toString(ec1400RowAverage) + "' ,"
						+ CalibrationECColumns.DoCalibrationEC_2700.getColumnName() + " = 0  WHERE " + primaryKey + "='"
						+ deviceId + "'";
				st.executeUpdate(quary2);
			}
		} catch (Exception ex) {
			System.err.println("error getting ec 2700 average raw");
		} finally {
			disconnect();
		}
		return ec1400RowAverage;
	}

	public static ECCalibration getECAverageRaw(String deviceId) {
		ECCalibration ecRowAverage = null;
		try {
			String query = "select * from " + tableName + " where " + CalibrationECColumns.DeviceId.getColumnName()
					+ "= '" + deviceId + "'";
			connect();
			rs = st.executeQuery(query);
			if (rs.next()) {
				ecRowAverage = new ECCalibration(
						rs.getFloat(CalibrationECColumns.EC_1400_2_Raw.getColumnName()),
						rs.getFloat(CalibrationECColumns.EC_1400_3_Raw.getColumnName()),
						rs.getFloat(CalibrationECColumns.EC_1400_Average.getColumnName()),
						rs.getFloat(CalibrationECColumns.EC_2700_2_Raw.getColumnName()),
						rs.getFloat(CalibrationECColumns.EC_2700_3_Raw.getColumnName()),
						rs.getFloat(CalibrationECColumns.EC_2700_Average.getColumnName()));
			}
		} catch (Exception ex) {
			System.err.println("error getting ec raws average ");
		} finally {
			disconnect();
		}
		return ecRowAverage;
	}

	public static void storeECCalibrationParameters(String deviceId, ECCalibration ecCalibration) {
		String query = "UPDATE " + tableName + " SET "
				+ CalibrationECColumns.A_EC.getColumnName() + " =  "
				+ Float.toString(ecCalibration.getA_ec()) + ","
				+ CalibrationECColumns.B_EC.getColumnName() + " = "
				+ Float.toString(ecCalibration.getB_ec())
				+ "  WHERE " + primaryKey + "='" + deviceId + "'";
		try {
			connect();
			st.executeUpdate(query);
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			disconnect();
		}
	}
	
	public static CalibrationData getCalibrationLastUpdate(String deviceId) {
		CalibrationData calibrationData = null;
		try {	
			String query =  "select calibration_ph.* ,calibration_ec.* from calibration_ph left join calibration_ec  on calibration_ph.device_id = calibration_ec.device_id where calibration_ph."+ primaryKey +  "='" + deviceId + "'";
			connect();
			rs = st.executeQuery(query);
			if (rs.next()) {
				calibrationData = new CalibrationData(
						rs.getString(CalibrationECColumns.LastECCalibration.getColumnName()),
						rs.getString(CalibrationPHColumns.LastPHCalibration.getColumnName()));
			}
		} catch (Exception ex) {
			System.err.println("error getting calibration data");
		} finally {
			disconnect();
		}
		return calibrationData;
	}
	
	public enum CalibrationECColumns {
		DeviceId("device_id"), DoCalibrationEC_1400("do_calibration_ec_1400"),
		EC_1400_1_Raw("1400_1_raw"), EC_1400_2_Raw("1400_2_raw"), EC_1400_3_Raw("1400_3_raw"), EC_1400_Average("1400_raw_average"), 
		DoCalibrationEC_2700("do_calibration_ec_2700"), EC_2700_1_Raw("2700_1_raw"), EC_2700_2_Raw("2700_2_raw"), 
		EC_2700_3_Raw("2700_3_raw"), EC_2700_Average("2700_raw_average"), A_EC("a_ec"), B_EC("b_ec"), C_EC("c_ec"), LastECCalibration("last_ec_calibration");

		private String column;

		CalibrationECColumns(String column) {
			this.column = column;
		}

		public String getColumnName() {
			return column;
		}
	}

}
