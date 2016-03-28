package frontend;
import core.*;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import core.ai.DumbAgent;
import core.input.PlayerInputComponent;
import core.input.Input;
import core.ActorFields;
import core.GameActor;
import core.GameObject;
import core.graphic.Animation;
import core.graphic.SpriteSheetImpl;
import core.physics.SolidCollisionComponent;
import core.util.Position;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import java.util.List;

public class Controller implements EventHandler<KeyEvent> {
    public Level current;
    private Timer timer;
    final private double FRAMES_PER_SECOND = 30.0;
    @FXML
    private Pane gameBoard;
    @FXML
    private Button tester;
    private List<GameObjectNode> list;
    private void setUpAnimationTimer() {
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        updateAnimation();
                        current.update();
                    }
                });
            }
        };

        final long startTimeInMilliseconds = 0;
        final long repetitionPeriodInMilliseconds = 100;
        long frameTimeInMilliseconds = (long)(1000.0 / FRAMES_PER_SECOND);
        this.timer = new java.util.Timer();
        this.timer.schedule(timerTask, 0, frameTimeInMilliseconds);
        this.tester.setOnKeyPressed(this);
        this.tester.setOnKeyReleased(this);

    }
    public void initialize() {
        this.list=new ArrayList<>();
        current=new Level(new Position(0,0));
        SpriteSheetImpl test1=new SpriteSheetImpl("/res/test2.png",128,128,1,1);
        test1.addAnimation("ANIMATION_DEFAULT", 0,1);
        SolidCollisionComponent component=new SolidCollisionComponent();
        for (int i=0; i<75; i++) {
            if (i > 30) {
                GameObject object2=new GameObject(new Position(i*64,-100),component,test1,current);
            }
            GameObject object=new GameObject(new Position(i*64,-436),component,test1,current);
        }
        GameObject object=new GameObject(new Position(64,-372),component,test1,current);


        ActorFields fields=new ActorFields() {
            @Override
            public String getName() {
                return "PLAYER";
            }

            @Override
            public int getVitality() {
                return 1;
            }

            @Override
            public int getMaxVitality() {
                return 1;
            }

            @Override
            public int getPower() {
                return 1;
            }

            @Override
            public int getDefense() {
                return 0;
            }

            @Override
            public boolean isHostile() {
                return false;
            }
            @Override
            public void onEvent(Event event, GameActor actor) {

            }

        };
        ActorFields fields2=new ActorFields() {
            @Override
            public String getName() {
                return "David Musicant";
            }
            @Override
            public int getVitality() {
                return 2;
            }

            @Override
            public int getMaxVitality() {
                return 2;
            }

            @Override
            public int getPower() {
                return 0;
            }

            @Override
            public int getDefense() {
                return 0;
            }

            @Override
            public boolean isHostile() {
                return true;
            }
            @Override
            public void onEvent(Event event, GameActor actor) {
                if (event==Event.EVENT_USED) {
                    System.out.println("Hello world. My name is "+getName());
                }
                Event.defaultHandle(event, actor);
            }

        };
        SpriteSheetImpl spriteSheet=new SpriteSheetImpl("/res/mario-temp.png",64,64,4,1);
        SpriteSheetImpl spriteSheet2=new SpriteSheetImpl("/res/goombas.png",128,128,5,4);
        spriteSheet2.addAnimation(Animation.DEFAULT_CONSTANT, 18,19);
        spriteSheet2.addAnimation("ANIMATION_JUMP",0,2);
        spriteSheet2.addAnimation("ANIMATION_ATTACK",0,1);
        spriteSheet2.addAnimation(Animation.MOVE_CONSTANT,12,14);
        spriteSheet2.addAnimation(Animation.DEAD_CONSTANT,0,1);
        spriteSheet.addAnimation(Animation.DEFAULT_CONSTANT, 0,1);
        spriteSheet.addAnimation("ANIMATION_JUMP",2,4);
        spriteSheet.addAnimation("ANIMATION_ATTACK",0,1);
        spriteSheet.addAnimation(Animation.MOVE_CONSTANT,0,2);
        spriteSheet.addAnimation(Animation.DEAD_CONSTANT,0,1);


        GameActor actor2 = new GameActor(new Position(100,0),fields2,spriteSheet2,current);
        GameActor actor3=new GameActor(new Position(1000,0),fields2,spriteSheet2,current);
        //actor3.setAI(new );
        actor2.setAI(new DumbAgent());


        SpriteSheetImpl sheet=new SpriteSheetImpl("/res/star-test2.png",64,64,1,3);
        sheet.addAnimation("ANIMATION_DEFAULT", 0,1);
        GameEffect effect=GameEffect.createEffect(actor2,sheet,0,0,10000);

        current.addPlayer(fields, spriteSheet, new PlayerInputComponent());
        current.update();
        this.setUpAnimationTimer();
    }
    private void updateAnimation() {
        this.gameBoard.getChildren().clear();

        Position pos=new Position(0,0);
        for (GameObject object : current.getGameObjects()) {
            if (object instanceof GameActor) {
                GameActor actor= (GameActor) object;
                if (actor.getInputComponent() != null) {
                    pos=new Position(actor.getCurrentPosition().getX()-100,actor.getCurrentPosition().getY()+250);
                }
            }
        }

        for (GameObject object : current.getDrawList(pos,675)) {
            GameObjectNode node = new GameObjectNode(object);
            node.draw();
            node.setLayoutX(node.getLayoutX()-pos.getX());
            node.setLayoutY(node.getLayoutY()+pos.getY());
            this.gameBoard.getChildren().add(node);

        }

    }
    /**
     * Handles keyboard events.
     * @param keyEvent
     */
    @Override
    public void handle(KeyEvent keyEvent) {
        Input input=null;
        KeyCode code=keyEvent.getCode();
        if (keyEvent.getEventType() == KeyEvent.KEY_RELEASED) {
            switch (code) {
                case LEFT:
                    input = Input.INPUT_RELEASE_LEFT;
                    break;
                case RIGHT:
                    input = Input.INPUT_RELEASE_RIGHT;
                    break;
                case Z:
                    input = Input.INPUT_RELEASE_BUTTON_2;
                    break;

            }
        }
        else if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED){
            switch (code) {
                case LEFT:
                    input = Input.INPUT_LEFT;
                    break;
                case RIGHT:
                    input = Input.INPUT_RIGHT;
                    break;
                case UP:
                    input = Input.INPUT_UP;
                    break;
                case X:
                    input=Input.INPUT_BUTTON_1;
                    break;
                case Z:
                    input = Input.INPUT_BUTTON_2;
                    break;
                case A:
                    input=Input.INPUT_BUTTON_3;
                    break;
                case S:
                    input=Input.INPUT_BUTTON_4;
                    break;
                default:
                    input=Input.INPUT_BUTTON_1;
                    break;
            }
        }
        if (input != null)
        current.handleInput(input);
        }
}
