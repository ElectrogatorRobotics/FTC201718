package org.firstinspires.ftc.teamcode.library;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.library.Lifter;

import static android.os.SystemClock.sleep;


/**
 * Created by alexander.cochran on 11/1/2017.
 */

public class LifterImpl implements Lifter {
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
    
    @Override
    public void set_height(Double height) {
        encoderDrive(HIGH_SPEED, height, timeout);
    }
    public void encoderDrive(double speed,
                             double lift_inches,
                             double timeoutS) {

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
