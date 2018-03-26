package org.usfirst.frc.team4590.utils.commandChain;

import java.util.Vector;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ParallelCommand {
	private Vector<Command> m_commands = new Vector<Command>();
	
	public ParallelCommand(Command command) {
		addParallel(command);
	}
	
	public void addParallel(Command command) {
		m_commands.add(command);
	}

	public Vector<Command> getParallelCommands() {
		return m_commands;
	}
	
	public boolean contains(Command command) {
		return m_commands.contains(command);
	}
	
	public void runCommands() {
		for (Command command : m_commands) 
			Scheduler.getInstance().add(command);
	}
	
	public boolean isCompleted() {
		for (Command command : m_commands) {
			if (command.isRunning())
				return false;
		}
		return true;
	}
	
	public boolean doesRequire(Subsystem subsystem) {
		for (Command command : m_commands) {
			if (command.doesRequire(subsystem))
				return true;
		}
		return false;
	}
}