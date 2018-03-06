package org.usfirst.frc.team4590.robot.commands.pitcher;

import org.usfirst.frc.team4590.robot.subsystems.Pitcher;
import org.usfirst.frc.team4590.utils.PitcherState;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

class HoldPitcherInState extends Command implements PitcherCommand {

	private static final double ABSOLUTE_TOLARENCE = 5d/180, 
								STATIC_POWER = 0.17;
	
	private double m_position;
	private PitcherState m_state;
	private boolean onCollectOrPlate;
	
    HoldPitcherInState(PitcherState state) {
    	requires(Pitcher.getInstance());
    	onCollectOrPlate = state == PitcherState.COLLECT || state == PitcherState.PLATE;
    	m_state = state;
    	m_position = state.getPosition();
    }

    protected void execute() {
    	if (!onCollectOrPlate) {
    		double equalibriumOffset = Pitcher.getInstance().getAngle() - Pitcher.EQUALIBRIUM_ANGLE,
    			   power = Math.abs(STATIC_POWER * Math.sin(Math.toRadians(equalibriumOffset)));
    		Pitcher.getInstance().setPower(equalibriumOffset > 0 ? power : -power);
   
    		if (Math.abs(m_position - Pitcher.getInstance().getPosition()) > ABSOLUTE_TOLARENCE)
    			Scheduler.getInstance().add(new MovePitcherToState(m_state));
    	}
    	else 
    		Pitcher.getInstance().stop();
    }

    protected boolean isFinished() {
        return false;
    }
    
    public PitcherState getToState() {
		return m_state;
	}
	
	public double getToPosition() {
		return m_position;
	}
}