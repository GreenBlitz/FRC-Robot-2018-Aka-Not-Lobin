package org.usfirst.frc.team4590.robot.commands.cannon;

import org.usfirst.frc.team4590.robot.subsystems.Cannon;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class PullDownPlatform extends Command {
	
	public PullDownPlatform() {
		requires(Cannon.getInstance());
		setInterruptible(false);
	}
	
	@Override
	protected void initialize() {
		Cannon.getInstance().setStartedWinding(true);
	}
	
	@Override
	protected void execute() {
		if (!Cannon.getInstance().isPlatformDown())
			Cannon.getInstance().setPower(Cannon.getDefaultPower());
	}
	
	@Override
	protected boolean isFinished() {
		return Cannon.getInstance().isPlatformDown();
	}
	
	@Override
	protected void end() {
		Cannon.getInstance().stop();
		Scheduler.getInstance().add(new ReleaseRope());
	}
}