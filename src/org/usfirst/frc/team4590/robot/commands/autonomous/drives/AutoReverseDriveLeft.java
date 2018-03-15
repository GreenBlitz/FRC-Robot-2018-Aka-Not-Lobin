package org.usfirst.frc.team4590.robot.commands.autonomous.drives;

import org.usfirst.frc.team4590.robot.commands.chassis.ArcadeDriveByValues;
import org.usfirst.frc.team4590.robot.commands.chassis.DriveForwardsByMeters;
import org.usfirst.frc.team4590.robot.commands.chassis.RotateByDegrees;
import org.usfirst.frc.team4590.utils.CommandChain;
import org.usfirst.frc.team4590.utils.GBGameData;
import org.usfirst.frc.team4590.utils.GBGameData.GameEntity;
import org.usfirst.frc.team4590.utils.Lengths;

import edu.wpi.first.wpilibj.command.Command;

public class AutoReverseDriveLeft extends CommandChain {

	@Override
	protected void onFirstRun() {
		while (!GBGameData.getInstance().hasData(GameEntity.SWITCH)) {}
		
		if (GBGameData.getInstance().charAt(GameEntity.SWITCH) == 'L') {
			Command driveVertical = new DriveForwardsByMeters(-(Lengths.SWITCH_FROM_ALLIANCE_WALL + Lengths.SWITCH_WIDTH/2 - Lengths.ROBOT_LENGTH/2 + 0.4)),
					rotate = new RotateByDegrees(90),
					driveHorizontal = new ArcadeDriveByValues(-0.6, 0, 2000);
			
			addCommand(driveVertical);
			addCommand(rotate);
			addCommand(driveHorizontal);
		}
		else {
			Command driveForwards = new DriveForwardsByMeters(-((Lengths.SWITCH_FROM_ALLIANCE_WALL + Lengths.SWITCH_WIDTH + Lengths.SCALE_FROM_ALLIANCE_WALL)/2 - Lengths.ROBOT_LENGTH/2 + 0.5)),
					firstRotate = new RotateByDegrees(90, false),
					driveHorizontal = new DriveForwardsByMeters(-(Lengths.SWITCH_LENGTH), 90, true, false),
					secondRotation = new RotateByDegrees(90),
					driveBack = new ArcadeDriveByValues(-0.6, 0, 2000);
			
			addCommand(driveForwards);
			addCommand(firstRotate);
			addCommand(driveHorizontal);
			addCommand(secondRotation);
			addCommand(driveBack);
		}
	}
}