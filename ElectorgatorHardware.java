package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

/**
 * Created by Luke on 10/1/2017.
 */

public class ElectorgatorHardware {
    // declare all motors and sensors here:
    DcMotor frontRightDrive = null;
    DcMotor frontLeftDrive  = null;
    DcMotor backRightDrive  = null;
    DcMotor backLeftDrive   = null;

    BNO055IMU imu           = null;
    Orientation orientation;
    Acceleration acceleration;

    HardwareMap hardwareMap = null;

    public ElectorgatorHardware (){}

    public void initMotors (HardwareMap hardware) {
        hardwareMap = hardware;

        // define and initialize motors
        frontRightDrive = hardwareMap.dcMotor.get("frd");
        frontLeftDrive  = hardwareMap.dcMotor.get("fld");
        backRightDrive  = hardwareMap.dcMotor.get("brd");
        backLeftDrive   = hardwareMap.dcMotor.get("bld");

        // set speed
        frontRightDrive.setPower(0.0);
        frontLeftDrive.setPower(0.0);
        backRightDrive.setPower(0.0);
        backLeftDrive.setPower(0.0);

        // set direction
        frontLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);

        // set mode
        frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void initIMU (HardwareMap hardware) {
        hardwareMap = hardware;

        // initialise the IMU
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        // setup the accelerometer
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.accelPowerMode = BNO055IMU.AccelPowerMode.NORMAL;
        parameters.accelBandwidth = BNO055IMU.AccelBandwidth.HZ62_5;
        // setup the gyro
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.gyroPowerMode = BNO055IMU.GyroPowerMode.FAST;
        parameters.gyroBandwidth = BNO055IMU.GyroBandwidth.HZ32;
        parameters.gyroRange = BNO055IMU.GyroRange.DPS2000;
        // setup the magnetometer
        parameters.magPowerMode = BNO055IMU.MagPowerMode.FORCE;
        parameters.magRate = BNO055IMU.MagRate.HZ20;
        // setup the calibration files and logging
        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
    }
}
