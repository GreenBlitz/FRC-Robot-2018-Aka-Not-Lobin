package org.usfirst.frc.team4590.robot;

import org.usfirst.frc.team4590.robot.commands.claw.CloseClaw;
import org.usfirst.frc.team4590.robot.commands.claw.OpenClaw;
import org.usfirst.frc.team4590.robot.commands.commandChains.PickupCube;
import org.usfirst.frc.team4590.robot.commands.intake.ShootToSwitch;
import org.usfirst.frc.team4590.robot.commands.pitcher.MovePitcherToState;
import org.usfirst.frc.team4590.utils.PitcherState;
import org.usfirst.frc.team4590.utils.SmartJoystick;
import org.usfirst.frc.team4590.utils.SmartJoystick.JoystickAxis;
import org.usfirst.frc.team4590.utils.SmartJoystick.JoystickBinding;

public class OI {

	private static OI instance;

	private SmartJoystick mainJS;

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

		mainJS.getButton(JoystickBinding.R1).whileHeld(new ShootToSwitch());
		
//		mainJS.getButton(JoystickBinding.Y).whenPressed(new ManualSwitchShifts());
		mainJS.getButton(JoystickBinding.A).whenPressed(new OpenClaw());
		mainJS.getButton(JoystickBinding.B).whenPressed(new CloseClaw());
		mainJS.getButton(JoystickBinding.L1).whenPressed(new PickupCube());

		mainJS.getButton(JoystickBinding.X).whenPressed(new MovePitcherToState(PitcherState.SWITCH_BACKWARD));
		mainJS.getButton(JoystickBinding.Y).whenPressed(new MovePitcherToState(PitcherState.EXCHANGE));
		mainJS.getButton(JoystickBinding.START).whenPressed(new MovePitcherToState(PitcherState.COLLECT));

//		mainJS.getButton(JoystickBinding.X).whileHeld(new ClimbUp());
	}

	public SmartJoystick getMainJS() {
		return mainJS;
	}
}