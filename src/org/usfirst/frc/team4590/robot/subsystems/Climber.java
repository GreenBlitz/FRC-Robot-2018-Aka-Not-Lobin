package org.usfirst.frc.team4590.robot.subsystems;

import org.usfirst.frc.team4590.robot.OI;
import org.usfirst.frc.team4590.robot.RobotMap;
import org.usfirst.frc.team4590.robot.commands.climber.ControlClimbByTriggers;
import org.usfirst.frc.team4590.utils.SmartTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Climber extends Subsystem {

	private static Climber instance;
	
	private static double defaultPower = 1;
	
	private SmartTalon motor;
	private DigitalInput isBottom;
	private DigitalInput isTop;
	
	public static Climber getInstance() {
		return instance;
	}
	
	public static void init() {
		instance = new Climber();
	}
	
	private Climber() {
		motor = new SmartTalon(RobotMap.CLIMBER_MOTOR_PORT);
		isBottom = new DigitalInput(RobotMap.CLIMBER_TOP_MICROSWITCH_PORT);
		isTop = new DigitalInput(RobotMap.CLIMBER_BOTTOM_MICROSWITCH_PORT);
		SmartDashboard.putNumber("Climber power", defaultPower);
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new ControlClimbByTriggers(OI.getInstance().getSideJS()));
    }
    
    public void update() {
    	defaultPower = SmartDashboard.getNumber("Climber power", defaultPower);
    	SmartDashboard.putBoolean("isOnTop", isOnTop());
    	SmartDashboard.putBoolean("isOnBottom", isOnBottom());
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
    
    /**
     * @return if the climber is at the button and the microswitch is pressed
     */
    public boolean isOnBottom() {
    	return isBottom.get();
    }
    
    /**
     * @return if the climber is at the top and the microswitch is pressed
     */
    public boolean isOnTop() {
    	return isTop.get();
    }
}