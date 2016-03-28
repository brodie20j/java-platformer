package core;

import core.util.Position;
import core.util.Velocity;
import core.physics.PhysicsComponent;
import core.graphic.GraphicComponent;
import core.util.ObjectSorter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jonathanbrodie on 8/25/15.
 */
public class GameObject {
    private Position currentPosition;
    private Velocity velocity=new Velocity();
    public final Level currentLevel;
    private PhysicsComponent physicsComponent;
    private GraphicComponent graphicComponent;
    private int graphicPointer;

    public GameObject(Position start, PhysicsComponent physics, GraphicComponent graphics, Level currentLevel) {
        this.currentPosition=start;
        this.physicsComponent=physics;
        this.graphicComponent=graphics;
        this.currentLevel=currentLevel;
        this.currentLevel.addObject(this);
    }

    public Position getCurrentPosition() {
        return this.currentPosition;
    }
    public Velocity getVelocity() {
        return this.velocity;
    }
    public void setPosition(Position pos) {
        this.currentPosition=pos;
    }


    /**
     * IF YOU CHOOSE TO OVERRIDE THIS METHOD, MAKE SURE YOU CALL SUPER() OTHERWISE YOU WILL EXPERIENCE WEIRD BEHAVIOR
     */
    public void step() {
        this.physicsComponent.update(this, this.currentLevel);
        this.graphicComponent.updateGraphics(this);
    }

    public GraphicComponent getGraphics() {
        return this.graphicComponent;
    }
    public PhysicsComponent getPhysics() {return this.physicsComponent;}
    public synchronized void setPointer(int value) {
        this.graphicPointer=value;
    }
    public synchronized int getPointer() {
        return this.graphicPointer;
    }
    @Override
    public GameObject clone() {
        GameObject obj= new GameObject(new Position(this.getCurrentPosition().getX(),this.getCurrentPosition().getY(),this.getCurrentPosition().getZ()),this.physicsComponent,this.graphicComponent,currentLevel);
        obj.setPointer(this.graphicPointer);
        return obj;
    }

    public void destroy() {

        this.currentLevel.removeObject(this);
    }
    public List<GameObject> getObjectsSortedByMe() {

        List<GameObject> newList=new ArrayList<>();
        for (GameObject object1 : this.currentLevel.getGameObjects()) {
            newList.add(object1);
        }
        ObjectSorter sorter=new ObjectSorter(this.getCurrentPosition());
        Collections.sort(newList, sorter);
        return newList;

    }

}
