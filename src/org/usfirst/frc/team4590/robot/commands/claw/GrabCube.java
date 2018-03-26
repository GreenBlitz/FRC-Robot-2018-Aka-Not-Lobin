package org.usfirst.frc.team4590.robot.commands.claw;

import org.usfirst.frc.team4590.robot.subsystems.Claw;

import edu.wpi.first.wpilibj.command.Scheduler;

public class GrabCube extends ClosingClawCommand {

	public GrabCube(long timeout) {
		super(timeout/1000d);
		requires(Claw.getInstance());
	}
	
	public GrabCube() {
		this(1500);
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
		Claw.getInstance().stop();
		Scheduler.getInstance().add(new HoldCube());
	}
}