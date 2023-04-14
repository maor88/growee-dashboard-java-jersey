/**
 * 
 */
package com.growee.objects.modify;

import com.growee.objects.calibration.CalibrationData;

/**
 * @author Maor
 *
 */
public class TargetAndCurrentLevel {

	CurrentPHAndEC currentPhAndEC;
	TargetPHBottelsAndEC targetPHBottelsAndEC;
	CalibrationData calibrationData;
	
	
	public TargetAndCurrentLevel(CurrentPHAndEC currentPhAndEC, TargetPHBottelsAndEC  targetPHBottelsAndEC, CalibrationData calibrationData) {
		this.currentPhAndEC = currentPhAndEC;
		this.targetPHBottelsAndEC = targetPHBottelsAndEC;
		this.calibrationData = calibrationData;
	}
	
	public CurrentPHAndEC getCurrentPhAndEC() {
		return currentPhAndEC;
	}
	public void setCurrentPhAndEC(CurrentPHAndEC currentPhAndEC) {
		this.currentPhAndEC = currentPhAndEC;
	}
	public TargetPHBottelsAndEC getTargetEC() {
		return targetPHBottelsAndEC;
	}
	public void setTargetEC(TargetPHBottelsAndEC targetEC) {
		this.targetPHBottelsAndEC = targetEC;
	}

	
}
