package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.library.Camera;
import org.firstinspires.ftc.teamcode.library.CameraImpl;
import org.firstinspires.ftc.teamcode.library.Claw;
import org.firstinspires.ftc.teamcode.library.ClawImpl;
import org.firstinspires.ftc.teamcode.library.Drive;
import org.firstinspires.ftc.teamcode.library.DriveImpl;
import org.firstinspires.ftc.teamcode.library.ElectorgatorHardware;
import org.firstinspires.ftc.teamcode.library.Lifter;
import org.firstinspires.ftc.teamcode.library.LifterImpl;

/**
 * Created by Luke on 11/20/2017.
 */

@Autonomous(name = "AutoRedLeft")
public class Autonomous_RedLeft extends LinearOpMode {

	@Override
	public void runOpMode() throws InterruptedException {
//make our helpers
		//Camera camera = new CameraImpl();
		Drive drive = new DriveImpl();
		drive.setTelemetry(telemetry);
		drive.initMotors(hardwareMap);
		//Lifter lifter= new LifterImpl(hardwareMap);
		//Camera.Glyph glyph;
		//Claw claw = new ClawImpl(hardwareMap);
		//ElapsedTime time= new ElapsedTime();

		waitForStart();
//start motor & scan glyph
		//glyph = camera.getGlyph();
		// move forward
		drive.forward_time(900);
		// turn of face blocks
		//drive.turn(40);
		// move forward
		//drive.forward(10);
		//grip block
	}
}
