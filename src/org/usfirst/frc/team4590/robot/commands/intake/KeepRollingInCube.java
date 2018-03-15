package org.usfirst.frc.team4590.robot.commands.intake;

import org.usfirst.frc.team4590.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

class KeepRollingInCube extends Command {
	
	public KeepRollingInCube() {
		requires(Intake.getInstance());
	}

	@Override
	protected void execute() {
		Intake.getInstance().setPower(-0.1);
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
