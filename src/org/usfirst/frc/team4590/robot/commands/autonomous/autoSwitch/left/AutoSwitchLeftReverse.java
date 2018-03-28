package org.usfirst.frc.team4590.robot.commands.autonomous.autoSwitch.left;

import org.usfirst.frc.team4590.robot.commands.chassis.DriveForwardsByMeters;
import org.usfirst.frc.team4590.utils.AutonomousCommand;
import org.usfirst.frc.team4590.utils.gameData.GBGameData.GameEntity;
import org.usfirst.frc.team4590.utils.gameData.Lengths;

public class AutoSwitchLeftReverse extends AutonomousCommand {
	public AutoSwitchLeftReverse() {
//		super(GameEntity.SWITCH,
//			  new AutoLeftLeftSwitchReverse(),
//			  new AutoLeftRightSwitchReverse());
	
		super(GameEntity.SWITCH,
			  new AutoLeftLeftSwitchReverse(),
			  new DriveForwardsByMeters(Lengths.AUTO_LINE));
	}
}