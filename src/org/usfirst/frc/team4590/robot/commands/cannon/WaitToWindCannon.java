package org.usfirst.frc.team4590.robot.commands.cannon;

import org.usfirst.frc.team4590.robot.subsystems.Cannon;
import org.usfirst.frc.team4590.robot.subsystems.Pitcher;

import edu.wpi.first.wpilibj.command.Command;

public class WaitToWindCannon extends Command {
	
	private static final long DELAY = 250;
	
	private boolean m_started = false;
	private long m_startTime;
	
	public WaitToWindCannon() {
		requires(Cannon.getInstance());
	}
	
	@Override
	protected void initialize() {
		m_startTime = -1;
	}
	
	@Override
	protected void execute() {
		Cannon.getInstance().setPower(0);
		
		if (!m_started && !Cannon.getInstance().isReadyToShoot() && Pitcher.getInstance().getAngle() < 90) {
			m_startTime = System.currentTimeMillis();
			m_started = true;
		}
	}
	
	@Override
	protected boolean isFinished() {
		return m_startTime != -1 &&
			   System.currentTimeMillis() - m_startTime >= DELAY;
	}
}