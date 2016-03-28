package core.util;
import core.GameObject;

import java.util.Comparator;

/**
 * Created by jonathanbrodie on 8/27/15.
 */
public class ObjectSorter implements Comparator<GameObject> {
    Position origin;

    public ObjectSorter() {
        this.origin = new Position(0,0,0);
    }
    public ObjectSorter(Position position) {
        this.origin=position;
    }

    @Override
    public int compare(GameObject o1, GameObject o2) {

        double pos1=Position.computeDistance(o1.getCurrentPosition(),this.origin);
        double pos2=Position.computeDistance(o2.getCurrentPosition(),this.origin);
        return (int) (pos1 - pos2);

        //return
    }
}