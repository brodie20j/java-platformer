package core.ai;

import core.Event;
import core.command.Command;
import core.object.GameActor;

/**
 * Created by jonathanbrodie on 12/29/15.
 */
public class DefaultAgent implements ArtificialIntelligence {
    private GameActor self;

    public DefaultAgent(GameActor actor) {
        this.self=actor;
    }


    @Override
    public Command update(GameActor actor) {
        return null;
    }
    @Override
    public void handle(Event event, GameActor actor) {

    }
}
