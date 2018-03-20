package org.usfirst.frc.team4590.robot.commands.chassis;

import org.usfirst.frc.team4590.robot.subsystems.Chassis;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveForwardsByMeters extends Command implements PIDSource, PIDOutput {
	private static final double ABSOLUTE_TOLARENCE = 10d/100,
								OUTPUT_RANGE = 0.7,
								ROTATION_SPEED = 5,
								kP = 3,
								kI = 0,
								kD = 0;
	private static final long TIME_ON_TARGET = 100;
	
	private PIDController m_controller;
	private double m_meters, m_angle;
	private long m_onTarget = -1;
	private boolean m_shouldStop;
	private boolean m_resetGyro;
	
	public DriveForwardsByMeters(double meters, double angle, boolean shouldStop, boolean resetGyro) {
		requires(Chassis.getInstance());
    	m_meters = meters;
    	m_angle = angle;
    	m_shouldStop = shouldStop;
    	m_resetGyro = resetGyro;
	}

	public DriveForwardsByMeters(double meters, boolean shouldStop, boolean resetGyro) {
    	this(meters, 0, true, resetGyro);
    }    
	
    public DriveForwardsByMeters(double meters) {
    	this(meters, 0, true, true);
    }
    
    public DriveForwardsByMeters(double meters, boolean shouldStop) {
    	this(meters, 0, shouldStop, true);
    }
    
    protected void initialize() {
    	System.out.println("Init DriveStraight to: " + m_meters);
    	m_controller = new PIDController(kP, kI, kD, this, this);
    	m_controller.setAbsoluteTolerance(ABSOLUTE_TOLARENCE);
    	m_controller.setSetpoint(m_meters + Chassis.getInstance().getDistance());
    	SmartDashboard.putNumber("Trying to reach", m_controller.getSetpoint());
    	m_controller.setOutputRange(-OUTPUT_RANGE, OUTPUT_RANGE);
    //	Chassis.getInstance().resetEncoders();
    	if (m_resetGyro)
    		Chassis.getInstance().resetGyro();
    	m_controller.enable();
    	m_onTarget = -1;
    }

    protected boolean isFinished() {
        if (m_onTarget == -1 && m_controller.onTarget())
        	m_onTarget = System.currentTimeMillis();
        if (!m_controller.onTarget())
        	m_onTarget = -1;
        return m_controller.onTarget() && (System.currentTimeMillis() - m_onTarget >= TIME_ON_TARGET);
    }

    protected void end() {
    	System.out.println("End DriveStraight to: " + m_meters);
    	m_controller.disable();
    	if (m_shouldStop)
    		Chassis.getInstance().stop();
    }
    
    @Override
	public double pidGet() {
    	return Chassis.getInstance().getDistance();
    }

	@Override
	public void pidWrite(double output) {
		Chassis.getInstance().arcadeDrive(output, ROTATION_SPEED * (m_angle - Chassis.getInstance().getAngle()) / 180);
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {}

	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}
}