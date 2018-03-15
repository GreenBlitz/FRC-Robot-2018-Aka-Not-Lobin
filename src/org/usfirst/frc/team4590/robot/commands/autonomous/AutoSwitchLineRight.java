package org.usfirst.frc.team4590.robot.commands.autonomous;

import org.usfirst.frc.team4590.robot.commands.chassis.DriveForwardsByMeters;
import org.usfirst.frc.team4590.utils.CommandChain;
import org.usfirst.frc.team4590.utils.GBGameData;
import org.usfirst.frc.team4590.utils.Lengths;
import org.usfirst.frc.team4590.utils.GBGameData.GameEntity;

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
