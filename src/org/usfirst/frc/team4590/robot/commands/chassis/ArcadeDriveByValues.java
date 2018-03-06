package org.usfirst.frc.team4590.robot.commands.chassis;

import org.usfirst.frc.team4590.robot.subsystems.Chassis;

import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDriveByValues extends Command {

	private double m_power, m_rotation;
	
	public ArcadeDriveByValues(double power, double rotation, long timeout) {
		super(timeout * 0.001);
		requires(Chassis.getInstance());
		m_power = power;
		m_rotation = rotation;
	}
	
	protected void initalize(){
		
	}
	
	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}
	
	protected void execute(){
		Chassis.getInstance().arcadeDrive(m_power, m_rotation);
	}

}
