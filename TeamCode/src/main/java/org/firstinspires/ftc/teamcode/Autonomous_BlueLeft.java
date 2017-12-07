package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.library.Camera;
import org.firstinspires.ftc.teamcode.library.CameraImpl;
import org.firstinspires.ftc.teamcode.library.ElectorgatorHardware;
import org.firstinspires.ftc.teamcode.library.Lifter;
import org.firstinspires.ftc.teamcode.library.LifterImpl;

/**
 * Created by Luke on 11/20/2017.
 */

@Autonomous(name = "AutoBlueLeft")
public class Autonomous_BlueLeft extends LinearOpMode {
	ElectorgatorHardware robot = new ElectorgatorHardware();
	Lifter lift = new LifterImpl(hardwareMap);
	Camera camera = new CameraImpl();

	@Override
	public void runOpMode() throws InterruptedException {

	}
}
