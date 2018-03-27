package org.usfirst.frc.team4590.robot.commands.autonomous.motion.autoSwitch.middle;

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

public class AutoMotionMiddleRightSwitchReverse extends CommandChain {
	public AutoMotionMiddleRightSwitchReverse() {
		PathFactory path = new PathFactory().connectLine(1.26,1.55,0.01).connectLine(1.41,2.41,0.01);
		ArenaMap map = new ArenaMap();
		path.construct(map);
		
		Command grabCube = new PickupCube(),
				driveToSwitch = new APPCMove(map, 0.5, 0.3, 0, 0, true),
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