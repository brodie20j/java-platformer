package core.physics;

import core.GameActor;
import core.GameObject;
import core.util.Combat;
/**
 * Created by jonathanbrodie on 9/12/15.
 */
public class ActorPhysicsComponent extends GravityCollisionComponent {

    public ActorPhysicsComponent() {
        super(false);
    }

    @Override
    public void collisionCallback(GameObject owner, GameObject other) {
        //check if the owner
        super.collisionCallback(owner,other);
        if (other instanceof GameActor) {
            this.collisionCallback((GameActor) owner, (GameActor) other);
        }
        else {
            this.collisionCallback((GameActor) owner, other);
        }
    }
    public void collisionCallback(GameActor actor, GameActor otherActor) {
        if (otherActor.getFields().isHostile()) {
            GameActor attacker=null;
            GameActor attacked=null;
            if (actor.INVINCIBLE_FLAG || otherActor.INVINCIBLE_FLAG) return;
            if (actor.ATTACK_FLAG && otherActor.ATTACK_FLAG) {
                //both actors are attacking!  Resolve!
                if (otherActor.getFields().getPower() > actor.getFields().getPower()) {
                    attacker=otherActor;
                    attacked=actor;
                }
                else if (otherActor.getFields().getPower() < actor.getFields().getPower()) {
                    attacker=actor;
                    attacked=otherActor;
                }
                else {
                    return;
                }
            }
            else if (actor.ATTACK_FLAG) {
                //owner attacks the otheractor
                attacker=actor;
                attacked=otherActor;
            }
            else if (otherActor.ATTACK_FLAG) {
                //the other actor attacks the owner
                attacker=otherActor;
                attacked=actor;
            }
            if ((attacker==null) || (attacked==null)) {
                return;
            }
            else {
                System.out.println("Combat");
                Combat.combat(attacker,attacked);

            }
        }
        else {
            actor.setTarget(otherActor);
        }
    }
    public void collisionCallback(GameActor actor, GameObject other) {
        //...
    }
    public boolean isSolid() {
        return false;
    }

}
