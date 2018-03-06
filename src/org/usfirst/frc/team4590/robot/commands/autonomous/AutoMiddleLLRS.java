package org.usfirst.frc.team4590.robot.commands.autonomous;

import org.usfirst.frc.team4590.robot.commands.chassis.ArcadeDriveByValues;
import org.usfirst.frc.team4590.robot.commands.chassis.DriveForwardsByMeters;
import org.usfirst.frc.team4590.robot.commands.chassis.RotateByDegrees;
import org.usfirst.frc.team4590.robot.commands.claw.CloseClawControlled;
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
 * will pass the auto line and will place a cube in the switch if the right one is ours.
 */
public class AutoMiddleLLRS extends CommandChain {

	protected void onFirstRun(){
		while (!GBGameData.getInstance().hasData(GameEntity.SWITCH)){}
		Command driveToLine = new DriveForwardsByMeters(-(Lengths.SWITCH_FROM_ALLIANCE_WALL - Lengths.ROBOT_LENGTH), false),
				grabCube = new CloseClawControlled(0.45),
				movePitcher = new MovePitcherToState(PitcherState.SWITCH_BACKWARD),
				shootCube = new ShootToSwitch(500);
		
		addCommand(driveToLine);
		if (GBGameData.getInstance().charAt(GameEntity.SWITCH) == 'R') {
			addCommand(grabCube);
			addCommand(movePitcher);
			addCommand(shootCube);
		}
	}
}
