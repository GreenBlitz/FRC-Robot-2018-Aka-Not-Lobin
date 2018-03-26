package org.usfirst.frc.team4590.utils.CTRE;

public class ControlledMotorAccelerator {
	
	private SmartTalon m_talon;
	
	private double m_powerTarget;
	
	
	private MotorAccelerationFunction m_function;
	
	public ControlledMotorAccelerator(SmartTalon motor, MotorAccelerationFunction function){
		m_talon = motor;
		m_function = function;
	}
	
	public void run(){
		double lastValue = m_talon.getLastValue();
		double accel = m_function.getAcceleration(lastValue, m_powerTarget);
		m_talon.set(accel + lastValue);
	}
	
	public boolean isOnTarget(){
		return m_powerTarget == m_talon.getLastValue();
	}

	public void setTarget(double target) {
		m_powerTarget = target;
	}
}
