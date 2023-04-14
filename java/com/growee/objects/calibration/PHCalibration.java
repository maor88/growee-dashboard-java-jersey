package com.growee.objects.calibration;

public class PHCalibration {

	static final float PH_4 = 4;
	static final float PH_7 = 7;

	float a_ph;
	float b_ph;
	float ph_4_2_raw;
	float ph_4_3_raw;
	float ph_4_raw_average;
	float ph_7_2_raw;
	float ph_7_3_raw;
	float ph_7_raw_average;

	public PHCalibration(float ph_4_2_raw, float ph_4_3_raw, float ph_4_raw_average, float ph_7_2_raw, float ph_7_3_raw,
			float ph_7_raw_average) {
		this.ph_4_2_raw = ph_4_2_raw;
		this.ph_4_3_raw = ph_4_3_raw;
		this.ph_4_raw_average = ph_4_raw_average;
		this.ph_7_2_raw = ph_7_2_raw;
		this.ph_7_3_raw = ph_7_3_raw;
		this.ph_7_raw_average = ph_7_raw_average;
		calculateParameters(ph_4_raw_average, ph_7_raw_average);
	}

	private void calculateParameters(float ph_4_raw_average, float ph_7_raw_average) {
		this.a_ph = (PH_4 - PH_7) / (ph_4_raw_average - ph_7_raw_average);
		this.b_ph = PH_4 - (a_ph * ph_4_raw_average);
	}

	public float getA_ph() {
		return a_ph;
	}

	public void setA_ph(float a_ph) {
		this.a_ph = a_ph;
	}

	public float getB_ph() {
		return b_ph;
	}

	public void setB_ph(float b_ph) {
		this.b_ph = b_ph;
	}

}
