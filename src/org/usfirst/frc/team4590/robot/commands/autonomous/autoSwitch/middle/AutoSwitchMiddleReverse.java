package org.usfirst.frc.team4590.robot.commands.autonomous.autoSwitch.middle;

import org.usfirst.frc.team4590.utils.AutonomousCommand;
import org.usfirst.frc.team4590.utils.gameData.GBGameData.GameEntity;

public class AutoSwitchMiddleReverse extends AutonomousCommand {
	public AutoSwitchMiddleReverse() {
		super(GameEntity.SWITCH, 
			  new AutoMiddleLeftSwitchReverse(), 
			  new AutoMiddleRightSwitchReverse());
	}
}