package org.usfirst.frc.team4590.robot.commands.pitcher;

import org.usfirst.frc.team4590.robot.subsystems.Pitcher;
import org.usfirst.frc.team4590.utils.PitcherState;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class GuydeHoldPitcher extends Command implements PitcherCommand {
	
	protected static final double GRAVITY_DEFLECTOR_POS = -0.14,
			  					GRAVITY_DEFLECTOR_NEG = -0.14;

	protected static final double ABSOLUTE_TOLARENCE = 5d/180;
	protected static final double CENTER_OF_MASS_ANGLE = 45;
	
	private boolean onCollectOrPlate;
	
	private PitcherState m_state;

	public GuydeHoldPitcher(PitcherState state) {
		m_state = state;
    }

    protected void execute() {
    	if (!onCollectOrPlate) {
    		double deflector = getGravityDeflector(Pitcher.getInstance().getAngle());
    		double angleDiff = m_state.getAngle() - Pitcher.getInstance().getAngle();
    		double powDiff = angleDiff * (-0.25/180);
    		double pow = deflector + powDiff;
    		Pitcher.getInstance().setPower(pow);
    		if (Math.abs(m_state.getPosition() - Pitcher.getInstance().getPosition()) > ABSOLUTE_TOLARENCE)
    			Scheduler.getInstance().add(new GuydeMovePitcher(m_state));
    	}
    	else 
    		Pitcher.getInstance().stop();
    }

    protected double getGravityDeflector(double angle) {
		double ret;
		if (angle > 150)
			ret = -Math.cos(Math.toRadians(angle + CENTER_OF_MASS_ANGLE)) * GRAVITY_DEFLECTOR_NEG;
		else if (angle > 110){
			ret = 0;
		} else
			ret = Math.cos(Math.toRadians(angle + CENTER_OF_MASS_ANGLE)) * GRAVITY_DEFLECTOR_POS;
		return ret;
	}

	protected boolean shouldFinish() {
        return false;
    }
    
    public PitcherState getState() {
    	return m_state;
    }
    
	@Override
	protected boolean isFinished() {
		return false;
	}
}