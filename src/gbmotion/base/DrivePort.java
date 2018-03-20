package gbmotion.base;

import org.usfirst.frc.team4590.robot.subsystems.Chassis;
import org.usfirst.frc.team4590.utils.CANRobotDrive;
import org.usfirst.frc.team4590.utils.CANRobotDrive.TalonID;
import org.usfirst.frc.team4590.utils.SmartEncoder;

public class DrivePort {

	public static final DrivePort DEFAULT = new DrivePort();
	
	// public static final DrivePort GILDABOI = new DrivePort(new SmartTalon(8), new SmartTalon(11));

	protected CANRobotDrive m_robotDrive;
	private SmartEncoder m_leftEncoder;
	private SmartEncoder m_rightEncoder;
	
	public DrivePort(){
	}

	/**
	 * @param leftValue
	 * @param rightValue
	 * @see edu.wpi.first.wpilibj.RobotDrive#tankDrive(double, double)
	 */
	public void tankDrive(double leftValue, double rightValue) {
		m_robotDrive.tankDrive(leftValue, rightValue);
	}

	/**
	 * @param moveValue
	 * @param rotateValue
	 * @see edu.wpi.first.wpilibj.RobotDrive#arcadeDrive(double, double)
	 */
	public void arcadeDrive(double moveValue, double rotateValue) {
		m_robotDrive.arcadeDrive(moveValue, rotateValue);
	}

	/**
	 * @param obj
	 * @return
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		return m_robotDrive.equals(obj);
	}

	/**
	 * @param leftOutput
	 * @param rightOutput
	 * @see edu.wpi.first.wpilibj.RobotDrive#setLeftRightMotorOutputs(double,
	 *      double)
	 */
	public void setLeftRightMotorOutputs(double leftOutput, double rightOutput) {
		m_robotDrive.setLeftRightMotorOutputs(leftOutput, rightOutput);
	}

	/**
	 * @param motor
	 * @param isInverted
	 * @see edu.wpi.first.wpilibj.RobotDrive#setInvertedMotor(edu.wpi.first.wpilibj.RobotDrive.MotorType,
	 *      boolean)
	 */
	public void setInvertedMotor(TalonID motor, boolean isInverted) {
		m_robotDrive.setInvetedMotor(motor, isInverted);
	}

	/**
	 * @param maxOutput
	 * @see edu.wpi.first.wpilibj.RobotDrive#setMaxOutput(double)
	 */
	public void setPowerLimit(double maxOutput) {
		m_robotDrive.setPowerLimit(maxOutput);
	}

	public SmartEncoder getEndoder(boolean dir) {
		if (dir)
			return m_rightEncoder;
		else
			return m_leftEncoder;
	}

	/**
	 * @return
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return m_robotDrive.toString();
	}
}
