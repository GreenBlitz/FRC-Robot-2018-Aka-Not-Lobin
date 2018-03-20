
package org.usfirst.frc.team4590.robot;

import org.usfirst.frc.team4590.robot.commands.claw.CloseClaw;
import org.usfirst.frc.team4590.robot.commands.claw.OpenClaw;
import org.usfirst.frc.team4590.robot.commands.claw.OpenClawOnWings;
import org.usfirst.frc.team4590.robot.commands.commandChains.PickupCube;
import org.usfirst.frc.team4590.robot.commands.intake.Collect;
import org.usfirst.frc.team4590.robot.commands.intake.ShootToSwitch;
import org.usfirst.frc.team4590.robot.commands.pitcher.MovePitcher;
import org.usfirst.frc.team4590.robot.commands.pitcher.StopPitcher;
import org.usfirst.frc.team4590.robot.commands.shifter.ManualSwitchShifts;
import org.usfirst.frc.team4590.robot.commands.vision.DriveByVision;
import org.usfirst.frc.team4590.utils.PitcherState;
import org.usfirst.frc.team4590.utils.SmartJoystick;
import org.usfirst.frc.team4590.utils.SmartJoystick.JoystickAxis;

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
		mainJS.A.whenPressed(new OpenClaw());
		mainJS.B.whileHeld(new CloseClaw());
		mainJS.L1.whenPressed(new PickupCube());
		mainJS.R1.whileHeld(new ShootToSwitch());
		//Chassis control by arcade drive
		

		//Assigning Key Bindings ---> Joystick 2 (SideJS)
		sideJS.A.whenPressed(new MovePitcher(PitcherState.COLLECT));
		sideJS.B.whenPressed(new MovePitcher(PitcherState.EXCHANGE));
		sideJS.Y.whenPressed(new MovePitcher(PitcherState.SWITCH_FORWARD));
		sideJS.X.whenPressed(new MovePitcher(PitcherState.SWITCH_BACKWARD));
		sideJS.START.whenPressed(new MovePitcher(PitcherState.PLATE));
//		sideJS.L3.whenPressed(new StopPitcher());
		sideJS.L3.whenPressed(new Collect());
		sideJS.R3.whileHeld(new OpenClawOnWings());
		sideJS.BACK.whileHeld(new DriveByVision());
		sideJS.L1.whileHeld(new CloseClaw());
		sideJS.R1.whileHeld(new OpenClaw());
		//Left trigger to move the climber down
		//Right trigger to move the climber up
	}
	
	public SmartJoystick getMainJS() {
		return mainJS;
	}
	
	public SmartJoystick getSideJS() {
		return sideJS;
	}
}