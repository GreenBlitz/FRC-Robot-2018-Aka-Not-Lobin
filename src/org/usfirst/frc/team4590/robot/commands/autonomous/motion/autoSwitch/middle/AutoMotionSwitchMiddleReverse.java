package org.usfirst.frc.team4590.robot.commands.autonomous.motion.autoSwitch.middle;

import org.usfirst.frc.team4590.utils.AutonomousCommand;
import org.usfirst.frc.team4590.utils.gameData.GBGameData.GameEntity;

public class AutoMotionSwitchMiddleReverse extends AutonomousCommand {
	public AutoMotionSwitchMiddleReverse() {
		super(GameEntity.SWITCH,
			  new AutoMotionMiddleLeftSwitchReverse(),
			  new AutoMotionMiddleRightSwitchReverse());
	}
}