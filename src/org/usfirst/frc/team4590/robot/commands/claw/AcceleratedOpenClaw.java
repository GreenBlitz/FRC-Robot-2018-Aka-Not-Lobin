package org.usfirst.frc.team4590.robot.commands.claw;

import org.usfirst.frc.team4590.robot.subsystems.Claw;

public class AcceleratedOpenClaw extends ClosingClawCommand {

	private static final long DEFAULT_TIMEOUT = 1500;
	private static final double MAX_VOLTAGE = 3;
	
	private long m_decelTime, m_startTime, m_timeout;
	
	public AcceleratedOpenClaw(long decelTime, long timeout) {
		requires(Claw.getInstance());
		m_timeout = timeout;
		m_decelTime = decelTime;
	}
	
	public AcceleratedOpenClaw(long decelTime) {
		this(decelTime, DEFAULT_TIMEOUT);
	}
	
	@Override
	protected void initialize() {
		m_startTime = System.currentTimeMillis();
	}
	
	@Override
	protected void executeCommand() {
		long timeSinceInitialized = System.currentTimeMillis() - m_startTime;
		double voltage;
		
		if (timeSinceInitialized < m_decelTime)
			voltage = (MAX_VOLTAGE - (timeSinceInitialized / m_decelTime) * (MAX_VOLTAGE - Claw.VOLTAGE_LIMIT));
		else
			voltage = Claw.VOLTAGE_LIMIT;
		
		Claw.getInstance().setVoltageCompensation(voltage);
		
		if (!Claw.getInstance().isOpen())
			Claw.getInstance().setPower(-1);
		else
			Claw.getInstance().setPower(0);
	}

	@Override
	protected boolean isFinished() {
		long timeSinceInitialized = System.currentTimeMillis() - m_startTime;
		return Claw.getInstance().isOpen() || timeSinceInitialized > m_timeout;
	}
	
	@Override
	protected void end() {
		Claw.getInstance().stop();
		Claw.getInstance().setVoltageCompensation(Claw.VOLTAGE_LIMIT);
	}
}