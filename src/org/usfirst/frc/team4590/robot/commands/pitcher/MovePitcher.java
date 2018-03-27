package org.usfirst.frc.team4590.robot.commands.pitcher;
	
import org.usfirst.frc.team4590.robot.Robot;
import org.usfirst.frc.team4590.robot.commands.claw.AcceleratedCloseClaw;
import org.usfirst.frc.team4590.robot.commands.claw.ClosingClawCommand;
import org.usfirst.frc.team4590.robot.commands.intake.Collect;
import org.usfirst.frc.team4590.robot.subsystems.Cannon;
import org.usfirst.frc.team4590.robot.subsystems.Claw;
import org.usfirst.frc.team4590.robot.subsystems.Pin;
import org.usfirst.frc.team4590.robot.subsystems.Pitcher;
import org.usfirst.frc.team4590.utils.enums.PitcherDirection;
import org.usfirst.frc.team4590.utils.enums.PitcherState;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MovePitcher extends Command implements PitcherCommand {
	private static final double ABSOLUTE_TOLARENCE = 5d/180, 
								UP_MOVE_POWER = 0.7,
								DOWN_MOVE_POWER = 0.7,
								STATIC_POWER = 0.15,
								GRAVITY_DEFLECTOR_POWER = 0.3,
								DECEL_RANGE = 40d/180;
	
	private static final long ACCEL_TIME = 200;

	private static final long DOWN_ITERRATIONS = 2;
	
	private final PitcherState m_unchangedToState;
	private long m_start;
	private PitcherState m_toState;
	private double m_toPosition, m_toAngle;
	private int iterration;
	
	public MovePitcher(PitcherState toState) {
		requires(Pitcher.getInstance());
		m_unchangedToState = toState;
	}
	
	@Override
	protected void initialize() {
		System.out.println(m_unchangedToState.name());
		setToState(m_unchangedToState);
		
//		if (Robot.getInstance().isEndgame() && m_toState != PitcherState.SWITCH_BACKWARD) {
//			setToState(PitcherState.SWITCH_BACKWARD);
//			System.out.println("Cannot move pitcher to anywhere but the"
//					+ " SWITCH_BACKWARD position during endgame.");
//		}
		
		if (Pin.getInstance().isWorking() && !Cannon.getInstance().isPlatformDown() && 
			(m_toState == PitcherState.SWITCH_BACKWARD || m_toState == PitcherState.PLATE)) {
			setToState(PitcherState.COLLECT);
			System.out.println("Cannot move pitcher to SWITCH_BACKWARD or PLATE "
					+ "when the cannon platform is not down.");
		}
		
//		System.out.println("Moving pitcher to " + m_toState.name());
		
		if (m_toState == PitcherState.PLATE) 
			Scheduler.getInstance().add(new Collect(0.1, 2000));
			
		iterration = 0;
		
		m_start = System.currentTimeMillis();
		
		boolean goingUp = m_toPosition > Pitcher.getInstance().getPosition(),
				isClosing = Claw.getInstance().getCurrentCommand() instanceof ClosingClawCommand;
		
		Pitcher.getInstance().setDirection(goingUp ? PitcherDirection.UP : PitcherDirection.DOWN);
		
		if(isClosing) {
			Scheduler.getInstance().add(new AcceleratedCloseClaw(500, true));
		}
		else if (((goingUp && Pitcher.getInstance().getAngle() < 90 && m_toAngle > 90) ||
				(!goingUp && Pitcher.getInstance().getAngle() > 120 && m_toAngle < 120)))
			Scheduler.getInstance().add(new AcceleratedCloseClaw(500, Pitcher.getInstance().getAngle() > 170));
	}

	@Override
	protected void execute() {
		boolean goingUp = Pitcher.getInstance().getDirection() == PitcherDirection.UP;
	
		double movePower = goingUp ? UP_MOVE_POWER : DOWN_MOVE_POWER;
		
		if (iterration < DOWN_ITERRATIONS && !goingUp)
			Pitcher.getInstance().setPower(0.4);
		else if (!goingUp && Pitcher.getInstance().getAngle() < 90) {
			Pitcher.getInstance().setPower(0);
		}
		else {
			double offset = m_toPosition - Pitcher.getInstance().getPosition();
			if (Math.abs(offset) < DECEL_RANGE) {
				double power = ((movePower - STATIC_POWER) * Math.abs(offset) / DECEL_RANGE) + STATIC_POWER;
				Pitcher.getInstance().setPower(offset > 0 ? -power : power);
			}
			else {
				long accelTime = System.currentTimeMillis() - m_start;
				if (accelTime < ACCEL_TIME) {
					Pitcher.getInstance().setPower(offset > 0 ? 
							(-movePower * (ACCEL_TIME - accelTime) / ACCEL_TIME) : 
							(movePower * (ACCEL_TIME - accelTime) / ACCEL_TIME));
				}
				else
					Pitcher.getInstance().setPower(offset > 0 ? -movePower : movePower);
			}
		}
		
		iterration++;
	}

	protected double getGravityDeflector() {
		double ret = GRAVITY_DEFLECTOR_POWER;
		double equalibriumOffset = Pitcher.getInstance().getAngle() - Pitcher.EQUALIBRIUM_ANGLE;
		ret = Math.abs(ret * Math.sin(Math.toRadians(equalibriumOffset)));
		
		boolean direction = m_toPosition > Pitcher.getInstance().getPosition();
		if ((direction && equalibriumOffset > 0) || (!direction && equalibriumOffset < 0)) 
			ret *= -1;
		return ret;
	}
	
	@Override
	protected void end() {
		System.out.println("Moved pitcher to " + m_toState.name());
		Scheduler.getInstance().add(new HoldPitcher(m_toState));
	}
	
	@Override
	protected boolean isFinished() {
		return Math.abs(m_toPosition - Pitcher.getInstance().getPosition()) <= ABSOLUTE_TOLARENCE;
	}
	
	public void setToState(PitcherState state) {
		m_toState = state;
		setToPosition(state.getPosition());
	}
	
	public void setToPosition(double position) {
		m_toPosition = position;
		m_toAngle = position*180;
		SmartDashboard.putNumber("Pitcher toAngle", m_toPosition*180);
	}
	
	public PitcherState getState() {
		return m_toState;
	}
	
	public double getToPosition() {
		return m_toPosition;
	}
	
	public double getToAngle() {
		return m_toAngle;
	}
}