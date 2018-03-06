package org.usfirst.frc.team4590.robot.commands.autonomous;

import org.usfirst.frc.team4590.robot.Robot;
import org.usfirst.frc.team4590.robot.commands.chassis.DriveForwardsByMeters;
import org.usfirst.frc.team4590.robot.commands.chassis.RotateByDegrees;
import org.usfirst.frc.team4590.utils.CommandChain;
import org.usfirst.frc.team4590.utils.GBGameData;
import org.usfirst.frc.team4590.utils.GBGameData.GameEntity;
import org.usfirst.frc.team4590.utils.Lengths;

import edu.wpi.first.wpilibj.command.Command;

public class AutoDriveForwardToSwitch extends CommandChain {

	public AutoDriveForwardToSwitch() {
	//	char mySwitchPlate = Robot.getInstance().getPlates().charAt(0);
		
	}
	
	protected void onFirstRun(){
		while (!GBGameData.getInstance().hasData(GameEntity.SWITCH)){}
		char mySwitchPlate = GBGameData.getInstance().charAt(GameEntity.SWITCH);
		Command driveToMiddle = new DriveForwardsByMeters((Lengths.SWITCH_FROM_ALLIANCE_WALL - Lengths.ROBOT_LENGTH) / 2),
				rotateBy90 = new RotateByDegrees(mySwitchPlate == 'R' ? 90 : -90),
				driveSidewaysToPlate = new DriveForwardsByMeters(Lengths.SWITCH_MIDDLE_TO_PLATE_MIDDLE),
				rotateBack = new RotateByDegrees(mySwitchPlate == 'R' ? -90 : 90),
				driveToPlate = new DriveForwardsByMeters((Lengths.SWITCH_FROM_ALLIANCE_WALL - Lengths.ROBOT_LENGTH) / 2);
		
		addCommand(driveToMiddle);
		addSequential(rotateBy90, driveToMiddle);
		addSequential(driveSidewaysToPlate, rotateBy90);
		addSequential(rotateBack, driveSidewaysToPlate);
		addSequential(driveToPlate, rotateBack);
	}
}