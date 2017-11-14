package org.firstinspires.ftc.teamcode.library;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by cameron.czekai on 11/1/2017.
 */

public class DriveImpl implements Drive {
    ElectorgatorHardware robot = new ElectorgatorHardware();
	Proportional proportional = new Proportional();
	Gamepad gamepad1 = new Gamepad();
	Gamepad gamepad2 = new Gamepad();

	/**
	 * This is the minimum power that the drive train can move
	 */
	public static final double MIN_SPEED = 0.3;

    /**
     * Calculate the number of ticks per inch of the wheel
     *
     * (Wheel diameter * PI) *  ticks per wheel regulation
     * wheel diameter = 4 inches
     * ticks per revolution of wheel = 7 cunts per motor revulsion * 20 gearbox reduction (20:1)
     */
    public static final double ENCODER_TICKS_PER_INCH = (4 * Math.PI) * (7 * 20);

    public void initRobot (HardwareMap hardwareMap) {
        robot.initIMU(hardwareMap);
        robot.initMotors(hardwareMap);
    }

    /**
     * @param targetDist
     * distance to drive in inches
     * @param motor
     * motor to drive
     * @param stop
     * stop after run if true, otherwise continue to drive
     */
    public void driveMotorToTarget(int targetDist, DcMotor motor, boolean stop, Proportional.ProportionalMode driveMotor){
        int curntPos;
        do {
            curntPos = motor.getCurrentPosition();
	        // calculate the speed of the motor proportionally from the distance form the target
	        motor.setPower(proportional.p(targetDist, curntPos, driveMotor));
        } while (targetDist * ENCODER_TICKS_PER_INCH < curntPos);
    }

    public double setMotorSpeed (double speed, MotorControlMode controlMode, double expoBase){
	    switch (controlMode){
		    case EXPONENTIAL_CONTROL:
			    return Math.pow(Range.clip(speed, -1.0, 1.0), expoBase);
		    case LINEAR_CONTROL:
			    return Math.pow(Range.clip(speed, -1.0, 1.0), expoBase);
			default:
				return 0;
	    }
    }

	public double setMotorSpeed (double speed, MotorControlMode controlMode){
		switch (controlMode){
			case EXPONENTIAL_CONTROL:
				return Math.pow(Range.clip(speed, -1.0, 1.0), 5);
			case LINEAR_CONTROL:
				return Math.pow(Range.clip(speed, -1.0, 1.0), 5);
			default:
				return 0;
		}
	}

	public double setMotorSpeedWithThrottle (double speed, MotorControlMode controlMode, double throttle){
		switch (controlMode){
			case EXPONENTIAL_CONTROL:
				return Math.pow(Range.clip(speed *= throttleControl(throttle, MIN_SPEED), -1.0, 1.0), 5);

			case LINEAR_CONTROL:
				return Range.clip(speed *= throttleControl(throttle, MIN_SPEED), -1.0, 1.0);
			default:
				return 0;
		}
	}

	public double throttleControl (double throttle, double minValue) {
		if (throttle > minValue)
			return throttle;
		return minValue;
	}

	public enum MotorControlMode {EXPONENTIAL_CONTROL, LINEAR_CONTROL}

	public enum ThrottleControl {LEFT_TRIGGER, RIGHT_TRIGGER}
}
