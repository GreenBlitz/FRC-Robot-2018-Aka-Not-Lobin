package org.usfirst.frc.team4590.robot.commands.claw;

import org.usfirst.frc.team4590.robot.subsystems.Claw;

class HoldCube extends ClosingClawCommand {

	HoldCube() {
		requires(Claw.getInstance());
	}
	
	@Override
	protected void executeCommand() {
		Claw.getInstance().setPower(Claw.getDefaultPower());
	}
		
	@Override
	protected boolean isFinished() {
//		return !Claw.getInstance().isClosed();
		return false;
	}
	
	@Override
	protected void end() {
		Claw.getInstance().stop();
	}
}