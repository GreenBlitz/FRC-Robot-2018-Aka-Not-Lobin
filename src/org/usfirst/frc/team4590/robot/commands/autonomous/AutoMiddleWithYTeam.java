package org.usfirst.frc.team4590.robot.commands.autonomous;

import org.usfirst.frc.team4590.utils.CommandChain;
import org.usfirst.frc.team4590.utils.GBGameData;
import org.usfirst.frc.team4590.utils.GBGameData.GameEntity;

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
			addCommand(new AutoSwitchMiddle());
		} else {
			if(myScale == 'L'){
				addCommand(new AutoSwitchMiddle());
			} else {
				addCommand(new AutoNoSwitchMiddle());
			}
		}
		
	}
	
}
