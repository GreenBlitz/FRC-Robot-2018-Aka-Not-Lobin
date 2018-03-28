package org.usfirst.frc.team4590.robot.subsystems;

import org.usfirst.frc.team4590.robot.RobotMap;
import org.usfirst.frc.team4590.robot.commands.intake.KeepRollingInCube;
import org.usfirst.frc.team4590.utils.CTRE.SmartTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Intake extends Subsystem {

	private static Intake instance;
	
	private static double defaultPower = 0.3;
	
	private SmartTalon motor;
	
	public static Intake getInstance() {
		return instance;
	}
	
	public static void init() {
		instance = new Intake();
	}
	
	private Intake() {
		motor = new SmartTalon(RobotMap.INTAKE_WHEELS_MOTOR_PORT);
		SmartDashboard.putNumber("Intake power", defaultPower);
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new KeepRollingInCube());
    }
    
    public void update() {
    	SmartDashboard.putString("Intake current command", getCurrentCommandName());
    	defaultPower = SmartDashboard.getNumber("Intake power", defaultPower);
    }
    
    public static double getDefaultPower() {
    	return defaultPower;
    }
    
    public void setPower(double power) {
    	motor.set(power);
    }
    
    public void stop() {
    	motor.set(0);
    }
}