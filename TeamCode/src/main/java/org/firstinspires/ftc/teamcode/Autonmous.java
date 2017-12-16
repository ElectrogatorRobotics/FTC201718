package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.library.Camera;
import org.firstinspires.ftc.teamcode.library.CameraImpl;
import org.firstinspires.ftc.teamcode.library.Claw;
import org.firstinspires.ftc.teamcode.library.ClawImpl;
import org.firstinspires.ftc.teamcode.library.Drive;
import org.firstinspires.ftc.teamcode.library.DriveImpl;
import org.firstinspires.ftc.teamcode.library.Lifter;
import org.firstinspires.ftc.teamcode.library.LifterImpl;

/**
 * Created by mira.eschliman on 11/6/2017.
 */
@TeleOp(name = "AutonmousBlue")
public class Autonmous extends LinearOpMode {

        @Override
        public void runOpMode() throws InterruptedException {
            //make our helpers
            Camera camera = new CameraImpl();
            Drive drive = new DriveImpl();
            Lifter lifter= new LifterImpl(hardwareMap, telemetry);
            Camera.Glyph glyph;
            Claw claw = new ClawImpl(hardwareMap);
            ElapsedTime time= new ElapsedTime();


//start motor & scan glyph
                glyph = camera.getGlyph();
                // move forward
                drive.forward(10);
                // turn of face blocks
                drive.turn(40);
                // move forward
                drive.forward(10);
                //grip block

                lifter.set_height(0.0);
                claw.openClaw();
                claw.closeClaw();
                // move forward
                drive.turn(180);
                drive.forward(10);
                // place block

                claw.openClaw();


                //power motors to 0


        }
}


