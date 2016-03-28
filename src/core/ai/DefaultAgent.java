package core.ai;

import core.Event;
import core.command.ActorCommand;
import core.command.Command;
import core.ActorState;
import core.GameActor;

/**
 * Created by jonathanbrodie on 12/29/15.
 */
public abstract class DefaultAgent implements ArtificialIntelligence {
    private ActorState self;
    private float hpHeuristic;
    private float enemyHeuristic;
    private boolean faction;


    public DefaultAgent(ActorState state) {
        this.self=state;
    }


    @Override
    public Command update(GameActor actor) {

        //update the current state
        this.self=new ActorState(actor);
        float max=Float.NEGATIVE_INFINITY;
        Command newCommand=null;
        for (Command command : ActorCommand.values()) {
            float score=this.eval(self.getSuccessor(command));
            if (score >= max) {
                max=score;
                newCommand=command;
            }
        }
        return newCommand;
    }

    @Override
    public abstract void handle(Event event, GameActor actor);
    @Override
    public abstract float eval(ActorState state);

}
