package org.usfirst.frc.team4590.robot.commands.chassis;

import org.usfirst.frc.team4590.robot.subsystems.Chassis;

import edu.wpi.first.wpilibj.command.Command;

public class DriveStraightByMoveValue extends Command {

	private static final double ROTATION_SPEED = 5;
	
	private double m_power, m_angle;
	
	public DriveStraightByMoveValue(double power, double angle, long timeout) {
		super(timeout * 0.001);
		requires(Chassis.getInstance());
		m_power = power;
		m_angle = angle;
	}
	
	public DriveStraightByMoveValue(double power, long timeout) {
		this(power, Chassis.getInstance().getAngle(), timeout);
	}
	
	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}
	
	protected void execute(){
		Chassis.getInstance().arcadeDrive(m_power, ROTATION_SPEED * (m_angle - Chassis.getInstance().getAngle()) / 180);
	}
}
