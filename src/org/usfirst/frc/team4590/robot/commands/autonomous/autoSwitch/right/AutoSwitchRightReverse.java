package org.usfirst.frc.team4590.robot.commands.autonomous.autoSwitch.right;

import org.usfirst.frc.team4590.robot.commands.autonomous.autoSwitch.left.AutoLeftRightSwitchReverse;
import org.usfirst.frc.team4590.robot.commands.chassis.DriveForwardsByMeters;
import org.usfirst.frc.team4590.utils.AutonomousCommand;
import org.usfirst.frc.team4590.utils.gameData.Lengths;
import org.usfirst.frc.team4590.utils.gameData.GBGameData.GameEntity;

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
