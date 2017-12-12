package org.firstinspires.ftc.teamcode.library;

/**
 * Created by Hal on 11/14/2017.
 */

public interface Camera {
    public enum Glyph{
        LEFT, RIGHT, CENTER, UNKNOWN
    }

    public Glyph getGlyph();
    public Location getLocation();

    public Long getRawX();
    public Long getRawY();
    public Double getRawAngle();
}
