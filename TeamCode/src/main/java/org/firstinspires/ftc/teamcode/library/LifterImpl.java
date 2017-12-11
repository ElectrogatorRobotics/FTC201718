package org.firstinspires.ftc.teamcode.library;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by alexander.cochran on 11/1/2017.
 */

public class LifterImpl implements Lifter {
    public DcMotor liftMotor1 = null;
    public DcMotor liftMotor2 = null;

    public LifterImpl (HardwareMap hardwareMap){
        liftMotor1 = hardwareMap.dcMotor.get("lift motor 1");
        liftMotor1.setDirection(DcMotorSimple.Direction.REVERSE);
        liftMotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        liftMotor2 = hardwareMap.dcMotor.get("lift motor 2");
        liftMotor2.setDirection(DcMotorSimple.Direction.FORWARD);
        liftMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     * The REV CORE Hex motor has 288 encoder ticks per revolution of the output shaft, and the winch has a diameter of
     * 1 inch. We need to find the circumference of the winch by using the formula PI * diameter, because the diameter is
     * 1, we can drop the diameter and just multiply the encoder ticks by PI. This will give us the distance traveled for
     * each encoder count.
     */
    private static final double ENCODER_TICKS_PER_INCH = (288 / Math.PI);

    public void setLiftSpeed (double control) {
	    /**
	     * NOTE: the Y axes of the controllers produces a negative value when pushed forward, so we need to negate the value.
	     */
	    liftMotor1.setPower(-control);
        liftMotor2.setPower(-control);
    }

	public double getLiftSpeed () {
		return (liftMotor1.getPower() + liftMotor2.getPower()) / 2;
	}

    private void set_height(int heightInEncoderTicks) {
        liftMotor1.setTargetPosition(heightInEncoderTicks);
        liftMotor1.setPower(1.0);
    }

    public void open_claw() {
        set_claw_position(70.0);
    }

    public void close_claw(Integer type) {
        set_claw_position(90.0);
    }

    private void set_claw_position(Double angle){}

}

