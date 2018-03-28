package org.usfirst.frc.team4590.robot.subsystems;

import org.usfirst.frc.team4590.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class NewClaw extends Subsystem {
	
	private static NewClaw instance;
	
	private DoubleSolenoid piston;
	
	private NewClaw() {
		piston = new DoubleSolenoid(RobotMap.NEWCLAW_SOLENOID_FORWARD_PORT, 
									RobotMap.NEWCLAW_SOLENOID_BACKWARD_PORT);
	}
	
	public static NewClaw getInstance() {
		return instance;
	}
	
	public static void init() {
		instance = new NewClaw();
	}
	
	protected void initDefaultCommand() {};
}