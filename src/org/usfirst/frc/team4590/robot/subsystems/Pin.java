package org.usfirst.frc.team4590.robot.subsystems;

import org.usfirst.frc.team4590.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Pin extends Subsystem {

	private static Pin instance;
	
	private SendableChooser<Boolean> isPinWorking = new SendableChooser<Boolean>();
	
	private DoubleSolenoid piston;
	
	private Pin() {
		piston = new DoubleSolenoid(RobotMap.PIN_SOLENOID_FORWARD_PORT, 
									RobotMap.PIN_SOLENOID_BACKWARD_PORT);
	
		isPinWorking.addDefault("TRUE", true);
		isPinWorking.addObject("FALSE", false);
		SmartDashboard.putData("Is pin working", isPinWorking);
	}
	
	public static Pin getInstance() {
		return instance;
	}
	
	public static void init() {
		instance = new Pin();
	}
	
	public void update() {
		SmartDashboard.putString("Pin current command", getCurrentCommandName());
		SmartDashboard.putString("Pin piston value", piston.get().name());
	}
	
	@Override
	protected void initDefaultCommand() {
//		setDefaultCommand(new WaitForShooter());
		
	}

	public void setPiston(Value value) {
		piston.set(value);
	}
	
	public boolean isWorking() {
//		return isPinWorking.getSelected();
		return true;
	}
}