package org.usfirst.frc.team4590.robot;

import java.util.LinkedList;
import java.util.List;

import org.usfirst.frc.team4590.robot.commands.autonomous.AutoSwitchLeftReverse;
import org.usfirst.frc.team4590.robot.commands.autonomous.AutoSwitchLineLeft;
import org.usfirst.frc.team4590.robot.commands.autonomous.AutoSwitchLineRight;
import org.usfirst.frc.team4590.robot.commands.autonomous.AutoSwitchMiddleReverse;
import org.usfirst.frc.team4590.robot.commands.autonomous.AutoSwitchRightReverse;
import org.usfirst.frc.team4590.robot.commands.autonomous.drives.AutoReverseDriveMiddle;
import org.usfirst.frc.team4590.robot.commands.cannon.WaitToWindCannon;
import org.usfirst.frc.team4590.robot.commands.chassis.ArcadeDriveByValues;
import org.usfirst.frc.team4590.robot.commands.chassis.DriveForwardsByMeters;
import org.usfirst.frc.team4590.robot.commands.pin.SetPin;
import org.usfirst.frc.team4590.robot.commands.shifter.SetShift;
import org.usfirst.frc.team4590.robot.subsystems.Cannon;
import org.usfirst.frc.team4590.robot.subsystems.Chassis;
import org.usfirst.frc.team4590.robot.subsystems.Claw;
import org.usfirst.frc.team4590.robot.subsystems.Climber;
import org.usfirst.frc.team4590.robot.subsystems.Intake;
import org.usfirst.frc.team4590.robot.subsystems.Pin;
import org.usfirst.frc.team4590.robot.subsystems.Pitcher;
import org.usfirst.frc.team4590.robot.subsystems.Shifter;
import org.usfirst.frc.team4590.utils.CameraSender;
import org.usfirst.frc.team4590.utils.GBGameData;
import org.usfirst.frc.team4590.utils.Lengths;
import org.usfirst.frc.team4590.utils.ShifterState;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import gbmotion.base.controller.IterativeController;
import gbmotion.util.PrintManager;

public class Robot extends IterativeRobot {
	
	private static final Robot instance = new Robot();
	
	public static final PrintManager managedPrinter = new PrintManager();
	private CameraSender m_videoSender;
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
		Cannon.init();
		Chassis.init();
		Claw.init();
		Climber.init();
		Intake.init();
		Pin.init();
		Pitcher.init();
		Shifter.init();
		OI.init();
		managedPrinter.registerPrintable(IterativeController.class);
		managedPrinter.registerPrintable(IterativeController.IterativeCalculationTask.class);
		m_videoSender = new CameraSender();
		
		m_videoSender.start();
		
		//CameraServer.getInstance().startAutomaticCapture();
		//CameraServer.getInstance().getVideo().getSource().setFPS(30);
		//CameraServer.getInstance().getVideo().getSource().setResolution(320, 240);
		
		m_autonomousChooser = new SendableChooser<>();
//		m_autonomousChooser.addObject("Auto Motion Test", new AutoMotionTest());
		m_autonomousChooser.addObject("REVERSE AutoSwitch left", new AutoSwitchLeftReverse());
		m_autonomousChooser.addDefault("REVERSE AutoSwitch middle", new AutoSwitchMiddleReverse());
		m_autonomousChooser.addObject("REVERSE AutoSwitch right", new AutoSwitchRightReverse());
		m_autonomousChooser.addObject("REVERSE AutoLine left", new DriveForwardsByMeters(-(Lengths.SWITCH_FROM_ALLIANCE_WALL - Lengths.ROBOT_LENGTH)));
		m_autonomousChooser.addObject("REVERSE AutoLine middle", new AutoReverseDriveMiddle());
		m_autonomousChooser.addObject("REVERSE AutoLine right", new DriveForwardsByMeters(-(Lengths.SWITCH_FROM_ALLIANCE_WALL - Lengths.ROBOT_LENGTH)));
		m_autonomousChooser.addObject("REVERSE AutoSwitchLine left", new AutoSwitchLineLeft());
		m_autonomousChooser.addObject("REVERSE AutoSwitchLine right", new AutoSwitchLineRight());
	
		m_autonomousChooser.addObject("stupid shit", new ArcadeDriveByValues(-0.7, 0, 4000));
		
		SmartDashboard.putData("Autonomous chooser", m_autonomousChooser);
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
		initCommands();
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
		initCommands();
	}

	@Override
	public void teleopPeriodic() {
		updateSubsystems();		
		Scheduler.getInstance().run();
	}
	
	@Override
	public void disabledInit() {
		Chassis.getInstance().resetGyro();
		Chassis.getInstance().resetEncoders();
//		Chassis.getInstance().resetLocalizer();
	}
	
	@Override
	public void disabledPeriodic(){
		updateSubsystems();
	}
	
	public void updateSubsystems() {
		Cannon.getInstance().update();
		Chassis.getInstance().update();
		Claw.getInstance().update();
		Climber.getInstance().update();
		Intake.getInstance().update();
		Pin.getInstance().update();
		Pitcher.getInstance().update();
		Shifter.getInstance().update();
	}
	
	private void initCommands() {
		boolean isSafeToPullDownPlatform = (Pitcher.getInstance().getAngle() > 170
										   && Claw.getInstance().isOpen()) 
										   || Pitcher.getInstance().getAngle() < 90;
		
		Scheduler.getInstance().add(new SetPin(Value.kForward));
		Scheduler.getInstance().add(new SetShift(ShifterState.POWER));
		if (Cannon.getInstance().isPlatformDown() || !isSafeToPullDownPlatform)
			Scheduler.getInstance().add(new WaitToWindCannon());
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