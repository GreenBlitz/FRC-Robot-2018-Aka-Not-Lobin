package org.usfirst.frc.team4590.robot.commands.autonomous.autoSwitch.middle;

import org.usfirst.frc.team4590.robot.commands.chassis.DriveForwardsByMeters;
import org.usfirst.frc.team4590.robot.commands.chassis.DriveStraightByMoveValue;
import org.usfirst.frc.team4590.robot.commands.chassis.RotateByDegrees;
import org.usfirst.frc.team4590.robot.commands.commandChains.PickupCube;
import org.usfirst.frc.team4590.robot.commands.intake.ShootToSwitch;
import org.usfirst.frc.team4590.robot.commands.pitcher.MovePitcher;
import org.usfirst.frc.team4590.utils.commandChain.CommandChain;
import org.usfirst.frc.team4590.utils.enums.PitcherState;
import org.usfirst.frc.team4590.utils.gameData.Lengths;

import edu.wpi.first.wpilibj.command.Command;

public class AutoMiddleRightSwitchReverse extends CommandChain {

	public AutoMiddleRightSwitchReverse() {
		Command grabCube = new PickupCube(),
				driveToMiddle = new DriveForwardsByMeters(-((Lengths.SWITCH_FROM_ALLIANCE_WALL - Lengths.ROBOT_LENGTH)/3)),
				rotateBy90 = new RotateByDegrees(90, false),
				/*
				 * PathFactory path = new PathFactory().connectLine(0.0,-0.0,0.01).connectLine(1.36,-0.03,0.01);
ArenaMap map = new ArenaMap();
path.construct(map);
				 */
				driveSidewaysToPlate = new DriveForwardsByMeters(-(Lengths.SWITCH_MIDDLE_TO_PLATE_MIDDLE /*+ 0.35*/), 90, true, false),
				rotateBack = new RotateByDegrees(0, false),
				driveToPlate = new DriveStraightByMoveValue(-0.7, 0, 2000),
				pitcherSwitchBack = new MovePitcher(PitcherState.SWITCH_BACKWARD),
				throwCube = new ShootToSwitch(1000);
		
		addCommand(grabCube);
		addParallel(driveToMiddle, grabCube);
		addSequential(rotateBy90);
		addSequential(driveSidewaysToPlate);
		addSequential(rotateBack);
		addSequential(driveToPlate);
		addParallel(pitcherSwitchBack, driveToPlate);
		addSequential(throwCube);
	}
}