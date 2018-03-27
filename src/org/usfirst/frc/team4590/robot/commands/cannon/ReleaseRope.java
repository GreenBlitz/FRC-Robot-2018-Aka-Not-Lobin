package org.usfirst.frc.team4590.robot.commands.cannon;

import org.usfirst.frc.team4590.robot.subsystems.Cannon;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class ReleaseRope extends Command {
	
	private static final long TIMEOUT = 5000,
							  DELAY = 100;
	
	private long m_startTime;
	
	public ReleaseRope() {
		requires(Cannon.getInstance());
//		setInterruptible(false);
	}

	@Override
	protected void initialize() {
		m_startTime = System.currentTimeMillis();
	}
	
	@Override
	protected void execute() {
		long timeSinceInitialized = System.currentTimeMillis() - m_startTime;
		
		if (timeSinceInitialized > DELAY && !Cannon.getInstance().isRopeLoose())
			Cannon.getInstance().setPower(-Cannon.getDefaultPower());
		else
			Cannon.getInstance().setPower(0);
	}
	
	@Override
	protected boolean isFinished() {
		long timeSinceInitialized = System.currentTimeMillis() - m_startTime;

		return Cannon.getInstance().isRopeLoose() || timeSinceInitialized >= TIMEOUT;
	}

	@Override
	protected void end() {
		Cannon.getInstance().stop();
		Scheduler.getInstance().add(new AddedThingy());
	}
}