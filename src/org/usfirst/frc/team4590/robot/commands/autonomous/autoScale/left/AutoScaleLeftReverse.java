package org.usfirst.frc.team4590.robot.commands.autonomous.autoScale.left;

import org.usfirst.frc.team4590.robot.commands.autonomous.autoSwitch.left.AutoSwitchLeftReverse;
import org.usfirst.frc.team4590.utils.AutonomousCommand;
import org.usfirst.frc.team4590.utils.gameData.GBGameData.GameEntity;

public class AutoScaleLeftReverse extends AutonomousCommand {
	public AutoScaleLeftReverse() {
//		super(GameEntity.SCALE,
//			  new AutoLeftLeftScaleReverse(),
//			  new AutoLeftRightScaleReverse());
	
		super(GameEntity.SCALE,
			  new AutoLeftLeftScaleReverse(),
			  new AutoSwitchLeftReverse());
	}
}
