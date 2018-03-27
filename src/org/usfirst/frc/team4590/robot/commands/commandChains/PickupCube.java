package org.usfirst.frc.team4590.robot.commands.commandChains;

import org.usfirst.frc.team4590.robot.commands.claw.AcceleratedCloseClaw;
import org.usfirst.frc.team4590.robot.commands.intake.Collect;
import org.usfirst.frc.team4590.utils.commandChain.CommandChain;

import edu.wpi.first.wpilibj.command.Command;

public class PickupCube extends CommandChain {
	
	public PickupCube() {
		this(1500, 1000);
	}
	
    public PickupCube(long grabTimeout, long collectTimeout) {
    	Command closeClaw = new AcceleratedCloseClaw(grabTimeout, true),
    			collect = new Collect(collectTimeout);
    	
    	addCommand(closeClaw);
    	addParallel(collect, closeClaw);
    }
}