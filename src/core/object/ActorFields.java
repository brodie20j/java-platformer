package core.object;

import core.Event;


/**
 * Created by jonathanbrodie on 12/12/15.
 */
public interface ActorFields {

    int getVitality();
    int getMaxVitality();
    int getPower();
    int getDefense();
    boolean isHostile();
    void onEvent(Event event, GameActor actor);

}
