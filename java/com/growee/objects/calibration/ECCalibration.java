package com.growee.objects.calibration;

public class ECCalibration {

    static final float EC_1400 = 1.413f;
    static final float EC_2700 = 2.764f;

    float a_ec;
    float b_ec;
    float ec_1400_2_raw;
    float ec_1400_3_raw;
    float ec_1400_raw_average;
    float ec_2700_2_raw;
    float ec_2700_3_raw;
    float ec_2700_raw_average;

    public ECCalibration(float ec_1400_2_raw,
                         float ec_1400_3_raw, float ec_1400_raw_average, float ec_2700_2_raw, float ec_2700_3_raw,
                         float ec_2700_raw_average) {
        this.ec_1400_2_raw = ec_1400_2_raw;
        this.ec_1400_3_raw = ec_1400_3_raw;
        this.ec_1400_raw_average = ec_1400_raw_average;
        this.ec_2700_2_raw = ec_2700_2_raw;
        this.ec_2700_3_raw = ec_2700_3_raw;
        this.ec_2700_raw_average = ec_2700_raw_average;
        calculateParameters(ec_1400_raw_average, ec_2700_raw_average);
    }

    private void calculateParameters(float ec_1400_raw_average, float ec_2700_raw_average) {
        this.a_ec = (EC_1400 - EC_2700) / (ec_1400_raw_average - ec_2700_raw_average);
        this.b_ec = EC_1400 - (a_ec * ec_1400_raw_average);
    }


    public float getA_ec() {
        return a_ec;
    }

    public float getB_ec() {
        return b_ec;
    }


}
