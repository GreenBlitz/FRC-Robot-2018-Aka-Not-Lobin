package org.usfirst.frc.team4590.robot.commands.autonomous.autoScale.left;

import org.usfirst.frc.team4590.robot.commands.autonomous.motion.AutoMotionTest;
import org.usfirst.frc.team4590.robot.commands.chassis.RotateByDegrees;
import org.usfirst.frc.team4590.robot.commands.commandChains.OpenClawAndShootToScale;
import org.usfirst.frc.team4590.robot.commands.commandChains.PickupCube;
import org.usfirst.frc.team4590.utils.commandChain.CommandChain;

import edu.wpi.first.wpilibj.command.Command;
import gbmotion.commands.APPCMove;
import gbmotion.path.ArenaMap;

public class AutoLeftRightScaleReverse extends CommandChain {

	public AutoLeftRightScaleReverse(){
		ArenaMap map = AutoMotionTest.getScaleOnOppositeSidePath(true);
		Command grabCube = new PickupCube(),
				driveVertical = new APPCMove(map, 1, 0.1, 0, 0, true),
				rotate = new RotateByDegrees(180, false),
				shootCube = new OpenClawAndShootToScale();
		
		addCommand(grabCube);
		addParallel(driveVertical);
		addSequential(rotate);
		addSequential(shootCube);
	}
}
