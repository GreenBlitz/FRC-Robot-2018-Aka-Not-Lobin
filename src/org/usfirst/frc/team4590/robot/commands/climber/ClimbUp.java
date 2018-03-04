package org.usfirst.frc.team4590.robot.commands.climber;

import org.usfirst.frc.team4590.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

public class ClimbUp extends Command {
	
	public ClimbUp() {
		requires(Climber.getInstance());
	}

	@Override
	public void execute() {
		Climber.getInstance().setPower(Climber.getDefaultPower());
	}

	@Override
	public void end() {
		Climber.getInstance().stop();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}