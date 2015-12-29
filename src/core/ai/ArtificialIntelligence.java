package core.ai;
import core.Event;
import core.command.Command;
import core.object.GameActor;;
/**
 * Created by jonathanbrodie on 9/12/15.
 */
public interface ArtificialIntelligence {

    Command update(GameActor actor);
    void handle(Event event, GameActor actor);
}
