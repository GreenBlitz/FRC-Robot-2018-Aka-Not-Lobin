package org.usfirst.frc.team4590.robot.commands.pitcher;

import org.usfirst.frc.team4590.robot.subsystems.Pitcher;

import edu.wpi.first.wpilibj.command.Command;

public class PitcherDown extends Command {

	public static boolean hasRun = false;
	
	public PitcherDown(long timeout) {
		super(timeout/1000d);
		requires(Pitcher.getInstance());
	}

	protected void execute() {
		if (!hasRun)
			Pitcher.getInstance().setPower(0.5);
	}
	
	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}
	
	@Override
	protected void end() {
		hasRun = true;
		Pitcher.getInstance().stop();
	}
}