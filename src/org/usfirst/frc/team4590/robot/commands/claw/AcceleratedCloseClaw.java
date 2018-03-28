package org.usfirst.frc.team4590.robot.commands.claw;

import org.usfirst.frc.team4590.robot.subsystems.Claw;
import org.usfirst.frc.team4590.robot.subsystems.Pitcher;
import org.usfirst.frc.team4590.utils.enums.PitcherDirection;

import edu.wpi.first.wpilibj.command.Scheduler;

public class AcceleratedCloseClaw extends ClosingClawCommand {

	private static final double MAX_VOLTAGE = 3;

	private long m_decelTime, m_startTime;
	private boolean m_shouldHold, m_shouldEnd;

	public AcceleratedCloseClaw(long decelTime, boolean shouldHold, boolean shouldEnd) {
		requires(Claw.getInstance());
		m_decelTime = decelTime;
		m_shouldHold = shouldHold;
		m_shouldEnd = shouldEnd;
	}

	public AcceleratedCloseClaw(long decelTime, boolean shouldHold) {
		this(decelTime, shouldHold, true);
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

		Claw.getInstance().setPower(1);
	}

	@Override
	protected boolean isFinished() {
		long timeSinceInitialized = System.currentTimeMillis() - m_startTime;
		return m_shouldEnd && timeSinceInitialized > m_decelTime;
	}

	@Override
	protected void end() {
		// Claw.getInstance().stop();
		System.out.println("Finish closing accel");
		Claw.getInstance().setVoltageCompensation(Claw.VOLTAGE_LIMIT);

		setInterruptible(true);
		if (m_shouldHold)
			Scheduler.getInstance().add(new HoldCube());
		else {
			Claw.getInstance().stop();
			Scheduler.getInstance().add(new StopClaw());
		}

	}

	@Override
	public synchronized boolean isInterruptible() {
		boolean shouldUnlockDownward = Pitcher.getInstance().getDirection() == PitcherDirection.DOWN
				&& Pitcher.getInstance().isSafeToOpenDownward(),
				shouldUnlockUpward = Pitcher.getInstance().getDirection() == PitcherDirection.UP
						&& Pitcher.getInstance().isSafeToOpenUpward(),
				shouldUnlockStationary = Pitcher.getInstance().getDirection() == PitcherDirection.STATIONARY;

		return super.isInterruptible() && (shouldUnlockStationary || shouldUnlockUpward || shouldUnlockDownward);
	}
}