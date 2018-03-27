package org.usfirst.frc.team4590.robot.commands.autonomous.autoScale.left;

import org.usfirst.frc.team4590.robot.commands.chassis.DriveForwardsByMeters;
import org.usfirst.frc.team4590.utils.AutonomousCommand;
import org.usfirst.frc.team4590.utils.gameData.Lengths;
import org.usfirst.frc.team4590.utils.gameData.GBGameData.GameEntity;

public class AutoLeftScaleReverse extends AutonomousCommand {
	public AutoLeftScaleReverse() {
//		super(GameEntity.SCALE,
//			  new AutoLeftLeftScaleReverse(),
//			  new AutoLeftRightScaleReverse());
	
		super(GameEntity.SCALE,
			  new AutoLeftLeftScaleReverse(),
			  new DriveForwardsByMeters(-Lengths.AUTO_LINE));
	}
}
