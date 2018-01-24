package org.firstinspires.ftc.teamcode.library;

/**
 * Created by Hal on 11/14/2017.
 */

public interface ExtensionArm {
    void setExtensionDistance(Double distance);
    void openClaw();
    void closeClaw();
    void retract();
    void setExtensionSpeed(double speed);
    void setWristPosition(double position);
}
