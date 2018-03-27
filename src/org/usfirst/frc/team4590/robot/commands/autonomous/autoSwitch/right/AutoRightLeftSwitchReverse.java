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

public class AutoRightLeftSwitchReverse extends CommandChain {

	public AutoRightLeftSwitchReverse() {
		Command grabCube = new PickupCube(),
				driveForwards = new DriveForwardsByMeters(-(5.9741 - Lengths.ROBOT_LENGTH/2)),
				firstRotate = new RotateByDegrees(-90),
				driveHorizontal = new DriveForwardsByMeters(-(4.7325)),
				secondRotation = new RotateByDegrees(-90),
				driveBack = new ArcadeDriveByValues(-0.5, 0, 2000),
				pitcherSwitchBack = new MovePitcher(PitcherState.SWITCH_BACKWARD),
				throwCube = new ShootToSwitch(1000);
		
		addCommand(grabCube);
		addParallel(driveForwards);
		addSequential(firstRotate);
		addSequential(driveHorizontal);
		addSequential(secondRotation);
		addSequential(driveBack);
		addParallel(pitcherSwitchBack);
		addSequential(throwCube);
	}
}