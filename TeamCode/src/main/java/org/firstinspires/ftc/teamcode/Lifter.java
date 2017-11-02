package org.firstinspires.ftc.teamcode;

/**
 * Created by alexander.cochran on 11/1/2017.
 */

public interface Lifter {
    public void set_height(Double height);
    public void set_speed(Double speed, Double time);
    public void open_claw();
    public void close_claw(Integer type);
    
}
