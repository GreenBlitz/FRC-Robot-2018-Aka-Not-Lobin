package org.usfirst.frc.team4590.robot.commands.pitcher;

import org.usfirst.frc.team4590.robot.Robot;
import org.usfirst.frc.team4590.robot.commands.claw.CloseClaw;
import org.usfirst.frc.team4590.robot.subsystems.Claw;
import org.usfirst.frc.team4590.robot.subsystems.Pitcher;
import org.usfirst.frc.team4590.utils.enums.PitcherState;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class GuydeMovePitcher extends Command implements PitcherCommand {
//	private static final double UP_MOVE_POWER = 0.6,
//								DOWN_MOVE_POWER = 0.5,
//								STATIC_POWER = 0.17;
//	private long m_start;
	
	protected static final double GRAVITY_DEFLECTOR_POS = -0.14,
				GRAVITY_DEFLECTOR_NEG = -0.14;

	protected static final double ABSOLUTE_TOLARENCE = 5d/180;
	protected static final double CENTER_OF_MASS_ANGLE = 45;
	
	private PitcherState m_toState;
	
	public GuydeMovePitcher(PitcherState toState) {
		m_toState = toState;
	}
	
	@Override
	protected void initialize() {
		if (Robot.getInstance().isEndgame() && m_toState != PitcherState.SWITCH_BACKWARD) {
			m_toState = PitcherState.SWITCH_BACKWARD;
			System.out.println("Cannot move pitcher to anywhere but the"
					+ " SWITCH_BACKWARD position during endgame.");
		}
		
//		m_start = System.currentTimeMillis();
		
		boolean goingUp = m_toState.getAngle() > Pitcher.getInstance().getPosition(),
				isClawClosing = Claw.getInstance().isClosing();
	
		if (!isClawClosing && 
		   ((goingUp && Pitcher.getInstance().getAngle() < 90 && m_toState.getAngle() > 90) ||
		   (!goingUp && Pitcher.getInstance().getAngle() > 120 && m_toState.getAngle() < 120))) 
			Scheduler.getInstance().add(new CloseClaw(1000l));
	}

	@Override
	protected void execute() {
		double gravityDeflector = getGravityDeflector(Pitcher.getInstance().getAngle());
		boolean goingUp = m_toState.getPosition() > Pitcher.getInstance().getPosition();
	
		if (!goingUp && m_toState == PitcherState.COLLECT && Pitcher.getInstance().getAngle() < 90) 
			Pitcher.getInstance().stop();
		
		else {
			double angleOffset = m_toState.getAngle() - Pitcher.getInstance().getAngle();
			double powDiff = angleOffset * (-1.5/180);
			double maxDiff = 0.8;
			if (maxDiff < Math.abs(powDiff)) 
				powDiff = maxDiff * Math.signum(powDiff);
			double power = gravityDeflector + powDiff;
			double maxPower = 0.65;
			if (maxPower < Math.abs(power))
				power = maxPower * Math.signum(power);
			
			Pitcher.getInstance().setPower(power);
		}
	}

	@Override
	protected void end() {
		Scheduler.getInstance().add(new GuydeHoldPitcher(m_toState));
	}
	
	@Override
	protected boolean isFinished() {
		return Math.abs(m_toState.getPosition() - Pitcher.getInstance().getPosition()) <= ABSOLUTE_TOLARENCE;
	}
	
	public PitcherState getState() {
		return m_toState;
	}
	
	   protected double getGravityDeflector(double angle) {
			double ret;
			if (angle > 150)
				ret = -Math.cos(Math.toRadians(angle + CENTER_OF_MASS_ANGLE)) * GRAVITY_DEFLECTOR_NEG;
			else if (angle > 110){
				ret = 0;
			} else
				ret = Math.cos(Math.toRadians(angle + CENTER_OF_MASS_ANGLE)) * GRAVITY_DEFLECTOR_POS;
			return ret;
		}
}