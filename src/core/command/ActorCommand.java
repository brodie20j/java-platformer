package core.command;

import core.Event;
import core.GameActor;
import core.GameObject;
import core.Power;
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
    POWER,
    POWER_OFF,
    COUNTER;


    final int hvelocity=2;
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
                    target.getVelocity().setX(target.getVelocity().getX() - hvelocity);
                break;
            case CROUCH:
                //crouch animation?
                break;
            case ATTACK:
                if (!target.ATTACK_FLAG)  {
                    target.ATTACK_FLAG=true;
                }
                break;
            case USE:
                //target.use();
                if (target.getTarget() != null) {
                    target.getTarget().getFields().onEvent(Event.EVENT_USED, target);
                }
                break;
            case COUNTER:
                if ((!target.ATTACK_FLAG) && (!target.COUNTER_FLAG)) {
                    target.COUNTER_FLAG=true;
                }
                break;
            case POWER:
                    if (!Power.POWER_ON) {
                        System.out.println("Power on");

                        Power.usePower(target);
                    }
                break;
            case POWER_OFF:
                    System.out.println("Power off");
                    Power.powerOff(target);
                break;
        }
    }
}
