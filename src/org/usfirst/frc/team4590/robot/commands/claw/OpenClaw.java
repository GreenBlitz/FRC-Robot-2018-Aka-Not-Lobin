package org.usfirst.frc.team4590.robot.commands.claw;

import org.usfirst.frc.team4590.robot.subsystems.Claw;

import edu.wpi.first.wpilibj.command.Command;

public class OpenClaw extends Command {

	private static final long DEFAULT_TIMEOUT = 1500;
	
	private double m_power;

	public OpenClaw(double power, long timeout) {
		super(timeout/1000d);
		requires(Claw.getInstance());
		if (Math.abs(power) > Claw.getDefaultPower())
			System.out.println("The power given to the claw motor is bigger than the default power."
					+ "\nGiven power: " + power 
					+ "\nDefault power: " + Claw.getDefaultPower());
		m_power = -Math.abs(power);
	}
	
	public OpenClaw(long timeout) {
		this(Claw.getDefaultPower(), timeout);
	}
	
	public OpenClaw() {
		this(Claw.getDefaultPower(), DEFAULT_TIMEOUT);
	}
	
	public OpenClaw(double power) {
		this(power, DEFAULT_TIMEOUT);
	}

	@Override
	protected void execute() {
		if (!Claw.getInstance().isClosing())
			Claw.getInstance().setPower(m_power);
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