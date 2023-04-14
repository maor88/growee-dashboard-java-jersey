package com.growee.objects.registarion;

public class RegistarionCode {

	boolean isRegistrationCodeValid = false;
	boolean isRegistarionCodeInUse = false;
	boolean isUserAlreadyRegistered = false;
	boolean registrationSuccees = false;

	public RegistarionCode() {}
	
	public boolean isValidRegistrationCode() {
		return isRegistrationCodeValid;
	}

	public void setValidRegistrationCode(boolean isValidRegistrationCode) {
		this.isRegistrationCodeValid = isValidRegistrationCode;
	}

	public boolean isRegistarionCodeInUse() {
		return isRegistarionCodeInUse;
	}

	public void setRegistarionCodeInUse(boolean isRegistarionCodeInUse) {
		this.isRegistarionCodeInUse = isRegistarionCodeInUse;
	}

	public boolean isUserAllreadyRegister() {
		return isUserAlreadyRegistered;
	}

	public void setUserAlreadyRegistered(boolean isUserAllreadyRegister) {
		this.isUserAlreadyRegistered = isUserAllreadyRegister;
	}

	public boolean isRegistrationSuccees() {
		return registrationSuccees;
	}

	public void setRegistrationSuccees(boolean registrationSuccees) {
		this.registrationSuccees = registrationSuccees;
	}

	
	
}
