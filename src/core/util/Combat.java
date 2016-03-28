package core.util;

import core.Event;
import core.GameActor;

/**
 * Created by jonathanbrodie on 12/20/15.
 */
public class Combat {

    public static void combat(GameActor attacker, GameActor attacked) {
        int difference=attacker.getFields().getPower() - attacked.getFields().getDefense();
        InvincibleThread t;
        if ((difference < 0 ) || (attacked.COUNTER_FLAG)) {
            counter(attacked,attacker);
            t=new InvincibleThread(attacker);
        }
        else {
            attacked.CURRENT_HP-=difference;
            attacked.getFields().onEvent(Event.EVENT_ATTACKED,attacked);
            t=new InvincibleThread(attacked);
        }
        t.start();
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

class InvincibleThread extends Thread {
    GameActor owner;

    InvincibleThread(GameActor own) {
        this.owner=own;
    }

    @Override
    public void run() {
        this.owner.INVINCIBLE_FLAG=true;
        try {
            Thread.sleep(4000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.owner.INVINCIBLE_FLAG=false;
    }
}