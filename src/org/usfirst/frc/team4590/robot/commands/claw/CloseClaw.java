package org.usfirst.frc.team4590.robot.commands.claw;

import org.usfirst.frc.team4590.robot.subsystems.Claw;

import edu.wpi.first.wpilibj.command.Command;

public class CloseClaw extends Command implements ClosingClawCommand {

	private double m_power;

	public CloseClaw() {
		this(Claw.getDefaultPower());
	}

	/**
	 * @param power
	 *            The absolute power to give to the motors.
	 */
	public CloseClaw(double power) {
		System.out.println("Closing claw");
		requires(Claw.getInstance());
		if (power > Claw.getDefaultPower())
			System.out.println("The power given to the claw motor is bigger than the default power."
					+ "\nGiven power: " + power 
					+ "\nDefault power: " + Claw.getDefaultPower());
		m_power = Math.abs(power);
	}
	
	public CloseClaw(long timeout) {
		super(timeout/1000d);
		requires(Claw.getInstance());
		double power = Claw.getDefaultPower();
		if (power > Claw.getDefaultPower())
			System.out.println("The power given to the claw motor is bigger than the default power."
					+ "\nGiven power: " + power 
					+ "\nDefault power: " + Claw.getDefaultPower());
		m_power = Math.abs(power);
	}

	@Override
	protected void execute() {
		Claw.getInstance().setPower(m_power);
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	@Override
	protected void end() {
		Claw.getInstance().stop();
	}
}