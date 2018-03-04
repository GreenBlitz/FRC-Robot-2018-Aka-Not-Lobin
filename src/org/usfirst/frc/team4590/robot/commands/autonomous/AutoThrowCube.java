package org.usfirst.frc.team4590.robot.commands.autonomous;

import org.usfirst.frc.team4590.robot.commands.claw.GrabCube;
import org.usfirst.frc.team4590.robot.commands.intake.Collect;
import org.usfirst.frc.team4590.robot.commands.intake.ShootToSwitch;
import org.usfirst.frc.team4590.robot.commands.pitcher.MovePitcherToState;
import org.usfirst.frc.team4590.utils.CommandChain;
import org.usfirst.frc.team4590.utils.PitcherState;

import edu.wpi.first.wpilibj.command.Command;

public class AutoThrowCube extends CommandChain {

	public AutoThrowCube() {
		Command grab = new GrabCube(),
				collect = new Collect(500),
				pitcherToSwitchForward = new MovePitcherToState(PitcherState.SWITCH_FORWARD),
				shoot = new ShootToSwitch(500);
		
		addCommand(grab);
		addSequential(collect, grab);
		addSequential(pitcherToSwitchForward, collect);
		addSequential(shoot, pitcherToSwitchForward);
	}
	
}
