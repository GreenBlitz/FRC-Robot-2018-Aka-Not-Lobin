package org.usfirst.frc.team4590.robot.commands.claw;

import org.usfirst.frc.team4590.robot.subsystems.Claw;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RawValuesToClaw extends Command {
	public RawValuesToClaw(){
		requires(Claw.getInstance());
		SmartDashboard.putNumber("clawPower", 0);
	}
	
	public boolean isFinished(){ return false;}
	
	public void execute(){
		Claw.getInstance().setPower(SmartDashboard.getNumber("clawPower", 0));
		System.out.println("Working at " + SmartDashboard.getNumber("clawPower", 0));
		
	}
	
	public void end(){ Claw.getInstance().stop();}
}
