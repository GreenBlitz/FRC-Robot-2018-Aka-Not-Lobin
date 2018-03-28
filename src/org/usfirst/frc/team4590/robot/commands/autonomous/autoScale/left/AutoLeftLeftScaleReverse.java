package org.usfirst.frc.team4590.robot.commands.autonomous.autoScale.left;

import org.usfirst.frc.team4590.robot.commands.chassis.DriveForwardsByMeters;
import org.usfirst.frc.team4590.robot.commands.commandChains.PickupCube;
import org.usfirst.frc.team4590.robot.commands.pin.ShootToScale;
import org.usfirst.frc.team4590.utils.commandChain.CommandChain;

import edu.wpi.first.wpilibj.command.Command;

public class AutoLeftLeftScaleReverse extends CommandChain {

	public AutoLeftLeftScaleReverse(){
//		ArenaMap map = new ArenaMap();
//		new PathFactory().connectLine(0,Lengths.SCALE_FROM_ALLIANCE_WALL, 0.01).construct(map);
//		Command grabCube = new PickupCube(),
//				driveVertical = new APPCMove(map, 1, 0.1, 0, 0, true),
//				rotate = new RotateByDegrees(180, false),
//				pitcherSwitchBack = new MovePitcher(PitcherState.PLATE),
//				shootCube = new OpenClawAndShootToScale();
//		
//		addCommand(grabCube);
//		addParallel(driveVertical);
//		addSequential(rotate);
//		addParallel(pitcherSwitchBack);
//		addSequential(shootCube);
		
		Command grabCube = new PickupCube(),
				driveToScale = new DriveForwardsByMeters(9, 0, true, false),
				shootToScale = new ShootToScale();
		
		addCommand(grabCube);
		addParallel(driveToScale, grabCube);
		addSequential(shootToScale);
	}
}
