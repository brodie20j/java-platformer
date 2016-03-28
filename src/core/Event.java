package core;

/**
 * Created by jonathanbrodie on 12/10/15.
 */


public enum Event {
    EVENT_ATTACKED,
    EVENT_USED,
    EVENT_SPAWN,
    EVENT_DEATH,
    EVENT_USER_DEFINE;

    public static void deathCallback(GameActor object) {

        object.destroy();

    }
    public static void useCallback(GameActor actor) {

    }
    public static void spawnCallback(GameActor object) {
        //;pl
    }
    public static void attackedCallback(GameActor actor) {
        //check actor's hp
        //actor.AI
    }
    public static void defaultHandle(Event event, GameActor actor) {

        switch (event) {
            case EVENT_ATTACKED:
                attackedCallback(actor);
                break;
            case EVENT_DEATH:
                deathCallback(actor);
                break;
            case EVENT_USED:
                //does this do anything lol?
                useCallback(actor);
                break;
            case EVENT_SPAWN:
                spawnCallback(actor);
                break;
            case EVENT_USER_DEFINE:
                //nothing here because there is no game-defined function for this
                break;
        }
        actor.aiHandle(event,actor);
    }
}
