package org.usfirst.frc.team4590.robot;

import org.usfirst.frc.team4590.robot.commands.claw.CloseClaw;
import org.usfirst.frc.team4590.robot.commands.claw.CloseClawControlled;
import org.usfirst.frc.team4590.robot.commands.claw.OpenClaw;
import org.usfirst.frc.team4590.robot.commands.commandChains.PickupCube;
import org.usfirst.frc.team4590.robot.commands.intake.Collect;
import org.usfirst.frc.team4590.robot.commands.intake.ShootToSwitch;
import org.usfirst.frc.team4590.robot.commands.pitcher.MovePitcherToState;
import org.usfirst.frc.team4590.robot.commands.pitcher.PitcherDown;
import org.usfirst.frc.team4590.robot.commands.pitcher.RawValueToPitcher;
import org.usfirst.frc.team4590.robot.commands.pitcher.StopPitcher;
import org.usfirst.frc.team4590.robot.commands.shifter.ManualSwitchShifts;
import org.usfirst.frc.team4590.utils.PitcherState;
import org.usfirst.frc.team4590.utils.SmartJoystick;
import org.usfirst.frc.team4590.utils.SmartJoystick.JoystickAxis;
import org.usfirst.frc.team4590.utils.SmartJoystick.JoystickBinding;

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
		mainJS.getButton(JoystickBinding.Y).whenPressed(new ManualSwitchShifts());
		mainJS.getButton(JoystickBinding.A).whileHeld(new OpenClaw());
		mainJS.getButton(JoystickBinding.B).whileHeld(new CloseClaw());
		mainJS.getButton(JoystickBinding.L1).whileHeld(new PickupCube());
		mainJS.getButton(JoystickBinding.R1).whileHeld(new ShootToSwitch());
		//Chassis control by arcade drive
		

		//Assigning Key Bindings ---> Joystick 1 (SideJS)
		sideJS.getButton(JoystickBinding.A).whenPressed(new MovePitcherToState(PitcherState.COLLECT));
		sideJS.getButton(JoystickBinding.B).whenPressed(new MovePitcherToState(PitcherState.EXCHANGE));
		sideJS.getButton(JoystickBinding.Y).whenPressed(new MovePitcherToState(PitcherState.SWITCH_FORWARD));
		sideJS.getButton(JoystickBinding.X).whenPressed(new MovePitcherToState(PitcherState.SWITCH_BACKWARD));
		sideJS.getButton(JoystickBinding.START).whenPressed(new MovePitcherToState(PitcherState.PLATE));
		sideJS.getButton(JoystickBinding.L3).whenPressed(new StopPitcher());
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