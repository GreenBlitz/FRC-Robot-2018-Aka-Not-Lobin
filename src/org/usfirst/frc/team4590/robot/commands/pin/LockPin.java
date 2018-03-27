package org.usfirst.frc.team4590.robot.commands.pin;

import org.usfirst.frc.team4590.robot.subsystems.Pin;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

public class LockPin extends Command {
	
	public LockPin(Value value) {
		requires(Pin.getInstance());
	}
	
	@Override
	protected void execute() {
		Pin.getInstance().setPiston(Value.kForward);
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
	
}
