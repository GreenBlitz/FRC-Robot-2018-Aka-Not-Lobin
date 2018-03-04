package org.usfirst.frc.team4590.robot.commands.pitcher;

import org.usfirst.frc.team4590.robot.subsystems.Pitcher;

import edu.wpi.first.wpilibj.command.Command;

public class StopPitcher extends Command {

	public StopPitcher() {
		requires(Pitcher.getInstance());
	}
	
	@Override
	protected void execute() {
		Pitcher.getInstance().setPower(0);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

}
