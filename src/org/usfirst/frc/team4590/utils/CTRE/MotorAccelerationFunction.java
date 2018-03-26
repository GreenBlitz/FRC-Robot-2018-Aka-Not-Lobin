package org.usfirst.frc.team4590.utils.CTRE;

public abstract class MotorAccelerationFunction {
	public abstract double getAcceleration(double lastPower, double newPower);
	
	public static class Limited extends MotorAccelerationFunction{

		private double m_max;
		
		public Limited(double max){
			m_max = max;
		}
		
		@Override
		public double getAcceleration(double lastPower, double newPower) {
			double diff = (newPower - lastPower);
			double sign = Math.signum(diff);
			return Math.min(Math.abs(diff), m_max) * sign;
		}
		
	}
	
	public static class AccelLimited extends MotorAccelerationFunction{

		private double m_max;
		
		public AccelLimited(double max){
			m_max = max;
		}
		
		
		
		@Override
		public double getAcceleration(double lastPower, double newPower) {
			double diff = (newPower - lastPower);
			double sign = Math.signum(diff);
			if (Math.abs(lastPower) > Math.abs(newPower) && newPower * lastPower >= 0){
				return diff;
			}

			return Math.min(Math.abs(diff), m_max) * sign;
			
		}
		
	}
}
