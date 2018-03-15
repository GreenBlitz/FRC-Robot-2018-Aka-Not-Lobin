package org.usfirst.frc.team4590.robot.commands.claw;

import org.usfirst.frc.team4590.robot.subsystems.Claw;

import edu.wpi.first.wpilibj.command.Command;

public class StopClaw extends Command {
	
	public StopClaw() {
		System.out.println("Stopping claw");
		requires(Claw.getInstance());
	}
	
	@Override
	protected void execute() {
		Claw.getInstance().stop();
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
}