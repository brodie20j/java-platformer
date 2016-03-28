package core.physics;

import core.Level;
import core.GameObject;

/**
 * Created by jonathanbrodie on 9/12/15.
 */
public class NullPhysicsComponent implements PhysicsComponent{
    public void update(GameObject object, Level level) {
        //....
        object.getVelocity().moveObject(object);
    }
    public boolean isCollidable() {
        return false;
    }

}
