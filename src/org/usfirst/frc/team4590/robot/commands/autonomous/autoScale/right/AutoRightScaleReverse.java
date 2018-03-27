package org.usfirst.frc.team4590.robot.commands.autonomous.autoScale.right;

import org.usfirst.frc.team4590.robot.commands.chassis.DriveForwardsByMeters;
import org.usfirst.frc.team4590.utils.AutonomousCommand;
import org.usfirst.frc.team4590.utils.gameData.Lengths;
import org.usfirst.frc.team4590.utils.gameData.GBGameData.GameEntity;

public class AutoRightScaleReverse extends AutonomousCommand {
	public AutoRightScaleReverse() {
//		super(GameEntity.SCALE,
//			  new AutoRightLeftScaleReverse(),
//			  new AutoRightRightScaleReverse());
		
		super(GameEntity.SCALE,
			  new DriveForwardsByMeters(Lengths.AUTO_LINE),
			  new AutoRightRightScaleReverse());
	}
}
