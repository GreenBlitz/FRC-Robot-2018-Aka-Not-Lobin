package org.usfirst.frc.team4590.robot.commands.intake;

import org.usfirst.frc.team4590.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Collect extends Command {
	
	public Collect(long timeout) {
		super(timeout/1000d);
		requires(Intake.getInstance());
	}
	
	public Collect() {
		requires(Intake.getInstance());
	}
	
    protected void execute() {
    	Intake.getInstance().setPower(-Intake.getDefaultPower());
    }

    protected boolean isFinished() {
    	return isTimedOut();
    }
    
    @Override
    protected void end() {
//    	Scheduler.getInstance().add(new KeepRollingInCube());
    	Intake.getInstance().stop();
    }
}