package org.usfirst.frc.team4590.robot.commands.intake;

import org.usfirst.frc.team4590.robot.subsystems.Claw;
import org.usfirst.frc.team4590.robot.subsystems.Pitcher;

import edu.wpi.first.wpilibj.command.Command;

public abstract class IntakeCommand extends Command {
	
	public IntakeCommand() {}
	
	public IntakeCommand(double timeout) {
		super(timeout);
	}
	
	@Override
	protected final void execute() {
		if (Pitcher.getInstance().getAngle() > 170 && Claw.getInstance().isOpen())
			Claw.getInstance().setPower(0);
		else
			executeCommand();
	}
	
	protected void executeCommand() {}
}