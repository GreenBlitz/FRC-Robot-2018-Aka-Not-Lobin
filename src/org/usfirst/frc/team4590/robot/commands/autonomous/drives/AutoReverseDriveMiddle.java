package org.usfirst.frc.team4590.robot.commands.autonomous.drives;

import org.usfirst.frc.team4590.robot.commands.chassis.DriveForwardsByMeters;
import org.usfirst.frc.team4590.robot.commands.chassis.DriveStraightByMoveValue;
import org.usfirst.frc.team4590.robot.commands.chassis.RotateByDegrees;
import org.usfirst.frc.team4590.utils.CommandChain;
import org.usfirst.frc.team4590.utils.GBGameData;
import org.usfirst.frc.team4590.utils.GBGameData.GameEntity;
import org.usfirst.frc.team4590.utils.Lengths;

import edu.wpi.first.wpilibj.command.Command;

public class AutoReverseDriveMiddle extends CommandChain {

	@Override
	protected void onFirstRun() {
		while (!GBGameData.getInstance().hasData(GameEntity.SWITCH)){}
		
		double angle = GBGameData.getInstance().charAt(GameEntity.SWITCH) == 'R' ? 90 : -90;
		
		Command driveToMiddle = new DriveForwardsByMeters(-((Lengths.SWITCH_FROM_ALLIANCE_WALL - Lengths.ROBOT_LENGTH)/2)),
				rotateBy90 = new RotateByDegrees(angle, false),
				driveSidewaysToPlate = new DriveForwardsByMeters(-(Lengths.SWITCH_MIDDLE_TO_PLATE_MIDDLE + 0.35), angle, true, false),
				rotateBack = new RotateByDegrees(0, false),
				driveToPlate = new DriveStraightByMoveValue(-0.7, 0, 2000);
		
		addCommand(driveToMiddle);
		addCommand(rotateBy90);
		addCommand(driveSidewaysToPlate);
		addCommand(rotateBack);
		addCommand(driveToPlate);
	}
	
}
