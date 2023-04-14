/**
 *
 */
package com.growee.database.mysql.daomysql;

import com.growee.database.mysql.QueryGenerator;
import com.growee.objects.modify.TargetPHBottelsAndEC;
import com.growee.objects.devices.DeviceGrowSettings;
import com.growee.objects.modify.TargetECBottelsNames;

/**
 * @author Maor
 */
public class DeviceGrowSettingsDAO extends DAOBaseClass {

    public static final String tableName = "device_grow_settings";
    public static final String primaryKey = "device_id";

    public static DeviceGrowSettings getDeviceSettingData(String deviceId) {
        String query = QueryGenerator.selectRowByPrimatyKey(tableName, primaryKey, deviceId);
        DeviceGrowSettings deviceGrowSettings = new DeviceGrowSettings();
        try {
            connect();
            rs = st.executeQuery(query);
            rs.next();
            if (!rs.wasNull()) {
                deviceGrowSettings.setDeviceGrowSettings(
                        rs.getString(DeviceGrowSettingsColumns.waterQuantity.getColumnName()),
                        rs.getString(DeviceGrowSettingsColumns.plantsNumber.getColumnName()),
                        rs.getString(DeviceGrowSettingsColumns.PlantsType.getColumnName()),
                        rs.getString(DeviceGrowSettingsColumns.NutrientsType.getColumnName()),
                        rs.getString(DeviceGrowSettingsColumns.StartGrowDate.getColumnName()));
            }
        } catch (Exception ex) {
            System.err.println(ex);
        } finally {
            disconnect();
        }
        return deviceGrowSettings;
    }

    public static int getDeviceWaterQuantity(String deviceId) {
        String query = QueryGenerator.selectRowByPrimatyKey(tableName, primaryKey, deviceId);
        int waterQuantity = 0;
        try {
            connect();
            rs = st.executeQuery(query);
            rs.next();
            if (!rs.wasNull()) {
                waterQuantity = rs.getInt(DeviceGrowSettingsColumns.waterQuantity.getColumnName());
            }
        } catch (Exception ex) {
            System.err.println(ex);
        } finally {
            disconnect();
        }
        return waterQuantity;
    }

    public static void setNewDeviceSettings(DeviceGrowSettings deviceGrowSettings, String deviceId) {

        String query = "UPDATE " + tableName + " SET " + DeviceGrowSettingsColumns.waterQuantity.getColumnName()
                + " = '" + deviceGrowSettings.getWater_quantity() + "' , "
                + DeviceGrowSettingsColumns.deviceName.getColumnName() + " ='" + deviceGrowSettings.getDevice_name()
                + "' ," + DeviceGrowSettingsColumns.plantsNumber.getColumnName() + " = '"
                + deviceGrowSettings.getPlants_number() + "' ," + DeviceGrowSettingsColumns.PlantsType.getColumnName()
                + " = '" + deviceGrowSettings.getPlants_type() + "' ,"
                + DeviceGrowSettingsColumns.StartGrowDate.getColumnName() + " = now() ,"
                + DeviceGrowSettingsColumns.NutrientsType.getColumnName() + " = '"
                + deviceGrowSettings.getNutrients_type() + "'," + DeviceGrowSettingsColumns.IsInitilized.getColumnName()
                + " = " + 1 + " WHERE " + primaryKey + "= '" + deviceId + "'";
        try {
            connect();
            st.executeUpdate(query);
        } catch (Exception ex) {
            System.err.println(ex);
        } finally {
            disconnect();
        }
    }

    public static void updateDeviceSettings(DeviceGrowSettings deviceGrowSettings, String deviceId) {
        String addTimesStampCommand = "timestampadd(SQL_TSI_DAY , " + deviceGrowSettings.getDaysToAdd() + ", '" + deviceGrowSettings.getStart_grow_date() + "')";
        String query = "UPDATE " + tableName + " SET " + DeviceGrowSettingsColumns.waterQuantity.getColumnName()
                + " = '" + deviceGrowSettings.getWater_quantity() + "' , "
                + DeviceGrowSettingsColumns.deviceName.getColumnName() + " ='" + deviceGrowSettings.getDevice_name()
                + "' ," + DeviceGrowSettingsColumns.plantsNumber.getColumnName() + " = '"
                + deviceGrowSettings.getPlants_number() + "' ," + DeviceGrowSettingsColumns.PlantsType.getColumnName()
                + " = '" + deviceGrowSettings.getPlants_type() + "' ,"
                + DeviceGrowSettingsColumns.LastChangeTime.getColumnName() + " = now() ,"
                + DeviceGrowSettingsColumns.StartGrowDate.getColumnName() + " = " + addTimesStampCommand + " , "
                + DeviceGrowSettingsColumns.NutrientsType.getColumnName() + " = '"
                + deviceGrowSettings.getNutrients_type() + "'," + DeviceGrowSettingsColumns.IsInitilized.getColumnName()
                + " = " + 1 + " WHERE " + primaryKey + "= '" + deviceId + "'";
        try {
            connect();
            st.executeUpdate(query);
        } catch (Exception ex) {
            System.err.println(ex);
        } finally {
            disconnect();
        }
    }

    public static void setBottelsNames(TargetPHBottelsAndEC targetEC, String deviceId) {
        String queryManual = "UPDATE " + tableName + " SET "
                + DeviceGrowSettingsColumns.BottleAName.getColumnName() + " = '"
                + targetEC.getBottelsNames().getBottleAName() + "' , "
                + DeviceGrowSettingsColumns.BottleBName.getColumnName() + " = '"
                + targetEC.getBottelsNames().getBottleBName() + "' ,"
                + DeviceGrowSettingsColumns.BottleCName.getColumnName() + " = '"
                + targetEC.getBottelsNames().getBottleCName()  + " = '"
                + DeviceGrowSettingsColumns.BottleDName.getColumnName() + " = '"
                + targetEC.getBottelsNames().getBottleAName() + "' , "
                + DeviceGrowSettingsColumns.BottleEName.getColumnName() + " = '"
                + targetEC.getBottelsNames().getBottleBName() + "' ,"
                + DeviceGrowSettingsColumns.BottleFName.getColumnName() + " = '"
                + targetEC.getBottelsNames().getBottleCName() + "' ,"
                + DeviceGrowSettingsColumns.BottleGName.getColumnName() + " = '"
                + targetEC.getBottelsNames().getBottleBName() + "' ,"
                + DeviceGrowSettingsColumns.BottleHName.getColumnName() + " = '"
                + targetEC.getBottelsNames().getBottleCName()
                + "' WHERE " + primaryKey + "= '" + deviceId + "'";

        String queryAuto = "UPDATE " + tableName + " SET "
                + DeviceGrowSettingsColumns.BottleANameTarget.getColumnName() + " = '"
                + targetEC.getBottelsNames().getBottleAName() + "' , "
                + DeviceGrowSettingsColumns.BottleBNameTarget.getColumnName() + " = '"
                + targetEC.getBottelsNames().getBottleBName() + "' ,"
                + DeviceGrowSettingsColumns.BottleCNameTarget.getColumnName() + " = '"
                + targetEC.getBottelsNames().getBottleCName() + "' ,"
                + DeviceGrowSettingsColumns.BottleDNameTarget.getColumnName() + " = '"
                + targetEC.getBottelsNames().getBottleDName() + "' , "
                + DeviceGrowSettingsColumns.BottleENameTarget.getColumnName() + " = '"
                + targetEC.getBottelsNames().getBottleEName() + "' ,"
                + DeviceGrowSettingsColumns.BottleFNameTarget.getColumnName() + " = '"
                + targetEC.getBottelsNames().getBottleFName() + "' ,"
                + DeviceGrowSettingsColumns.BottleGNameTarget.getColumnName() + " = '"
                + targetEC.getBottelsNames().getBottleGName() + "' ,"
                + DeviceGrowSettingsColumns.BottleHNameTarget.getColumnName() + " = '"
                + targetEC.getBottelsNames().getBottleHName()
                + "' WHERE " + primaryKey + "= '" + deviceId + "'";

        try {
            connect();
            if (!targetEC.getTargetECAndPh().getTargetEC().equals("0.0")) {
                st.executeUpdate(queryAuto);
            } else {
                st.executeUpdate(queryManual);
            }
        } catch (Exception ex) {
            System.err.println(ex);
        } finally {
            disconnect();
        }
    }

    public static TargetECBottelsNames getTargetBottlesNamesAndWaterQuantity(String deviceId) {
        String query = "select * from " + tableName + " where " + primaryKey + "='" + deviceId + "'";
        TargetECBottelsNames bottelsNameas = new TargetECBottelsNames();
        try {
            connect();
            rs = st.executeQuery(query);
            rs.next();
            if (!rs.wasNull()) {
                bottelsNameas.setBottelsName(
                        rs.getString(DeviceGrowSettingsColumns.BottleAName.getColumnName()),
                        rs.getString(DeviceGrowSettingsColumns.BottleBName.getColumnName()),
                        rs.getString(DeviceGrowSettingsColumns.BottleCName.getColumnName()),
                        rs.getString(DeviceGrowSettingsColumns.BottleDName.getColumnName()),
                        rs.getString(DeviceGrowSettingsColumns.BottleEName.getColumnName()),
                        rs.getString(DeviceGrowSettingsColumns.BottleFName.getColumnName()),
                        rs.getString(DeviceGrowSettingsColumns.BottleGName.getColumnName()),
                        rs.getString(DeviceGrowSettingsColumns.BottleHName.getColumnName()),
                        rs.getString(DeviceGrowSettingsColumns.BottleANameTarget.getColumnName()),
                        rs.getString(DeviceGrowSettingsColumns.BottleBNameTarget.getColumnName()),
                        rs.getString(DeviceGrowSettingsColumns.BottleCNameTarget.getColumnName()),
                        rs.getString(DeviceGrowSettingsColumns.BottleDNameTarget.getColumnName()),
                        rs.getString(DeviceGrowSettingsColumns.BottleENameTarget.getColumnName()),
                        rs.getString(DeviceGrowSettingsColumns.BottleFNameTarget.getColumnName()),
                        rs.getString(DeviceGrowSettingsColumns.BottleGNameTarget.getColumnName()),
                        rs.getString(DeviceGrowSettingsColumns.BottleHNameTarget.getColumnName()),
                        rs.getString(DeviceGrowSettingsColumns.waterQuantity.getColumnName()),
                        rs.getString(DeviceGrowSettingsColumns.NutrientsType.getColumnName()));
            }
        } catch (Exception ex) {
            System.err.println("error getting bottels names");
        } finally {
            disconnect();
        }
        return bottelsNameas;
    }

    public static boolean isDeviceInitilized(String deviceId) {
        String query = "select * from " + tableName + " where " + primaryKey + "='" + deviceId + "'";
        boolean res = false;
        try {
            connect();
            rs = st.executeQuery(query);
            rs.next();
            if (!rs.wasNull()) {
                res = rs.getBoolean(DeviceGrowSettingsColumns.IsInitilized.getColumnName());
            }
        } catch (Exception ex) {
            System.err.println("error to know if deviceInitilized");
        } finally {
            disconnect();
        }
        return res;
    }

    public static void addNewDevice(String deviceId, String userId) {
        String query = " insert into " + tableName + "(" + DeviceGrowSettingsColumns.DeviceId.getColumnName() + ","
                + DeviceGrowSettingsColumns.UserId.getColumnName() + " ) values ('" + deviceId + "' , '" + userId
                + "')";
        try {
            connect();
            st.executeUpdate(query);
        } catch (

                Exception ex) {
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

    public enum DeviceGrowSettingsColumns {
        DeviceId("device_id"), UserId("user_id"), waterQuantity("water_quantity"), deviceName("device_name"),
        plantsNumber("plants_number"), WaterType("water_type"), PlantsType("plants_type"),
        StartGrowDate("start_grow_date"),
        NutrientsType("nutrients_type"),
        BottleAName("bottle_a_name"), BottleBName("bottle_b_name"), BottleCName("bottle_c_name"),
        BottleDName("bottle_d_name"), BottleEName("bottle_e_name"), BottleFName("bottle_f_name"),
        BottleGName("bottle_g_name"), BottleHName("bottle_h_name"),
        BottleANameTarget("bottle_a_name_target"),  BottleBNameTarget("bottle_b_name_target"), BottleCNameTarget("bottle_c_name_target"),
        BottleDNameTarget("bottle_d_name_target"),  BottleENameTarget("bottle_e_name_target"), BottleFNameTarget("bottle_f_name_target"),
        BottleGNameTarget("bottle_g_name_target"),  BottleHNameTarget("bottle_h_name_target"),
        IsInitilized("is_initilized"), LastChangeTime("last_change_time");

        private String column;

        DeviceGrowSettingsColumns(String column) {
            this.column = column;
        }

        public String getColumnName() {
            return column;
        }
    }

}
