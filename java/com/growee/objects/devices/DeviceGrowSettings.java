/**
 * 
 */
package com.growee.objects.devices;

/**
 * @author Maor
 *
 */
public class DeviceGrowSettings {

	String device_id;
	String user_id;
	String water_quantity;
	String plants_number;
	String plants_type;
	String nutrients_type;
	String start_grow_date;
	String device_name;
	String daysToAdd;
	
	
	public void setDeviceGrowSettings(String waterQuantity, String plantsNumber , String plantsType ,String nutrientsType, String growStartDate) {
		this.water_quantity = waterQuantity;
		this.plants_number = plantsNumber;
		this.plants_type = plantsType;
		this.nutrients_type = nutrientsType;
		this.start_grow_date = growStartDate;
	}
	
	
	
	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getWater_quantity() {
		return water_quantity;
	}

	public void setWater_quantity(String water_quantity) {
		this.water_quantity = water_quantity;
	}

	public String getPlants_number() {
		return plants_number;
	}

	public void setPlants_number(String plants_number) {
		this.plants_number = plants_number;
	}

	public String getPlants_type() {
		return plants_type;
	}

	public void setPlants_type(String plants_type) {
		this.plants_type = plants_type;
	}

	public String getNutrients_type() {
		return nutrients_type;
	}

	public void setNutrients_type(String nutrients_type) {
		this.nutrients_type = nutrients_type;
	}

		public String getStart_grow_date() {
		return start_grow_date;
	}


	public void setStart_grow_date(String start_grow_date) {
		this.start_grow_date = start_grow_date;
	}


	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public String getDaysToAdd() {
		return daysToAdd;
	}

	public void setDaysToAdd(String daysToAdd) {
		this.daysToAdd = daysToAdd;
	}

	
}
