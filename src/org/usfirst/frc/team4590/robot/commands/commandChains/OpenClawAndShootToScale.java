package org.usfirst.frc.team4590.robot.commands.commandChains;

import org.usfirst.frc.team4590.robot.commands.claw.OpenClawOnPlate;
import org.usfirst.frc.team4590.robot.commands.pin.ShootToScale;
import org.usfirst.frc.team4590.utils.commandChain.CommandChain;

import edu.wpi.first.wpilibj.command.Command;

public class OpenClawAndShootToScale extends CommandChain {
	public OpenClawAndShootToScale() {
		Command openClaw = new OpenClawOnPlate(),
				shoot = new ShootToScale();
		
		addCommand(openClaw);
		addSequential(shoot);
	}
}
