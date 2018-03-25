package org.usfirst.frc.team4590.robot.commands.climber;

import org.usfirst.frc.team4590.robot.subsystems.Climber;
import org.usfirst.frc.team4590.utils.SmartJoystick;
import org.usfirst.frc.team4590.utils.SmartJoystick.JoystickAxis;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Allows the driver to control the climber with the right and left triggers
 * 
 * Use RT to move up and LT to move down.
 */
public class ControlClimbByTriggers extends Command {

	private static final double CLIMB_MULTIPLIER = 1;
	private SmartJoystick m_JS;
	
    public ControlClimbByTriggers(SmartJoystick JS) {
        requires(Climber.getInstance());
        m_JS = JS;
    }

    protected void execute() {
    	
    	double leftTrigger = m_JS.getAxisValue(JoystickAxis.LEFT_TRIGGER);
    	double rightTrigger = m_JS.getAxisValue(JoystickAxis.RIGHT_TRIGGER);
    	double power;
    	
//    	if(Climber.getInstance().isOnTop()) 
//    		power = -leftTrigger;
//    	else if (Climber.getInstance().isOnBottom())
//    		power = rightTrigger;
//    	else 
    	power = rightTrigger - leftTrigger;
    	
    	Climber.getInstance().setPower(power * CLIMB_MULTIPLIER);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Climber.getInstance().setPower(0);
    }
}