package org.firstinspires.ftc.teamcode.library;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;


/**
 * Created by alexander.cochran on 11/1/2017.
 */

public class LifterImpl implements Lifter {

    public static final Double TRANSIT = 3.0;

    private Telemetry LOG;

    public DcMotor liftMotor1 = null;
    public DcMotor liftMotor2 = null;

    public LifterImpl (HardwareMap hardwareMap, Telemetry telem){
        LOG = telem;
        liftMotor1 = hardwareMap.dcMotor.get("lift motor 1");
        liftMotor1.setDirection(DcMotorSimple.Direction.REVERSE);
        //liftMotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        liftMotor2 = hardwareMap.dcMotor.get("lift motor 2");
        liftMotor2.setDirection(DcMotorSimple.Direction.FORWARD);
        //liftMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
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

    public void set_height(Double height) {
        double heightInEncoderTicks = height * ENCODER_TICKS_PER_INCH;
        LOG.addData("Lifter to height",heightInEncoderTicks);
        liftMotor1.setTargetPosition((int)heightInEncoderTicks);
        liftMotor2.setTargetPosition((int)heightInEncoderTicks);
        setLiftSpeed(1);
    }

    public void run_motors(int millis){
        liftMotor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        liftMotor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        LOG.addData("LiftFor",millis);
        ElapsedTime runtime = new ElapsedTime();
        double time;
        do {
            time = runtime.milliseconds();
            setLiftSpeed(1);
            //liftMotor1.setPower(1.0);
            //liftMotor2.setPower(1.0);
        } while (time < millis);
        LOG.addLine("ShutdownLifters");
        setLiftSpeed(0);
        LOG.update();
    }
}

