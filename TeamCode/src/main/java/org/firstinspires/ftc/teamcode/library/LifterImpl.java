package org.firstinspires.ftc.teamcode.library;

import com.qualcomm.robotcore.hardware.DcMotor;
<<<<<<< HEAD
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
=======
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.library.Lifter;


import static android.os.SystemClock.sleep;


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

    private static int counts_per_inch = 10;// TODO: wtf is the counts per inch. this is a placeholder
    private ElapsedTime runtime = new ElapsedTime();
    static final double     HIGH_SPEED             = 0.6;
    static final double     LOW_SPEED              = 0.3;
    static final double     timeout                = 7.0;
    private DcMotor lift_drive  =   null;
    
    public LifterImpl(HardwareMap hardware) {
        lift_drive = hardware.dcMotor.get("arm");
        lift_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lift_drive.setPower(0.0);
    }
    
    public void set_height(Double height) {
        encoderDrive(HIGH_SPEED, height, timeout);
    }
    public void encoderDrive(double speed,
                             double lift_inches,
                             double timeoutS) {

    public void open_claw() {
        set_claw_position(70.0);
    }

    public void close_claw(Integer type) {
        set_claw_position(90.0);
    }

    private void set_claw_position(Double angle){}

    int newhight=(int)(lift_inches*counts_per_inch);
            // Determine new target position, and pass to motor controller
            lift_drive.setTargetPosition(newhight);

            // Turn On RUN_TO_POSITION
            lift_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            lift_drive.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while ((runtime.seconds() < timeoutS) &&
                    lift_drive.isBusy()) {

                // Display it for the driver.
                //telemetry.addData("Lifter",  "Running to %7d", newhight);
                //telemetry.addData("Lifter2",  "currently at %7d ",
                       // lift_driver.getCurrentPosition());
                //telemetry.update();
                sleep(10);

            }

            // Stop all motion;
            lift_drive.setPower(0);

            // Turn off RUN_TO_POSITION
            lift_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
    }
}

