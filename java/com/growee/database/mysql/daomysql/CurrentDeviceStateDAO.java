package com.growee.database.mysql.daomysql;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.growee.database.mysql.QueryGenerator;
import com.growee.objects.devices.DeviceCurrentState;

/**
 * @author Maor
 */

public class CurrentDeviceStateDAO extends DAOBaseClass {

    public static final String tableName = "device_current_state";
    public static final String primaryKey = "device_id";

    public static DeviceCurrentState getDeviceCurrentData(String userId, String deviceId) {
        DeviceCurrentState deviceCurrentData = new DeviceCurrentState();
        String querty = "select * from " + tableName + " where " + primaryKey + "= '" + deviceId + "'";
        try {
            connect();
            rs = st.executeQuery(querty);
            rs.next();
            if (!rs.wasNull()) {
                deviceCurrentData.setCurrentDeviceDataWithTimeStemp(
                        rs.getString(CurrenStateColumns.DeviceId.getColumnName()),
                        rs.getString(CurrenStateColumns.PH.getColumnName()),
                        rs.getString(CurrenStateColumns.EC.getColumnName()),
                        rs.getString(CurrenStateColumns.Humidity.getColumnName()),
                        rs.getString(CurrenStateColumns.AirTmp.getColumnName()),
                        rs.getString(CurrenStateColumns.WaterTmp.getColumnName()),
                        rs.getString(CurrenStateColumns.LastUpdate.getColumnName()),
                        rs.getFloat(CurrenStateColumns.WaterLiterLevel.getColumnName()),
                        rs.getBoolean(CurrenStateColumns.PH_Module_Connected.getColumnName()),
                        rs.getBoolean(CurrenStateColumns.EC_Module_Connected.getColumnName()),
                        rs.getInt(CurrenStateColumns.EC_Pumps_Connected.getColumnName())

                );

            }
        } catch (Exception ex) {
            System.err.println(ex);
        } finally {
            disconnect();
        }
        return deviceCurrentData;
    }

    public static List<DeviceCurrentState> getAllDevicesTable() {
        List<DeviceCurrentState> devicesCurrentData = new ArrayList<DeviceCurrentState>();
        DeviceCurrentState deviceCurrentData = new DeviceCurrentState();
        try {
            connect();
            rs = st.executeQuery(QueryGenerator.selectAllTable(tableName));
            while (rs.next()) {
                deviceCurrentData.setCurrentDeviceDataWithTimeStemp(
                        rs.getString(CurrenStateColumns.DeviceId.getColumnName()),
                        rs.getString(CurrenStateColumns.PH.getColumnName()),
                        rs.getString(CurrenStateColumns.EC.getColumnName()),
                        rs.getString(CurrenStateColumns.Humidity.getColumnName()),
                        rs.getString(CurrenStateColumns.AirTmp.getColumnName()),
                        rs.getString(CurrenStateColumns.WaterTmp.getColumnName()),
                        rs.getString(CurrenStateColumns.LastUpdate.getColumnName()),
                        rs.getFloat(CurrenStateColumns.WaterLiterLevel.getColumnName()),
                        rs.getBoolean(CurrenStateColumns.PH_Module_Connected.getColumnName()),
                        rs.getBoolean(CurrenStateColumns.EC_Module_Connected.getColumnName()),
                        rs.getInt(CurrenStateColumns.EC_Pumps_Connected.getColumnName())
                );
                devicesCurrentData.add(deviceCurrentData);
            }
        } catch (Exception ex) {
            System.err.println(ex);
        } finally {
            disconnect();
        }
        return devicesCurrentData;
    }

    public static Timestamp getLastUpdateTimeStamp(String deviceId) {
        String time = null;
        Timestamp timeStamp = null;
        try {
            connect();
            rs = st.executeQuery(QueryGenerator.selectRowByPrimatyKey(tableName, primaryKey, deviceId));
            rs.next();
            if (!rs.wasNull()) {
                time = rs.getString(CurrenStateColumns.LastUpdate.getColumnName());
                timeStamp = Timestamp.valueOf(time);
            }
        } catch (Exception ex) {
            System.err.println(ex);
        } finally {
            disconnect();
        }
        return timeStamp;
    }

    public static void addNewDevice(String deviceId, String userId) {
        String query = " insert into " + tableName + "(" + CurrenStateColumns.DeviceId.getColumnName() + ","
                + CurrenStateColumns.UserId.getColumnName() + " ) values ('" + deviceId + "' , " + userId + ")";
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
        String query = "DELETE FROM " + tableName + " WHERE " + primaryKey + "='" + deviceId + "'";
        try {
            connect();
            st.executeUpdate(query);
        } catch (Exception ex) {
            System.err.println(ex);
        } finally {
            disconnect();
        }
    }


    public enum CurrenStateColumns {
        DeviceId("device_id"), UserId("user_id"), WaterTmp("water_tmp"), PH("PH"), EC("EC"), Humidity(
                "humidity"), AirTmp("air_tmp"), LastUpdate("last_update"), TimeWorkPump1(
                "time_work_pump1"), TimeWorkPump2("time_work_pump2"), TimeWorkPump3(
                "time_work_pump3"), TimeWorkPump4("time_work_pump4"), TimeWorkPump5("time_work_pump5"),
        WaterLiterLevel("water_liter_level"), EC_Module_Connected("is_ec_module_connected"),
        PH_Module_Connected("is_ph_module_connected"),
        EC_Pumps_Connected("ec_pumps_connected");

        private String column;

        CurrenStateColumns(String column) {
            this.column = column;
        }

        public String getColumnName() {
            return column;
        }
    }


}
