package core;

import core.input.Input;
import core.object.GameObject;
import core.object.GameActor;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by jonathanbrodie on 9/8/15.
 */
public class Level {
    private List<GameObject> gameObjects;
    private List<GameObject> deleteList;
    private List<GameActor> inputList;
    public Level() {
        this.gameObjects = new ArrayList<GameObject>();
        this.deleteList = new ArrayList<GameObject>();

        this.inputList = new ArrayList<GameActor>();
        //ini
    }

    public void update() {
        for (GameObject object : this.gameObjects) {
            object.step();
        }
        for (GameObject object : this.deleteList) {
            this.gameObjects.remove(object);
        }
        this.deleteList.clear();
    }
    public void handleInput(Input input) {
        for (GameActor object : this.inputList) {
            object.handleInput(input);
        }
    }
    public void addObject(GameObject object) {
        this.gameObjects.add(object);
    }
    public void addActorToInput(GameActor actor) {
        this.inputList.add(actor);
    }
    public List<GameObject> getDrawList() {
        //find the closest object to the camera
        return this.gameObjects;
    }
    public void removeObject(GameObject object) {
        this.deleteList.add(object);
    }


}
