package org.usfirst.frc.team4590.robot.subsystems;

import org.usfirst.frc.team4590.robot.RobotMap;
import org.usfirst.frc.team4590.robot.commands.pitcher.MovePitcherToState;
import org.usfirst.frc.team4590.robot.commands.pitcher.PitcherCommand;
import org.usfirst.frc.team4590.utils.PitcherState;
import org.usfirst.frc.team4590.utils.SmartTalon;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Pitcher extends Subsystem {

	private static Pitcher instance;
	
	private static final int SAFE_TO_OPEN_BOTTOM_THRESHOLD = 0,
	   				   		 SAFE_TO_OPEN_TOP_THRESHOLD = 0;
	
	public static final double DOWN_ANGLE = 0,
							   UP_ANGLE = 180,
							   EQUALIBRIUM_ANGLE = 130;
	
	private static double DOWN_STATE,
						  UP_STATE;
								
	private SmartTalon motor;
	private AnalogPotentiometer potentiometer;
	
	public static Pitcher getInstance() {
		return instance;
	}
	
	public static void init() {
		instance = new Pitcher();
	}
	
	private Pitcher() {
		motor = new SmartTalon(RobotMap.PITCHER_MOTOR_PORT);
		potentiometer = new AnalogPotentiometer(RobotMap.PITCHER_POTENTIOMETER_PORT);
		UP_STATE = potentiometer.get();
		DOWN_STATE = UP_STATE - 0.693;
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new MovePitcherToState(PitcherState.PLATE));
    }
    
    public void update() {
    	SmartDashboard.putString("Pitcher current command", getCurrentCommandName());
    	SmartDashboard.putNumber("Pitcher currentAngle", getPosition()*180);
    	SmartDashboard.putNumber("Pitcher currentPosition", getPosition());
    	SmartDashboard.putNumber("Pitcher toPosition", getCurrentCommand() instanceof MovePitcherToState ? 
    			(((MovePitcherToState) getCurrentCommand()).getToPosition()) : -1 );
    }

    public void setPower(double power) {
    	motor.set(power);
    	SmartDashboard.putNumber("Pitcher setPower", power);
    }
    
    public void stop() {
    	setPower(0);
    }
    
    public double getPosition() {
    	return (potentiometer.get() - DOWN_STATE) / (UP_STATE - DOWN_STATE);
    }
    
    public double getAngle() {
    	return getPosition() * 180;
    }
    
    public boolean isSafeToOpenBottom() {
		return getPosition() < SAFE_TO_OPEN_BOTTOM_THRESHOLD;
	}
	
	public boolean isSafeToOpenTop() {
		return getPosition() > SAFE_TO_OPEN_TOP_THRESHOLD;
	}
    
    public boolean isSafeToOpen() {
    	return isSafeToOpenBottom() || isSafeToOpenTop();
    }
    
    @Override
    public String getCurrentCommandName() {
    	Command currentCommand = getCurrentCommand();
        if (currentCommand != null) {
        	if (currentCommand instanceof PitcherCommand)
        		return currentCommand.getName() + ": " + ((PitcherCommand) currentCommand).getToState();
        	return currentCommand.getName();
        } 
        else
          return "";
    }
}