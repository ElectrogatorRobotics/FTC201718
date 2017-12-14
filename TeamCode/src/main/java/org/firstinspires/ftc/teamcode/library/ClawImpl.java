package org.firstinspires.ftc.teamcode.library;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Luke on 11/24/2017.
 */

public class ClawImpl implements Claw {

	private Servo leftClaw   = null;
	private Servo rightClaw  = null;

	private static final double OPEN = 0.6;
	private static final double CLOSED = 0.4;

	public ClawImpl(HardwareMap hardware){
		leftClaw  = hardware.servo.get("left claw");
		rightClaw = hardware.servo.get("right claw");

		leftClaw.setPosition(0.5);

		rightClaw.setPosition(0.5);
		rightClaw.setDirection(Servo.Direction.REVERSE);
	}

	public void openClaw () {
		leftClaw.setPosition(OPEN);
		rightClaw.setPosition(OPEN);
	}

	public void closeClaw () {
		leftClaw.setPosition(CLOSED);
		rightClaw.setPosition(CLOSED);
	}

	public ClawState getClawState () {
		if (leftClaw.getPosition() >= OPEN && rightClaw.getPosition() >= OPEN)
			return ClawState.OPEN;

		else if (leftClaw.getPosition() >= CLOSED && rightClaw.getPosition() >= CLOSED)
			return ClawState.CLOSED;
		else
			return ClawState.UNKNOWN;
	}
}
