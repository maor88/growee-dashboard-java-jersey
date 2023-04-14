package com.growee.objects.devices;

import com.growee.objects.modify.TargetECAndPH;

public class DeviceCurrentState {
    String PH;
    String EC;
    String phRange;
    String ecRange;
    String targetEC;
    String targetPH;
    String water_tmp;
    String humidity;
    String air_tmp;
    String device_id;
    String time_work_pump1;
    String time_work_pump2;
    String time_work_pump3;
    String time_work_pump4;
    String time_work_pump5;
    String lastUpdate;
    boolean isTargetECOn = false;
    boolean isTargetPHOn = false;
    float waterLiterLevel;
    boolean is_ph_module_connected = false;
    boolean is_ec_module_connected = false;
    int ec_pumps_connected = 0;

    public DeviceCurrentState() {
    }

    public DeviceCurrentState(String deviceId, String water_tmp, String PH, String EC, String humidity,
                              String air_tmp, String time_work_pump1, String time_work_pump2, String time_work_pump3, String time_work_pump4, String time_work_pump5) {
        this.device_id = deviceId;
        this.water_tmp = water_tmp;
        this.PH = PH;
        this.EC = EC;
        this.humidity = humidity;
        this.air_tmp = air_tmp;
        this.time_work_pump1 = time_work_pump1;
        this.time_work_pump2 = time_work_pump2;
        this.time_work_pump3 = time_work_pump3;
        this.time_work_pump4 = time_work_pump4;
        this.time_work_pump5 = time_work_pump5;

    }

    public boolean is_ph_module_connected() {
        return is_ph_module_connected;
    }

    public void set_ph_module_connected(boolean is_ph_module_connected) {
        this.is_ph_module_connected = is_ph_module_connected;
    }

    public boolean is_ec_module_connected() {
        return is_ec_module_connected;
    }

    public void set_ec_module_connected(boolean is_ec_module_connected) {
        this.is_ec_module_connected = is_ec_module_connected;
    }

    public void setCurrentDeviceDataWithTimeStemp(String deviceId, String PH, String EC, String humidity,
                                                  String airTmp, String waterTmp, String lastUpdate, float waterLiterLevel
            , boolean is_ph_module_connected, boolean is_ec_module_connected, int ec_pumps_connected) {
        this.device_id = deviceId;
        this.water_tmp = waterTmp;
        this.PH = PH;
        this.EC = EC;
        this.humidity = humidity;
        this.air_tmp = airTmp;
        this.lastUpdate = lastUpdate;
        this.waterLiterLevel = waterLiterLevel;
        this.is_ph_module_connected = is_ph_module_connected;
        this.is_ec_module_connected = is_ec_module_connected;
        this.ec_pumps_connected = ec_pumps_connected;
    }


    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getPH() {
        return PH;
    }

    public void setPH(String pH) {
        PH = pH;
    }

    public String getEC() {
        return EC;
    }

    public void setEC(String eC) {
        EC = eC;
    }

    public String getWater_tmp() {
        return water_tmp;
    }

    public void setWater_tmp(String water_tmp) {
        this.water_tmp = water_tmp;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getAir_tmp() {
        return air_tmp;
    }

    public void setAir_tmp(String air_tmp) {
        this.air_tmp = air_tmp;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_Id) {
        this.device_id = device_Id;
    }

    public String getTime_work_pump1() {
        return time_work_pump1;
    }

    public void setTime_work_pump1(String time_work_pump1) {
        this.time_work_pump1 = time_work_pump1;
    }

    public String getTime_work_pump2() {
        return time_work_pump2;
    }

    public void setTime_work_pump2(String time_work_pump2) {
        this.time_work_pump2 = time_work_pump2;
    }

    public String getTime_work_pump3() {
        return time_work_pump3;
    }

    public void setTime_work_pump3(String time_work_pump3) {
        this.time_work_pump3 = time_work_pump3;
    }

    public String getTime_work_pump4() {
        return time_work_pump4;
    }

    public void setTime_work_pump4(String time_work_pump4) {
        this.time_work_pump4 = time_work_pump4;
    }

    public String getTime_work_pump5() {
        return time_work_pump5;
    }

    public void setTime_work_pump5(String time_work_pump5) {
        this.time_work_pump5 = time_work_pump5;
    }

    public int getEc_pumps_connected() {
        return ec_pumps_connected;
    }


    public void isTargetsProgramOn(TargetECAndPH targetEcAndPh) {
        if (Float.parseFloat(targetEcAndPh.getTargetEC()) > 0) {
            this.targetEC = targetEcAndPh.getTargetEC();
            this.ecRange = targetEcAndPh.getEcRange();
        }
        if (Float.parseFloat(targetEcAndPh.getTargetPH()) > 0) {
            this.targetPH = targetEcAndPh.getTargetPH();
            this.phRange = targetEcAndPh.getPhRange();
        }
        isTargetECOn = Float.parseFloat(targetEcAndPh.getTargetEC()) > 0;
        isTargetPHOn = Float.parseFloat(targetEcAndPh.getTargetPH()) > 0;

    }

    @Override
    public String toString() {
        return String.format("DeviceId:%s, PH:%s, EC:%s, waterTemperature:%s, humidity:%s, airTemperature:%s",
                device_id, PH, EC, water_tmp, humidity, air_tmp);
    }


}
