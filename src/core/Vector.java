package core;

/**
 * Created by jonathanbrodie on 9/3/15.
 */
public class Vector {
    private Position p1;
    private Position p2;
    public Vector(Position p1, Position p2) {
        this.p1=p1;
        this.p2=p2;
    }
    public Position getMinPosition() {
        return this.p1;
    }
    public Position getMaxPosition() {
        return this.p2;
    }

    @Override
    public String toString() {
        return "Vector{" +
                "p1=" + p1 +
                ", p2=" + p2 +
                '}';
    }
}
