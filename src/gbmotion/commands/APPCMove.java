package gbmotion.commands;

import org.usfirst.frc.team4590.robot.subsystems.Chassis;

import edu.wpi.first.wpilibj.command.Command;
import gbmotion.appc.APPController;
import gbmotion.base.controller.AbstractController.State;
import gbmotion.path.ArenaMap;
import gbmotion.path.Path;

public class APPCMove extends Command {

	private APPController m_controller;
	
	public APPCMove(ArenaMap p, double lookAhead, double tolDist, double tolTime, double slowDist, boolean reverse) {
		requires(Chassis.getInstance());
		m_controller = new APPController(
				Chassis.getInstance().getLocalizer(reverse),
				Chassis.getInstance().getAPPCOut(reverse),
				0.0125,
				p,
				lookAhead,
				tolDist,
				tolTime,
				slowDist);
	}

	@Override
	protected void end() {
		m_controller.free();
		Chassis.getInstance().stop();
		System.out.println("Finished APPC");
	}

	@Override
	protected void initialize() {
		System.out.println("Starting APPC");
		m_controller.start();
	}

	@Override
	protected boolean isFinished() {
		return m_controller.getControllerState() == State.END;
	}

}
