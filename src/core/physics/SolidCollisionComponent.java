package core.physics;

import core.GameObject;
import core.util.Position;

import java.util.List;
/**
 * Created by jonathanbrodie on 9/9/15.
 */
public class SolidCollisionComponent extends CollisionComponentImpl {

    private final double FRICTION_X=0.7;
    private final double FRICTION_Y=0.9;

    public boolean isSolid() {
        return true;
    }


    @Override
    public void collisionCallback( GameObject owner, GameObject object) {
        //determine object's position relative to owner
        Position ownerPosition = owner.getCurrentPosition();
        Position otherPosition = object.getCurrentPosition();
        double newX=otherPosition.getX();
        double newY=otherPosition.getY();

        //resolve the collision by computing the larger of the two distances
        List<Position> ownerPositions=this.getCollisionShape(owner);
        List<Position> otherPositions=this.getCollisionShape(object);


        Position optimal=super.closestPoints(owner, object);
        double mindist=Math.sqrt(optimal.getX()*optimal.getX()+optimal.getY()*optimal.getY());

        double angle=Math.atan2(optimal.getY(),optimal.getX());
        double xdist=Math.cos(angle)*mindist;
        double ydist=Math.sin(angle)*mindist;
        if (ydist != 0) owner.getVelocity().setY(owner.getVelocity().getY()*FRICTION_Y);
        if (xdist != 0) owner.getVelocity().setX(owner.getVelocity().getX()*FRICTION_X);
        newY+=ydist;
        newX+=xdist;
        object.setPosition(new Position(newX, newY));
    }
    @Override
    public void move(GameObject object) {
        object.getVelocity().moveObject(object);
    }
}
