package org.usfirst.frc.team4590.robot.commands.autonomous;

import org.usfirst.frc.team4590.robot.commands.autonomous.autoSwitch.middle.AutoSwitchMiddleReverse;
import org.usfirst.frc.team4590.utils.commandChain.CommandChain;
import org.usfirst.frc.team4590.utils.gameData.GBGameData;
import org.usfirst.frc.team4590.utils.gameData.GBGameData.GameEntity;

/**
 * Autonomous for LRL (RIGHT) , LLR (LEFT), RRL (RIGHT) with Y team
 * @author user
 *
 */
public class AutoMiddleWithYTeam extends CommandChain {
	
	public AutoMiddleWithYTeam() {
		
	}
	
	@Override
	public void onFirstRun() {
		
		while(!GBGameData.getInstance().hasData(GameEntity.SWITCH)){}
		char mySwitch = GBGameData.getInstance().charAt(GameEntity.SWITCH);
		char myScale = GBGameData.getInstance().charAt(GameEntity.SCALE);
		
		if(mySwitch == 'R') {
			addCommand(new AutoSwitchMiddleReverse());
		} else {
			if(myScale == 'L'){
				addCommand(new AutoSwitchMiddleReverse());
			} else {
				addCommand(new AutoNoSwitchMiddle());
			}
		}
		
	}
	
}
