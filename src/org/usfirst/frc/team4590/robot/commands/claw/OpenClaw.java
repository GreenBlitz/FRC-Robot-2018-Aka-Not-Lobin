package org.usfirst.frc.team4590.robot.commands.claw;

import org.usfirst.frc.team4590.robot.subsystems.Claw;

import edu.wpi.first.wpilibj.command.Command;

public class OpenClaw extends Command {

	private static final long DEFAULT_TIMEOUT = 1500;
	
	public OpenClaw() {
		this(DEFAULT_TIMEOUT);
	}
	
	public OpenClaw(long timeout) {
		super(timeout/1000d);
		requires(Claw.getInstance());
	}

	@Override
	protected void execute() {
		Claw.getInstance().setPower(-1);
	}

	@Override
	protected boolean isFinished() {
		return Claw.getInstance().isOpen() || isTimedOut();
	}

	@Override
	protected void end() {
		Claw.getInstance().stop();
	}
}