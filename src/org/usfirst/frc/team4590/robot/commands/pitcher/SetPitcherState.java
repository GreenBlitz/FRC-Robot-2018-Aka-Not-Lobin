//package org.usfirst.frc.team4590.robot.commands.pitcher;
//
//import org.usfirst.frc.team4590.robot.subsystems.Pitcher;
//import org.usfirst.frc.team4590.utils.PitcherState;
//
//import edu.wpi.first.wpilibj.command.Command;
//
//public class SetPitcherState extends Command {
//	private PitcherState toState;
//	
//	public SetPitcherState(PitcherState state) {
//		toState = state;
//	}
//	
//	@Override
//	public void execute() {
//		Command currentCommand = Pitcher.getInstance().getCurrentCommand();
//		if (currentCommand instanceof __SetPitcherStateNotByPID) 
//			((__SetPitcherStateNotByPID) currentCommand).setToState(toState);
//	}
//
//	@Override
//	protected boolean isFinished() {
//		return true;
//	}
//}