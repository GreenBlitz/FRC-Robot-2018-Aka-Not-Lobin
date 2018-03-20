package org.usfirst.frc.team4590.utils;

import org.usfirst.frc.team4590.robot.RobotMap;

public enum PitcherState {
	COLLECT(RobotMap.PITCHER_COLLECT_VALUE),
	SWITCH_FORWARD(RobotMap.PITCHER_SWITCH_FORWARD_VALUE),
	SWITCH_BACKWARD(RobotMap.PITCHER_SWITCH_BACKWARD_VALUE),
	PLATE(RobotMap.PITCHER_PLATE_VALUE),
	EXCHANGE(RobotMap.PITCHER_EXCHANGE_VALUE);
	
	private double m_position;
	private PitcherState(double degrees) {
		m_position = degrees;
	}
	
	public double getPosition() {
		return m_position;
	}
	
	public double getAngle() {
		return getPosition() * 180;
	}
}
