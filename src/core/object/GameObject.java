package core.object;

import core.Level;
import core.Position;
import core.Velocity;
import core.object.physics.PhysicsComponent;
import core.object.graphic.GraphicComponent;

/**
 * Created by jonathanbrodie on 8/25/15.
 */
public class GameObject {
    private Position currentPosition;
    private Velocity velocity=new Velocity();
    private Level currentLevel;
    private PhysicsComponent physicsComponent;
    private GraphicComponent graphicComponent;
    private int graphicPointer;

    public GameObject(Position start, PhysicsComponent physics, GraphicComponent graphics, Level currentLevel) {
        this.currentPosition=start;
        this.physicsComponent=physics;
        this.graphicComponent=graphics;
        this.currentLevel=currentLevel;
    }

    public Level getLevel() {
        return this.currentLevel;
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
    public void setPointer(int value) {
        this.graphicPointer=value;
    }
    public int getPointer() {
        return this.graphicPointer;
    }



}
