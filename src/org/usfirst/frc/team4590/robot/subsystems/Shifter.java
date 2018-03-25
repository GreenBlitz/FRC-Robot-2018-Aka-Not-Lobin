package org.usfirst.frc.team4590.robot.subsystems;

import org.usfirst.frc.team4590.robot.RobotMap;
import org.usfirst.frc.team4590.utils.ShifterState;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shifter extends Subsystem {

	private static Shifter instance;

	private DoubleSolenoid piston;
	
	public static Shifter getInstance() {
		return instance;
	}
	
	public static void init() {
		instance = new Shifter();
	}
	
	private Shifter() {
		piston = new DoubleSolenoid(RobotMap.SHIFTER_SOLENOID_FORWARD_PORT, 
									RobotMap.SHIFTER_SOLENOID_REVERSE_PORT);
	}

    public void initDefaultCommand() {}
    
    public void update() {
    	SmartDashboard.putString("Shifter current command", getCurrentCommandName());
    	SmartDashboard.putString("Shifter current shift", getShift().toString());
    }
    
    public void setShift(ShifterState newShift) {
    	piston.set(newShift.getValue());
    }
    
    public void switchShift() {
    	if (getShift() == ShifterState.POWER)
    		setShift(ShifterState.SPEED);
    	else
    		setShift(ShifterState.POWER);
    }
    
    public ShifterState getShift() {
    	if (piston.get() == ShifterState.POWER.getValue())
    		return ShifterState.POWER;
    	else if (piston.get() == ShifterState.SPEED.getValue())
    		return ShifterState.SPEED;
    	return ShifterState.OFF;
    }
}