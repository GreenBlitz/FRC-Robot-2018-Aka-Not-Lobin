package org.usfirst.frc.team4590.robot;

import java.util.LinkedList;
import java.util.List;

import org.usfirst.frc.team4590.robot.commands.autonomous.AutoSwitch;
import org.usfirst.frc.team4590.robot.subsystems.Chassis;
import org.usfirst.frc.team4590.robot.subsystems.Claw;
import org.usfirst.frc.team4590.robot.subsystems.Climber;
import org.usfirst.frc.team4590.robot.subsystems.Intake;
import org.usfirst.frc.team4590.robot.subsystems.Pitcher;
import org.usfirst.frc.team4590.robot.subsystems.Shifter;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends IterativeRobot {
	
	private static final Robot instance = new Robot();;
	
	private List<Command> permanentCommands = new LinkedList<Command>();

	public static Robot getInstance() {
		return instance;
	}

	public void addPermanentCommand(Command command) {
		permanentCommands.add(command);
	}
	
	@Override
	public void robotInit() {
		Chassis.init();
		Intake.init();
		Claw.init();
		Pitcher.init();
		Climber.init();
		Shifter.init();
		OI.init();
	}

	@Override
	public void autonomousInit() {
		Scheduler.getInstance().removeAll();
		Scheduler.getInstance().add(new AutoSwitch());
	}

	@Override
	public void autonomousPeriodic() {
		updateSubsystems();
		Scheduler.getInstance().run();
	}
	
	@Override
	public void teleopInit() {
		Scheduler.getInstance().removeAll();
		Chassis.getInstance().resetSensors();
	}

	@Override
	public void teleopPeriodic() {
		updateSubsystems();
		Scheduler.getInstance().run();
	}
	
	@Override
	public void disabledInit() {
		//Scheduler.getInstance().add(new SetPitcherState(PitcherState.COLLECT));
	}

	public void updateSubsystems() {
		Pitcher.getInstance().update();
		Claw.getInstance().update();
		Intake.getInstance().update();
		Chassis.getInstance().update();
		Climber.getInstance().update();
		Shifter.getInstance().update();
	}
	
	public String getPlate() {
		String ret = "";
		while (ret.isEmpty())
			ret +=  DriverStation.getInstance().getGameSpecificMessage();
		System.out.println(ret);
		return ret;
	}
}