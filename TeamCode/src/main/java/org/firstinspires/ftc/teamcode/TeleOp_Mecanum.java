
package org.firstinspires.ftc.teamcode;

import android.util.Range;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.library.Claw;
import org.firstinspires.ftc.teamcode.library.ClawImpl;
import org.firstinspires.ftc.teamcode.library.Drive;
import org.firstinspires.ftc.teamcode.library.DriveImpl;
import org.firstinspires.ftc.teamcode.library.ElectorgatorHardware;
import org.firstinspires.ftc.teamcode.library.ExtensionArm;
import org.firstinspires.ftc.teamcode.library.ExtensionArmImpl;
import org.firstinspires.ftc.teamcode.library.Lifter;
import org.firstinspires.ftc.teamcode.library.LifterImpl;

/**
 * Created by Luke on 10/1/2017.
 */

@TeleOp(name = "TeleOp test")
public class TeleOp_Mecanum extends LinearOpMode {
    ElectorgatorHardware hardware = new ElectorgatorHardware();
	Drive drive;
	Claw claw;
	Lifter lift;
	ExtensionArm arm;

    double frontLeftDrive, frontRightDrive, backRightDrive, backLeftDrive;
    boolean startRelic = false;
    double maxDrive = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        // initialise the motors
        telemetry.addLine("Initialising... please wait.");
        telemetry.update();

        double adjFactor;
        double throtle;

        drive = new DriveImpl();
        claw  = new ClawImpl(hardwareMap);
        lift  = new LifterImpl(hardwareMap, telemetry);
        arm   = new ExtensionArmImpl(hardwareMap);

        hardware.initMotors(hardwareMap);
        hardware.initLifter(hardwareMap);

        telemetry.addLine("Ready to start... thank you for waiting!");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            // calculate the motor speeds
//            frontRightDrive = gamepad1.left_stick_y + gamepad1.right_stick_x + gamepad1.left_stick_x;
//            frontLeftDrive  = gamepad1.left_stick_y - gamepad1.right_stick_x - gamepad1.left_stick_x;
//            backRightDrive  = gamepad1.left_stick_y + gamepad1.right_stick_x - gamepad1.left_stick_x;
//            backLeftDrive   = gamepad1.left_stick_y - gamepad1.right_stick_x + gamepad1.left_stick_x;

            // calculate the throttle position that will be used when calculating the motor powers
            throtle = drive.throttleControl(gamepad1.left_trigger, .4);

            /**
             * Calculate the power of each motor by multiplying the left Y-axes and the left X-axes that are
             * used for driving normal by the throttle value that we calculated above. The right X-axes is
             * not multiplied by the throttle, because it is used for sliding sideways and can not be controlled
             * efficiently with the throttle due to the high power requirements of sliding.
             */
            frontRightDrive = ((gamepad1.left_stick_y * throtle) + (gamepad1.left_stick_x * throtle) + gamepad1.right_stick_x);
            frontLeftDrive  = ((gamepad1.left_stick_y * throtle) - (gamepad1.left_stick_x * throtle) - gamepad1.right_stick_x);
            backRightDrive  = ((gamepad1.left_stick_y * throtle) + (gamepad1.left_stick_x * throtle) - gamepad1.right_stick_x);
            backLeftDrive   = ((gamepad1.left_stick_y * throtle) - (gamepad1.left_stick_x * throtle) + gamepad1.right_stick_x);

            /**
             * The motor powers can be calculated to be higher than 1.0 and less than -1.0, so rater than just
             * clipping the values to 1.0 if they are above 1.0 or -1.0 if below -1.0, we decided to scale the
             * values down to preserve the control resolution that we would have if the motor powers were divided
             * by 2.
             */
            // error adjustment based on the front right drive wheel
            maxDrive = Math.abs(frontRightDrive);
            setMaxDrive(frontLeftDrive);
            setMaxDrive(backRightDrive);
            setMaxDrive(backLeftDrive);
            // we set the adjustment factor to 1 so it does not change the motor powers if if they are in the 1.0 to -1.0 range
            adjFactor = 1;
            // we need to use the abs value to of the max drive power to determine if we need to scale the motor powers down or not
            if (maxDrive > 1) {
                adjFactor = (1 / maxDrive);
            }

            hardware.frontLeftDrive.setPower(drive.setMotorSpeed(frontLeftDrive * adjFactor, DriveImpl.MotorControlMode.LINEAR_CONTROL));
	        hardware.frontRightDrive.setPower(drive.setMotorSpeed(frontRightDrive * adjFactor, DriveImpl.MotorControlMode.LINEAR_CONTROL));
	        hardware.backLeftDrive.setPower(drive.setMotorSpeed(backLeftDrive * adjFactor, DriveImpl.MotorControlMode.LINEAR_CONTROL));
	        hardware.backRightDrive.setPower(drive.setMotorSpeed(backRightDrive * adjFactor, DriveImpl.MotorControlMode.LINEAR_CONTROL));

//            hardware.frontRightDrive.setPower(drive.setMotorSpeed(frontRightDrive, DriveImpl.MotorControlMode.LINEAR_CONTROL));
//            hardware.frontLeftDrive.setPower(drive.setMotorSpeed(frontLeftDrive, DriveImpl.MotorControlMode.LINEAR_CONTROL));
//            hardware.backLeftDrive.setPower(drive.setMotorSpeed(backLeftDrive, DriveImpl.MotorControlMode.LINEAR_CONTROL));
//            hardware.backRightDrive.setPower(drive.setMotorSpeed(backRightDrive, DriveImpl.MotorControlMode.LINEAR_CONTROL));

	        if (gamepad2.left_bumper) {
		        claw.openClaw();
	        }
	        if (gamepad2.right_bumper){
		        claw.closeClaw();
	        }

	        lift.setLiftSpeed(gamepad2.right_stick_y);

	        if (gamepad2.start && gamepad2.y) {
                startRelic = true;
            }
            if (startRelic) {
                arm.setExtensionSpeed(gamepad2.left_stick_x);
                arm.setWristPosition(gamepad2.right_trigger);

                if (gamepad2.b) arm.closeClaw();
                if (gamepad2.x) arm.openClaw();
            }
            if (gamepad2.back) startRelic = false;
            // display the motor speeds

            telemetry.addData("Front right drive speed = ", "%1.2f", frontRightDrive);
            telemetry.addData("Front left drive speed  = ", "%1.2f", frontLeftDrive);
            telemetry.addData("Back right drive speed  = ", "%1.2f", backRightDrive);
            telemetry.addData("Back left drive speed   = ", "%1.2f", backLeftDrive);
//            telemetry.addData("Front right drive speed = ", "%1.2f", hardware.frontRightDrive.getPower());
//            telemetry.addData("Front left drive speed  = ", "%1.2f", hardware.frontLeftDrive.getPower());
//            telemetry.addData("Back right drive speed  = ", "%1.2f", hardware.backRightDrive.getPower());
//            telemetry.addData("Back left drive speed   = ", "%1.2f", hardware.backLeftDrive.getPower());
	        telemetry.addData("Lift speed              = ", "%1.2f", lift.getLiftSpeed());
	        telemetry.addData("Claw position           = ", claw.getClawState());
//	        telemetry.addData("Throttle                = ", "%1.2f", drive.throttleControl(gamepad1.left_trigger, drive.MIN_SPEED));
	        telemetry.addData("Throttle                = ", "%1.2f", throtle);
	        telemetry.addData("lift pos                = ", lift.getLiftPos());
            telemetry.update();
        }
    }

    void setMaxDrive(double motor) {
        if (Math.abs(motor) > maxDrive) {
            maxDrive = Math.abs(motor);
        }
    }
}
