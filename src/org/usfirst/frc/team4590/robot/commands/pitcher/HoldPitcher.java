package org.usfirst.frc.team4590.robot.commands.pitcher;

import org.usfirst.frc.team4590.robot.commands.claw.AcceleratedCloseClaw;
import org.usfirst.frc.team4590.robot.commands.claw.ClosingClawCommand;
import org.usfirst.frc.team4590.robot.subsystems.Claw;
import org.usfirst.frc.team4590.robot.subsystems.Pitcher;
import org.usfirst.frc.team4590.utils.enums.PitcherDirection;
import org.usfirst.frc.team4590.utils.enums.PitcherState;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class HoldPitcher extends Command implements PitcherCommand {

	private static final double ABSOLUTE_TOLARENCE = 5d/180, 
								STATIC_POWER = 0.15;
	
	private double m_position;
	private PitcherState m_state;
	private boolean onCollectOrPlate;
	
    HoldPitcher(PitcherState state) {
    	requires(Pitcher.getInstance());
    	onCollectOrPlate = state == PitcherState.COLLECT || state == PitcherState.PLATE;
    	m_state = state;
    	m_position = state.getPosition();
    }
    
    @Override
    protected void initialize() {
    	Pitcher.getInstance().setDirection(PitcherDirection.STATIONARY);
    }

    protected void execute() {
    	if (!onCollectOrPlate) {
    		double equalibriumOffset = Pitcher.getInstance().getAngle() - Pitcher.EQUALIBRIUM_ANGLE,
    			   power = Math.abs(STATIC_POWER * Math.sin(Math.toRadians(equalibriumOffset)));
    		Pitcher.getInstance().setPower(equalibriumOffset > 0 ? power : -power);
   
    		if (Math.abs(m_position - Pitcher.getInstance().getPosition()) > ABSOLUTE_TOLARENCE)
    			Scheduler.getInstance().add(new MovePitcher(m_state));
    	}
    	else 
    		Pitcher.getInstance().stop();
    }

    protected boolean isFinished() {
        return false;
    }
    
    public PitcherState getState() {
		return m_state;
	}
	
	public double getToPosition() {
		return m_position;
	}
}