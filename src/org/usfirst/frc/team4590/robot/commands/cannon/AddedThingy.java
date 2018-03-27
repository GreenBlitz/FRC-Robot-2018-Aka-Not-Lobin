package org.usfirst.frc.team4590.robot.commands.cannon;

import org.usfirst.frc.team4590.robot.subsystems.Cannon;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class AddedThingy extends Command {
	
	private static final double POWER = 0.3;
	
	public AddedThingy() {
		requires(Cannon.getInstance());
//		setInterruptible(false);
	}
	
	@Override
	protected void execute() {
		Cannon.getInstance().setPower(POWER);
	}
	
	@Override
	protected boolean isFinished() {
		return !Cannon.getInstance().isRopeLoose();
	}
	
	@Override
	protected void end() {
		Cannon.getInstance().setReadyToShoot(true);
		Scheduler.getInstance().add(new WaitToWindCannon());
	}
}
