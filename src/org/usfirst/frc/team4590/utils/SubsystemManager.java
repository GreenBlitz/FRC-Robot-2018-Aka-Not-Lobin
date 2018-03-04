package org.usfirst.frc.team4590.utils;

import java.util.ArrayList;
import java.util.List;

public final class SubsystemManager {
	private static List<SmartSubsystem> subsystems = new ArrayList<SmartSubsystem>();
	
	public static void initSubsystems() {
		
	}
	
	public static void updateSubsystems() {
		for (SmartSubsystem subsystem : subsystems)
			subsystem.update();
	}
	
	public static void addSubsystem(SmartSubsystem subsystem) {
		subsystems.add(subsystem);
	}
}
