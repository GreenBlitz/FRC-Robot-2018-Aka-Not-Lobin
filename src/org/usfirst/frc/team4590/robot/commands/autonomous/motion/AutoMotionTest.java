package org.usfirst.frc.team4590.robot.commands.autonomous.motion;

import org.usfirst.frc.team4590.robot.commands.chassis.DriveStraightByMoveValue;
import org.usfirst.frc.team4590.robot.commands.claw.GrabCube;
import org.usfirst.frc.team4590.robot.commands.intake.Collect;
import org.usfirst.frc.team4590.robot.commands.intake.ShootToSwitch;
import org.usfirst.frc.team4590.robot.commands.pitcher.MovePitcher;
import org.usfirst.frc.team4590.utils.CommandChain;
import org.usfirst.frc.team4590.utils.Lengths;
import org.usfirst.frc.team4590.utils.PitcherState;

import edu.wpi.first.wpilibj.command.Command;
import gbmotion.commands.APPCMove;
import gbmotion.path.ArenaMap;
import gbmotion.path.PathFactory;

public class AutoMotionTest extends CommandChain {
	
	public static ArenaMap getScaleOnSameSidePath(){
		return new PathFactory().connectLine(
				0,
				-Lengths.SCALE_FROM_ALLIANCE_WALL + 0.5 * Lengths.ROBOT_LENGTH, 
				0.01).construct(new ArenaMap());
	}
	
	public static ArenaMap getLeftToRightScalePath(){
		return null;
	}
	// TODO for the function above and below use getScaleOnOppositeSidePath() with some value for inverted
	public static ArenaMap getRightToLeftScalePath(){
		return null;
	}
	
	public static ArenaMap getScaleOnOppositeSidePath(boolean invert){
		// TODO finish this
		int sign = invert ? -1:1;
		return new PathFactory()
				.connectLine(0,
						(Lengths.PATH_BETWEEN_RAMP_AND_SWITCH_MIDDLE_TO_ALLIANCE_WALL - 0.5 * Lengths.ROBOT_LENGTH + 0.1),
						0.01)
				.connectLine(sign*(Lengths.ALLIANCE_WALL_LENGTH + Lengths.CORNER_X_LENGTH - Lengths.ROBOT_WIDTH - 0.5),
						(Lengths.PATH_BETWEEN_RAMP_AND_SWITCH_MIDDLE_TO_ALLIANCE_WALL - 0.5 * Lengths.ROBOT_LENGTH + 0.1),
						0.01)
				.connectLine(sign*(Lengths.ALLIANCE_WALL_LENGTH + Lengths.CORNER_X_LENGTH - Lengths.ROBOT_WIDTH - 0.5),
						(Lengths.FIELD_LENGTH/2 - Lengths.ROBOT_LENGTH/2 - Lengths.NULL_LENGTH/2),
						0.01)
				.construct(new ArenaMap());
	
	}
	
	public AutoMotionTest() {
		Command closeClaw = new GrabCube(),
				collect = new Collect(500);
		addCommand(closeClaw);
		addCommand(collect);
	}

	public void onFirstRun() {
		double length = (Lengths.SWITCH_FROM_ALLIANCE_WALL - Lengths.ROBOT_LENGTH);
		double length2 = Lengths.SWITCH_MIDDLE_TO_PLATE_MIDDLE;
		double extra = 0.35;
		
		PathFactory path = new PathFactory().connectLine(0, length / 2 - extra, 0.01)
				.connectLine(length2, length / 2 + extra, 0.01).connectLine(length2, length, 0.01);
		ArenaMap map = new ArenaMap();
		path.construct(map);
		
		Command driveMeter = new APPCMove(map, 0.5, 0.1, 0, 0.2, false),
				driveExtra = new DriveStraightByMoveValue(-0.5, 0, 1000),
				movePitcher = new MovePitcher(PitcherState.SWITCH_BACKWARD),
				throwCube = new ShootToSwitch(1000);
		addCommand(driveMeter);
		addCommand(driveExtra);
		addCommand(movePitcher);
		addCommand(throwCube);
	}
}