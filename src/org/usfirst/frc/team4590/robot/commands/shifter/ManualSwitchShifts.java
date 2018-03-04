package org.usfirst.frc.team4590.robot.commands.shifter;

import org.usfirst.frc.team4590.robot.subsystems.Shifter;

import edu.wpi.first.wpilibj.command.Command;

public class ManualSwitchShifts extends Command {

    public ManualSwitchShifts() {
       requires(Shifter.getInstance());
    }

    protected void execute() {
    	Shifter.getInstance().switchShift();
    }

    protected boolean isFinished() {
    	return true;
    }
}