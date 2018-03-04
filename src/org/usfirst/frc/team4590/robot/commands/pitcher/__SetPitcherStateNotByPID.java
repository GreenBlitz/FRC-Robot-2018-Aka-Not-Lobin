//package org.usfirst.frc.team4590.robot.commands.pitcher;
//
//import org.usfirst.frc.team4590.robot.commands.claw.CloseClaw;
//import org.usfirst.frc.team4590.robot.subsystems.Claw;
//import org.usfirst.frc.team4590.robot.subsystems.Pitcher;
//import org.usfirst.frc.team4590.utils.PitcherState;
//
//import edu.wpi.first.wpilibj.command.Command;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//
//public class __SetPitcherStateNotByPID extends Command {
//
//	private static final double ABSOLUTE_TOLARENCE = 5d/180, 
//								MOVE_POWER = 0.6,
//								STATIC_POWER = 0.11,
//								DECEL_RANGE = 20d/180,
//								DOWNWARD_THRESHOLD = PitcherState.DOWNWARD_CLOSE.getPosition(),
//								UPWARD_THRESHOLD = PitcherState.UPWARD_CLOSE.getPosition(),
//								CLOSE_RANGE = 0.2,
//								EQUALIBRIUM_ANGLE = 150;
//	private static final long ACCEL_TIME = 200;
//	
//	private long m_start;
//	
//	private PitcherState m_toState;
//	private double m_toPosition;
//	
//	private boolean m_onCollect = true;
//	
//	public __SetPitcherStateNotByPID(PitcherState state) {
//		requires(Pitcher.getInstance());
//		setToState(state);
//	}
//	
//	@Override
//	protected void execute() {
//		if (!m_onCollect) {
//			double positionOffset = m_toPosition - Pitcher.getInstance().getPosition();
//			if (Math.abs(positionOffset) >= ABSOLUTE_TOLARENCE)
//				moveToPosition(positionOffset);
//			else 
//				stayOnPosition();
//		}
//
//		getNewSpecificAngle();
//		
////		closeClaw();
//	}
//	
//	private void moveToPosition(double offset) {
//		if (Math.abs(offset) < DECEL_RANGE) {
//			double power = ((MOVE_POWER - STATIC_POWER) * Math.abs(offset) / DECEL_RANGE) + STATIC_POWER;
//			Pitcher.getInstance().setPower(offset > 0 ? -power : power);
//		}
//		else {
//			long accelTime = System.currentTimeMillis() - m_start;
//			if (accelTime < ACCEL_TIME) {
//				Pitcher.getInstance().setPower(offset > 0 ? 
//						-MOVE_POWER * (ACCEL_TIME - accelTime) / ACCEL_TIME : 
//							MOVE_POWER * (ACCEL_TIME - accelTime) / ACCEL_TIME);
//			}
//			else
//				Pitcher.getInstance().setPower(offset > 0 ? -MOVE_POWER : MOVE_POWER);
//		}
//	}
//	
//	private void stayOnPosition() {
//		if (m_toPosition <= 10d/180) {
//			m_onCollect = true;
//			Pitcher.getInstance().setPower(0);
//		}
//		else {
//			double equalibriumOffset = Pitcher.getInstance().getAngle() - EQUALIBRIUM_ANGLE;
//			Pitcher.getInstance().setPower(STATIC_POWER * Math.sin(Math.toRadians(equalibriumOffset)));
//		}
//	}
//	
//	private void closeClaw() {
//		double curPosition = Pitcher.getInstance().getPosition();
//		boolean direction = Math.abs(curPosition - m_toPosition) > 5d/180;
//		if ((direction && Math.abs(curPosition - DOWNWARD_THRESHOLD) < CLOSE_RANGE) ||
//		   (!direction && Math.abs(curPosition - UPWARD_THRESHOLD) < CLOSE_RANGE)) {
//			if (!(Claw.getInstance().getCurrentCommand() instanceof CloseClaw))
//				SmartDashboard.putString("Intake", "Wants to close");
////			Scheduler.getInstance().add(new CloseClaw());
//		}
//		else
//			SmartDashboard.putString("Intake", "Doesnt want to close");
//	}
//	
//	private void getNewSpecificAngle() {
//		double tmpPosition = SmartDashboard.getNumber("Pitcher toAngle", m_toPosition*180);
//		if (Math.abs(m_toPosition*180 - tmpPosition) >= 5)
//			setToPosition(tmpPosition/180);
//	}
//	
//	@Override
//	protected boolean isFinished() {
//		return false;
//	}
//	
//	@Override
//	protected void end() {
//		Pitcher.getInstance().setPower(0);
//	}
//	
//	public void setToState(PitcherState state) {
//		m_toState = state;
//		setToPosition(state.getPosition());
//	}
//	
//	public void setToPosition(double position) {
//		if (position >= 10d/180)
//			m_onCollect = false;
//		m_toPosition = position;
//		m_start = System.currentTimeMillis();
//		SmartDashboard.putNumber("Pitcher toAngle", m_toPosition*180);
//	}
//	
//	public PitcherState getToState() {
//		return m_toState;
//	}
//	
//	public double getToPosition() {
//		return m_toPosition;
//	}
//}