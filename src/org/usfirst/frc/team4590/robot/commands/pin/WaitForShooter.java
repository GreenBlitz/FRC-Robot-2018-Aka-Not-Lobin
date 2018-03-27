package org.usfirst.frc.team4590.robot.commands.pin;

import org.usfirst.frc.team4590.robot.subsystems.Cannon;
import org.usfirst.frc.team4590.robot.subsystems.Claw;
import org.usfirst.frc.team4590.robot.subsystems.Pin;
import org.usfirst.frc.team4590.robot.subsystems.Pitcher;
import org.usfirst.frc.team4590.utils.enums.PitcherState;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class WaitForShooter extends Command {

	public WaitForShooter() {
		requires(Pin.getInstance());
	}

	@Override
	protected void execute() {
		boolean isSafeToShoot = (Pitcher.getInstance().getCurrentState() == PitcherState.PLATE && 
								Claw.getInstance().isOpen()) || 
								Pitcher.getInstance().getAngle() < 90;

		setInterruptible(Cannon.getInstance().isReadyToShoot() && isSafeToShoot);
	
		SmartDashboard.putBoolean("isSafeToShoot", isSafeToShoot);
		SmartDashboard.putBoolean("waitforshooter isinterruptble", isInterruptible());
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
}