package org.usfirst.frc.team4590.robot.commands.commandChains;

import org.usfirst.frc.team4590.robot.commands.claw.CloseClawControlled;
import org.usfirst.frc.team4590.robot.commands.claw.GrabCube;
import org.usfirst.frc.team4590.robot.commands.intake.Collect;
import org.usfirst.frc.team4590.utils.CommandChain;

import edu.wpi.first.wpilibj.command.Command;

public class PickupCube extends CommandChain {
    public PickupCube() {
    	Command closeClaw = new CloseClawControlled(0.45),
    			collect = new Collect(1000);
    	
    	addCommand(closeClaw);
    	addSequential(collect, closeClaw);
    }
}
