package core.util;

import core.GameObject;
import core.Power;

import java.lang.Math;
/**
 * Created by jonathanbrodie on 8/25/15.
 */
public class Velocity {
    private double x;
    private double y;
    private int hspeedCap=10;
    private int vspeedCap=35;
    public Velocity() {
        this.x=0;
        this.y=0;
    }
    public void moveObject(GameObject object) {
        //linear algebra shit right here
        Position objectPosition=object.getCurrentPosition();
        double objectx=objectPosition.getX();
        double objecty=objectPosition.getY();
        if (Math.abs(this.x) > hspeedCap) {
            if (this.x < 0) {
                this.x=hspeedCap*-1;
            }
            else {
                this.x=hspeedCap;
            }
        }
        if (Math.abs(this.y) > vspeedCap) {
            if (this.y < 0) {
                this.y=vspeedCap*-1;
            }
            else {
                this.y=vspeedCap;
            }
        }
        if (this.x > 0) {
        }
        object.setPosition(new Position(objectx+this.x,objecty+this.y));

    }
    public double getNetVelocity() {
        //calc 3 shit right here
        double square= Math.pow(x,2)+Math.pow(y,2);
        return Math.sqrt(square);
    }
    public void superSpeed() {
        this.hspeedCap*= Power.SPEED_MULTIPLIER;
        this.x*= Power.SPEED_MULTIPLIER;
        this.y*= Power.SPEED_MULTIPLIER;
        this.vspeedCap*=Power.SPEED_MULTIPLIER;
        System.out.println("Speed increased");
    }
    public void releaseSpeed() {
        this.hspeedCap/=Power.SPEED_MULTIPLIER;
        this.vspeedCap/=Power.SPEED_MULTIPLIER;
        this.x/= Power.SPEED_MULTIPLIER;
        this.y/= Power.SPEED_MULTIPLIER;
    }
    public double getX() {
        return this.x;
    }
    public double getY() {
        return this.y;
    }
    public void setX(double x) {
        this.x=x;
    }
    public void setY(double y) {
        this.y=y;
    }
    @Override
    public String toString() {
        return "Velocity{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
