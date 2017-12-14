package org.firstinspires.ftc.teamcode.library;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by alexander.cochran on 11/1/2017.
 */

public interface Lifter {

    void setLiftSpeed (double control);
    double getLiftSpeed ();
//    void open_claw();
//    void close_claw(Integer type);

//    enum LiftPosition {CARRY_GLYPH, FIRST_GLYPH, SECOND_GLYPH, THIRD_GLYPH, FORTH_GLYPH}

    void set_height(Double height);


}
