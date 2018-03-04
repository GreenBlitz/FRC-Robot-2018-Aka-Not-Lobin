package org.usfirst.frc.team4590.utils;

import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class SmartSubsystem extends Subsystem {
	
	public SmartSubsystem() {
		SubsystemManager.addSubsystem(this);
	}
	
	public abstract void update();
	
	public abstract void init();
}
