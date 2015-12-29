package core.object.physics;

import core.object.GameObject;

/**
 * Created by jonathanbrodie on 9/12/15.
 */
public interface GravityComponent extends PhysicsComponent {
    public static final double GRAVITY_CONSTANT=-0.982;
    void gravity(GameObject object);
    boolean inAir();

}
