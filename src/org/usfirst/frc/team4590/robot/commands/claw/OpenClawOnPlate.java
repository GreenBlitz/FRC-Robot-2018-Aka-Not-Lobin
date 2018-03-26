package org.usfirst.frc.team4590.robot.commands.claw;

import org.usfirst.frc.team4590.robot.subsystems.Claw;

import edu.wpi.first.wpilibj.command.Command;

public class OpenClawOnPlate extends Command {

	private static final int VOLTAGE = 3;
	
	private static final long DEFAULT_TIMEOUT = 1500;
	
	public OpenClawOnPlate() {
		this(DEFAULT_TIMEOUT);
	}
	
	public OpenClawOnPlate(long timeout) {
		super(timeout/1000d);
		requires(Claw.getInstance());
	}

	@Override
	protected void initialize() {
		Claw.getInstance().setVoltageCompensation(VOLTAGE);
	}
	
	@Override
	protected void execute() {
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
	}
}