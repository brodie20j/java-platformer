package core.util;

import core.Event;
import core.object.GameActor;

/**
 * Created by jonathanbrodie on 12/20/15.
 */
public class Combat {

    public static void combat(GameActor attacker, GameActor attacked) {
        int difference=attacker.getFields().getPower() - attacked.getFields().getDefense();

        if ((difference < 0 ) || (attacked.COUNTER_FLAG)) {
            counter(attacked,attacker);
        }
        else {
            attacked.CURRENT_HP-=-difference;
            attacked.getFields().onEvent(Event.EVENT_ATTACKED,attacked);
        }

    }
    private static void counter(GameActor counter, GameActor countered) {
        int difference=counter.getFields().getPower() - countered.getFields().getDefense();

        if (difference < 0 )  {
            difference=0;
        }
        countered.CURRENT_HP-=difference;
        countered.getFields().onEvent(Event.EVENT_ATTACKED,countered);
    }
}
