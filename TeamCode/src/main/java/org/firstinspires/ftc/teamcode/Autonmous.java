package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

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
@TeleOp(name = "Autonmous")
public class Autonmous extends LinearOpMode {

        @Override
        public void runOpMode() throws InterruptedException {
            //make our helpers
            Camera camera = new CameraImpl();
            Drive drive = new DriveImpl();
            Lifter lifter= new LifterImpl();
            Camera.Glyph glyph;
            Claw claw = new ClawImpl();
            run time= new Elapse Time=


//start motor & scan glyph
                glyph = camera.getGlyph();
                // move forward
                drive.forward();
                // turn of face blocks
                drive.turn();
                // move forward
                drive.forward;
                //grip block

                lifter.set_height();
                Lifter.open_claw ();
                lifter.close_claw();
                // move forward
                drive.turn();
                drive.forward;
                // place block

                lifter.open_claw();
                // repeat for 30 seconds
                getRuntime(30seconds )
                //power motors to 0


        }
}


