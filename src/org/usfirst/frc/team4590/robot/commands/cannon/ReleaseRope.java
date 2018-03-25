package org.usfirst.frc.team4590.robot.commands.cannon;

import org.usfirst.frc.team4590.robot.subsystems.Cannon;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class ReleaseRope extends Command {
	
	public ReleaseRope() {
		requires(Cannon.getInstance());
		setInterruptible(false);
	}

	@Override
	protected void execute() {
		if (timeSinceInitialized() >= 0.1 && !Cannon.getInstance().isRopeLoose())
			Cannon.getInstance().setPower(-Cannon.getDefaultPower());
		else
			Cannon.getInstance().setPower(0);
	}
	
	@Override
	protected boolean isFinished() {
		return Cannon.getInstance().isRopeLoose();
	}

	@Override
	protected void end() {
		Cannon.getInstance().stop();
		Cannon.getInstance().setReadyToShoot(true);
		Scheduler.getInstance().add(new AddedThingy());
	}
}