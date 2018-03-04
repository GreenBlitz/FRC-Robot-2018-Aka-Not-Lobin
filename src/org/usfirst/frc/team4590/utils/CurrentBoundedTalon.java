package org.usfirst.frc.team4590.utils;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.ControlMode;

/**
 * Making sure that the current passed to the engine cannot exceed a certain current
 * @author user
 */
public class CurrentBoundedTalon extends SmartTalon {
	public static final int MAX_CURRENT = 2;
	
	private final int m_maxCurrent;
	
	public CurrentBoundedTalon(int deviceNumber, int maxCurrent) {
		super(deviceNumber);
		m_maxCurrent = maxCurrent;
		ErrorCode peakLimitEC = configPeakCurrentLimit(maxCurrent, 10);
		ErrorCode durationLimitEC = configPeakCurrentDuration(0, 10);
		if (peakLimitEC != ErrorCode.OK) {
			System.err.println("talon '" + deviceNumber + "' has failed to config current peak limit");
		}
		if (durationLimitEC != ErrorCode.OK) {
			System.err.println("talon '" + deviceNumber + "' has failed to config current peak duration");
		}
		enableCurrentLimit(true);
	}
	
	public CurrentBoundedTalon(int deviceNumber) {
		this(deviceNumber, MAX_CURRENT);
	}
	
	public void setCurrent(double current) {
		set(ControlMode.Current, current);
	}

	public double getMaxCurrent() {
		return m_maxCurrent;
	}
}	