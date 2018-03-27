package org.usfirst.frc.team4590.robot.commands.claw;

import org.usfirst.frc.team4590.robot.subsystems.Cannon;
import org.usfirst.frc.team4590.robot.subsystems.Pin;
import org.usfirst.frc.team4590.robot.subsystems.Pitcher;
import org.usfirst.frc.team4590.utils.enums.PitcherDirection;

import edu.wpi.first.wpilibj.command.Command;

public abstract class ClosingClawCommand extends Command {
	
	public ClosingClawCommand() {}
	
	public ClosingClawCommand(double timeout) {
		super(timeout);
	}
	
	@Override
	protected final void execute() {
		boolean shouldUnlockDownward = Pitcher.getInstance().getDirection() == PitcherDirection.DOWN
								 	   && Pitcher.getInstance().isSafeToOpenDownward(),
				shouldUnlockUpward = Pitcher.getInstance().getDirection() == PitcherDirection.UP
							   		 && Pitcher.getInstance().isSafeToOpenUpward(),
				shouldUnlockStationary = Pitcher.getInstance().getDirection() == PitcherDirection.STATIONARY;
	
//		setInterruptible(shouldUnlockStationary || shouldUnlockUpward || shouldUnlockDownward);
		
		if ((Pin.getInstance().isWorking() && Cannon.getInstance().isPlatformDown()) || 
			!Cannon.getInstance().hasStartedWinding() || 
			Pitcher.getInstance().getAngle() < 90)
			executeCommand();
		else
			System.out.println("Cant Close claw");
	}
	
	protected abstract void executeCommand();
}