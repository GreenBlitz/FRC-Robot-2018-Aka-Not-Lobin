package org.usfirst.frc.team4590.robot.commands.commandChains;

import org.usfirst.frc.team4590.robot.commands.claw.GrabCube;
import org.usfirst.frc.team4590.robot.commands.claw.OpenClaw;
import org.usfirst.frc.team4590.robot.commands.pitcher.MovePitcherToState;
import org.usfirst.frc.team4590.utils.CommandChain;
import org.usfirst.frc.team4590.utils.PitcherState;

import edu.wpi.first.wpilibj.command.Command;

/**
 * prepare to climb by opening the wings and allowing climb control
 */
public class OpenWings extends CommandChain {

    public OpenWings() {
        Command closeClaw = new GrabCube(),
        		movePitcher = new MovePitcherToState(PitcherState.PLATE),
        		openWings = new OpenClaw();
        
        addCommand(closeClaw);
        addSequential(movePitcher, closeClaw);
        addSequential(openWings, movePitcher);
    }
}
