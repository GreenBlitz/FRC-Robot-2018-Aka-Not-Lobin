package org.usfirst.frc.team4590.robot.commands.claw;

import org.usfirst.frc.team4590.robot.subsystems.Claw;

public class CloseClawOnPlate extends ClosingClawCommand {

	private static final int VOLTAGE = 3;
	
	private static final long DEFAULT_TIMEOUT = 1500;
	
	public CloseClawOnPlate() {
		this(DEFAULT_TIMEOUT);
	}
	
	public CloseClawOnPlate(long timeout) {
		super(timeout/1000d);
		requires(Claw.getInstance());
	}

	@Override
	protected void initialize() {
		Claw.getInstance().setVoltageCompensation(VOLTAGE);
	}
	
	@Override
	protected void executeCommand() {
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