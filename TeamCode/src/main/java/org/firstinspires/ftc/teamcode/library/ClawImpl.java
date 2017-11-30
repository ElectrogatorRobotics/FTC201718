package org.firstinspires.ftc.teamcode.library;

/**
 * Created by Luke on 11/24/2017.
 */

public class ClawImpl implements Claw {
	ElectorgatorHardware robot = new ElectorgatorHardware();

	private static final double OPEN = 1.0;
	private static final double CLOSED = 0.5;

	public void openClaw () {
		robot.leftClaw.setPosition(OPEN);
		robot.rightClaw.setPosition(OPEN);
	}

	public void closeClaw () {
		robot.leftClaw.setPosition(CLOSED);
		robot.rightClaw.setPosition(CLOSED);
	}

	public ClawState getClawState () {
		if (robot.leftClaw.getPosition() >= OPEN && robot.rightClaw.getPosition() >= OPEN)
			return ClawState.OPEN;

		else if (robot.leftClaw.getPosition() >= CLOSED && robot.rightClaw.getPosition() >= CLOSED)
			return ClawState.CLOSED;
		else
			return ClawState.UNKNOWN;
	}
}
