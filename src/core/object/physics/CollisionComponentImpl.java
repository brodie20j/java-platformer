package core.object.physics;

import core.Level;
import core.Position;
import core.object.GameObject;
import core.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathanbrodie on 9/8/15.
 */
public abstract class CollisionComponentImpl implements CollisionComponent  {



    /**
     *
     * @param object
     * @param level
     */
    public void update(GameObject object, Level level) {

        //dummy collision technique that runs in O(n) time.  This sucks, make something better dumbass.
        for (GameObject objects : level.getDrawList()) {
            if (objects != object) {
                if (this.collision(object, objects)) {
                    if (objects.getPhysics().isCollidable())
                    this.collisionCallback(object, objects);
                }
            }
        }
        this.move(object);

    }
    public boolean isCollidable() {
        return true;
    }

    public Vector getCollisionVector(GameObject object) {
        double originX = object.getCurrentPosition().getX();
        double originY = object.getCurrentPosition().getY();

        double width = object.getGraphics().getWidth();
        double height = object.getGraphics().getHeight();

        return new Vector(new Position(originX, originY), new Position(originX + width, originY + height));
    }

    public List<Position> getCollisionShape(GameObject object) {
        List<Position> pointList=new ArrayList<>();
        pointList.add(object.getCurrentPosition());

        double originX = object.getCurrentPosition().getX();
        double originY = object.getCurrentPosition().getY();

        if (object.getGraphics().getHeight() > 0) {
            pointList.add(new Position(originX,originY+object.getGraphics().getHeight()));
        }
        if (object.getGraphics().getWidth() > 0) {
            pointList.add(new Position(originX+object.getGraphics().getWidth(),originY));
        }
        if ((object.getGraphics().getHeight() > 0) && (object.getGraphics().getWidth() > 0)) {
            pointList.add(new Position(originX+object.getGraphics().getWidth(),originY+object.getGraphics().getHeight()));
        }
        return pointList;
    }
    public boolean collision(GameObject owner, GameObject object2) {
        //create a vector for the owner's sprite
        Vector collisionVector=this.getCollisionVector(owner);
        List<Position> positionList=this.getCollisionShape(object2);
        boolean collided=false;
        for (Position position: positionList) {
            if ((position.getX() >= collisionVector.getMinPosition().getX())
                    && (position.getY() >= collisionVector.getMinPosition().getY())
                    && (position.getX() < collisionVector.getMaxPosition().getX())
                    && (position.getY() < collisionVector.getMaxPosition().getY())) collided=true;
        }
        return collided;
    }
    //Returns the difference between the closest points on two objects
    public Position closestPoints(GameObject owner, GameObject object) {
        //resolve the collision by computing the larger of the two distances
        List<Position> ownerPositions=this.getCollisionShape(owner);
        List<Position> otherPositions=this.getCollisionShape(object);


        double mindist=Double.MAX_VALUE;
        Position optimal=new Position(Double.MAX_VALUE,Double.MAX_VALUE);
        for (Position pos : ownerPositions) {
            for (Position pos2 : otherPositions) {
                if ((int) Position.computeDistance(pos,pos2) < (int) mindist) {
                    mindist=Position.computeDistance(pos,pos2);
                    optimal=Position.difference(pos,pos2);
                }
            }
        }
        return optimal;
    }
    abstract void collisionCallback(GameObject owner, GameObject object);
    abstract void move(GameObject object);
    public abstract boolean isSolid();

}
