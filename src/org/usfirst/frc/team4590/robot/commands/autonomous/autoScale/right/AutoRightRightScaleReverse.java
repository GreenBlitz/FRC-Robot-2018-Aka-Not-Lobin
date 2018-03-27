package org.usfirst.frc.team4590.robot.commands.autonomous.autoScale.right;

import org.usfirst.frc.team4590.robot.commands.chassis.RotateByDegrees;
import org.usfirst.frc.team4590.robot.commands.commandChains.OpenClawAndShootToScale;
import org.usfirst.frc.team4590.robot.commands.commandChains.PickupCube;
import org.usfirst.frc.team4590.robot.commands.pitcher.MovePitcher;
import org.usfirst.frc.team4590.utils.commandChain.CommandChain;
import org.usfirst.frc.team4590.utils.enums.PitcherState;
import org.usfirst.frc.team4590.utils.gameData.Lengths;

import edu.wpi.first.wpilibj.command.Command;
import gbmotion.commands.APPCMove;
import gbmotion.path.ArenaMap;
import gbmotion.path.PathFactory;

public class AutoRightRightScaleReverse extends CommandChain {
	public AutoRightRightScaleReverse(){
		ArenaMap map = new ArenaMap();
		new PathFactory().connectLine(0,Lengths.SCALE_FROM_ALLIANCE_WALL, 0.01).construct(map);
		Command grabCube = new PickupCube(),
				driveVertical = new APPCMove(map, 1, 0.1, 0, 0, true),
				rotate = new RotateByDegrees(0, false),
				pitcherSwitchBack = new MovePitcher(PitcherState.PLATE),
				shootCube = new OpenClawAndShootToScale();
		
		addCommand(grabCube);
		addParallel(driveVertical);
		addSequential(rotate);
		addParallel(pitcherSwitchBack);
		addSequential(shootCube);
	}
}