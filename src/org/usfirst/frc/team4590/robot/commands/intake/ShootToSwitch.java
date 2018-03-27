package org.usfirst.frc.team4590.robot.commands.intake;

import org.usfirst.frc.team4590.robot.subsystems.Intake;

public class ShootToSwitch extends IntakeCommand {

	private static final double POWER = 1;
	
	public ShootToSwitch(long timeout){
		super(timeout/1000d);
		requires(Intake.getInstance());
	}
	
	public ShootToSwitch() {
		requires(Intake.getInstance());
	}
	
	@Override
	protected void initialize() {
		System.out.println("Shooting cube");
	}
	
    protected void executeCommand() {
    	Intake.getInstance().setPower(POWER);
    }
    
    @Override
    protected boolean isFinished() {
    	return isTimedOut();
    }
    
    protected void end() {
    	Intake.getInstance().stop();
    }
}