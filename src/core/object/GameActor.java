package core.object;
import core.*;
import core.ai.ArtificialIntelligence;
import core.command.Command;
import core.input.Input;
import core.input.InputComponent;
import core.object.graphic.SpriteSheetImpl;
import core.object.physics.ActorPhysicsComponent;
import core.object.graphic.GraphicComponent;
import core.object.physics.GravityComponent;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by jonathanbrodie on 10/1/15.
 */
public class GameActor extends GameObject {
    private Queue<Command> commandQueue;
    private Stack<GameActor> targets;
    private ActorFields fields;

    //player-controlled
    private InputComponent inputComponent;
    //ai-controlled
    private ArtificialIntelligence aiComponent;

    private GameObject target;

    public int CURRENT_HP;
    public boolean ATTACK_FLAG = false;
    public boolean COUNTER_FLAG=false;



    public GameActor(Position start, ActorFields fields, GraphicComponent graphicComponent, Level currentLevel) {
        super(start, new ActorPhysicsComponent(), graphicComponent, currentLevel);
        this.fields=fields;
        this.commandQueue=new LinkedBlockingQueue<>();
        this.CURRENT_HP=fields.getMaxVitality();
        this.targets=new Stack<>();
    }


    @Override
    public void step() {

        if (this.aiComponent != null) {
            this.commandQueue.add(this.aiComponent.update(this));
        }

        if (this.commandQueue.peek() != null) {
            Command nextCommand=this.commandQueue.poll();
            nextCommand.execute(this);
        }

        if (this.CURRENT_HP > this.fields.getMaxVitality()) this.CURRENT_HP=this.fields.getMaxVitality();


        if (this.CURRENT_HP <= 0) {
            fields.onEvent(Event.EVENT_DEATH,this);
        }

        this.targets.clear();
        super.step();

    }
    /**
     * Add command to the command queue
     * @param command
     */
    public void addCommand(Command command) {
        this.commandQueue.add(command);
    }
    /**
     * Send command to the front of the command queue
     * @param command
     */
    public void doCommand(Command command) {
        Queue<Command> newQueue = new LinkedBlockingQueue<>();
        newQueue.add(command);
        while (this.commandQueue.peek() != null) {
            newQueue.add(this.commandQueue.poll());
        }
        this.commandQueue=newQueue;
    }

    public ActorFields getFields() {
        return this.fields;
    }
    public void setAI(ArtificialIntelligence aiComponent) {
        this.aiComponent=aiComponent;
    }
    public void setInput(InputComponent input) {
        this.getLevel().addActorToInput(this);
        this.inputComponent=input;
    }
    public void handleInput(Input input) {
        if (this.inputComponent == null) {
            System.out.println("Error: object has no input component!");
        }
        else {
            this.inputComponent.handleInput(input,this);
        }
    }
    public void setTarget(GameActor object) {
        this.targets.add(object);
    }
    public GameActor getTarget() {
        if (this.targets.empty()) return null;
        return this.targets.peek();
    }
    public void aiHandle(Event event, GameActor actor) {
        if (this.aiComponent != null) this.aiComponent.handle(event,actor);
    }
    public SpriteSheetImpl getActorGraphics() {
        return (SpriteSheetImpl) this.getGraphics();
    }
    public GravityComponent getActorPhysics() {
        return (GravityComponent) super.getPhysics();
    }
    public void useCallback() {
        this.fields.onEvent(Event.EVENT_USED, this);
    }
}
