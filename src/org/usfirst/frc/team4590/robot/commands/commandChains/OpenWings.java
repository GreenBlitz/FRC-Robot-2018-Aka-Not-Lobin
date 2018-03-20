package org.usfirst.frc.team4590.robot.commands.commandChains;

import org.usfirst.frc.team4590.robot.commands.claw.CloseClaw;
import org.usfirst.frc.team4590.robot.commands.claw.OpenClawOnWings;
import org.usfirst.frc.team4590.robot.commands.pitcher.MovePitcher;
import org.usfirst.frc.team4590.utils.CommandChain;
import org.usfirst.frc.team4590.utils.PitcherState;

import edu.wpi.first.wpilibj.command.Command;

/**
 * prepare to climb by opening the wings.
 * <p>
 * ONLY USE WHEN THE ROBOT IS ON THE PLATFORM.
 * </p>
 */
public class OpenWings extends CommandChain {

    public OpenWings() {
        Command closeClaw = new CloseClaw(),
        		movePitcher = new MovePitcher(PitcherState.SWITCH_BACKWARD),
        		openClaw = new OpenClawOnWings(500l);
        
        addCommand(closeClaw);
        addSequential(movePitcher, closeClaw);
        addSequential(openClaw, movePitcher);
    }
}
