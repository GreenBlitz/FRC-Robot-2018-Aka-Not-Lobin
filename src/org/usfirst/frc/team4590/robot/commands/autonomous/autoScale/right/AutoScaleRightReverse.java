package org.usfirst.frc.team4590.robot.commands.autonomous.autoScale.right;

import org.usfirst.frc.team4590.robot.commands.autonomous.autoSwitch.right.AutoSwitchRightReverse;
import org.usfirst.frc.team4590.utils.AutonomousCommand;
import org.usfirst.frc.team4590.utils.gameData.GBGameData.GameEntity;

public class AutoScaleRightReverse extends AutonomousCommand {
	public AutoScaleRightReverse() {
//		super(GameEntity.SCALE,
//			  new AutoRightLeftScaleReverse(),
//			  new AutoRightRightScaleReverse());
		
		super(GameEntity.SCALE,
			  new AutoSwitchRightReverse(),
			  new AutoRightRightScaleReverse());
	}
}