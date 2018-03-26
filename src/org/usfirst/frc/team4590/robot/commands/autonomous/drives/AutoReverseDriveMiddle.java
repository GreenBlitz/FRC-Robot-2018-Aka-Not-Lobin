package org.usfirst.frc.team4590.robot.commands.autonomous.drives;

import org.usfirst.frc.team4590.robot.commands.chassis.DriveForwardsByMeters;
import org.usfirst.frc.team4590.robot.commands.chassis.DriveStraightByMoveValue;
import org.usfirst.frc.team4590.utils.commandChain.CommandChain;
import org.usfirst.frc.team4590.utils.gameData.GBGameData;
import org.usfirst.frc.team4590.utils.gameData.Lengths;
import org.usfirst.frc.team4590.utils.gameData.GBGameData.GameEntity;

public class AutoReverseDriveMiddle extends CommandChain {

	@Override
	protected void onFirstRun() {
		while (!GBGameData.getInstance().hasData(GameEntity.SWITCH)){}
		
		double angle =90 - Math.atan(Lengths.SWITCH_FROM_ALLIANCE_WALL / Lengths.SWITCH_MIDDLE_TO_PLATE_MIDDLE);
		if (GBGameData.getInstance().charAt(GameEntity.SWITCH) == 'L')
			angle*= -1;

		double meters = Math.sqrt(Math.pow(Lengths.SWITCH_FROM_ALLIANCE_WALL, 2) + Math.pow(Lengths.SWITCH_MIDDLE_TO_PLATE_MIDDLE, 2));
		
		addCommand(new DriveForwardsByMeters(meters, angle, false, true));
		addCommand(new DriveStraightByMoveValue(0.6, 0, 500));
/*		
		double angle = GBGameData.getInstance().charAt(GameEntity.SWITCH) == 'R' ? 90 : -90;
		
		Command driveToMiddle = new DriveForwardsByMeters(-((Lengths.SWITCH_FROM_ALLIANCE_WALL - Lengths.ROBOT_LENGTH)/2 + 0.3)),
				rotateBy90 = new RotateByDegrees(angle, false),
				driveSidewaysToPlate = new DriveForwardsByMeters(-(Lengths.SWITCH_MIDDLE_TO_PLATE_MIDDLE + 0.35), angle, true, false),
				rotateBack = new RotateByDegrees(0, false),
				driveToPlate = new DriveStraightByMoveValue(-0.7, 0, 2000);
		
		addCommand(driveToMiddle);
		addCommand(rotateBy90);
		addCommand(driveSidewaysToPlate);
		addCommand(rotateBack);
		addCommand(driveToPlate);
*/
	}
}