package org.usfirst.frc.team4590.robot.commands.shifter;

import org.usfirst.frc.team4590.robot.subsystems.Shifter;
import org.usfirst.frc.team4590.utils.ShifterState;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetShift extends Command {
	private ShifterState newShift;
	
    public SetShift(ShifterState shift) {
    	requires(Shifter.getInstance());
    	newShift = shift;
    }

    protected void execute() {
    	Shifter.getInstance().setShift(newShift);
    }

    protected boolean isFinished() {
        return true;
    }
}