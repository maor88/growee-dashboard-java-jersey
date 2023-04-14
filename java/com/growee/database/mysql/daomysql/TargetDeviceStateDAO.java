package com.growee.database.mysql.daomysql;

import java.util.ArrayList;
import java.util.List;

import com.growee.database.mysql.QueryGenerator;
import com.growee.objects.devices.DeviceTargetState;
import com.growee.objects.modify.*;

/**
 * @author Maor
 */
public class TargetDeviceStateDAO extends DAOBaseClass {

    public static final String tableName = "device_data_target";
    public static final String primaryKey = "device_id";

    public static void setNewECBottelsMilliliterAndTarget(TargetPHBottelsAndEC targetPHBottelsAndEC, String deviceId) {
        String queryManual = "UPDATE " + tableName + " SET "
                + TargetDeviceData.TargetEC.getColumnName() + " ='"
                + targetPHBottelsAndEC.getTargetECAndPh().getTargetEC() + "' ,"
                + TargetDeviceData.RangeEC.getColumnName() + " ='"
                + targetPHBottelsAndEC.getTargetECAndPh().getEcRange() + "' ,"
                + TargetDeviceData.LastTargetEC.getColumnName() + " ='"
                + targetPHBottelsAndEC.getTargetECAndPh().getTargetEC() + "' ,"
                + TargetDeviceData.LastUpdateEC.getColumnName() + " = now() ,"
                + TargetDeviceData.PumpAml.getColumnName() + "='"
                + targetPHBottelsAndEC.getBottelsMililiters().getBottleA() + "', "
                + TargetDeviceData.PumpBml.getColumnName() + "='"
                + targetPHBottelsAndEC.getBottelsMililiters().getBottleB() + "', "
                + TargetDeviceData.PumpCml.getColumnName() + "='"
                + targetPHBottelsAndEC.getBottelsMililiters().getBottleC() + "',"
                + TargetDeviceData.PumpDml.getColumnName() + "='"
                + targetPHBottelsAndEC.getBottelsMililiters().getBottleD() + "', "
                + TargetDeviceData.PumpEml.getColumnName() + "='"
                + targetPHBottelsAndEC.getBottelsMililiters().getBottleE() + "', "
                + TargetDeviceData.PumpFml.getColumnName() + "='"
                + targetPHBottelsAndEC.getBottelsMililiters().getBottleF() + "',"
                + TargetDeviceData.PumpGml.getColumnName() + "='"
                + targetPHBottelsAndEC.getBottelsMililiters().getBottleG() + "', "
                + TargetDeviceData.PumpHml.getColumnName() + "='"
                + targetPHBottelsAndEC.getBottelsMililiters().getBottleH() + "',"
                + TargetDeviceData.LastPumpAml.getColumnName() + "='"
                + targetPHBottelsAndEC.getBottelsMililiters().getBottleA() + "', "
                + TargetDeviceData.LastPumpBml.getColumnName() + "='"
                + targetPHBottelsAndEC.getBottelsMililiters().getBottleB() + "', "
                + TargetDeviceData.LastPumpCml.getColumnName() + "='"
                + targetPHBottelsAndEC.getBottelsMililiters().getBottleC() + "', "
                + TargetDeviceData.LastPumpDml.getColumnName() + "='"
                + targetPHBottelsAndEC.getBottelsMililiters().getBottleD() + "', "
                + TargetDeviceData.LastPumpEml.getColumnName() + "='"
                + targetPHBottelsAndEC.getBottelsMililiters().getBottleE() + "', "
                + TargetDeviceData.LastPumpFml.getColumnName() + "='"
                + targetPHBottelsAndEC.getBottelsMililiters().getBottleF() + "', "
                + TargetDeviceData.LastPumpGml.getColumnName() + "='"
                + targetPHBottelsAndEC.getBottelsMililiters().getBottleG() + "', "
                + TargetDeviceData.LastPumpHml.getColumnName() + "='"
                + targetPHBottelsAndEC.getBottelsMililiters().getBottleH() + "'  WHERE " + primaryKey + "='"
                + deviceId + "'";

        String queryAuto = "UPDATE " + tableName + " SET "
                + TargetDeviceData.TargetEC.getColumnName() + " ='"
                + targetPHBottelsAndEC.getTargetECAndPh().getTargetEC() + "' ,"
                + TargetDeviceData.RangeEC.getColumnName() + " ='"
                + targetPHBottelsAndEC.getTargetECAndPh().getEcRange() + "' ,"
                + TargetDeviceData.LastTargetEC.getColumnName() + " ='"
                + targetPHBottelsAndEC.getTargetECAndPh().getTargetEC() + "' ,"
                + TargetDeviceData.LastUpdateEC.getColumnName() + " = now() ,"
                + TargetDeviceData.PumpAml.getColumnName() + "='"
                + targetPHBottelsAndEC.getBottelsMililiters().getBottleA() + "', "
                + TargetDeviceData.PumpBml.getColumnName() + "='"
                + targetPHBottelsAndEC.getBottelsMililiters().getBottleB() + "', "
                + TargetDeviceData.PumpCml.getColumnName() + "='"
                + targetPHBottelsAndEC.getBottelsMililiters().getBottleC() + "',"
                + TargetDeviceData.PumpDml.getColumnName() + "='"
                + targetPHBottelsAndEC.getBottelsMililiters().getBottleD() + "', "
                + TargetDeviceData.PumpEml.getColumnName() + "='"
                + targetPHBottelsAndEC.getBottelsMililiters().getBottleE() + "', "
                + TargetDeviceData.PumpFml.getColumnName() + "='"
                + targetPHBottelsAndEC.getBottelsMililiters().getBottleF() + "',"
                + TargetDeviceData.PumpGml.getColumnName() + "='"
                + targetPHBottelsAndEC.getBottelsMililiters().getBottleG() + "', "
                + TargetDeviceData.PumpHml.getColumnName() + "='"
                + targetPHBottelsAndEC.getBottelsMililiters().getBottleH() + "',"
                + TargetDeviceData.LastPumpAmlTarget.getColumnName() + "='"
                + targetPHBottelsAndEC.getBottelsMililiters().getBottleA_target() + "', "
                + TargetDeviceData.LastPumpBmlTarget.getColumnName() + "='"
                + targetPHBottelsAndEC.getBottelsMililiters().getBottleB_target() + "', "
                + TargetDeviceData.LastPumpCmlTarget.getColumnName() + "='"
                + targetPHBottelsAndEC.getBottelsMililiters().getBottleC_target() + "', "
                + TargetDeviceData.LastPumpDmlTarget.getColumnName() + "='"
                + targetPHBottelsAndEC.getBottelsMililiters().getBottleD_target() + "', "
                + TargetDeviceData.LastPumpEmlTarget.getColumnName() + "='"
                + targetPHBottelsAndEC.getBottelsMililiters().getBottleE_target() + "', "
                + TargetDeviceData.LastPumpFmlTarget.getColumnName() + "='"
                + targetPHBottelsAndEC.getBottelsMililiters().getBottleF_target() + "', "
                + TargetDeviceData.LastPumpGmlTarget.getColumnName() + "='"
                + targetPHBottelsAndEC.getBottelsMililiters().getBottleG_target() + "', "
                + TargetDeviceData.LastPumpHmlTarget.getColumnName() + "='"
                + targetPHBottelsAndEC.getBottelsMililiters().getBottleH_target() + "', "
                + TargetDeviceData.LastAmountPerLiter.getColumnName() + "='"
                + targetPHBottelsAndEC.getTargetECAndPh().getMilliliterPerLitter()
                + "'  WHERE " + primaryKey + "='"
                + deviceId + "'";

        try {
            connect();
            if (!targetPHBottelsAndEC.getTargetECAndPh().getTargetEC().equals("0.0")) {
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

    public static void setPHTargetFromUser(TargetPH targetPH, String deviceId) {
        String query = "UPDATE " + tableName + " SET " + TargetDeviceData.TargetPH.getColumnName() + " ='" + targetPH.getTargetPH() + "' ,"
                + TargetDeviceData.LastTargetPH.getColumnName() + " ='" + targetPH.getTargetPH() + "' ,"
                + TargetDeviceData.RangePH.getColumnName() + " ='" + targetPH.getRange() + "' ,"
                + TargetDeviceData.LastUpdatePH.getColumnName() + " = now()  WHERE " + primaryKey + "='" + deviceId
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

    public static void pausePHTarget(TargetPH targetPH, String deviceId) {
        String query = "UPDATE " + tableName + " SET " + TargetDeviceData.TargetPH.getColumnName() + " ='" + targetPH.getTargetPH() + "' ,"
                + TargetDeviceData.LastUpdatePH.getColumnName() + " = now()  WHERE " + primaryKey + "='" + deviceId
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

    public static List<DeviceTargetState> getAllDevicesTable() {
        List<DeviceTargetState> devicesTargetData = new ArrayList<DeviceTargetState>();
        try {
            connect();
            rs = st.executeQuery(QueryGenerator.selectAllTable(tableName));
            while (rs.next()) {
                devicesTargetData.add(new DeviceTargetState(rs.getString("user_id"), rs.getString("device_id"),
                        rs.getString("PH"), rs.getString("EC"), rs.getString("last_update")));
            }
        } catch (Exception ex) {
            System.err.println(ex);
        } finally {
            disconnect();
        }
        return devicesTargetData;
    }

    public static String getTargetPH(String deviceId) {
        String targetPH = null;
        try {
            connect();
            rs = st.executeQuery(QueryGenerator.selectRowByPrimatyKey(tableName, primaryKey, deviceId));
            if (!rs.wasNull()) {
                targetPH = rs.getString(TargetDeviceData.TargetPH.getColumnName());
            }
        } catch (Exception ex) {
            System.err.println(ex);
        } finally {
            disconnect();
        }
        return targetPH;
    }

    public static String getTargetEC(String deviceId) {
        String targetPH = null;
        try {
            connect();
            rs = st.executeQuery(QueryGenerator.selectRowByPrimatyKey(tableName, primaryKey, deviceId));
            if (!rs.wasNull()) {
                targetPH = rs.getString(TargetDeviceData.TargetEC.getColumnName());
            }
        } catch (Exception ex) {
            System.err.println(ex);
        } finally {
            disconnect();
        }
        return targetPH;
    }

    public static TargetECAndPH getTargetPHAndECValueAndLastTimeChange(String deviceId) {
        TargetECAndPH targetPHAndEC = new TargetECAndPH();
        try {
            connect();
            rs = st.executeQuery(QueryGenerator.selectRowByPrimatyKey(tableName, primaryKey, deviceId));
            rs.next();
            if (!rs.wasNull()) {
                targetPHAndEC.setTargetECAndPH(rs.getString(TargetDeviceData.TargetPH.getColumnName()),
                        rs.getString(TargetDeviceData.LastTargetPH.getColumnName()),
                        rs.getString(TargetDeviceData.RangePH.getColumnName()),
                        rs.getString(TargetDeviceData.TargetEC.getColumnName()),
                        rs.getString(TargetDeviceData.RangeEC.getColumnName()),
                        rs.getString(TargetDeviceData.LastUpdatePH.getColumnName()),
                        rs.getString(TargetDeviceData.LastUpdateEC.getColumnName()),
                        rs.getString(TargetDeviceData.LastAmountPerLiter.getColumnName())
                );
            }
        } catch (Exception ex) {
            System.err.println(ex);
        } finally {
            disconnect();
        }
        return targetPHAndEC;
    }

    public static void setWaterQuantity(String waterLiter, String userId) {
        String query = "UPDATE " + tableName + " SET water_quantity ='" + waterLiter + "' WHERE user_id='" + userId
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

    public static TargetECMililiters getLastModifyBottelsMililiters(String deviceId) {
        TargetECMililiters targetECMililiters = new TargetECMililiters();
        try {
            connect();
            rs = st.executeQuery(QueryGenerator.selectRowByPrimatyKey(tableName, primaryKey, deviceId));
            rs.next();
            if (!rs.wasNull()) {
                targetECMililiters.setBottelsMililiters(
                        rs.getString(TargetDeviceData.LastPumpAml.getColumnName()),
                        rs.getString(TargetDeviceData.LastPumpBml.getColumnName()),
                        rs.getString(TargetDeviceData.LastPumpCml.getColumnName()),
                        rs.getString(TargetDeviceData.LastPumpDml.getColumnName()),
                        rs.getString(TargetDeviceData.LastPumpEml.getColumnName()),
                        rs.getString(TargetDeviceData.LastPumpFml.getColumnName()),
                        rs.getString(TargetDeviceData.LastPumpGml.getColumnName()),
                        rs.getString(TargetDeviceData.LastPumpHml.getColumnName()),
                        rs.getString(TargetDeviceData.LastPumpAmlTarget.getColumnName()),
                        rs.getString(TargetDeviceData.LastPumpBmlTarget.getColumnName()),
                        rs.getString(TargetDeviceData.LastPumpCmlTarget.getColumnName()),
                        rs.getString(TargetDeviceData.LastPumpDmlTarget.getColumnName()),
                        rs.getString(TargetDeviceData.LastPumpEmlTarget.getColumnName()),
                        rs.getString(TargetDeviceData.LastPumpFmlTarget.getColumnName()),
                        rs.getString(TargetDeviceData.LastPumpGmlTarget.getColumnName()),
                        rs.getString(TargetDeviceData.LastPumpHmlTarget.getColumnName())
                );
            }
        } catch (Exception ex) {
            System.err.println(ex);
        } finally {
            disconnect();
        }
        return targetECMililiters;
    }

    public static void addNewDevice(String deviceId, String userId) {
        String query = " insert into " + tableName + "(" + TargetDeviceData.DeviceId.getColumnName() + ","
                + TargetDeviceData.UserId.getColumnName() + " ) values ('" + deviceId + "' , " + userId + ")";
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

    public static void stopTargetEC(String deviceId) {
        String query = "UPDATE " + tableName + " SET " + TargetDeviceData.TargetEC.getColumnName() + " ='0' ,"
                + TargetDeviceData.RangeEC.getColumnName() + " ='0' ,"
                + TargetDeviceData.LastUpdateEC.getColumnName() + " = now()  WHERE " + primaryKey + "='" + deviceId
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

    public static void forgetWifi(String deviceId) {
        String query = "UPDATE " + tableName + " SET " + TargetDeviceData.ForgetWifi.getColumnName() + " = 1  WHERE " + primaryKey + "='" + deviceId + "'";
        try {
            connect();
            st.executeUpdate(query);
        } catch (Exception ex) {
            System.err.println("forgetWifi error: " + ex);
        } finally {
            disconnect();
        }
    }


    public static void setFlushPumps(FlushPumps flushPumps, String deviceId) {
        String queryUpdate = "UPDATE " + tableName + " SET "
                + TargetDeviceData.FlushPumpA.getColumnName() + " = "+flushPumps.getPumps_A() + " , "
                + TargetDeviceData.FlushPumpB.getColumnName() + "= "+flushPumps.getPumps_B()+ " , "
                + TargetDeviceData.FlushPumpC.getColumnName() + "= "+flushPumps.getPumps_C() + " , "
                + TargetDeviceData.FlushPumpD.getColumnName() + "= "+flushPumps.getPumps_D() + " , "
                + TargetDeviceData.FlushPumpE.getColumnName() + " = "+flushPumps.getPumps_E() + " , "
                + TargetDeviceData.FlushPumpF.getColumnName() + "= "+flushPumps.getPumps_F()+ " , "
                + TargetDeviceData.FlushPumpG.getColumnName() + "= "+flushPumps.getPumps_G() + " , "
                + TargetDeviceData.FlushPumpH.getColumnName() + "= "+flushPumps.getPumps_H() + " , "
                + TargetDeviceData.FlushPump_pH_minus.getColumnName() + "= " + flushPumps.getPumps_pH_minus() + " , "
                + TargetDeviceData.FlushPump_pH_plus.getColumnName() + "= " + flushPumps.getPumps_pH_plus()
                + "  WHERE " + primaryKey + "='" +deviceId + "'";
        try {
            connect();
            st.executeUpdate(queryUpdate);
        } catch (Exception ex) {
            System.err.println("setFlushPumps error:" + ex);
        } finally {
            disconnect();
        }
    }



    public enum TargetDeviceData {
        DeviceId("device_id"), UserId("user_id"), TargetPH("T_PH"),LastTargetPH("last_T_PH"), RangePH("PH_range"), TargetEC("T_EC"), RangeEC("T_EC_range"),
        LastTargetEC("last_T_EC"), LastUpdateEC("last_update_ec"), LastUpdatePH("last_update_ph"), WaterQuantity("water_quantity"),
        IgnoreJson("ignore_json"), PumpAml("pump_1_ml"), PumpBml("pump_2_ml"), PumpCml("pump_3_ml"), PumpDml("pump_4_ml"),
        PumpEml("pump_5_ml"), PumpFml("pump_6_ml"),  PumpGml("pump_7_ml"), PumpHml("pump_8_ml"), LastPumpAml("last_pump_1_ml"),
        LastPumpBml("last_pump_2_ml"), LastPumpCml("last_pump_3_ml"), LastPumpDml("last_pump_4_ml"), LastPumpEml("last_pump_5_ml"),
        LastPumpFml("last_pump_6_ml"), LastPumpGml("last_pump_7_ml"), LastPumpHml("last_pump_8_ml"),
        FlushPumpA("flush_ec_a"), FlushPumpB("flush_ec_b"), FlushPumpC("flush_ec_c"),FlushPumpD("flush_ec_d"),
        FlushPumpE("flush_ec_e"), FlushPumpF("flush_ec_f"), FlushPumpG("flush_ec_g"),FlushPumpH("flush_ec_h"),
        FlushPump_pH_minus("flush_ph_down"), FlushPump_pH_plus("flush_ph_up"),
        LastPumpAmlTarget("last_pump_1_ml_target"), LastPumpBmlTarget("last_pump_2_ml_target"), LastPumpCmlTarget("last_pump_3_ml_target"),
        LastPumpDmlTarget("last_pump_4_ml_target"), LastPumpEmlTarget("last_pump_5_ml_target"), LastPumpFmlTarget("last_pump_6_ml_target"),
        LastPumpGmlTarget("last_pump_7_ml_target"), LastPumpHmlTarget("last_pump_8_ml_target"),
        LastAmountPerLiter("last_lamount_per_liter"), ForgetWifi("forget_wifi");

        private String column;

        TargetDeviceData(String column) {
            this.column = column;
        }

        public String getColumnName() {
            return column;
        }
    }

}
