package org.usfirst.frc.team4590.robot.commands.pin;

import org.usfirst.frc.team4590.robot.commands.commandChains.PitcherDownAfterCannon;
import org.usfirst.frc.team4590.robot.subsystems.Cannon;
import org.usfirst.frc.team4590.robot.subsystems.Pin;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class ShootToScale extends Command {
	
	private static final long TIMEOUT = 250;
	
	private long m_startTime;
	
	public ShootToScale() {
		requires(Pin.getInstance());
	}
	
	@Override
	protected void initialize() {
		m_startTime = System.currentTimeMillis();
	}
	
	@Override
	protected void execute() {
		Pin.getInstance().setPiston(Value.kReverse);
	}
	
	@Override
	protected boolean isFinished() {
		return System.currentTimeMillis() - m_startTime >= TIMEOUT;
	}
	
	@Override
	protected void end() {
		Cannon.getInstance().setStartedWinding(false);
		Cannon.getInstance().setReadyToShoot(false);
		Pin.getInstance().setPiston(Value.kForward);
		Scheduler.getInstance().add(new PitcherDownAfterCannon());
	}
}