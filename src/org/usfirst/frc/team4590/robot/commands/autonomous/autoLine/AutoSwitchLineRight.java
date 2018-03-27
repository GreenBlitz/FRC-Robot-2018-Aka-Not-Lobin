package org.usfirst.frc.team4590.robot.commands.autonomous.autoLine;

import org.usfirst.frc.team4590.robot.commands.autonomous.autoSwitch.right.AutoSwitchRightReverse;
import org.usfirst.frc.team4590.robot.commands.chassis.DriveForwardsByMeters;
import org.usfirst.frc.team4590.utils.commandChain.CommandChain;
import org.usfirst.frc.team4590.utils.gameData.GBGameData;
import org.usfirst.frc.team4590.utils.gameData.Lengths;
import org.usfirst.frc.team4590.utils.gameData.GBGameData.GameEntity;

public class AutoSwitchLineRight extends CommandChain {
	
	@Override
	protected void onFirstRun() {
		while (!GBGameData.getInstance().hasData(GameEntity.SWITCH)) {};
		
		if (GBGameData.getInstance().charAt(GameEntity.SWITCH) == 'R')
			addCommand(new AutoSwitchRightReverse());
		else
			addCommand(new DriveForwardsByMeters(-(Lengths.SWITCH_FROM_ALLIANCE_WALL)));
	}
}
