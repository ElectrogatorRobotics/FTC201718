package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by Luke on 10/26/2017.
 */

@TeleOp()
public class calculateDistanceAngleAngle_Vuforia extends LinearOpMode {
	double distanceFromTarget;
	double angleFromTarget;

	@Override
	public void runOpMode() throws InterruptedException {
		telemetry.addLine("Initialising Vuforia");
		telemetry.update();

		VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
		parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
		parameters.useExtendedTracking = false;

		parameters.vuforiaLicenseKey = " AcQ1bDH/////AAAAGbeTEaYpQERvjzqffYFSNJ8QPE2WpLBlCrwtO/DiWyLybwT0nVooetcjuHOkFZuZy2i6NguJRCSPg82kDPbqejpCFybLDpiIuhP4yMrfrlxEkMNyVs6F6yL3CPNXSW6gO1PVKptMpAutGveCoxIcv/A6S/NbR5oOSDIq6Rzet0fZlDi1P+Eo9XhWD2+JZHDZaloCYzkocAOElc6UJG9YIUh5ujdp5ra8zQadKoj0gD+oBMdXyJ3qjtwvYGtyUreoQDTUWBLqTZ5rZWr3St3SlSwF31XAq3BzpZHpJtsOe3z380xKAZgO9oMyFCYYl5BycK5WvTwU7wTTK0Dj2IQSZ7GJtw4A4Ypb/rowWnR6RCiA ";

		VuforiaLocalizer vuforia = ClassFactory.createVuforiaLocalizer(parameters);
		VuforiaTrackables pictograph = vuforia.loadTrackablesFromAsset("RelicVuMark");

		VuforiaTrackable image = pictograph.get(0);
		image.setName("pictograph");

		telemetry.addLine("Vuforia is initialised");
		telemetry.update();

		waitForStart();
		pictograph.activate();

		while (opModeIsActive()) {
			RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(image);

			for (VuforiaTrackable target : pictograph) {
				OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) target.getListener()).getPose();
				if (vuMark != RelicRecoveryVuMark.UNKNOWN) {
					VectorF translation = pose.getTranslation();

					// store the distance and angle from the target in double form
					distanceFromTarget = translation.magnitude();
					angleFromTarget = Math.toDegrees(Math.atan2(translation.get(0), translation.get(2)));

					telemetry.addData("VuMark ", "%s is viable", vuMark);
					telemetry.addData("rotation from pictograph", angleFromTarget);
					telemetry.addData("distance from pictograph", distanceFromTarget);






//			for (VuforiaTrackable target : pictograph) {
//				OpenGLMatrix pose = ((VuforiaTrackableDefaultListener)target.getListener()).getPose();
//
//				if (pose != null) {
//					VectorF translation = pose.getTranslation();
//
//					telemetry.addData("distance form pictograph", translation.magnitude());
//					telemetry.addData("rotational Z from pictograph", Math.toDegrees(Math.atan2(translation.get(0), translation.get(2))));
					telemetry.update();
//				}
				}
			}
		}
	}
}
