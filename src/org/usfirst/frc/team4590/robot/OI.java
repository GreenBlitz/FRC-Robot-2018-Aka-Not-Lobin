package org.usfirst.frc.team4590.robot;

import org.usfirst.frc.team4590.robot.commands.claw.AcceleratedCloseClaw;
import org.usfirst.frc.team4590.robot.commands.claw.AcceleratedOpenClaw;
import org.usfirst.frc.team4590.robot.commands.commandChains.OpenClawAndShootToScale;
import org.usfirst.frc.team4590.robot.commands.commandChains.PickupCube;
import org.usfirst.frc.team4590.robot.commands.intake.ShootToSwitch;
import org.usfirst.frc.team4590.robot.commands.pitcher.MovePitcher;
import org.usfirst.frc.team4590.robot.commands.shifter.ManualSwitchShifts;
import org.usfirst.frc.team4590.robot.commands.vision.DriveByVision;
import org.usfirst.frc.team4590.utils.SmartJoystick;
import org.usfirst.frc.team4590.utils.SmartJoystick.JoystickAxis;
import org.usfirst.frc.team4590.utils.enums.PitcherState;

public class OI {

	private static OI instance;

	private SmartJoystick mainJS, sideJS;

	public static OI getInstance() {
		return instance;
	}

	public static void init() {
		instance = new OI();
	}

	private OI() {
		mainJS = new SmartJoystick(RobotMap.MAINJS_ID);
		mainJS.setAxisInverted(JoystickAxis.LEFT_Y, true);
		mainJS.setAxisInverted(JoystickAxis.RIGHT_Y, true);
		
		sideJS = new SmartJoystick(RobotMap.SIDEJS_ID);
		sideJS.setAxisInverted(JoystickAxis.LEFT_Y, true);
		sideJS.setAxisInverted(JoystickAxis.RIGHT_Y, true);
		
		//Assigning Key Bindings ---> Joystick 0 (MainJS)
		mainJS.Y.whenPressed(new ManualSwitchShifts());
		mainJS.A.whenPressed(new AcceleratedOpenClaw(500));
		mainJS.B.whileHeld(new AcceleratedCloseClaw(500, false, false));
		mainJS.L1.whenPressed(new PickupCube());
		mainJS.R1.whileHeld(new ShootToSwitch());
		mainJS.BACK.whileHeld(new DriveByVision());
		//Chassis control by arcade drive
		//Left trigger to move the climber down
		//Right trigger to move the climber up

		//Assigning Key Bindings ---> Joystick 2 (SideJS)
		sideJS.A.whenPressed(new MovePitcher(PitcherState.COLLECT));
		sideJS.B.whenPressed(new MovePitcher(PitcherState.EXCHANGE));
		sideJS.Y.whenPressed(new MovePitcher(PitcherState.SWITCH_FORWARD));
		sideJS.X.whenPressed(new MovePitcher(PitcherState.SWITCH_BACKWARD));
		sideJS.START.whenPressed(new MovePitcher(PitcherState.PLATE));
		sideJS.L3.whenPressed(new OpenClawAndShootToScale());
		sideJS.L1.whileHeld(new AcceleratedCloseClaw(500, false, false));
		sideJS.R1.whileHeld(new AcceleratedOpenClaw(500));
	}
	
	public SmartJoystick getMainJS() {
		return mainJS;
	}
	
	public SmartJoystick getSideJS() {
		return sideJS;
	}
}