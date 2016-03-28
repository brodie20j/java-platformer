package core.input;

import core.GameObject;

/**
 * Created by jonathanbrodie on 9/14/15.
 */
public interface InputComponent {

    void handleInput(Input input, GameObject object);
}
