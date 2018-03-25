package org.usfirst.frc.team4590.robot.commands.cannon;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class ManualWindCannon extends Command {
	
	@Override
	protected void execute() {
		Scheduler.getInstance().add(new PullDownPlatform());
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
}
