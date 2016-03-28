package core.physics;

import core.GameObject;

/**
 * Created by jonathanbrodie on 9/12/15.
 */
public interface GravityComponent extends PhysicsComponent {
    public static final double GRAVITY_CONSTANT=-0.99;
    void gravity(GameObject object);
    boolean inAir();

}
