package org.usfirst.frc.team4590.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SPI;

public class RobotMap {
	//Joysticks
	public static final int MAINJS_ID = 0,
							SIDEJS_ID = 2;
	
	//Motor & Sensor ports
	//Chassis
	public static final int CHASSIS_FRONT_LEFT_MOTOR_PORT = 0,
							CHASSIS_REAR_LEFT_MOTOR_PORT = 1,
							CHASSIS_FRONT_RIGHT_MOTOR_PORT = 2,
							CHASSIS_REAR_RIGHT_MOTOR_PORT = 3;
	public static final SPI.Port CHASSIS_GYRO_PORT = SPI.Port.kMXP;
	
	//Shifter
	public static final int SHIFTER_SOLENOID_FORWARD_PORT = 2,
							SHIFTER_SOLENOID_REVERSE_PORT = 3;
	
	//Shooter
	public static final int SHOOTER_WINDER_MOTOR_PORT = 0,
							SHOOTER_BOTTOM_MICROSWITCH_PORT = 0,
							SHOOTER_ROPE_MICROSWITCH_PORT = 0;
							
		
	//Shooter Rotator
	public static final int ROTATOR_MOTOR_PORT = 0,
							ROTATOR_POTENTIOMETER_PORT = 0,
							ROTATOR_MIDDLE_MICROSWITCH_PORT = 0,
							ROTATOR_LEFT_MICROSWITCH_PORT = 0,
							ROTATOR_RIGHT_MICROSWITCH_PORT = 0;
	
	//Shooter Pin
	public static final int PIN_MOTOR_PORT = 0,
							PIN_MICROSWITCH_PORT = 0;
	
	//Claw
	public static final int CLAW_MOTOR_PORT = 7,
							CLAW_CLOSED_MICROSWITCH_PORT = 2,
							CLAW_OPEN_MICROSWITCH_PORT = 3;
	
	//Intake
	public static final int INTAKE_WHEELS_MOTOR_PORT = 6;
	
	//Pitcher
	public static final int PITCHER_MOTOR_PORT = 5,
							PITCHER_POTENTIOMETER_PORT = 1;
	
	//Climber
	public static final int CLIMBER_MOTOR_PORT = 4,
							CLIMBER_TOP_MICROSWITCH_PORT = 4,
							CLIMBER_BOTTOM_MICROSWITCH_PORT = 5;
	
	//Enum values
	//Pitcher
	public static final double PITCHER_COLLECT_VALUE = 0d/180,
							   PITCHER_SWITCH_FORWARD_VALUE = 80d/180,
							   PITCHER_SWITCH_BACKWARD_VALUE = 155d/180,
							   PITCHER_PLATE_VALUE = 180d/180,
							   PITCHER_EXCHANGE_VALUE = 20d/180;
	
	//Shooter rotation
	public static final double SHOOTER_ROTATION_LEFT_VALUE = 0,
							   SHOOTER_ROTATION_MIDDLE_VALUE = 0,
							   SHOOTER_ROTATION_RIGHT_VALUE = 0;
	
	//Shifter
	public static final DoubleSolenoid.Value SHIFTER_SPEED_VALUE = DoubleSolenoid.Value.kForward,
											 SHIFTER_POWER_VALUE = DoubleSolenoid.Value.kReverse;
}