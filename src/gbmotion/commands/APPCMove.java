package gbmotion.commands;

import org.usfirst.frc.team4590.robot.subsystems.Chassis;

import edu.wpi.first.wpilibj.command.Command;
import gbmotion.appc.APPController;
import gbmotion.base.controller.AbstractController.State;
import gbmotion.path.ArenaMap;

public class APPCMove extends Command {

	private APPController m_controller;

	public APPCMove(ArenaMap p, double lookAhead, double tolDist, double tolTime, double slowDist) {
		requires(Chassis.getInstance());
		m_controller = new APPController(
				Chassis.getInstance().getLocalizer(),
				Chassis.getInstance().getAPPCOut(),
				0.025,
				p,
				lookAhead,
				tolDist,
				tolTime,
				slowDist);
	}

	@Override
	protected void end() {
		Chassis.getInstance().stop();
	}

	@Override
	protected void initialize() {
		m_controller.start();
	}

	@Override
	protected boolean isFinished() {
		return m_controller.getControllerState() == State.END;
	}

}
