package org.usfirst.frc.team4590.robot.commands.autonomous.reversed;

import org.usfirst.frc.team4590.robot.commands.chassis.ArcadeDriveByValues;
import org.usfirst.frc.team4590.robot.commands.chassis.DriveForwardsByMeters;
import org.usfirst.frc.team4590.robot.commands.chassis.RotateByDegrees;
import org.usfirst.frc.team4590.robot.commands.claw.GrabCube;
import org.usfirst.frc.team4590.robot.commands.intake.Collect;
import org.usfirst.frc.team4590.robot.commands.intake.ShootToSwitch;
import org.usfirst.frc.team4590.robot.commands.pitcher.MovePitcherToState;
import org.usfirst.frc.team4590.utils.CommandChain;
import org.usfirst.frc.team4590.utils.GBGameData;
import org.usfirst.frc.team4590.utils.Lengths;
import org.usfirst.frc.team4590.utils.PitcherState;
import org.usfirst.frc.team4590.utils.GBGameData.GameEntity;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Its basically the AutoSwitchMiddle but reversed:
 * its the same but with minus (-) sign everywhere.
 */
public class ReverseAutoSwitchMiddle extends CommandChain {

    public ReverseAutoSwitchMiddle() {
    	
	}
    
    @Override
    public void onFirstRun() {
    	while (!GBGameData.getInstance().hasData(GameEntity.SWITCH)){}
    	// TODO: remember to place the robot reversed if running PID!!!!
    	char myPlate = GBGameData.getInstance().charAt(GameEntity.SWITCH);
    	double angle = myPlate == 'R' ? 90 : -90;
    	Command driveToMiddle = new DriveForwardsByMeters( - (Lengths.SWITCH_FROM_ALLIANCE_WALL - Lengths.ROBOT_LENGTH) / 2),
				rotateBy90 = new RotateByDegrees(-angle, false),
				driveSidewaysToPlate = new DriveForwardsByMeters(-Lengths.SWITCH_MIDDLE_TO_PLATE_MIDDLE, - angle, true, false),
				rotateBack = new RotateByDegrees(0, false),
				driveToPlate = new DriveForwardsByMeters(-(Lengths.SWITCH_FROM_ALLIANCE_WALL - Lengths.ROBOT_LENGTH) / 2 - 0.15, 0, false, false),
				driveExtra = new ArcadeDriveByValues(-0.5, 0, 500),
				closeClaw = new GrabCube(),
				collect = new Collect(500),
				movePitcherToSwitchForward = new MovePitcherToState(PitcherState.SWITCH_BACKWARD),
				throwToSwitch = new ShootToSwitch(500),
				pitcherToPlate = new MovePitcherToState(PitcherState.PLATE);
    	
    	addCommand(closeClaw);
		addParallel(collect, closeClaw);
		addSequential(driveToMiddle, closeClaw);
		addSequential(rotateBy90, driveToMiddle);
		addSequential(driveSidewaysToPlate, rotateBy90);
		addSequential(rotateBack, driveSidewaysToPlate);
		addSequential(driveToPlate, rotateBack);
		addSequential(driveExtra, driveToPlate);
		addSequential(movePitcherToSwitchForward, driveExtra);
		addSequential(throwToSwitch, movePitcherToSwitchForward);		
		addSequential(pitcherToPlate, throwToSwitch);
    }
}
