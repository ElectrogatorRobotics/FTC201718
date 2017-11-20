
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.library.Drive;
import org.firstinspires.ftc.teamcode.library.DriveImpl;
import org.firstinspires.ftc.teamcode.library.ElectorgatorHardware;

/**
 * Created by Luke on 10/1/2017.
 */

@TeleOp(name = "Mecanum drive")
public class TeleOp_Mecanum extends LinearOpMode {
    ElectorgatorHardware hardware = new ElectorgatorHardware();
	Drive drive = new DriveImpl();

    double frontLeftDrive, frontRightDrive, backRightDrive, backLeftDrive;
    @Override
    public void runOpMode() throws InterruptedException {
        // initialise the motors
        telemetry.addLine("Initialising... please wait.");
        telemetry.update();

        gamepad1.setJoystickDeadzone(0.9f);
	    hardware.initMotors(hardwareMap);

        telemetry.addLine("Ready to start... thank you for waiting!");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            // calculate the motor speeds
            frontRightDrive = gamepad1.left_stick_y + gamepad1.right_stick_x + gamepad1.left_stick_x;
            frontLeftDrive  = gamepad1.left_stick_y - gamepad1.right_stick_x - gamepad1.left_stick_x;
            backRightDrive  = gamepad1.left_stick_y + gamepad1.right_stick_x - gamepad1.left_stick_x;
            backLeftDrive   = gamepad1.left_stick_y - gamepad1.right_stick_x + gamepad1.left_stick_x;

	        hardware.frontLeftDrive.setPower(drive.setMotorSpeedWithThrottle(frontLeftDrive, DriveImpl.MotorControlMode.LINEAR_CONTROL, gamepad1.left_trigger));
	        hardware.frontRightDrive.setPower(drive.setMotorSpeedWithThrottle(frontRightDrive, DriveImpl.MotorControlMode.LINEAR_CONTROL, gamepad1.left_trigger));
	        hardware.backLeftDrive.setPower(drive.setMotorSpeedWithThrottle(backLeftDrive, DriveImpl.MotorControlMode.LINEAR_CONTROL, gamepad1.left_trigger));
	        hardware.backRightDrive.setPower(drive.setMotorSpeedWithThrottle(backRightDrive, DriveImpl.MotorControlMode.LINEAR_CONTROL, gamepad1.left_trigger));

	        if (gamepad1.right_bumper)
	            hardware.jewelServo.setPosition(1.0);
	        else
	            hardware.jewelServo.setPosition(0.0);

	        hardware.liftMotor.setPower(gamepad1.right_stick_y);

	        // display the motor speeds

            telemetry.addData("Front right drive speed = ", "%1.2f", hardware.frontRightDrive.getPower());
            telemetry.addData("Front left drive speed  = ", "%1.2f", hardware.frontLeftDrive.getPower());
            telemetry.addData("Back right drive speed  = ", "%1.2f", hardware.backRightDrive.getPower());
            telemetry.addData("Back left drive speed   = ", "%1.2f", hardware.backLeftDrive.getPower());
	        telemetry.addData("Lift speed              = ", "%1.2f", hardware.liftMotor.getPower());
	        telemetry.addData("Claw position           = ", "%1.2f", hardware.liftMotor);
	        telemetry.addData("Throttle                = ", "%1.2f", drive.throttleControl(gamepad1.left_trigger, drive.MIN_SPEED));
            telemetry.update();
        }
    }
}
