package org.firstinspires.ftc.teamcode.library;

/**
 * Created by alexander.cochran on 11/1/2017.
 */

public interface Lifter {
    void set_height(Double height);
    void set_speed(Double speed, Double time);
    void open_claw();
    void close_claw(Integer type);
    
}
