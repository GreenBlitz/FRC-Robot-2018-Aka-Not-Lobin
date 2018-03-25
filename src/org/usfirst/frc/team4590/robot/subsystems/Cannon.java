package org.usfirst.frc.team4590.robot.subsystems;

import org.usfirst.frc.team4590.robot.RobotMap;
import org.usfirst.frc.team4590.robot.commands.cannon.PullDownPlatform;
import org.usfirst.frc.team4590.utils.SmartTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Cannon extends Subsystem {

	private static final double DEFAULT_POWER = 1;
	
	private static Cannon instance;
	
	private SmartTalon motor;
	private DigitalInput platform, rope;
	
	private boolean readyToShoot = true,
					startedWinding;
	
	private Cannon() {
		motor = new SmartTalon(RobotMap.CANNON_MOTOR_PORT);
		platform = new DigitalInput(RobotMap.CANNON_PLATFORM_MICROSWITCH_PORT);
		rope = new DigitalInput(RobotMap.CANNON_ROPE_MICROSWITCH_PORT);
		readyToShoot = isPlatformDown();
	}
	
	public static Cannon getInstance() {
		return instance;
	}
	
	public static void init() {
		instance = new Cannon();
	}
	
	public void update() {
		SmartDashboard.putString("Cannon current command", getCurrentCommandName());
		SmartDashboard.putBoolean("Cannon isPlatformDown", isPlatformDown());
		SmartDashboard.putBoolean("Cannon isRopeLoose", isRopeLoose());
		SmartDashboard.putBoolean("Cannon isReadyToShoot", readyToShoot);
	}
	
	public static double getDefaultPower() {
		return DEFAULT_POWER;
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new PullDownPlatform());
	}
	
	public void setPower(double power) {
		motor.set(power);
	}
	
	public void stop() {
		setPower(0);
	}
	
	public boolean isPlatformDown() {
		return platform.get();
	}
	
	public boolean isRopeLoose() {
		return !rope.get();
	}
	
	public boolean isReadyToShoot() {
		return readyToShoot;
	}
	
	public void setReadyToShoot(boolean isReadyToShoot) {
		readyToShoot = isReadyToShoot;
	}
	
	public boolean hasStartedWinding() {
		return startedWinding;
	}
	
	public void setStartedWinding(boolean hasStartedWinding) {
		startedWinding = hasStartedWinding;
	}
}