package org.usfirst.frc.team4590.robot.commands.climber;

import org.usfirst.frc.team4590.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

public class ClimbDown extends Command {

    public ClimbDown() {
        requires(Climber.getInstance());
    }

    protected void execute() {
    	Climber.getInstance().setPower(- Climber.getDefaultPower());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Climber.getInstance().stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
