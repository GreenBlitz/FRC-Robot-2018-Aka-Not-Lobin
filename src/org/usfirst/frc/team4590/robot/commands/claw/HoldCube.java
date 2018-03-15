package org.usfirst.frc.team4590.robot.commands.claw;

import org.usfirst.frc.team4590.robot.subsystems.Claw;

import edu.wpi.first.wpilibj.command.Command;

class HoldCube extends Command implements ClosingClawCommand {

	HoldCube() {
		requires(Claw.getInstance());
	}
	
	@Override
	protected void execute() {
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