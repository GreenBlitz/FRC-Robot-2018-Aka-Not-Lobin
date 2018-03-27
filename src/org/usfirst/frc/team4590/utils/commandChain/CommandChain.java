package org.usfirst.frc.team4590.utils.commandChain;

import java.lang.reflect.Field;
import java.util.Vector;

import edu.wpi.first.wpilibj.command.Command;

public class CommandChain extends Command {
	
	
	public CommandChain() {
		addCommand(new Command() {protected boolean isFinished() {return true;}});
	}

	private final Vector<ParallelCommand> m_commands = new Vector<ParallelCommand>();
	private int m_currentCommand = 0;
	private boolean m_hasRan = false;
	
	protected final void addCommand(Command toRun) {
		m_commands.add(new ParallelCommand(toRun));
	}
	
	/**
	 *  Adds a command to run after the last entered group of parallel commands.
	 */
	protected final void addSequential(Command toRun) {
		addCommand(toRun);
	}
	
	protected final void addSequential(Command toRun, Command after) {
		for (ParallelCommand parallelCommand : m_commands) {
			if (parallelCommand.contains(after)) {
				try {
				m_commands.get(m_commands.indexOf(parallelCommand) + 1).addCommand(toRun);
				} catch (Exception e) {
					m_commands.add(new ParallelCommand(toRun));
				}
				return;
			}
		}
		throw new IllegalArgumentException("The Command " + after.getName() + 
				" is not a part of this command chain. please enter it beforehand.");
	}
	
	/**
	 * Adds a command to run with the last entered group of parallel commands.
	 */
	protected final void addParallel(Command toRun) {
		addParallel(toRun, m_commands.lastElement().getParallelCommands().get(0));
	}
	
	protected final void addParallel(Command toRun, Command with) {
		for (ParallelCommand parallelCommand : m_commands) {
			if (parallelCommand.contains(with)) {
				parallelCommand.addCommand(toRun);
				return;
			}
		}
		throw new IllegalArgumentException("The Command " + with.getName() + 
				" is not a part of this command chain. please enter it beforehand.");
	}

	@Override
	protected final void initialize() {
		if (!m_hasRan){
			m_hasRan = true;
			onFirstRun();
		}
		m_currentCommand = 0;
		m_commands.get(m_currentCommand).runCommands();
	}
	
	protected void onFirstRun() {}
	
	@Override
	protected final void execute() {
		ParallelCommand currentCommands = m_commands.get(m_currentCommand);
		if (currentCommands.isFinished()) {
			m_currentCommand++;
			if (!isFinished()) {
				m_commands.get(m_currentCommand).runCommands();
				return;
			}
		}
	}
	
	@Override
	protected final boolean isFinished() {
		return m_currentCommand == m_commands.size();
	}
}