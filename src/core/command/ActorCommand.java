package core.command;

import core.object.GameActor;
import core.object.GameObject;

/**
 * Created by jonathanbrodie on 11/7/15.
 */
public enum ActorCommand implements Command{
    JUMP,
    MOVE_RIGHT,
    MOVE_LEFT,
    CROUCH,
    ATTACK,
    USE,
    COUNTER;


    final int hvelocity=10;
    final int vvelocity=10;
    @Override
    public void execute(GameObject gameObject) {
        GameActor target = (GameActor) gameObject;
        switch(this) {
            case JUMP:
                if (!target.getActorPhysics().inAir()) {
                    target.getVelocity().setY(30);
                    target.getVelocity().moveObject(target);
                }
                break;
            case MOVE_RIGHT:
                target.getVelocity().setX(target.getVelocity().getX()+hvelocity);
                break;
            case MOVE_LEFT:
                target.getVelocity().setX(target.getVelocity().getX()-hvelocity);
                break;
            case CROUCH:
                //crouch animation?
                break;
            case ATTACK:
                //
                if (!target.ATTACK_FLAG)  {
                    target.ATTACK_FLAG=true;
                }
                break;
            case USE:
                //target.use();
                if (target.getTarget() != null)
                target.getTarget().useCallback();
                break;
            case COUNTER:
                if ((!target.ATTACK_FLAG) && (!target.COUNTER_FLAG)) {
                    target.COUNTER_FLAG=true;
                }
                break;
        }
    }
}
