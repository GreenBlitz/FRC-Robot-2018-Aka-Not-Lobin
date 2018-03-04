package org.usfirst.frc.team4590.utils;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class SmartEncoder {
	private TalonSRX m_talon;
	private double m_ticksPerMeter;

	public SmartEncoder(TalonSRX talon, double ticksPerMeter) {
		if (ticksPerMeter == +0.0 || !Double.isFinite(ticksPerMeter) || ticksPerMeter == -0.0)
			throw new IllegalArgumentException("invalid ticks per meter value '" + ticksPerMeter + "'");

		m_talon = talon;
		m_ticksPerMeter = ticksPerMeter;
	}

	public double getTicksPerMeter() {
		return m_ticksPerMeter;
	}

	public int getTicks() {
		return m_talon.getSensorCollection().getQuadraturePosition();
	}

	public double getDistance() {
		return getTicks() / m_ticksPerMeter;
	}

	public double getSpeed() {
		return m_talon.getSensorCollection().getQuadratureVelocity();
	}

	public ErrorCode reset() {
		ErrorCode ec = m_talon.getSensorCollection().setQuadraturePosition(0, 10);
		if (ec != ErrorCode.OK) {
			System.err.println("error occured while reseting encoder '" + m_talon.getHandle() + "': " + ec);
		}
		return ec;
	}
}