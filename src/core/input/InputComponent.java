package core.input;

import core.object.GameActor;
/**
 * Created by jonathanbrodie on 9/14/15.
 */
public interface InputComponent {

    void handleInput(Input input, GameActor player);
}
