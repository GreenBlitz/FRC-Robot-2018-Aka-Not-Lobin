package org.usfirst.frc.team4590.robot.commands.commandChains;

import org.usfirst.frc.team4590.robot.commands.claw.AcceleratedCloseClaw;
import org.usfirst.frc.team4590.robot.commands.pitcher.MovePitcher;
import org.usfirst.frc.team4590.robot.subsystems.Pitcher;
import org.usfirst.frc.team4590.utils.commandChain.CommandChain;
import org.usfirst.frc.team4590.utils.enums.PitcherState;

import edu.wpi.first.wpilibj.command.Command;

public class PitcherDownAfterCannon extends CommandChain {
	public PitcherDownAfterCannon() {
		Command closeClaw = new AcceleratedCloseClaw(1000l, false),
				movePitcher = new MovePitcher(PitcherState.COLLECT);
		
		addCommand(closeClaw);
		if (Pitcher.getInstance().getAngle() > 90)
			addSequential(movePitcher);
	}
}