package org.usfirst.frc.team4590.robot.commands.claw;

import org.usfirst.frc.team4590.robot.subsystems.Claw;

import edu.wpi.first.wpilibj.command.Command;

public class CloseClaw extends Command implements ClosingClawCommand {
	
	public CloseClaw() {
		super(0.5);
		requires(Claw.getInstance());
	}

	@Override
	protected void execute() {
		Claw.getInstance().setPower(-Claw.getDefaultPower());
	}

	@Override
	protected boolean isFinished() {
		return Claw.getInstance().isClosed() || isTimedOut();
	}
	
	@Override
	protected void end() {
		Claw.getInstance().stop();
	}
}