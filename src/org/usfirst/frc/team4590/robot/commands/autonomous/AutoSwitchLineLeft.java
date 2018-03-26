package org.usfirst.frc.team4590.robot.commands.autonomous;

import org.usfirst.frc.team4590.robot.commands.chassis.DriveForwardsByMeters;
import org.usfirst.frc.team4590.utils.commandChain.CommandChain;
import org.usfirst.frc.team4590.utils.gameData.GBGameData;
import org.usfirst.frc.team4590.utils.gameData.Lengths;
import org.usfirst.frc.team4590.utils.gameData.GBGameData.GameEntity;

public class AutoSwitchLineLeft extends CommandChain {

	@Override
	protected void onFirstRun() {
		while (!GBGameData.getInstance().hasData(GameEntity.SWITCH)) {};
		
		if (GBGameData.getInstance().charAt(GameEntity.SWITCH) == 'L')
			addCommand(new AutoSwitchLeftReverse());
		else
			addCommand(new DriveForwardsByMeters(-(Lengths.SWITCH_FROM_ALLIANCE_WALL)));
	}
}
