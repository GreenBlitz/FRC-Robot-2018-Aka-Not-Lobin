package org.usfirst.frc.team4590.robot.commands.chassis;

import org.usfirst.frc.team4590.robot.subsystems.Chassis;
import org.usfirst.frc.team4590.utils.SmartJoystick;
import org.usfirst.frc.team4590.utils.SmartJoystick.JoystickAxis;

import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDriveByJoystick extends Command {
	private SmartJoystick m_stick;
	private final double mult = 1;
	
    public ArcadeDriveByJoystick(SmartJoystick stick) {
    	requires(Chassis.getInstance());
    	m_stick = stick;
    }

    protected void execute() {
    	Chassis.getInstance().arcadeDrive(m_stick.getAxisValue(JoystickAxis.LEFT_Y) * mult, 
    									  m_stick.getAxisValue(JoystickAxis.RIGHT_X) * mult);
    }

    protected boolean isFinished() {
        return false;
    }
    
    @Override
    protected void end() {
    	Chassis.getInstance().stop();;
    }
}