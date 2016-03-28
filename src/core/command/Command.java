package core.command;

import core.GameObject;

/**
 * Created by jonathanbrodie on 9/22/15.
 */
public interface Command {
    void execute(GameObject gameObject);
}
