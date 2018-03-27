package org.usfirst.frc.team4590.robot.commands.autonomous.motion.autoSwitch.left;

import org.usfirst.frc.team4590.robot.commands.chassis.DriveStraightByMoveValue;
import org.usfirst.frc.team4590.robot.commands.commandChains.PickupCube;
import org.usfirst.frc.team4590.robot.commands.intake.ShootToSwitch;
import org.usfirst.frc.team4590.robot.commands.pitcher.MovePitcher;
import org.usfirst.frc.team4590.utils.commandChain.CommandChain;
import org.usfirst.frc.team4590.utils.enums.PitcherState;

import edu.wpi.first.wpilibj.command.Command;
import gbmotion.commands.APPCMove;
import gbmotion.path.ArenaMap;
import gbmotion.path.PathFactory;

public class AutoMotionLeftLeftSwitchReverse extends CommandChain {
	public AutoMotionLeftLeftSwitchReverse() {
		PathFactory path = new PathFactory().connectLine(-0.0,-0.0,0.01).connectLine(-0.0,-2.9,0.01).connectLine(-0.38,-3.7,0.01);
		ArenaMap map = new ArenaMap();
		path.construct(map);
		
		Command grabCube = new PickupCube(),
				driveToSwitch = new APPCMove(map, 0.5, 0.1, 0.1, 0.4, true),
				extraDrive = new DriveStraightByMoveValue(-0.5, 0, 1000),
				pitcherSwitchBack = new MovePitcher(PitcherState.SWITCH_BACKWARD),
				throwCube = new ShootToSwitch(1000);
		
		addCommand(grabCube);
		addParallel(driveToSwitch, grabCube);
		addSequential(extraDrive);
		addParallel(pitcherSwitchBack, extraDrive);
		addSequential(throwCube);
	}
}