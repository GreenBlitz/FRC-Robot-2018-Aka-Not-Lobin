package org.usfirst.frc.team4590.robot.commands.shifter;

import org.usfirst.frc.team4590.robot.subsystems.Chassis;
import org.usfirst.frc.team4590.robot.subsystems.Shifter;
import org.usfirst.frc.team4590.utils.enums.ShifterState;

import edu.wpi.first.wpilibj.command.Command;

public class AutoSwitchShifts extends Command {
	private static final int SPEED_THRESHOLD = 0,
			 				 POWER_THRESHOLD = 0;
	
    public AutoSwitchShifts() {
    	requires(Shifter.getInstance());
    }

    protected void execute() {
    	if (Chassis.getInstance().getSpeed() > SPEED_THRESHOLD) 
			Shifter.getInstance().setShift(ShifterState.SPEED);
		else if (Chassis.getInstance().getSpeed() < POWER_THRESHOLD)
			Shifter.getInstance().setShift(ShifterState.POWER);
    }

    protected boolean isFinished() {
        return false;
    }
}