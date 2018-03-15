package org.usfirst.frc.team4590.robot.commands.vision;

import org.usfirst.frc.team4590.robot.subsystems.Chassis;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveByVision extends Command{

	private double m_lastValue = 0;
	
	private static final double MULTI = 0.3;
	
	public DriveByVision() {
		super("DriveByVision");
		requires(Chassis.getInstance());
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	public void execute(){
		m_lastValue = NetworkTable.getTable("vision").getNumber("pos", 0);
		double k = MULTI * m_lastValue;
		Chassis.getInstance().tankDrive(0.6 + k, 0.6 - k );
	}
	
	public void end(){
		Chassis.getInstance().stop();
	}

}
