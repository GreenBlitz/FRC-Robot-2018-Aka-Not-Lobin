package org.usfirst.frc.team4590.robot.subsystems;

import org.usfirst.frc.team4590.robot.RobotMap;
import org.usfirst.frc.team4590.robot.commands.pitcher.HoldPitcher;
import org.usfirst.frc.team4590.robot.commands.pitcher.MovePitcher;
import org.usfirst.frc.team4590.robot.commands.pitcher.PitcherCommand;
import org.usfirst.frc.team4590.utils.PitcherState;
import org.usfirst.frc.team4590.utils.SmartTalon;

import edu.wpi.first.wpilibj.AnalogInput;
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
							   EQUALIBRIUM_ANGLE = 130,
							   DOWN_STATE = 0.763,
							   UP_STATE = 0.147;
								
	private SmartTalon motor;
	private AnalogPotentiometer potentiometer;
	private AnalogInput analog;
	
	public static Pitcher getInstance() {
		return instance;
	}
	
	public static void init() {
		instance = new Pitcher();
	}
	
	private Pitcher() {
		motor = new SmartTalon(RobotMap.PITCHER_MOTOR_PORT);
		analog = new AnalogInput(RobotMap.PITCHER_POTENTIOMETER_PORT);
		potentiometer = new AnalogPotentiometer(analog);
	}

    public void initDefaultCommand() {}
    
    public void update() {
    	SmartDashboard.putString("Pitcher current command", getCurrentCommandName());
    	SmartDashboard.putNumber("analoginput raw voltage", analog.getVoltage());
    	SmartDashboard.putNumber("analog raw value", analog.getValue());
    	SmartDashboard.putNumber("Potentiometer raw value", potentiometer.get());
    	SmartDashboard.putNumber("Pitcher currentAngle", getPosition()*180);
    	SmartDashboard.putNumber("Pitcher currentPosition", getPosition());
    	SmartDashboard.putNumber("Pitcher toPosition", getCurrentCommand() instanceof MovePitcher ? 
    			(((MovePitcher) getCurrentCommand()).getToPosition()) : -1 );
    }

    public void setPower(double power) {
    	motor.set(power);
    	SmartDashboard.putNumber("Pitcher setPower", power);
    }
    
    public void stop() {
    	setPower(0);
    }
    
    public PitcherState getCurrentState() {
    	if (getCurrentCommand() instanceof HoldPitcher)
    		return ((HoldPitcher) getCurrentCommand()).getState();
    	else 
    		return null;
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
        		return currentCommand.getName() + ": " + ((PitcherCommand) currentCommand).getState();
        	return currentCommand.getName();
        } 
        else
          return "";
    }
}