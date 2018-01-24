package org.firstinspires.ftc.teamcode.library;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Luke on 11/20/2017.
 */

public class ExtensionArmImpl implements ExtensionArm {
	DcMotor extensionMotor = null;
	Servo clawServo = null;
	Servo wristServo = null;

	private static final double CLAW_OPEN = 0.25;
	private static final double CLAW_CLOSED = 0;
	private static final double CLAW_MID = 0.1;
	private static final double CLAW_UP = 1;
	private static final double CLAW_DOWN = 0;
	public ExtensionArmImpl (HardwareMap hardwareMap) {
		extensionMotor = hardwareMap.dcMotor.get("extension arm");
		extensionMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
		extensionMotor.setPower(0.0);

		clawServo = hardwareMap.servo.get("extension claw");
		clawServo.setPosition(CLAW_MID);

		wristServo = hardwareMap.servo.get("extension claw wrist");
	}
	@Override
	public void setExtensionDistance(Double distance) {

	}

	public void setExtensionSpeed (double speed) {
		extensionMotor.setPower(speed);
	}

	@Override
	public void openClaw() {
		clawServo.setPosition(CLAW_OPEN);
	}

	@Override
	public void closeClaw() {
		clawServo.setPosition(CLAW_CLOSED);
	}

	public void setWristPosition(double position) {
		wristServo.setPosition(position);
	}

	@Override
	public void retract() {

	}
}
