package org.usfirst.frc.team4590.robot.commands.chassis;

import org.usfirst.frc.team4590.robot.subsystems.Chassis;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

public class RotateByDegrees extends Command implements PIDSource, PIDOutput {
	private static final double ABSOLUTE_TOLARENCE = 7,
								OUTPUT_RANGE = 0.7,
								kP = 0.07,
								kI = 0,
								kD = 0;
	private static final long TIME_ON_TARGET = 100;

	private PIDController m_controller;
	private double m_angles;
	private long m_onTarget = -1;
	private boolean m_gyroReset;
	
	public RotateByDegrees(double angles){
		this(angles, true);
	}
	
	public RotateByDegrees(double angles, boolean resetGyro) {
		requires(Chassis.getInstance());
		m_angles = angles;
		m_gyroReset = resetGyro;
	}
	
	@Override
	protected void initialize() {
    	System.out.println("Init Rotation to: " + m_angles);
    	if (m_gyroReset) 
    		Chassis.getInstance().resetGyro();
		m_controller = new PIDController(kP, kI, kD, this, this);
    	m_controller.setOutputRange(-OUTPUT_RANGE, OUTPUT_RANGE);
		m_controller.setAbsoluteTolerance(ABSOLUTE_TOLARENCE);
    	m_controller.setSetpoint(m_angles);
    	m_controller.enable();
    	m_onTarget = -1;
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
		if (m_onTarget == -1 && m_controller.onTarget())
        	m_onTarget = System.currentTimeMillis();
        if (!m_controller.onTarget())
        	m_onTarget = -1;
        return m_controller.onTarget() && (System.currentTimeMillis() - m_onTarget >= TIME_ON_TARGET);
    }
}