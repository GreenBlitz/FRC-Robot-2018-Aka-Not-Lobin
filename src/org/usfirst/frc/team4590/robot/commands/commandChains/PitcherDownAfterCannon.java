package org.usfirst.frc.team4590.robot.commands.commandChains;

import org.usfirst.frc.team4590.robot.commands.claw.CloseClawOnPlate;
import org.usfirst.frc.team4590.robot.commands.pitcher.MovePitcher;
import org.usfirst.frc.team4590.utils.commandChain.CommandChain;
import org.usfirst.frc.team4590.utils.enums.PitcherState;

import edu.wpi.first.wpilibj.command.Command;

public class PitcherDownAfterCannon extends CommandChain {

	public PitcherDownAfterCannon() {
		Command closeClaw = new CloseClawOnPlate(1000l),
				movePitcher = new MovePitcher(PitcherState.COLLECT);
		
		addCommand(closeClaw);
		addSequential(movePitcher);
	}
}
