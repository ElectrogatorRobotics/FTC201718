package org.firstinspires.ftc.teamcode.library;

/**
 * Created by Hal on 11/14/2017.
 */

public class Location {
    public Long x;
    public Long y;
    public Double angle;

    public Location(){}

    public Location(Long x, Long y, Double angle){
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }

    public Double getAngle() {
        return angle;
    }

    public void setAngle(Double angle) {
        this.angle = angle;
    }
}
