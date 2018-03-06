package org.usfirst.frc.team4590.robot.commands.claw;

import org.usfirst.frc.team4590.robot.subsystems.Claw;
import org.usfirst.frc.team4590.utils.ControlledMotorAccelerator;
import org.usfirst.frc.team4590.utils.MotorAccelerationFunction;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class CloseClawControlled extends Command implements ClosingClawCommand {

	private boolean m_isFinished = false;
	
	private ControlledMotorAccelerator m_accelerator;
	
	private double target;
	
	private long finishedTime = 0l;
	
	private long maxPowerDuration = 100l; //milliseconds
	
	public CloseClawControlled(double target) {
		requires(Claw.getInstance());
		this.target = target;
	}
	
	public void initialize(){
		m_accelerator = new ControlledMotorAccelerator(Claw.getInstance().getMotor(), 
				new MotorAccelerationFunction.AccelLimited(0.05));
		m_accelerator.setTarget(target);
		m_isFinished = false;
		finishedTime = 0;
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	public void execute(){
		if (m_isFinished){
			Claw.getInstance().setPower(-Claw.getDefaultPower());
		}
		m_accelerator.run();
		if (m_accelerator.isOnTarget()){
			if (finishedTime <= 0l) finishedTime = System.currentTimeMillis();
			if (System.currentTimeMillis() - finishedTime >= maxPowerDuration) 
				m_isFinished = true;
		}
	}
	
	public void end(){ Claw.getInstance().setPower(0);}
	

}
