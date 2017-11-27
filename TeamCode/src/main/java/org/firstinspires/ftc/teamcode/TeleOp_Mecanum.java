
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.library.Claw;
import org.firstinspires.ftc.teamcode.library.ClawImpl;
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
	Claw claw = new ClawImpl();

    double frontLeftDrive, frontRightDrive, backRightDrive, backLeftDrive;
    @Override
    public void runOpMode() throws InterruptedException {
	    boolean clawState = false;
	    boolean bPrevState = false;
	    boolean bCurrState = false;

	    // initialise the motors
        telemetry.addLine("Initialising... please wait.");
        telemetry.update();

        gamepad1.setJoystickDeadzone(0.9f);
	    hardware.initMotors(hardwareMap);
	    hardware.initLifter(hardwareMap);

        telemetry.addLine("Ready to start... thank you for waiting!");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            // calculate the motor speeds
            frontRightDrive = gamepad1.left_stick_y + gamepad1.right_stick_x + gamepad1.left_stick_x;
            frontLeftDrive  = gamepad1.left_stick_y - gamepad1.right_stick_x - gamepad1.left_stick_x;
            backRightDrive  = gamepad1.left_stick_y + gamepad1.right_stick_x - gamepad1.left_stick_x;
            backLeftDrive   = gamepad1.left_stick_y - gamepad1.right_stick_x + gamepad1.left_stick_x;

			bCurrState = gamepad1.right_bumper;

	        hardware.frontLeftDrive.setPower(drive.setMotorSpeedWithThrottle(frontLeftDrive, DriveImpl.MotorControlMode.LINEAR_CONTROL, gamepad1.left_trigger));
	        hardware.frontRightDrive.setPower(drive.setMotorSpeedWithThrottle(frontRightDrive, DriveImpl.MotorControlMode.LINEAR_CONTROL, gamepad1.left_trigger));
	        hardware.backLeftDrive.setPower(drive.setMotorSpeedWithThrottle(backLeftDrive, DriveImpl.MotorControlMode.LINEAR_CONTROL, gamepad1.left_trigger));
	        hardware.backRightDrive.setPower(drive.setMotorSpeedWithThrottle(backRightDrive, DriveImpl.MotorControlMode.LINEAR_CONTROL, gamepad1.left_trigger));

	        if (bCurrState && (bCurrState != bPrevState))  {
		        clawState = !clawState;
		        sleep(200);
	        }

	        if (clawState) {
		        hardware.leftClaw.setPosition(0.6);
		        hardware.rightClaw.setPosition(0.6);
	        } else {
		        hardware.leftClaw.setPosition(0.5);
		        hardware.rightClaw.setPosition(0.5);
	        }

	         hardware.liftMotor.setPower(gamepad1.right_stick_y);

	        // display the motor speeds

            telemetry.addData("Front right drive speed = ", "%1.2f", hardware.frontRightDrive.getPower());
            telemetry.addData("Front left drive speed  = ", "%1.2f", hardware.frontLeftDrive.getPower());
            telemetry.addData("Back right drive speed  = ", "%1.2f", hardware.backRightDrive.getPower());
            telemetry.addData("Back left drive speed   = ", "%1.2f", hardware.backLeftDrive.getPower());
	        telemetry.addData("Lift speed              = ", "%1.2f", hardware.liftMotor.getPower());
//	        telemetry.addData("Claw position           = ", claw.getClawState());
	        telemetry.addData("Throttle                = ", "%1.2f", drive.throttleControl(gamepad1.left_trigger, drive.MIN_SPEED));
            telemetry.update();
        }
    }
}
