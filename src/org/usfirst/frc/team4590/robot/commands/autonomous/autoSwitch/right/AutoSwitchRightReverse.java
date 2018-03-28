package org.usfirst.frc.team4590.robot.commands.autonomous.autoSwitch.right;

import org.usfirst.frc.team4590.robot.commands.chassis.DriveForwardsByMeters;
import org.usfirst.frc.team4590.utils.AutonomousCommand;
import org.usfirst.frc.team4590.utils.gameData.GBGameData.GameEntity;
import org.usfirst.frc.team4590.utils.gameData.Lengths;

public class AutoSwitchRightReverse extends AutonomousCommand {
	public AutoSwitchRightReverse() {
//		super(GameEntity.SWITCH,
//			  new AutoLeftRightSwitchReverse(),
//			  new AutoRightRightSwitchReverse());
	
		super(GameEntity.SWITCH,
			  new DriveForwardsByMeters(Lengths.AUTO_LINE),
			  new AutoRightRightSwitchReverse());
	}
}
