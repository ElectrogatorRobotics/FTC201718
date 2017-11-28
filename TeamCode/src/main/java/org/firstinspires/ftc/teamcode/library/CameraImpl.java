package org.firstinspires.ftc.teamcode.library;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.R;

/**
 * Created by Luke on 11/20/2017.
 */

public class CameraImpl implements Camera {

	private VuforiaTrackables pictograph;
	private VuforiaTrackable image;
	private Glyph game_glyph;

	public CameraImpl(){
//		telemetry.addLine("Initialising Vuforia");
//		telemetry.update();
		game_glyph = Glyph.UNKNOWN;

		VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
		parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
		parameters.useExtendedTracking = false;

		parameters.vuforiaLicenseKey = " AcQ1bDH/////AAAAGbeTEaYpQERvjzqffYFSNJ8QPE2WpLBlCrwtO/DiWyLybwT0nVooetcjuHOkFZuZy2i6NguJRCSPg82kDPbqejpCFybLDpiIuhP4yMrfrlxEkMNyVs6F6yL3CPNXSW6gO1PVKptMpAutGveCoxIcv/A6S/NbR5oOSDIq6Rzet0fZlDi1P+Eo9XhWD2+JZHDZaloCYzkocAOElc6UJG9YIUh5ujdp5ra8zQadKoj0gD+oBMdXyJ3qjtwvYGtyUreoQDTUWBLqTZ5rZWr3St3SlSwF31XAq3BzpZHpJtsOe3z380xKAZgO9oMyFCYYl5BycK5WvTwU7wTTK0Dj2IQSZ7GJtw4A4Ypb/rowWnR6RCiA ";

		VuforiaLocalizer vuforia = ClassFactory.createVuforiaLocalizer(parameters);
		pictograph = vuforia.loadTrackablesFromAsset("RelicVuMark");

		image = pictograph.get(0);
		image.setName("pictograph");

//		telemetry.addLine("Vuforia is initialised");
//		telemetry.update();

		pictograph.activate();
	}

	@Override
	public Glyph getGlyph() {
		if(game_glyph == Glyph.UNKNOWN) {
			RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(image);
			if (vuMark != RelicRecoveryVuMark.UNKNOWN) {
				if(vuMark == RelicRecoveryVuMark.CENTER){
					game_glyph = Glyph.CENTER;
				}
				else if(vuMark == RelicRecoveryVuMark.LEFT){
					game_glyph = Glyph.LEFT;
				}
				else if(vuMark == RelicRecoveryVuMark.RIGHT) {
					game_glyph = Glyph.RIGHT;
				}
			}
		}
		return game_glyph;
	}

	@Override
	public Location getLocation() {
		return null;
	}

	@Override
	public Long getRawX() {
		return null;
	}

	@Override
	public Long getRawY() {
		return null;
	}

	@Override
	public Double getRawAngle() {
		return null;
	}
}
