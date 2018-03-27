package org.usfirst.frc.team4590.robot.commands;

import org.usfirst.frc.team4590.utils.CTRE.TalonManager;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class FakeDisable extends Command {
	
	@Override
	protected void initialize() {
		TalonManager.setAllTalons(0);
		Scheduler.getInstance().removeAll();
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
	
}
