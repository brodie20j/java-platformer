package core;

import core.input.Input;
import core.input.InputComponent;
import core.graphic.GraphicComponent;
import core.util.Position;

import java.util.*;

/**
 * Created by jonathanbrodie on 9/8/15.
 */
public class Level {

    private List<GameObject> gameObjects;
    private List<GameObject> sortedXObjects;
    private List<GameObject> sortedYObjects;

    private List<GameObject> deleteList;
    private List<GameObject> addList;
    private List<GameActor> inputList;
    private Position entryPoint;


    public Level(Position entryPoint) {
        this.entryPoint=entryPoint;
        this.gameObjects = new ArrayList<GameObject>();

        this.sortedYObjects=this.gameObjects;
        this.sortedXObjects=this.gameObjects;

        this.deleteList = new ArrayList<GameObject>();
        this.addList = new ArrayList<GameObject>();
        this.inputList = new ArrayList<GameActor>();
        //initPlayer()
    }

    public void update() {
        boolean bUpdated=false;


        if (this.addList.size() > 0) {
            bUpdated=true;
            for (GameObject object : this.addList) {
                this.gameObjects.add(object);
            }
            this.addList.clear();
        }
        if (this.deleteList.size() > 0) {
            bUpdated=true;
            for (GameObject object : this.deleteList) {
                this.gameObjects.remove(object);
            }
            this.deleteList.clear();
        }

        if (bUpdated) {
            this.sortedYObjects=this.gameObjects;
            this.sortedXObjects=this.gameObjects;

        }
        //Collections.sort(this.sortedYObjects,new YSorter());
        //Collections.sort(this.sortedXObjects, new XSorter());

        for (GameObject object : this.gameObjects) {
            object.step();
        }



    }
    public void handleInput(Input input) {

        for (GameActor object : this.inputList) {
            object.handleInput(input);
        }
    }
    public void addObject(GameObject object) {
        this.addList.add(object);
    }
    public void addActorToInput(GameActor actor) {
        this.inputList.add(actor);
    }
    public void addPlayer(ActorFields fields, GraphicComponent graphic, InputComponent inputComponent) {
       new GamePlayer(this.entryPoint,fields,graphic,this,inputComponent);
    }
    public Position getEntryPoint() {
        return this.entryPoint;
    }
    public List<GameObject> getGameObjects() {
        return this.gameObjects;
    }
    public List<GameObject> getDrawList(Position origin, double length) {
        List<GameObject> drawList=new ArrayList<>();
        List<GameObject> sortedList=this.getGameObjects();

        //find the closest object to the position

        List<GameObject> temp=new ArrayList<>();

        for (GameObject object : sortedList) {
            if ( Position.computeDistance(object.getCurrentPosition(),origin) <= length) {
                temp.add(object);
            }
        }
        return temp;
        /*
        for (GameObject object : temp) {
            GameObject clone = object.clone();
            this.removeObject(clone);

            //linear transformation
            clone.setPosition(new Position(clone.getCurrentPosition().getX() - origin.getX(), clone.getCurrentPosition().getY() - origin.getY()));
            drawList.add(clone);
        }
        return drawList;
        */
    }
    public List<GameObject> getSortedXObjects() {
        //Collections.sort(this.sortedXObjects, new XSorter());
        return this.sortedXObjects;
    }
    public List<GameObject> getSortedYObjects() {
        //Collections.sort(this.sortedYObjects, new YSorter());

        return this.sortedYObjects;
    }
    public void removeObject(GameObject object) {
        this.deleteList.add(object);
    }

}
