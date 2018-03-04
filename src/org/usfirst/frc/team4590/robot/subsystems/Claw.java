package org.usfirst.frc.team4590.robot.subsystems;

import org.usfirst.frc.team4590.robot.RobotMap;
import org.usfirst.frc.team4590.utils.SmartTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Claw extends Subsystem {

	private static Claw instance;
	
	private static double defaultPower = 0.2;
//	private static int currentLimit = 0;
	
	private SmartTalon motor;
	private DigitalInput openMicroswitch, closedMicroswitch;
	
	public static Claw getInstance() {
		return instance;
	}
	
	public static void init() {
		instance = new Claw();
	}
	
	private Claw() {
		motor = new SmartTalon(RobotMap.CLAW_MOTOR_PORT);
//		motor.configPeakCurrentLimit(currentLimit, 10);	
		openMicroswitch = new DigitalInput(RobotMap.CLAW_OPEN_MICROSWITCH_PORT);
		closedMicroswitch = new DigitalInput(RobotMap.CLAW_CLOSED_MICROSWITCH_PORT);
//		SmartDashboard.putNumber("Claw power", defaultPower);
//		SmartDashboard.putNumber("Claw current limit", currentLimit);
	}
	
    public void initDefaultCommand() {}
    
    public void update() {
    	SmartDashboard.putString("Claw current command", getCurrentCommandName());
    	defaultPower = SmartDashboard.getNumber("Claw powe", defaultPower);
//    	int tmpCurrentLimit = (int) SmartDashboard.getNumber("Claw current limit", currentLimit);
//    	if (tmpCurrentLimit != currentLimit) {
//    		currentLimit = tmpCurrentLimit;
//    		motor.configPeakCurrentLimit(currentLimit, 10);
//    	}
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
    
    public boolean isOpen() {
    	return openMicroswitch.get();
    }
    
    public boolean isClosed() {
    	return closedMicroswitch.get();
    }
}