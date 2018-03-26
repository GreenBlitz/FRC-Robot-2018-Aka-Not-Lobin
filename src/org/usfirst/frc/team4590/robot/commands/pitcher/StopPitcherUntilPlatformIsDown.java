package org.usfirst.frc.team4590.robot.commands.pitcher;

import org.usfirst.frc.team4590.robot.subsystems.Cannon;
import org.usfirst.frc.team4590.robot.subsystems.Pitcher;
import org.usfirst.frc.team4590.utils.enums.PitcherState;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class StopPitcherUntilPlatformIsDown extends Command implements PitcherCommand {

	public StopPitcherUntilPlatformIsDown() {
		requires(Pitcher.getInstance());
		setInterruptible(false);
	}
	
	@Override
	public PitcherState getState() {
		return PitcherState.PLATE;
	}

	@Override
	protected void execute() {
		Pitcher.getInstance().setPower(0);
	}
	
	@Override
	protected boolean isFinished() {
		return Cannon.getInstance().isPlatformDown();
	}
	
	@Override
	protected void end() {
		Scheduler.getInstance().add(new MovePitcher(PitcherState.PLATE));
	}
}