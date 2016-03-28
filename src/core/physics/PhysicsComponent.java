package core.physics;

import core.Level;
import core.GameObject;

/**
 * Created by jonathanbrodie on 9/8/15.
 */
public interface PhysicsComponent {
    boolean isCollidable();

    void update(GameObject object, Level level);
}
