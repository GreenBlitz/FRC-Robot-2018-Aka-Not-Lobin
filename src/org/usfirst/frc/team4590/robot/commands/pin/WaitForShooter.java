package org.usfirst.frc.team4590.robot.commands.pin;

import org.usfirst.frc.team4590.robot.subsystems.Cannon;
import org.usfirst.frc.team4590.robot.subsystems.Claw;
import org.usfirst.frc.team4590.robot.subsystems.Pin;
import org.usfirst.frc.team4590.robot.subsystems.Pitcher;
import org.usfirst.frc.team4590.utils.PitcherState;

import edu.wpi.first.wpilibj.command.Command;

public class WaitForShooter extends Command {

	public WaitForShooter() {
		requires(Pin.getInstance());
	}

	@Override
	protected void execute() {
		setInterruptible(Cannon.getInstance().isReadyToShoot() && 
						 Pitcher.getInstance().getCurrentState() == PitcherState.PLATE && 
						 Claw.getInstance().isOpen());
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
}