package org.firstinspires.ftc.teamcode.library;

/**
 * Created by Luke on 11/20/2017.
 */

public class JewelArmImpl implements JewelArm {
	ElectorgatorHardware robot = new ElectorgatorHardware();
	private static final int BLUE = 240, RED = 0;
	private static final double DOWN = 1.0, UP = 0.0;

	public JewelColor getJewelColor () {
		robot.jewelColorSensor.enableLed(true);
		int color = robot.jewelColorSensor.argb();

		if (color > RED) return JewelColor.BLUE;
		else if (color > BLUE) return JewelColor.RED;
		return JewelColor.NONE;
	}

	public Motion readJewel () {
		robot.jewelServo.setPosition(DOWN);

		if (getJewelColor() == JewelColor.BLUE) return Motion.BACKWARD;
		if (getJewelColor() == JewelColor.RED) return Motion.FORWARD;
		return null;
	}
}
