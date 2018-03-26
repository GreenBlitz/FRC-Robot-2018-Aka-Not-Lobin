package org.usfirst.frc.team4590.robot.commands.autonomous.motion;

import org.usfirst.frc.team4590.robot.commands.chassis.DriveStraightByMoveValue;
import org.usfirst.frc.team4590.robot.commands.chassis.RotateByDegrees;
import org.usfirst.frc.team4590.robot.commands.commandChains.PickupCube;
import org.usfirst.frc.team4590.robot.commands.commandChains.ReadyToCollect;
import org.usfirst.frc.team4590.robot.commands.intake.ShootToSwitch;
import org.usfirst.frc.team4590.robot.commands.pitcher.MovePitcher;
import org.usfirst.frc.team4590.robot.commands.vision.DriveByVision;
import org.usfirst.frc.team4590.utils.commandChain.CommandChain;
import org.usfirst.frc.team4590.utils.enums.PitcherState;
import org.usfirst.frc.team4590.utils.gameData.Lengths;

import edu.wpi.first.wpilibj.command.Command;
import gbmotion.commands.APPCMove;
import gbmotion.path.ArenaMap;
import gbmotion.path.PathFactory;

public class AutoMotionTest extends CommandChain {

	Command grabCube,
			motionDrive1,
			driveExtra,
			pitcherSwitchBack,
			throwFirstCube,
			readyToCollect,
			motionDrive2,
			rotateToCube,
			visionDrive,
			pickupCube,
			driveBack,
			pitcherSwitchForward,
			driveToSwitch,
			throwSecondCube;
	
	public AutoMotionTest() {
//		double length = (Lengths.SWITCH_FROM_ALLIANCE_WALL - Lengths.ROBOT_LENGTH);
//		double length2 = Lengths.SWITCH_MIDDLE_TO_PLATE_MIDDLE;
//		double extra = 0.35;
		
		double length = Lengths.SWITCH_FROM_ALLIANCE_WALL - Lengths.ROBOT_LENGTH,
			   length2 = Lengths.SWITCH_FROM_ALLIANCE_WALL + Lengths.SWITCH_WIDTH/2 - Lengths.ROBOT_LENGTH/2,
			   length3 = Lengths.MIDDLE_OF_PLATFORM_ZONE_FLOOR_FROM_ALLIANCE_STATION + 0.2,
			   extra = 0.25;
		
		//PathFactory path = new PathFactory().connectLine(0, length / 2 - extra, 0.01)
		//		.connectLine(length2, length / 2 + extra, 0.01).connectLine(length2, length, 0.01);
		ArenaMap map1 = new ArenaMap();
		PathFactory path1 = new PathFactory().connectLine(0.5, length ,0.01).connectLine(0, length2 - extra, 0.01);
		path1.construct(map1);
		
		grabCube = new PickupCube();
		motionDrive1 = new APPCMove(map1, 1, 0.1, 0, 0, true);
		driveExtra = new DriveStraightByMoveValue(-0.6, 90, 1000);
		pitcherSwitchBack = new MovePitcher(PitcherState.SWITCH_BACKWARD);
		throwFirstCube = new ShootToSwitch(500);
		
		ArenaMap map2 = new ArenaMap();
		PathFactory path2 = new PathFactory().connectLine(-0.05, -(length2 - extra), 0.01).connectLine(-0.05, -length3, 0.01).connectLine(0.9, -length3 - 0.05, 0.01);
		path2.construct(map2);

//		PathFactory path3 = new PathFactory().connectLine(0,-3.8,0.01).connectLine(-0.2,-5.1,0.01).connectLine(0.9
//				,-6.75,0.01).connectLine(1.1,-6,0.01);
		
		readyToCollect = new ReadyToCollect();
		motionDrive2 = new APPCMove(map2, 0.6, 0.1, 0, 0, false);
		rotateToCube = new RotateByDegrees(-5, false);
		visionDrive = new DriveByVision(500);
		pickupCube = new PickupCube(1000, 1000);
		driveBack = new DriveStraightByMoveValue(-0.5, 15, 500);
		pitcherSwitchForward = new MovePitcher(PitcherState.SWITCH_FORWARD);
		driveToSwitch = new DriveStraightByMoveValue(0.6, -15, 500);
		throwSecondCube = new ShootToSwitch(500);
	}
	
	public static ArenaMap getScaleOnSameSidePath(){
		return new PathFactory().connectLine(
				0,
				-Lengths.SCALE_FROM_ALLIANCE_WALL + 0.5 * Lengths.ROBOT_LENGTH, 
				0.01).construct(new ArenaMap());
	}
	
	public static ArenaMap getLeftToRightScalePath(){
		return null;
	}
	
	// TODO for the function above and below use getScaleOnOppositeSidePath() with some value for inverted
	public static ArenaMap getRightToLeftScalePath(){
		return null;
	}
	
	public static ArenaMap getScaleOnOppositeSidePath(boolean invert){
		// TODO finish this
		int sign = invert ? -1:1;
		return new PathFactory()
				.connectLine(0,
						(Lengths.PATH_BETWEEN_RAMP_AND_SWITCH_MIDDLE_TO_ALLIANCE_WALL - 0.5 * Lengths.ROBOT_LENGTH + 0.1),
						0.01)
				.connectLine(sign*(Lengths.ALLIANCE_WALL_LENGTH + Lengths.CORNER_X_LENGTH - Lengths.ROBOT_WIDTH - 0.5),
						(Lengths.PATH_BETWEEN_RAMP_AND_SWITCH_MIDDLE_TO_ALLIANCE_WALL - 0.5 * Lengths.ROBOT_LENGTH + 0.1),
						0.01)
				.connectLine(sign*(Lengths.ALLIANCE_WALL_LENGTH + Lengths.CORNER_X_LENGTH - Lengths.ROBOT_WIDTH - 0.5),
						(Lengths.FIELD_LENGTH/2 - Lengths.ROBOT_LENGTH/2 - Lengths.NULL_LENGTH/2),
						0.01)
				.construct(new ArenaMap());
	
	}
	
	public void onFirstRun() {
		//First cube
		addCommand(grabCube);
		addParallel(motionDrive1, grabCube);
		addSequential(driveExtra);
		addParallel(pitcherSwitchBack, driveExtra);
		addSequential(throwFirstCube);
		
		//Second cube
		addSequential(readyToCollect);
		addParallel(motionDrive2, readyToCollect);
		addSequential(rotateToCube);
		addSequential(visionDrive);
		addSequential(pickupCube);
		addSequential(driveBack);
		addParallel(pitcherSwitchForward, driveBack);
		addSequential(driveToSwitch);
		addSequential(throwSecondCube);
	}
}