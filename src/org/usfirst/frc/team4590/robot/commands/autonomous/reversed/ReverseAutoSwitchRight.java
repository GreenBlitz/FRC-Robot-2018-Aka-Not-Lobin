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

public class ReverseAutoSwitchRight extends CommandChain {
	
	public ReverseAutoSwitchRight() {
	}
	/*
	 * TODO: why is AutoSwitchRight so different from AutoSwitchLeft?
	 * TODO: MAKE IT SIMILAR
	 */
	protected void onFirstRun(){
		Command pitcherToPlate = new MovePitcherToState(PitcherState.PLATE);
		while (!GBGameData.getInstance().hasData(GameEntity.SWITCH)){}
		char mySwitchPlate = GBGameData.getInstance().charAt(GameEntity.SWITCH);
		
		Command closeClaw = new GrabCube(),
				collect = new Collect(500),
				movePitcher = new MovePitcherToState(PitcherState.SWITCH_BACKWARD),
				throwCube = new ShootToSwitch(500);
		
		addCommand(closeClaw);
		addCommand(collect);
		
		if (mySwitchPlate == 'R') {
			Command driveVertical = new DriveForwardsByMeters(-(4.2751 - Lengths.ROBOT_LENGTH/2)),
					rotate = new RotateByDegrees(-90),
					driveHorizontal = new DriveForwardsByMeters(-(1.439 - Lengths.ROBOT_LENGTH/2));
			
			addCommand(driveVertical);
			addCommand(rotate);
			addCommand(driveHorizontal);
		}
		else {
			Command driveForwards = new DriveForwardsByMeters(-(5.9741 - Lengths.ROBOT_LENGTH/2)),
					firstRotate = new RotateByDegrees(-90),
					driveHorizontal = new DriveForwardsByMeters(-4.7325),
					secondRotation = new RotateByDegrees(-90),
					driveBack = new DriveForwardsByMeters(-(1.3326 - Lengths.ROBOT_LENGTH/2));
			
			addCommand(driveForwards);
			addCommand(firstRotate);
			addCommand(driveHorizontal);
			addCommand(secondRotation);
			addCommand(driveBack);
		}
		// TODO: line is unclear so we didn't know if we should change it. we did change (added a -), make sure we were right
		addCommand(new ArcadeDriveByValues(-0.2, 0, 350));
		addCommand(movePitcher);
		addCommand(throwCube);
		addCommand(pitcherToPlate);
	}
	
}
