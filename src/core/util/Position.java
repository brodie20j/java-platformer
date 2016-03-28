package core.util;

/**
 * Created by jonathanbrodie on 8/25/15.
 */
public class Position {
    private double x;
    private double y;
    private double z;

    public Position(double x, double y, double z) {
        this.x=x;
        this.y=y;
        this.z=z;
    }
    public Position(double x, double y) {
        this.x=x;
        this.y=y;
        this.z=0;
    }
    public double getX() {
        return this.x;
    }
    public double getY() {
        return this.y;
    }
    public double getZ() {
        return this.z;
    }

    public static Position difference(Position pos1, Position pos2) {
        return new Position(pos1.getX()-pos2.getX(),pos1.getY()-pos2.getY(),pos1.getZ()-pos2.getZ());
    }
    public static double computeDistance(Position pos1, Position pos2) {
        Position diffPosition=difference(pos1,pos2);
        return Math.sqrt((diffPosition.getX()*diffPosition.getX())+(diffPosition.getY()*diffPosition.getY()));
    }
    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
