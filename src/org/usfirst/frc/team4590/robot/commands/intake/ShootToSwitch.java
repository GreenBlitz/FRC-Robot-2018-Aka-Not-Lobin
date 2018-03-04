package org.usfirst.frc.team4590.robot.commands.intake;

import org.usfirst.frc.team4590.robot.commands.pitcher.MovePitcherToState;
import org.usfirst.frc.team4590.robot.subsystems.Intake;
import org.usfirst.frc.team4590.utils.PitcherState;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class ShootToSwitch extends Command {

	private static final double POWER = 1;
	
	public ShootToSwitch(long timeout){
		super(timeout/1000d);
		requires(Intake.getInstance());
	}
	
	public ShootToSwitch() {
		requires(Intake.getInstance());
	}
	
    protected void execute() {
    	Intake.getInstance().setPower(POWER);
    }
    
    @Override
    protected boolean isFinished() {
    	return false;
    }
    
    protected void end() {
    	Intake.getInstance().stop();
    	Scheduler.getInstance().add(new MovePitcherToState(PitcherState.PLATE));
    }
}