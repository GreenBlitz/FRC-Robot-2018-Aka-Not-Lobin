package org.usfirst.frc.team4590.robot.commands.claw;

import org.usfirst.frc.team4590.robot.subsystems.Claw;

import edu.wpi.first.wpilibj.command.Command;

public class OpenClaw extends Command {

	private double m_power;

	public OpenClaw(long timeout) {
		super(timeout/1000d);
		requires(Claw.getInstance());
		m_power = -Math.abs(Claw.getDefaultPower());
	}
	
	public OpenClaw() {
		this(Claw.getDefaultPower());
	}

	/**
	 * @param power
	 *            The absolute power to give to the motors.
	 */
	public OpenClaw(double power) {
		requires(Claw.getInstance());
		if (Math.abs(power) > Claw.getDefaultPower())
			System.out.println("The power given to the claw motor is bigger than the default power."
					+ "\nGiven power: " + power 
					+ "\nDefault power: " + Claw.getDefaultPower());
		m_power = -Math.abs(power);
	}

	@Override
	protected void execute() {
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