package org.usfirst.frc.team4590.robot;

import java.util.LinkedList;
import java.util.List;

import org.usfirst.frc.team4590.robot.commands.autonomous.AutoSwitchLeftReverse;
import org.usfirst.frc.team4590.robot.commands.autonomous.AutoSwitchLineLeft;
import org.usfirst.frc.team4590.robot.commands.autonomous.AutoSwitchLineRight;
import org.usfirst.frc.team4590.robot.commands.autonomous.AutoSwitchMiddleReverse;
import org.usfirst.frc.team4590.robot.commands.autonomous.AutoSwitchRightReverse;
import org.usfirst.frc.team4590.robot.commands.autonomous.drives.AutoReverseDriveMiddle;
import org.usfirst.frc.team4590.robot.commands.chassis.ArcadeDriveByValues;
import org.usfirst.frc.team4590.robot.commands.chassis.DriveForwardsByMeters;
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
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import gbmotion.util.PrintManager;

// Actual robot

public class Robot extends IterativeRobot {
	
	private static final Robot instance = new Robot();
	
	public static final PrintManager managedPrinter = new PrintManager();
	
	private List<Command> permanentCommands = new LinkedList<Command>();

	private Command m_autonomousCommand;
	private SendableChooser<Command> m_autonomousChooser;

	private boolean endgame = false;
	
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
		
		CameraServer.getInstance().startAutomaticCapture();
		CameraServer.getInstance().getVideo().getSource().setFPS(30);
		CameraServer.getInstance().getVideo().getSource().setResolution(320, 240);
		
		m_autonomousChooser = new SendableChooser<>();
		m_autonomousChooser.addObject("REVERSE AutoSwitch left", new AutoSwitchLeftReverse());
		m_autonomousChooser.addObject("REVERSE AutoSwitch middle", new AutoSwitchMiddleReverse());
		m_autonomousChooser.addObject("REVERSE AutoSwitch right", new AutoSwitchRightReverse());
		m_autonomousChooser.addObject("REVERSE AutoLine left", new DriveForwardsByMeters(-(Lengths.SWITCH_FROM_ALLIANCE_WALL - Lengths.ROBOT_LENGTH)));
		m_autonomousChooser.addObject("REVERSE AutoLine middle", new AutoReverseDriveMiddle());
		m_autonomousChooser.addObject("REVERSE AutoLine right", new DriveForwardsByMeters(-(Lengths.SWITCH_FROM_ALLIANCE_WALL - Lengths.ROBOT_LENGTH)));
		m_autonomousChooser.addObject("REVERSE AutoSwitchLine left", new AutoSwitchLineLeft());
		m_autonomousChooser.addObject("REVERSE AutoSwitchLine right", new AutoSwitchLineRight());
	
		m_autonomousChooser.addObject("stupid shit", new ArcadeDriveByValues(-0.7, 0, 4000));
		
		SmartDashboard.putData("Chooser", m_autonomousChooser);
	}

	@Override
	public void autonomousInit() {
		endgame = false;
		Chassis.getInstance().resetSensors();
		/*Chassis.getInstance().resetLocalizer();
		Chassis.getInstance().enableLocalizer();*/
		Timer.delay(0.02);
		
		GBGameData.getInstance().insertData(getPlates());
		m_autonomousCommand = m_autonomousChooser.getSelected();
		Scheduler.getInstance().add(m_autonomousCommand);
		Scheduler.getInstance().add(new SetShift(ShifterState.POWER));
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
		
//		endgame = DriverStation.getInstance().getMatchTime() <= 30;
	}
	
	@Override
	public void disabledInit() {
		//Scheduler.getInstance().add(new SetPitcherState(PitcherState.COLLECT));
	}
	
	@Override
	public void disabledPeriodic(){
		updateSubsystems();
	}
	
	@Override
	public void testPeriodic(){}

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
	
	public boolean isEndgame() {
		return endgame;
	}
	
	public void setEndgame(boolean isEndgame) {
		endgame = isEndgame;
	}
}