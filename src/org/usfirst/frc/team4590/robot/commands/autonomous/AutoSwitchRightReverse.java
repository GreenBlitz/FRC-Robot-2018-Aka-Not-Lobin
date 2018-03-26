package org.usfirst.frc.team4590.robot.commands.autonomous;

import org.usfirst.frc.team4590.robot.commands.autonomous.drives.AutoReverseDriveRight;
import org.usfirst.frc.team4590.robot.commands.claw.GrabCube;
import org.usfirst.frc.team4590.robot.commands.intake.Collect;
import org.usfirst.frc.team4590.robot.commands.intake.ShootToSwitch;
import org.usfirst.frc.team4590.robot.commands.pitcher.MovePitcher;
import org.usfirst.frc.team4590.utils.commandChain.CommandChain;
import org.usfirst.frc.team4590.utils.enums.PitcherState;

import edu.wpi.first.wpilibj.command.Command;

public class AutoSwitchRightReverse extends CommandChain {
	
	public AutoSwitchRightReverse() {
		Command closeClaw = new GrabCube(),
				collect = new Collect(500);
		
		addCommand(closeClaw);
		addCommand(collect);
	}
	
	@Override
	public void onFirstRun() {
		Command driveToSwitch = new AutoReverseDriveRight(),
				movePitcher = new MovePitcher(PitcherState.SWITCH_BACKWARD),
				throwCube = new ShootToSwitch(1000);
		
		addCommand(driveToSwitch);
		addCommand(movePitcher);
		addCommand(throwCube);
	}
}
