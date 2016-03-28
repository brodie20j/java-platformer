package core;


/**
 * Created by jonathanbrodie on 12/12/15.
 */
public interface ActorFields {

    String getName();
    int getVitality();
    int getMaxVitality();
    int getPower();
    int getDefense();
    boolean isHostile();
    void onEvent(Event event, GameActor actor);


}
