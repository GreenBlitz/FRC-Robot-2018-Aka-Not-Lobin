package org.usfirst.frc.team4590.robot.commands.commandChains;

import org.usfirst.frc.team4590.robot.commands.claw.AcceleratedOpenClaw;
import org.usfirst.frc.team4590.robot.commands.pin.ShootToScale;
import org.usfirst.frc.team4590.utils.commandChain.CommandChain;

import edu.wpi.first.wpilibj.command.Command;

public class OpenClawAndShootToScale extends CommandChain {
	public OpenClawAndShootToScale() {
		Command openClaw = new AcceleratedOpenClaw(500),
				shoot = new ShootToScale();
		
		addCommand(openClaw);
		addSequential(shoot);
	}
}
