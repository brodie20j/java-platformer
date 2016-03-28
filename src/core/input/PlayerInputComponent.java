package core.input;

import core.GameObject;
import core.command.ActorCommand;
import core.GameActor;

/**
 * Created by jonathanbrodie on 11/7/15.
 */
public class PlayerInputComponent implements InputComponent{


    @Override
    public void handleInput(Input input, GameObject object) {
        this.handleInput(input, (GameActor) object);
    }
    private void handleInput(Input input, GameActor player) {

        switch (input) {
            case INPUT_LEFT:
                player.addCommand(ActorCommand.MOVE_LEFT);
                break;
            case INPUT_RIGHT:
                player.addCommand(ActorCommand.MOVE_RIGHT);
                //player.getVelocity().setX(5);
                break;
            case INPUT_UP:
                //don't let the player jump while in the air
                player.addCommand(ActorCommand.JUMP);
                break;
            case INPUT_BUTTON_1:
                player.addCommand(ActorCommand.USE);
                player.addCommand(ActorCommand.ATTACK);
                break;
            case INPUT_BUTTON_2:
                player.addCommand(ActorCommand.POWER);
                break;
            case INPUT_RELEASE_BUTTON_2:
                player.addCommand(ActorCommand.POWER_OFF);
                break;
            case INPUT_BUTTON_3:
                player.addCommand(ActorCommand.COUNTER);
                break;
            case INPUT_BUTTON_4:
                player.addCommand(ActorCommand.JUMP);
                break;
            case INPUT_DOWN:
                //player.getVelocity().setY(-5);
                break;
            case INPUT_RELEASE_RIGHT:
                if (player.getVelocity().getX() > 0) player.getVelocity().setX(0);
                break;
            case INPUT_RELEASE_LEFT:
                if (player.getVelocity().getX() < 0) player.getVelocity().setX(0);
                break;
        }
    }
}
