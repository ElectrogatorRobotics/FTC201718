package org.firstinspires.ftc.teamcode.library;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by cameron.czekai on 11/1/2017.
 */

public interface Drive {
	public static final double MIN_SPEED = 0.2;

	/**
	 * Initalise the entire robot (all sensors and motors) with one function call.
	 *
	 * @param hardwareMap
	 */
	void initRobot(HardwareMap hardwareMap);

	/**
	 * @param targetDist
	 * @param motor
	 * @param stop
	 * @param driveMotor
	 */
	void driveMotorToTarget(int targetDist, DcMotor motor, boolean stop, Proportional.ProportionalMode driveMotor);

	/**
	 * Set the speed of a motor with or with out expo.
	 * Use this method if the expo base needs to be less than or grater than 5
	 *
	 * @param speed
	 * @param controlMode
	 * @param expoBase
	 * @return
	 */
	double setMotorSpeed(double speed, DriveImpl.MotorControlMode controlMode, double expoBase);

	/**
	 * Set the speed of a motor with or with out expo.
	 * Use this method if the expo base needs to be 5
	 *
	 * @param speed
	 * @param controlMode
	 * @return
	 */
	double setMotorSpeed(double speed, DriveImpl.MotorControlMode controlMode);

	/**
	 *
	 * @param throttle
	 * @param minValue
	 * @return
	 */
	double throttleControl (double throttle, double minValue);

	/**
	 *
	 * @param speed
	 * @param controlMode
	 * @param throttle
	 * @return
	 */
	double setMotorSpeedWithThrottle (double speed, DriveImpl.MotorControlMode controlMode, double throttle);

	/**
	 * Thees are the motor control modes that we can use
	 */
	enum MotorControlMode {
		EXPO_CONTROL, LINEAR_CONTROL
	}

	enum ThrottleControl {LEFT_TRIGGER, RIGHT_TRIGGER}
}
