package org.usfirst.frc.team4590.utils;

import org.usfirst.frc.team4590.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public enum ShifterState {
	SPEED(RobotMap.SHIFTER_SPEED_VALUE),
	POWER(RobotMap.SHIFTER_POWER_VALUE),
	OFF(DoubleSolenoid.Value.kOff);
	
	private DoubleSolenoid.Value value;
	
	private ShifterState(DoubleSolenoid.Value _value) {
		value = _value;
	}
	
	public DoubleSolenoid.Value getValue() {
		return value;
	}
}
