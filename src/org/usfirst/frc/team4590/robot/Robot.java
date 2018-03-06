package org.usfirst.frc.team4590.robot;

import java.util.LinkedList;
import java.util.List;

import org.usfirst.frc.team4590.robot.commands.autonomous.AutoDriveForwardToSwitch;
import org.usfirst.frc.team4590.robot.commands.autonomous.AutoMiddleLLRS;
import org.usfirst.frc.team4590.robot.commands.autonomous.AutoMiddleWithYTeam;
import org.usfirst.frc.team4590.robot.commands.autonomous.AutoSwitchLeft;
import org.usfirst.frc.team4590.robot.commands.autonomous.AutoSwitchMiddle;
import org.usfirst.frc.team4590.robot.commands.autonomous.AutoSwitchRight;
import org.usfirst.frc.team4590.robot.commands.autonomous.reversed.ReverseAutoSwitchLeft;
import org.usfirst.frc.team4590.robot.commands.autonomous.reversed.ReverseAutoSwitchMiddle;
import org.usfirst.frc.team4590.robot.commands.autonomous.reversed.ReverseAutoSwitchRight;
import org.usfirst.frc.team4590.robot.commands.chassis.DriveForwardsByMeters;
import org.usfirst.frc.team4590.robot.commands.claw.GrabCube;
import org.usfirst.frc.team4590.robot.commands.shifter.SetShift;
import org.usfirst.frc.team4590.robot.subsystems.Chassis;
import org.usfirst.frc.team4590.robot.subsystems.Claw;
import org.usfirst.frc.team4590.robot.subsystems.Climber;
import org.usfirst.frc.team4590.robot.subsystems.Intake;
import org.usfirst.frc.team4590.robot.subsystems.Pitcher;
import org.usfirst.frc.team4590.robot.subsystems.Shifter;
import org.usfirst.frc.team4590.utils.GBGameData;
import org.usfirst.frc.team4590.utils.Lengths;
import org.usfirst.frc.team4590.utils.ShifterState;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	
	private static final Robot instance = new Robot();
	
	private List<Command> permanentCommands = new LinkedList<Command>();

	private Command m_autonomousCommand;
	private SendableChooser<Command> m_autonomousChooser;
	
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
		
		SmartDashboard.putString("Testing", "Working");
		
		CameraServer.getInstance().startAutomaticCapture();

		CameraServer.getInstance().getVideo().getSource().setFPS(30);
		CameraServer.getInstance().getVideo().getSource().setResolution(160, 120);
		m_autonomousChooser = new SendableChooser<>();
		m_autonomousChooser.addObject("Auto with Y team", new AutoMiddleWithYTeam());
		m_autonomousChooser.addObject("AutoLLineRSwitch", new AutoMiddleLLRS());
		m_autonomousChooser.addObject("AutoSwitch left", new AutoSwitchLeft());
		m_autonomousChooser.addDefault("AutoSwitch middle", new AutoSwitchMiddle());
		m_autonomousChooser.addObject("AutoSwitch right", new AutoSwitchRight());
		m_autonomousChooser.addObject("REVERSE AutoSwitch left", new ReverseAutoSwitchLeft());
		m_autonomousChooser.addDefault("REVERSE AutoSwitch middle", new ReverseAutoSwitchMiddle());
		m_autonomousChooser.addObject("REVERSE AutoSwitch right", new ReverseAutoSwitchRight());
		m_autonomousChooser.addObject("AutoLine left", new DriveForwardsByMeters(Lengths.SWITCH_FROM_ALLIANCE_WALL - Lengths.ROBOT_LENGTH));
		m_autonomousChooser.addObject("AutoLine middle", new AutoDriveForwardToSwitch());
		m_autonomousChooser.addObject("AutoLine right", new DriveForwardsByMeters(Lengths.SWITCH_FROM_ALLIANCE_WALL - Lengths.ROBOT_LENGTH));
		SmartDashboard.putData("Auto Command", m_autonomousChooser);
	}

	@Override
	public void autonomousInit() {
		Chassis.getInstance().resetSensors();
		m_autonomousCommand = m_autonomousChooser.getSelected();
		GBGameData.getInstance().insertData(getPlates());
		Scheduler.getInstance().add(new SetShift(ShifterState.POWER));
		Scheduler.getInstance().add(m_autonomousCommand);
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
		Scheduler.getInstance().add(new SetShift(ShifterState.POWER));
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
	
	@Override
	public void disabledPeriodic(){
		updateSubsystems();
	}

	public void updateSubsystems() {
		Pitcher.getInstance().update();
		Claw.getInstance().update();
		Intake.getInstance().update();
		Chassis.getInstance().update();
		Climber.getInstance().update();
		Shifter.getInstance().update();
	}
	
	public static String getPlates() {
		String ret = "";
		while (ret.isEmpty())
			ret +=  DriverStation.getInstance().getGameSpecificMessage();
		System.out.println(ret);
		return ret;
	}
}