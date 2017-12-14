package org.firstinspires.ftc.teamcode.library;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by cameron.czekai on 11/1/2017.
 */

public class DriveImpl implements Drive {
    public DcMotor frontRightDrive = null;
    public DcMotor frontLeftDrive  = null;
    public DcMotor backRightDrive  = null;
    public DcMotor backLeftDrive   = null;


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

    public DriveImpl(){}

    public DriveImpl(HardwareMap hwm){
        initMotors(hwm);
    }

    public void initMotors (HardwareMap hardwareMap) {
        // initialize motors
        frontRightDrive = hardwareMap.dcMotor.get("front right drive");
        frontLeftDrive  = hardwareMap.dcMotor.get("front left drive");
        backRightDrive  = hardwareMap.dcMotor.get("back right drive");
        backLeftDrive   = hardwareMap.dcMotor.get("back left drive");

        // set speed
        frontRightDrive.setPower(0.0);
        frontLeftDrive.setPower(0.0);
        backRightDrive.setPower(0.0);
        backLeftDrive.setPower(0.0);


        // set direction
        frontLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);

        // set mode
        // TODO: 11/9/2017 set drive mode to RUN_USING_ENCODER once the encoders are hocked up
        frontRightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }


	/**
	 * Return the average position of the robot in the X axes
	 * @return
	 */
	public double getDriveX () {
		return (frontLeftDrive.getCurrentPosition() + frontRightDrive.getCurrentPosition() +
				backLeftDrive.getCurrentPosition() + backRightDrive.getCurrentPosition()) / 4;
	}

	/**
	 * Autonomous Methods:
	 */

	/**
	 * Move the drive to a precise location and orientation in one move.
	 * @param xInches
	 * @param yInches
	 * @param rotationDegrees
	 */
	public void driveToCord (double xInches, double yInches, double rotationDegrees) {
//		while () {

//		}
	}

	/**
	 * TeleOp methods:
	 */

	/**
	 * @param targetDist
	 * distance to drive in inches
	 * @param motor
	 * motor to drive
	 * @param stop
	 * stop after run if true, otherwise continue to drive
	 */
    public void driveMotorToTarget(int targetDist, DcMotor motor, boolean stop, Proportional.ProportionalMode driveMotor) {
	    int curntPos = 0;
    }

    /**
     * @param targetDist
     * distance to drive in inches
     * @param motor
     * motor to drive
     * @param stop
     * stop after run if true, otherwise continue to drive
     */
    public void driveToTarget(int targetDist, Proportional.ProportionalMode driveMotor){
        int curPosFL = frontLeftDrive.getCurrentPosition();
        int curPosFR = frontRightDrive.getCurrentPosition();
        int curPosBL = backLeftDrive.getCurrentPosition();
        int curPosBR = backRightDrive.getCurrentPosition();
        int targetFL = curPosFL + (int)(targetDist * ENCODER_TICKS_PER_INCH);
        int targetFR = curPosFR + (int)(targetDist * ENCODER_TICKS_PER_INCH);
        int targetBL = curPosBL + (int)(targetDist * ENCODER_TICKS_PER_INCH);
        int targetBR = curPosBR + (int)(targetDist * ENCODER_TICKS_PER_INCH);


        do {
            curPosFL = frontLeftDrive.getCurrentPosition();
            curPosFR = frontRightDrive.getCurrentPosition();
            curPosBL = backLeftDrive.getCurrentPosition();
            curPosBR = backRightDrive.getCurrentPosition();
            // calculate the speed of the motor proportionally from the distance form the target
	        frontLeftDrive.setPower(proportional.p(targetDist, curPosFL, driveMotor));
            frontRightDrive.setPower(proportional.p(targetDist, curPosFR, driveMotor));
            backLeftDrive.setPower(proportional.p(targetDist, curPosBL, driveMotor));
            backRightDrive.setPower(proportional.p(targetDist, curPosBR, driveMotor));
        } while (curPosFL < targetFL &&
                 curPosFR < targetFR &&
                 curPosBL < targetBL &&
                 curPosBR < targetBR);
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

	public void forward(int inches){
		driveToTarget(inches, Proportional.ProportionalMode.NONE );
	}

	public void turn(double angle){
        //need to come up with a way to handle turning. Kinda an issue.
    }
}
