package org.firstinspires.ftc.teamcode.library;

import org.firstinspires.ftc.teamcode.library.Lifter;

/**
 * Created by alexander.cochran on 11/1/2017.
 */

public class LifterImpl implements Lifter {


    @Override
    public void set_height(Double height) {

    }

    @Override
    public void open_claw() {
        set_claw_position(70.0);
    }

    @Override
    public void close_claw(Integer type) {
        set_claw_position(90.0);
    }

    private void set_claw_position(Double angle){}
}
