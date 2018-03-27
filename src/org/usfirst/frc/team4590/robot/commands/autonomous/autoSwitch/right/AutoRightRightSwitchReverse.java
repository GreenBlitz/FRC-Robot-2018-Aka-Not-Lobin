package org.usfirst.frc.team4590.robot.commands.autonomous.autoSwitch.right;

import org.usfirst.frc.team4590.robot.commands.chassis.ArcadeDriveByValues;
import org.usfirst.frc.team4590.robot.commands.chassis.DriveForwardsByMeters;
import org.usfirst.frc.team4590.robot.commands.chassis.RotateByDegrees;
import org.usfirst.frc.team4590.robot.commands.commandChains.PickupCube;
import org.usfirst.frc.team4590.robot.commands.intake.ShootToSwitch;
import org.usfirst.frc.team4590.robot.commands.pitcher.MovePitcher;
import org.usfirst.frc.team4590.utils.commandChain.CommandChain;
import org.usfirst.frc.team4590.utils.enums.PitcherState;
import org.usfirst.frc.team4590.utils.gameData.Lengths;

import edu.wpi.first.wpilibj.command.Command;

public class AutoRightRightSwitchReverse extends CommandChain {

	public AutoRightRightSwitchReverse() {
		Command grabCube = new PickupCube(),
				driveVertical = new DriveForwardsByMeters(-(Lengths.SWITCH_FROM_ALLIANCE_WALL + Lengths.SWITCH_WIDTH/2 - Lengths.ROBOT_LENGTH/2)),
				rotate = new RotateByDegrees(-90),
				driveHorizontal = new ArcadeDriveByValues(-0.5, -90, 2000),
				pitcherSwitchBack = new MovePitcher(PitcherState.SWITCH_BACKWARD),
				throwCube = new ShootToSwitch(1000);
		
		addCommand(grabCube);
		addParallel(driveVertical);
		addSequential(rotate);
		addSequential(driveHorizontal);
		addParallel(pitcherSwitchBack);
		addSequential(throwCube);
	}
}