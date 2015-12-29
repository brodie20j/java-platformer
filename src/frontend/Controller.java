package frontend;
import core.*;

import java.util.Timer;
import java.util.TimerTask;

import core.input.DefaultInputComponent;
import core.input.Input;
import core.object.ActorFields;
import core.object.GameActor;
import core.object.GameObject;
import core.object.graphic.SpriteSheetImpl;
import core.object.physics.SolidCollisionComponent;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;


public class Controller implements EventHandler<KeyEvent> {
    public Level current;
    private Timer timer;
    final private double FRAMES_PER_SECOND = 30.0;
    @FXML
    private Pane gameBoard;
    @FXML
    private Button tester;

    private void setUpAnimationTimer() {
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        updateAnimation();
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
        current=new Level();
        SpriteSheetImpl test1=new SpriteSheetImpl("/res/test.png",64,64,1,1);
        test1.addAnimation("ANIMATION_DEFAULT", 0,1);
        GameObject object = new GameObject(new Position(0,-300),new SolidCollisionComponent(),test1,current);
        GameObject object2 = new GameObject(new Position(64,-436),new SolidCollisionComponent(),test1,current);
        GameObject object3 = new GameObject(new Position(129,-436),new SolidCollisionComponent(),test1,current);
        GameObject object4 = new GameObject(new Position(200,-436),new SolidCollisionComponent(),test1,current);
        GameObject object5 = new GameObject(new Position(270,-436),new SolidCollisionComponent(),test1,current);
        GameObject object6 = new GameObject(new Position(270,-372),new SolidCollisionComponent(),test1,current);
        GameObject object7 = new GameObject(new Position(0,-365),new SolidCollisionComponent(),test1,current);

        ActorFields fields=new ActorFields() {
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
            public int getVitality() {
                return 1;
            }

            @Override
            public int getMaxVitality() {
                return 1;
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
                Event.defaultHandle(event, actor);
            }

        };
        SpriteSheetImpl spriteSheet=new SpriteSheetImpl("/test2.png",64,64,3,3);
        spriteSheet.addAnimation("ANIMATION_DEFAULT", 0,4);
        spriteSheet.addAnimation("ANIMATION_JUMP",0,1);
        spriteSheet.addAnimation("ANIMATION_ATTACK",0,1);
        spriteSheet.addAnimation("ANIMATION_RUN",0,1);

        GameActor actor = new GameActor(new Position(0,0),fields,spriteSheet,current);
        GameActor actor2 = new GameActor(new Position(200,0),fields2,spriteSheet,current);

        actor.setInput(new DefaultInputComponent());
        current.addObject(object);
        current.addObject(object2);
        current.addObject(object3);
        current.addObject(object4);
        current.addObject(object5);
        current.addObject(actor);
        current.addObject(actor2);
        current.addObject(object6);
        current.addObject(object7);


        this.setUpAnimationTimer();
    }
    private void updateAnimation() {
        this.gameBoard.getChildren().clear();
        this.current.update();
        for (GameObject object : current.getDrawList()) {
            GameObjectNode node = new GameObjectNode(object);
            this.gameBoard.getChildren().add(node);
            node.draw();
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
            System.out.println("are we even here");
            switch (code) {
                case LEFT:
                    input = Input.INPUT_RELEASE_LEFT;
                    break;
                case RIGHT:
                    input = Input.INPUT_RELEASE_RIGHT;
                    break;
                case SHIFT:
                    input=Input.INPUT_BUTTON_2;
                    break;
                default:
                    input = Input.INPUT_BUTTON_1;
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
                case DOWN:
                    input = Input.INPUT_RIGHT;
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
    public static void tester(GameActor actor) {
        System.out.println("HELLO WORLD!!! The Callback worked!");
    }
}
