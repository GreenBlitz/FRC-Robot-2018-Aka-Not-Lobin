package org.usfirst.frc.team4590.robot.commands.autonomous.drives;

import org.usfirst.frc.team4590.robot.commands.chassis.ArcadeDriveByValues;
import org.usfirst.frc.team4590.robot.commands.chassis.DriveForwardsByMeters;
import org.usfirst.frc.team4590.robot.commands.chassis.RotateByDegrees;
import org.usfirst.frc.team4590.utils.commandChain.CommandChain;
import org.usfirst.frc.team4590.utils.gameData.GBGameData;
import org.usfirst.frc.team4590.utils.gameData.Lengths;
import org.usfirst.frc.team4590.utils.gameData.GBGameData.GameEntity;

import edu.wpi.first.wpilibj.command.Command;

public class AutoReverseDriveRight extends CommandChain {

	@Override
	protected void onFirstRun() {
		while (!GBGameData.getInstance().hasData(GameEntity.SWITCH)) {}
		
		if (GBGameData.getInstance().charAt(GameEntity.SWITCH) == 'R') {
			Command driveVertical = new DriveForwardsByMeters(-(Lengths.SWITCH_FROM_ALLIANCE_WALL + Lengths.SWITCH_WIDTH/2 - Lengths.ROBOT_LENGTH/2)),
					rotate = new RotateByDegrees(-90),
					driveHorizontal = new ArcadeDriveByValues(-0.5, 0, 2000);
			
			addCommand(driveVertical);
			addCommand(rotate);
			addCommand(driveHorizontal);
		}
		else {
			Command driveForwards = new DriveForwardsByMeters(-(5.9741 - Lengths.ROBOT_LENGTH/2)),
					firstRotate = new RotateByDegrees(-90),
					driveHorizontal = new DriveForwardsByMeters(-(4.7325)),
					secondRotation = new RotateByDegrees(-90),
					driveBack = new ArcadeDriveByValues(-0.5, 0, 2000);
			
			addCommand(driveForwards);
			addCommand(firstRotate);
			addCommand(driveHorizontal);
			addCommand(secondRotation);
			addCommand(driveBack);
		}
	}
}