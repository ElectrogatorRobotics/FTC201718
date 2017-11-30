package org.firstinspires.ftc.teamcode.library;

/**
 * Created by Luke on 11/24/2017.
 */

public interface Claw {
	void openClaw ();
	void closeClaw ();
	ClawState getClawState();

	enum ClawState {
		OPEN, CLOSED, UNKNOWN
	}
}
