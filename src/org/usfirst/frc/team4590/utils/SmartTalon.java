package org.usfirst.frc.team4590.utils;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class SmartTalon extends TalonSRX {

	public SmartTalon(int deviceNumber) {
		super(deviceNumber);
	}
	
	private double m_lastValue = 0;

	public void set(double power) {
		m_lastValue = power;
		set(ControlMode.PercentOutput, power);
	}
	
	public double getLastValue(){ return m_lastValue;}
	
	
}