package org.usfirst.frc.team4590.robot.commands.claw;

import org.usfirst.frc.team4590.robot.subsystems.Claw;

public class AcceleratedCloseClaw extends ClosingClawCommand {

	private static final long DEFAULT_DECEL_TIME = 500;
	private static final double MAX_VOLTAGE = 3;
	
	private long m_decelTime, m_startTime;
	
	public AcceleratedCloseClaw(long decelTime, long timeout) {
		super(timeout/1000d);
		requires(Claw.getInstance());
		m_decelTime = decelTime;
	}
	
	public AcceleratedCloseClaw(long decelTime) {
		requires(Claw.getInstance());
		m_decelTime = decelTime;
	}
	
	public AcceleratedCloseClaw() {
		this(DEFAULT_DECEL_TIME);
	}
	
	@Override
	protected void initialize() {
		m_startTime = System.currentTimeMillis();
	}
	
	@Override
	protected void executeCommand() {
		double timeSinceInitialized = System.currentTimeMillis() - m_startTime,
			   voltage;
		
		if (timeSinceInitialized < m_decelTime)
			voltage = (MAX_VOLTAGE - (timeSinceInitialized / m_decelTime) * (MAX_VOLTAGE - Claw.VOLTAGE_LIMIT));
		else
			voltage = Claw.VOLTAGE_LIMIT;
		
		Claw.getInstance().setVoltageCompensation(voltage);
		
		Claw.getInstance().setPower(1);
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}
	
	@Override
	protected void end() {
		Claw.getInstance().stop();
		Claw.getInstance().setVoltageCompensation(Claw.VOLTAGE_LIMIT);
	}
}