package org.usfirst.frc.team4590.robot.commands.chassis;

import org.usfirst.frc.team4590.robot.subsystems.Chassis;
import org.usfirst.frc.team4590.utils.SmartJoystick;
import org.usfirst.frc.team4590.utils.SmartJoystick.JoystickAxis;

import edu.wpi.first.wpilibj.command.Command;

public class TankDriveByJoystick extends Command {
	private SmartJoystick m_stick;
	
    public TankDriveByJoystick(SmartJoystick stick) {
    	requires(Chassis.getInstance());
    	m_stick = stick;
    }

    protected void execute() {
    	Chassis.getInstance().tankDrive(m_stick.getAxisValue(JoystickAxis.LEFT_Y), 
    									m_stick.getAxisValue(JoystickAxis.RIGHT_Y));
    }

    protected boolean isFinished() {
        return false;
    }
    
    @Override
    protected void end() {
    	Chassis.getInstance().tankDrive(0, 0);
    }
}