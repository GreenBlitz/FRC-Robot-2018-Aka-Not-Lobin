package org.usfirst.frc.team4590.utils;

import org.usfirst.frc.team4590.utils.gameData.GBGameData;
import org.usfirst.frc.team4590.utils.gameData.GBGameData.GameEntity;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.ConditionalCommand;

public class AutonomousCommand extends ConditionalCommand {
	private GameEntity m_entity;
	
	public AutonomousCommand(GameEntity entity, Command onLeft, Command onRight) {
		super(onLeft, onRight);
		m_entity = entity;
	}
	
	protected boolean condition() {
		while (!GBGameData.getInstance().hasData(m_entity));
		
		return GBGameData.getInstance().charAt(m_entity) == 'L';
	}
}