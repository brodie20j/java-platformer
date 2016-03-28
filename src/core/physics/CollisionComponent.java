package core.physics;

import core.GameObject;

/**
 * Created by jonathanbrodie on 9/9/15.
 */
public interface CollisionComponent extends PhysicsComponent {
    boolean isSolid();
    boolean collision(GameObject componentOwner, GameObject other);

}
