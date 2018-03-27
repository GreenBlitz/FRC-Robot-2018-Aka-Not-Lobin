package org.usfirst.frc.team4590.robot.commands.claw;

import org.usfirst.frc.team4590.robot.subsystems.Claw;
import org.usfirst.frc.team4590.robot.subsystems.Pitcher;
import org.usfirst.frc.team4590.utils.enums.PitcherDirection;

import edu.wpi.first.wpilibj.command.Command;

public class StopClaw extends Command {
	
	StopClaw() {
		requires(Claw.getInstance());
	}
	
	@Override
	protected void execute() {
		boolean shouldUnlockDownward = Pitcher.getInstance().getDirection() == PitcherDirection.DOWN
			 	&& Pitcher.getInstance().isSafeToOpenDownward(),
			 	 shouldUnlockUpward = Pitcher.getInstance().getDirection() == PitcherDirection.UP
		   		 && Pitcher.getInstance().isSafeToOpenUpward(),
		   		 shouldUnlockStationary = Pitcher.getInstance().getDirection() == PitcherDirection.STATIONARY;

		setInterruptible(shouldUnlockStationary || shouldUnlockUpward || shouldUnlockDownward);
		
		Claw.getInstance().stop();
	}
	
	@Override
	protected boolean isFinished() {
		return isInterruptible();
	}
}