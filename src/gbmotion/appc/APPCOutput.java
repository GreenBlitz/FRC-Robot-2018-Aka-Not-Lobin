package gbmotion.appc;

import org.usfirst.frc.team4590.robot.Robot;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import gbmotion.base.DrivePort;
import gbmotion.base.EnvironmentPort;
import gbmotion.base.controller.Output;
import gbmotion.util.RobotStats;

/**
 * Output object used in APPC
 * 
 * @see APPController
 * @author karlo
 */
public class APPCOutput implements Output<APPController.APPDriveData> {
	private static final double POWER_FACTOR = 0.9;
	private static final double FULL_POWER = 1; //0.8
	private static final double DEFAULT_ROTATION_FACTOR = -2.5;// RobotStats.VERTICAL_WHEEL_DIST
														// /
														// RobotStats.HORIZONTAL_WHEEL_DIST;

	private static double ROTATION_FACTOR = DEFAULT_ROTATION_FACTOR;
	
	private EnvironmentPort ePort = EnvironmentPort.DEFAULT;
	private DrivePort dPort = DrivePort.DEFAULT;
	private final int m_inverted;

	public static void setRotationFactor(double value) {
		ROTATION_FACTOR = value;
	}
	
	public static void resetRotationFactor() {
		ROTATION_FACTOR = DEFAULT_ROTATION_FACTOR;
	}
	
	public static double getDefaultRotationFactor() {
		return DEFAULT_ROTATION_FACTOR;
	}
	
	public APPCOutput(EnvironmentPort ePort, DrivePort dPort) {
		this.ePort = ePort;
		this.dPort = dPort;
		m_inverted = 1;
	}

	public APPCOutput() {
		this(false);
	}

	public APPCOutput(boolean inverted) {
		m_inverted = inverted ? -1 : 1;
	}

	public void setDrivePort(DrivePort dPort) {
		this.dPort = dPort;
	}

	public void setEnvironmentPort(EnvironmentPort ePort) {
		this.ePort = ePort;
	}

	/**
	 * Drive by curve and max power
	 * 
	 * @param r
	 *            DrivePort object
	 * @param power
	 *            maximal power possible
	 * @param curve
	 *            turning curvature
	 */
	@Deprecated
	public void curveDrive(DrivePort r, double power, double curve) {
		ePort.putNumber("Curve", curve);
		if (curve == 0) {
			r.tankDrive(power, power);
			ePort.putNumber("powerR", power);
			ePort.putNumber("powerL", power);
			return;
		}

		double d = RobotStats.Ragnarok.HORIZONTAL_WHEEL_DIST;
		double R = 1 / Math.abs(curve);
		double ratio;
		ratio = (R - d / 2) / (R + d / 2);
		ePort.putNumber("Ratio", ratio);
		Robot.managedPrinter.println(getClass(), "ratio: " + ratio + ", power: " + power);
		if (curve < 0) {
			r.tankDrive(power, power * ratio);
			ePort.putNumber("powerL", power);
			ePort.putNumber("powerR", power * ratio);
		} else {
			r.tankDrive(power * ratio, power);
			ePort.putNumber("powerL", power * ratio);
			ePort.putNumber("powerR", power);
		}
	}

	/**
	 * @see APPCOutput#calculatePelegDrive(double, double, double)
	 * @param maxPower
	 *            maximal power possible
	 * @param dXdY
	 *            x and y difference as an array
	 */
	public void cordDrive(double maxPower, double[] dXdY) {
		cordDrive(maxPower, dXdY[0], dXdY[1]);
	}

	/**
	 * Calculates the power to the engines by using the intuitive method
	 * (calculates correct ratio between engines and applies it to
	 * {@code maxPower}
	 * 
	 * @param maxPower
	 *            maximal power possible (will be used)
	 * @param dX
	 *            x axis difference
	 * @param dY
	 *            y axis difference
	 * @return the power that should be used on the left and right engines
	 */
	public static double[] calculatePelegDrive(double maxPower, double dX, double dY) {
		double left, right;

		double rotationPowerLeft = dX * ROTATION_FACTOR;
		double rotationPowerRight = -rotationPowerLeft;

		double powerUnscaledLeft = dY + rotationPowerLeft;
		double powerUnscaledRight = dY + rotationPowerRight;

		if (Math.abs(powerUnscaledLeft) > Math.abs(powerUnscaledRight)) {
			left = maxPower * sign(powerUnscaledLeft);
			right = left * powerUnscaledRight / powerUnscaledLeft;
		} else if (Math.abs(powerUnscaledLeft) < Math.abs(powerUnscaledRight)) {
			right = maxPower * sign(powerUnscaledRight);
			left = right * powerUnscaledLeft / powerUnscaledRight;
		} else {
			left = maxPower * Math.signum(powerUnscaledLeft);
			right = maxPower * Math.signum(powerUnscaledRight);
		}

		Robot.managedPrinter.warnln(APPCOutput.class, "power: left = " + left + ", right = " + right);
		return new double[] { left, right };
	}

	/**
	 * Applies the values gained by
	 * {@link APPCOutput#cordDrive(double, double, double)}
	 * 
	 * @see APPCOutput#calculatePelegDrive(double, double, double)
	 * @param maxPower
	 *            maximal power possible
	 * @param dX
	 *            x axis difference
	 * @param dY
	 *            y axis difference
	 */
	public void cordDrive(double maxPower, double dX, double dY) {
		double[] values = calculatePelegDrive(maxPower, dX, dY);
		tankDrive(values[0], values[1]);
	}

	/**
	 * @param output
	 *            the output to use on the engines. output[0]- power, output[1]-
	 *            curve
	 */
	@Override
	public void use(APPController.APPDriveData output) {
		Robot.managedPrinter.println(getClass(),
				"power: " + output.power + ", x diff: " + output.dx + ", y diff: " + output.dy);
		cordDrive(output.power, output.dx, output.dy);
	}

	@Override
	public APPController.APPDriveData noPower() {
		return new APPController.APPDriveData(.0, .0, .0);
	}

	/**
	 * @param left
	 *            left engines power
	 * @param right
	 *            right engines power
	 */
	private static double[] limitPower(double left, double right) {
		right *= POWER_FACTOR;
		left *= POWER_FACTOR;
		double c = left > right ? (left > FULL_POWER ? FULL_POWER / Math.abs(left) : 1)
				: (right > FULL_POWER ? FULL_POWER / Math.abs(right) : 1);
		return new double[] { c * left, c * right };
	}

	/**
	 * @param left
	 *            left engines power
	 * @param right
	 *            right engines power
	 * @param squared
	 *            squared inputs
	 */
	public void tankDrive(double left, double right) {
		NetworkTable motionTable = NetworkTable.getTable("motion");
		motionTable.putNumber("motorRight", right);
		motionTable.putNumber("motorLeft", left);
		double[] tmp = limitPower(left, right);
		left = m_inverted == 1 ? tmp[0] : tmp[1];
		right = m_inverted == 1 ? tmp[1] : tmp[0];
		dPort.tankDrive(m_inverted * left, m_inverted * right);
	}

	/**
	 * @param magnitude
	 *            drive power
	 * @param curve
	 *            drive curve
	 */
	public void arcadeDrive(double magnitude, double curve) {
		dPort.arcadeDrive(POWER_FACTOR * magnitude, curve);
	}

	/**
	 * Sometimes we just prefer this version over usual
	 * {@link Math#signum(double)}
	 * 
	 * @param num
	 * @return -1 if {@code num < 0}, 1 otherwise
	 */
	private static int sign(double num) {
		return num < 0 ? -1 : 1;
	}
}
