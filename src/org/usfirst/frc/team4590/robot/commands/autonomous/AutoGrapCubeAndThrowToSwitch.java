package org.usfirst.frc.team4590.robot.commands.autonomous;

import org.usfirst.frc.team4590.robot.commands.claw.GrabCube;
import org.usfirst.frc.team4590.robot.commands.intake.Collect;
import org.usfirst.frc.team4590.robot.commands.intake.ShootToSwitch;
import org.usfirst.frc.team4590.robot.commands.pitcher.MovePitcherToState;
import org.usfirst.frc.team4590.utils.PitcherState;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;

public class AutoGrapCubeAndThrowToSwitch extends CommandGroup {

	public AutoGrapCubeAndThrowToSwitch() {
		addParallel(new GrabCube());
		addSequential(new Collect(), 0.5);
		addSequential(new MovePitcherToState(PitcherState.SWITCH_FORWARD));
	}
	
	@Override
	protected void end() {
		Scheduler.getInstance().add(new ShootToSwitch());
	}
}
