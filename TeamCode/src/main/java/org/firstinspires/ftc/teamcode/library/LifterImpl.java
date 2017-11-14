package org.firstinspires.ftc.teamcode.library;

import org.firstinspires.ftc.teamcode.library.Lifter;

/**
 * Created by alexander.cochran on 11/1/2017.
 */

public class LifterImpl implements Lifter {
    public static final int BLOCK = 1;
    public static final int RELIC = 2;


    @Override
    public void set_height(Double height) {

    }

    @Override
    public void set_speed(Double speed, Double time) {

    }

    @Override
    public void open_claw() {
        set_claw_position(0.0);
    }

    @Override
    public void close_claw(Integer type) {
        if (type==BLOCK){
            set_claw_position(90.0);
        }
        else if (type==RELIC){
            set_claw_position(100.0);
        }
    }

    private void set_claw_position(Double angle){}
}
