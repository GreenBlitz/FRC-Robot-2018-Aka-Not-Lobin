package org.usfirst.frc.team4590.robot.commands.claw;

import org.usfirst.frc.team4590.robot.subsystems.Claw;

public class CloseClawUntillSafe extends ClosingClawCommand {
	
	public CloseClawUntillSafe() {
		requires(Claw.getInstance());
	}
	
	@Override
	protected void executeCommand() {
		Claw.getInstance().setPower(1);
	}
	
	@Override
	protected boolean isFinished() {
		return isInterruptible();
	}
}