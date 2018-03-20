package org.usfirst.frc.team4590.robot.commands.commandChains;

import org.usfirst.frc.team4590.robot.commands.claw.OpenClaw;
import org.usfirst.frc.team4590.robot.commands.pitcher.MovePitcher;
import org.usfirst.frc.team4590.robot.subsystems.Pitcher;
import org.usfirst.frc.team4590.utils.PitcherState;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class ReadyToCollect extends Command {

	@Override
	protected void initialize() {
		Scheduler.getInstance().add(new MovePitcher(PitcherState.COLLECT));
	}
	
	@Override
	protected boolean isFinished() {
		return Pitcher.getInstance().getAngle() < 90;
	}

	@Override
	protected void end() {
		Scheduler.getInstance().add(new OpenClaw());
	}
}