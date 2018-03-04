package org.usfirst.frc.team4590.robot.commands.commandChains;

import org.usfirst.frc.team4590.robot.commands.claw.GrabCube;
import org.usfirst.frc.team4590.robot.commands.intake.Collect;
import org.usfirst.frc.team4590.robot.commands.pitcher.MovePitcherToState;
import org.usfirst.frc.team4590.utils.CommandChain;
import org.usfirst.frc.team4590.utils.PitcherState;

import edu.wpi.first.wpilibj.command.Command;

public class PickupCube extends CommandChain {
    public PickupCube() {
    	Command closeClaw = new GrabCube(),
    			collect = new Collect(1000),
    			movePitcher = new MovePitcherToState(PitcherState.SWITCH_FORWARD);
    	
    	addCommand(closeClaw);
    	addSequential(collect, closeClaw);
    	addSequential(movePitcher, collect);
    }
}
