package org.usfirst.frc.team4590.robot.commands.chassis;

import org.usfirst.frc.team4590.robot.subsystems.Chassis;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

public class RotateByDegrees extends Command implements PIDSource, PIDOutput {
	private static final double ABSOLUTE_TOLARENCE = 3,
								kP = 0.1,
								kI = 0,
								kD = 0;
	private static final int TIME_ON_TARGET = 1;

	private PIDController m_controller;
	private double m_angles;
	private int m_timeOnTarget = -1;
	
	public RotateByDegrees(double angles) {
		requires(Chassis.getInstance());
		m_angles = angles;
	}
	
	@Override
	protected void initialize() {
    	System.out.println("Init Rotation to: " + m_angles);
    	Chassis.getInstance().resetGyro();
		m_controller = new PIDController(kP, kI, kD, this, this);
    	m_controller.setOutputRange(-0.8, 0.8);
		m_controller.setAbsoluteTolerance(ABSOLUTE_TOLARENCE);
    	m_controller.setSetpoint(m_angles);
    	m_controller.enable();
	}
	
	@Override
	protected void end() {
		System.out.println("End Rotation to: " + m_angles);
		m_controller.disable();
		Chassis.getInstance().tankDrive(0, 0);
	}
	
	@Override
	public void pidWrite(double output) {
		Chassis.getInstance().tankDrive(output, -output);
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {}

	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		return Chassis.getInstance().getAngle();
	}

	@Override
	protected boolean isFinished() {
        if (m_controller.onTarget())
        	m_timeOnTarget++;
        else
        	m_timeOnTarget = 0;
        return m_timeOnTarget > TIME_ON_TARGET;
    }
}