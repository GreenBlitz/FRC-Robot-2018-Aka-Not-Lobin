package org.usfirst.frc.team4590.robot.commands.pin;

import org.usfirst.frc.team4590.robot.commands.commandChains.PitcherDownAfterCannon;
import org.usfirst.frc.team4590.robot.subsystems.Cannon;
import org.usfirst.frc.team4590.robot.subsystems.Claw;
import org.usfirst.frc.team4590.robot.subsystems.Pin;
import org.usfirst.frc.team4590.robot.subsystems.Pitcher;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class ShootToScale extends Command {
	
	private static final long TIMEOUT = 500;
	
	private long m_startTime;
	private boolean shouldRun;
	
	public ShootToScale() {
		requires(Pin.getInstance());
	}
	
	@Override
	protected void initialize() {
		m_startTime = System.currentTimeMillis();
		boolean isSafeToShoot = (Pitcher.getInstance().getAngle() > 170 && 
				Claw.getInstance().isOpen()) || 
				Pitcher.getInstance().getAngle() < 90;
		
		shouldRun = Cannon.getInstance().isReadyToShoot() && isSafeToShoot;
	}
	
	@Override
	protected void execute() {
		if (shouldRun)
			Pin.getInstance().setPiston(Value.kReverse);
	}
	
	@Override
	protected boolean isFinished() {
		return !shouldRun || System.currentTimeMillis() - m_startTime >= TIMEOUT;
	}
	
	@Override
	protected void end() {
		if (shouldRun) {
			Cannon.getInstance().setStartedWinding(false);
			Cannon.getInstance().setReadyToShoot(false);
			Pin.getInstance().setPiston(Value.kForward);
			Scheduler.getInstance().add(new PitcherDownAfterCannon());
			System.out.println("shot to scale");
		}
	}
}