package org.usfirst.frc.team4590.robot.commands.autonomous;

import org.usfirst.frc.team4590.robot.commands.chassis.DriveForwardsByMeters;
import org.usfirst.frc.team4590.robot.commands.chassis.RotateByDegrees;
import org.usfirst.frc.team4590.robot.commands.claw.GrabCube;
import org.usfirst.frc.team4590.robot.commands.intake.Collect;
import org.usfirst.frc.team4590.utils.commandChain.CommandChain;
import org.usfirst.frc.team4590.utils.gameData.GBGameData;
import org.usfirst.frc.team4590.utils.gameData.Lengths;
import org.usfirst.frc.team4590.utils.gameData.GBGameData.GameEntity;

import edu.wpi.first.wpilibj.command.Command;

public class AutoNoSwitchMiddle extends CommandChain {

	public AutoNoSwitchMiddle() {

	}
	
	protected void onFirstRun(){

		while (!GBGameData.getInstance().hasData(GameEntity.SWITCH)){}
		char mySwitchPlate = GBGameData.getInstance().charAt(GameEntity.SWITCH);
		double angle = mySwitchPlate == 'R' ? -90 : 90;
		Command driveToMiddle = new DriveForwardsByMeters((Lengths.SWITCH_FROM_ALLIANCE_WALL - Lengths.ROBOT_LENGTH) / 2),
				rotateBy90 = new RotateByDegrees(angle, false),
				driveSidewaysToPlate = new DriveForwardsByMeters(Lengths.SWITCH_MIDDLE_TO_PLATE_MIDDLE, angle, true, false),
				rotateBack = new RotateByDegrees(0, false),
				driveToPlate = new DriveForwardsByMeters((Lengths.SWITCH_FROM_ALLIANCE_WALL - Lengths.ROBOT_LENGTH) / 2, 0, false, false),
				closeClaw = new GrabCube(),
				collect = new Collect(500);
		
		addCommand(closeClaw);
		addParallel(collect, closeClaw);
		addSequential(driveToMiddle, closeClaw);
		addSequential(rotateBy90, driveToMiddle);
		addSequential(driveSidewaysToPlate, rotateBy90);
		addSequential(rotateBack, driveSidewaysToPlate);
		addSequential(driveToPlate, rotateBack);
	
	}
}