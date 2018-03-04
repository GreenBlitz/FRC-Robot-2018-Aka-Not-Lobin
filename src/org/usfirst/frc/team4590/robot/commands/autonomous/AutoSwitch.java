package org.usfirst.frc.team4590.robot.commands.autonomous;

import org.usfirst.frc.team4590.robot.Robot;
import org.usfirst.frc.team4590.robot.commands.chassis.DriveForwardsByMeters;
import org.usfirst.frc.team4590.robot.commands.chassis.RotateByDegrees;
import org.usfirst.frc.team4590.robot.commands.claw.GrabCube;
import org.usfirst.frc.team4590.robot.commands.intake.Collect;
import org.usfirst.frc.team4590.robot.commands.intake.ShootToSwitch;
import org.usfirst.frc.team4590.robot.commands.pitcher.MovePitcherToState;
import org.usfirst.frc.team4590.utils.CommandChain;
import org.usfirst.frc.team4590.utils.Lengths;
import org.usfirst.frc.team4590.utils.PitcherState;

import edu.wpi.first.wpilibj.command.Command;

public class AutoSwitch extends CommandChain {

	public AutoSwitch() {
		char mySwitchPlate = Robot.getInstance().getPlate().charAt(0);
		Command driveToMiddle = new DriveForwardsByMeters((Lengths.SWITCH_FROM_ALLIANCE_WALL - Lengths.ROBOT_LENGTH) / 2),
				rotateBy90 = new RotateByDegrees(mySwitchPlate == 'R' ? 90 : -90),
				driveSidewaysToPlate = new DriveForwardsByMeters(Lengths.SWITCH_MIDDLE_TO_PLATE_MIDDLE),
				rotateBack = new RotateByDegrees(mySwitchPlate == 'R' ? -90 : 90),
				driveToPlate = new DriveForwardsByMeters((Lengths.SWITCH_FROM_ALLIANCE_WALL - Lengths.ROBOT_LENGTH) / 2, false),
				closeClaw = new GrabCube(),
				collect = new Collect(500),
				movePitcherToSwitchForward = new MovePitcherToState(PitcherState.SWITCH_FORWARD),
				throwToSwitch = new ShootToSwitch(500);

		addCommand(closeClaw);
		addParallel(collect, closeClaw);
		addSequential(driveToMiddle, closeClaw);
		addSequential(rotateBy90, driveToMiddle);
		addSequential(driveSidewaysToPlate, rotateBy90);
		addSequential(rotateBack, driveSidewaysToPlate);
		addSequential(driveToPlate, rotateBack);
		addSequential(movePitcherToSwitchForward, driveToPlate);
		addSequential(throwToSwitch, movePitcherToSwitchForward);
	}
}