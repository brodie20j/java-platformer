package core.ai;
import core.Event;
import core.command.Command;
import core.ActorState;
import core.GameActor;;
/**
 * Created by jonathanbrodie on 9/12/15.
 */
public interface ArtificialIntelligence {

    Command update(GameActor actor);
    void handle(Event event, GameActor actor);
    float eval(ActorState actorState);
}
