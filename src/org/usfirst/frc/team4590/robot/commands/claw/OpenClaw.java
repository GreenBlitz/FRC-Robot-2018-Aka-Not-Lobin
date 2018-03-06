package org.usfirst.frc.team4590.robot.commands.claw;

import org.usfirst.frc.team4590.robot.subsystems.Claw;

import edu.wpi.first.wpilibj.command.Command;

public class OpenClaw extends Command {

	public OpenClaw() {
		requires(Claw.getInstance());
	}
	
	@Override
	protected void execute() {
		Claw.getInstance().setPower(Claw.getDefaultPower());
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		Claw.getInstance().stop();
	}
}