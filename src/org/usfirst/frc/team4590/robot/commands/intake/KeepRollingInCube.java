package org.usfirst.frc.team4590.robot.commands.intake;

import org.usfirst.frc.team4590.robot.subsystems.Intake;

public class KeepRollingInCube extends IntakeCommand {
	
	public KeepRollingInCube() {
		requires(Intake.getInstance());
	}

	@Override
	protected void executeCommand() {
		Intake.getInstance().setPower(-0.05);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		Intake.getInstance().stop();
	}
}
