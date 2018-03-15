package org.usfirst.frc.team4590.utils;

public class VoltageBoundedTalon extends SmartTalon {

	public VoltageBoundedTalon(int deviceNumber, double maxVoltage) {
		super(deviceNumber);
		enableVoltageCompensation(true);
		configVoltageCompSaturation(maxVoltage, 10);
	}
}