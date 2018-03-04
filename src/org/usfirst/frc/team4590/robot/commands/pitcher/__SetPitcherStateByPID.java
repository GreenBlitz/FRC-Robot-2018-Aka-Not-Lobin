//package org.usfirst.frc.team4590.robot.commands.pitcher;
//
//
//import org.usfirst.frc.team4590.robot.subsystems.Pitcher;
//import org.usfirst.frc.team4590.utils.PitcherState;
//
//import edu.wpi.first.wpilibj.PIDController;
//import edu.wpi.first.wpilibj.PIDOutput;
//import edu.wpi.first.wpilibj.PIDSource;
//import edu.wpi.first.wpilibj.PIDSourceType;
//import edu.wpi.first.wpilibj.command.Command;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//
///*TODO: 
// *	edit the pidOutput method to multiply the output 
// * 	by the current angle of the pitcher. 
// * 	Test if just multiplying by the cosine of the angle works.
// * 
// * 	Find a solution for the loss of the Pitcher encoder reset function
// * 	due to it being changed from an encoder to a potentiometer,
// * 	which does not have a reset capability.
// */
///**
// * Will close the intake on its own in order to not destroy the robot.
// */
//public class __SetPitcherStateByPID extends Command implements PIDSource, PIDOutput{
//
//	private static final double ABSOLUTE_TOLERANCE = 0,
//								kP = 0.97, 
//								kI = 0, 
//								kD = 0;
//	
//	private PitcherState toState;
//	private double toPosition;
//
//	private static final double DOWNWARD_CLOSE_THRESHOLD = PitcherState.DOWNWARD_CLOSE.getPosition(), 
//								TOP_THRESHOLD = PitcherState.UPWARD_CLOSE.getPosition(),
//								ANGLE_RANGE = 0;
//	
//	private PIDController controller;
//
//	public __SetPitcherStateByPID(PitcherState state) {
//		requires(Pitcher.getInstance());
//		toState = state;
//		toPosition = toState.getPosition();
//		controller = new PIDController(kP, kI, kD, this, this);
//	}
//
//	public double pidGet() {
//		return Pitcher.getInstance().getPosition();
//	}
//
//	//TODO
//	@Override
//	public void pidWrite(double output) {
////		Pitcher.getInstance().setPower(
////				Math.cos(Math.toRadians(Pitcher.getInstance().getPosition() * 180)) * output);
//		Pitcher.getInstance().setPower(-output);
//	}
//
//
//	@Override
//	public void initialize() {
//		controller.setAbsoluteTolerance(ABSOLUTE_TOLERANCE);
//		controller.setSetpoint(toPosition);
//		controller.setOutputRange(-0.5, 0.5);
//		controller.enable();
//	}
//
//	//TODO
//	@Override
//	public void execute() {
//		if (controller.onTarget()) {
//			Pitcher.getInstance().setState(toState);
////			if (toState == PitcherState.COLLECT)
////				Pitcher.getInstance().resetEncoder();
//		}
//		
//		double tmpPosition = SmartDashboard.getNumber("Pitcher toAngle", toPosition*180);
//		if (Math.abs(toPosition*180 - tmpPosition) >= 5)
//			setToState(tmpPosition/180);
//		
////		double currAngle = pidGet();
////		boolean direction = currAngle < toPosition;
////		if ((direction && Math.abs(currAngle - DOWNWARD_CLOSE_THRESHOLD) > ANGLE_RANGE) ||
////		   (!direction && Math.abs(currAngle - TOP_THRESHOLD) > ANGLE_RANGE))
////			Scheduler.getInstance().add(new CloseClaw());
//	}
//	
//	@Override
//	protected boolean isFinished() {
//		return false;
//	}
//
//	public void setToState(PitcherState state) {
//		toState = state;
//		toPosition = state.getPosition();
//
//		controller.reset();
//		controller.setSetpoint(toPosition);
//		controller.enable();
//	}
//	
//	public void setToState(double position) {
//		toPosition = position;
//
//		controller.reset();
//		controller.setSetpoint(toPosition);
//		controller.enable();
//	}
//	
//	public PitcherState getToState() {
//		return toState;
//	}
//	
//	public double getToPosition() {
//		return toPosition;
//	}
//
//	@Override
//	public void setPIDSourceType(PIDSourceType pidSource) {}
//
//	@Override
//	public PIDSourceType getPIDSourceType() {
//		return PIDSourceType.kDisplacement;
//	}
//	
//	@Override
//	protected void end() {
//		Pitcher.getInstance().setPower(0);
//	}
//}