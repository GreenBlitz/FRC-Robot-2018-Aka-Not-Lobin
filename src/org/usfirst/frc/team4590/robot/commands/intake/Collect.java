package org.usfirst.frc.team4590.robot.commands.intake;

import org.usfirst.frc.team4590.robot.subsystems.Intake;

public class Collect extends IntakeCommand {
	
	private double m_power;
	
	public Collect(double power, long timeout) {
		super(timeout/1000d);
		requires(Intake.getInstance());
		m_power = -Math.abs(power);
	}
	
	public Collect(long timeout) {
		this(Intake.getDefaultPower(), timeout);
	}
	
	public Collect(double power) {
		requires(Intake.getInstance());
		m_power = -Math.abs(power);
	}
	
	public Collect() {
		this(Intake.getDefaultPower());
	}
	
    protected void executeCommand() {
    	Intake.getInstance().setPower(m_power);
    }

    protected boolean isFinished() {
    	return isTimedOut();
    }
    
    @Override
    protected void end() {
    	Intake.getInstance().stop();
    }
}