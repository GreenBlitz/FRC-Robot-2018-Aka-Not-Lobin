package org.usfirst.frc.team4590.robot.commands.claw;

import org.usfirst.frc.team4590.robot.subsystems.Claw;

import edu.wpi.first.wpilibj.command.Scheduler;

public class StrongCloseClaw extends ClosingClawCommand {
	
	public StrongCloseClaw() {
		super(1);
		requires(Claw.getInstance());
	}
	
	@Override
	protected void initialize() {
		Claw.getInstance().setVoltageCompensation(3.5);
	}
	
	@Override
	protected void executeCommand() {
		Claw.getInstance().setPower(1);
	}
	
	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}
	
	@Override
	protected void end() {
		Claw.getInstance().setVoltageCompensation(Claw.VOLTAGE_LIMIT);
		Scheduler.getInstance().add(new AcceleratedCloseClaw(500, true));
	}
}	