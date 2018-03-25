package org.usfirst.frc.team4590.robot.commands.pin;

import org.usfirst.frc.team4590.robot.subsystems.Pin;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

public class SetPin extends Command {
	
	private Value m_value;
	
	public SetPin(Value value) {
		requires(Pin.getInstance());
		m_value = value;
	}
	
	@Override
	protected void execute() {
		Pin.getInstance().setPiston(m_value);
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
	
}
