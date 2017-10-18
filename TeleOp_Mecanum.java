package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Luke on 10/1/2017.
 */

@TeleOp(name = "Mecanum drive")
public class TeleOp_Mecanum extends LinearOpMode {
    ElectorgatorHardware hardware = new ElectorgatorHardware();

    double frontLeftDrive, frontRightDrive, backRightDrive, backLeftDrive;
    @Override
    public void runOpMode() throws InterruptedException {
        // initialise the motors
        telemetry.addLine("Initialising... please wait.");
        telemetry.update();

        hardware.initMotors(hardwareMap);

        telemetry.addLine("Ready to start... thank you for waiting!");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            // calculate the motor speeds
            frontRightDrive = gamepad1.left_stick_x - gamepad1.left_stick_y - gamepad1.right_stick_y;
            frontLeftDrive  = gamepad1.left_stick_x + gamepad1.left_stick_y + gamepad1.right_stick_y;
            backRightDrive  = gamepad1.left_stick_x - gamepad1.left_stick_y + gamepad1.right_stick_y;
            backLeftDrive   = gamepad1.left_stick_y + gamepad1.left_stick_y - gamepad1.right_stick_y;

            // set the motors to the speeds calculated above
            hardware.frontRightDrive.setPower(frontRightDrive);
            hardware.frontLeftDrive.setPower(frontLeftDrive);
            hardware.backRightDrive.setPower(backRightDrive);
            hardware.backLeftDrive.setPower(frontLeftDrive);

            // display the motor speeds
            telemetry.addLine("motor name               motor speed");
            telemetry.addLine();
            telemetry.addData("Front right drive speed = ", hardware.frontRightDrive);
            telemetry.addData("Front left drive speed  = ", hardware.frontLeftDrive);
            telemetry.addData("Back right drive speed  = ", hardware.backRightDrive);
            telemetry.addData("Back left drive speed   = ", hardware.backLeftDrive);
            telemetry.update();
        }
    }
}
