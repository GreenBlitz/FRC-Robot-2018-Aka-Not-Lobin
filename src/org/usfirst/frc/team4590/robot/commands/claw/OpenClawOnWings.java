package org.usfirst.frc.team4590.robot.commands.claw;

import org.usfirst.frc.team4590.robot.subsystems.Claw;

import edu.wpi.first.wpilibj.command.Command;

public class OpenClawOnWings extends Command {

	private static final double VOLTAGE = 8;
	
	public OpenClawOnWings() {
		requires(Claw.getInstance());
	}
	
	@Override
	protected void initialize() {
		Claw.getInstance().setVoltageCompensation(VOLTAGE);
	}

	public OpenClawOnWings(long timeout) {
		super(timeout/1000d);
		requires(Claw.getInstance());
	}
	
	@Override
	protected void execute() {
		Claw.getInstance().openWings(-0.4);
	}
	
	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}
	
	@Override
	protected void end() {
		Claw.getInstance().setVoltageCompensation(Claw.VOLTAGE_LIMIT);
		Claw.getInstance().stop();
	}
}
