package org.usfirst.frc.team4590.robot.commands.claw;

import org.usfirst.frc.team4590.robot.subsystems.Claw;

public class CloseClaw extends ClosingClawCommand {

	public CloseClaw() {
		requires(Claw.getInstance());
	}
	
	public CloseClaw(long timeout) {
		super(timeout/1000d);
		requires(Claw.getInstance());
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
	}
}