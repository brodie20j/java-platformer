package core.graphic;
import core.GameObject;
import core.util.Pair;
/**
 * Created by jonathanbrodie on 8/31/15.
 */
public interface GraphicComponent {

    void updateGraphics(GameObject object);
    int getWidth();
    int getHeight();
    Pair getImageAt(int index);
    String getPath();

}
