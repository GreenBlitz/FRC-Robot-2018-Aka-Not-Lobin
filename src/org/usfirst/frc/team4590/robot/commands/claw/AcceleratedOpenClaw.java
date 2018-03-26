package org.usfirst.frc.team4590.robot.commands.claw;

import org.usfirst.frc.team4590.robot.subsystems.Claw;

import edu.wpi.first.wpilibj.command.Scheduler;

public class AcceleratedOpenClaw extends ClosingClawCommand {

	private static final long DEFAULT_DECEL_TIME = 500,
							  DEFAULT_TIMEOUT = 1500;
	private static final double MAX_VOLTAGE = 3;
	
	private long m_decelTime, m_startTime, m_timeout;
	private boolean m_shouldHold;
	
	public AcceleratedOpenClaw(long decelTime, long timeout, boolean shouldHold) {
		m_timeout = timeout;
		requires(Claw.getInstance());
		m_decelTime = decelTime;
		m_shouldHold = shouldHold;
	}
	
	public AcceleratedOpenClaw() {
		this(DEFAULT_DECEL_TIME, DEFAULT_TIMEOUT, false);
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
		
		Claw.getInstance().setPower(-1);
	}

	@Override
	protected boolean isFinished() {
		return Claw.getInstance().isOpen() || isTimedOut();
	}
	
	@Override
	protected void end() {
		Claw.getInstance().stop();
		Claw.getInstance().setVoltageCompensation(Claw.VOLTAGE_LIMIT);
		if(m_shouldHold) 
			Scheduler.getInstance().add(new HoldCube());
	}
}