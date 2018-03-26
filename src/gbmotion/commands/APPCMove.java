package gbmotion.commands;

import org.usfirst.frc.team4590.robot.subsystems.Chassis;

import edu.wpi.first.wpilibj.command.Command;
import gbmotion.appc.APPCOutput;
import gbmotion.appc.APPController;
import gbmotion.base.controller.AbstractController.State;
import gbmotion.path.ArenaMap;

public class APPCMove extends Command {

	private APPController m_controller;
	private double m_lookAhead, m_tolDist, m_tolTime, m_slowDist, m_rotation;
	private boolean m_reverse;
	private ArenaMap m_map;
	
	public APPCMove(ArenaMap map, double lookAhead, double tolDist, double tolTime, double slowDist, boolean reverse, double rotation) {
		requires(Chassis.getInstance());
		m_map = map;
		m_lookAhead = lookAhead;
		m_tolDist = tolDist;
		m_tolTime = tolTime;
		m_slowDist = slowDist;
		m_reverse = reverse;
		m_rotation = rotation;
	}
	
	public APPCMove(ArenaMap map, double lookAhead, double tolDist, double tolTime, double slowDist, boolean reverse) {
		this(map, lookAhead, tolDist, tolTime, slowDist, reverse, APPCOutput.getDefaultRotationFactor());
	}

	@Override
	protected void end() {
		APPCOutput.resetRotationFactor();
		m_controller.free();
		Chassis.getInstance().stop();
		System.out.println("Finished APPC");
	}

	@Override
	protected void initialize() {
		m_controller = new APPController(
				Chassis.getInstance().getLocalizer(m_reverse),
				Chassis.getInstance().getAPPCOut(m_reverse),
				0.0125,
				m_map,
				m_lookAhead,
				m_tolDist,
				m_tolTime,
				m_slowDist);
		System.out.println("Starting APPC");
		APPCOutput.setRotationFactor(m_rotation);
		m_controller.start();
	}
	
	@Override
	protected boolean isFinished() {
		return m_controller.getControllerState() == State.END;
	}
}