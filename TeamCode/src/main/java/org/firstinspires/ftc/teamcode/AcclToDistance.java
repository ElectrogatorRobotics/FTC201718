package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Luke on 10/13/2017.
 *
 * TESTING!
 *
 * All units are metric:
 * Speed measured in meters per second.
 * Distance is measured in meters
 *
 * Robot will drive 1.00 meters and stop, without encoders
 */

@TeleOp(name = "Drive using accelerometer", group = "Prof of concept")
//@Disabled
public class AcclToDistance extends LinearOpMode {
	ProtoBot_hardware protoBot = new ProtoBot_hardware();
	ElapsedTime runtime = new ElapsedTime();

	private static final double TARGET_SPEED = ((Math.PI * 90) * 62.5) / 60;
	private static final double SPEED_THRESHOLD = 0.0025;

	double leftDrive;
	double rightDrive;

	volatile double xAccel;

	double estTime;
	double realTime;
	double error;
	double targetDistInMeters; // travel 1.00 meter or 3.28 feet

	@Override
	public void runOpMode() throws InterruptedException {
		telemetry.addLine("Initialisation has started, please wait...");
		telemetry.update();

		protoBot.initMotors(hardwareMap);
		protoBot.initIMU(hardwareMap);

//		estTime = targetDistInMeters / TARGET_SPEED; // calculate the estimated time to reach the target

		telemetry.addLine("Initialisation has finished, thank you for waiting!");
		telemetry.update();

		waitForStart();

		while (opModeIsActive()) {
			telemetry.addLine("OpMode has started!");

			setDistanceInMeters(1);

			xAccel = protoBot.imu.getLinearAcceleration().xAccel;
			telemetry.addData("accl x", xAccel);

			realTime = runtime.milliseconds();
			telemetry.addData("distance target", targetDistInMeters);
			telemetry.addData("runtime", realTime);
			telemetry.addData("estimated time", speedToTime(xAccel));
			telemetry.addData("distance from target", distanceFromTarget(xAccel));
			telemetry.update();
		}

		protoBot.imu.close();
	}

	public double distanceFromTarget (double speed) {
		return speed / speedToTime(speed);
	}

	public double speedToTime (double speed) {
		return (targetDistInMeters / speed);
	}

	public void setDistanceInMeters(double distance){
		targetDistInMeters = distance;
	}

	public double getAcclX (double xAccelValue) {
		if (xAccelValue > 0.001) return xAccelValue;
		return 0;
	}
}
