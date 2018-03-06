package org.usfirst.frc.team4590.robot.commands.pitcher;

import org.usfirst.frc.team4590.robot.subsystems.Pitcher;

import edu.wpi.first.wpilibj.command.Command;

public class RawValueToPitcher extends Command{
	
	private double m_value;
	
	public RawValueToPitcher(double value){
		m_value = value;
		requires(Pitcher.getInstance());
	}
	protected boolean isFinished(){ return false; }
	
	protected void execute(){ Pitcher.getInstance().setPower(m_value);}
	
	protected void end(){ Pitcher.getInstance().stop();}
}
