package core.util;
import java.util.Comparator;

/**
 * Created by jonathanbrodie on 8/27/15.
 */
public class ObjectSorter implements Comparator<core.object.GameObject> {
    int xBase;

    public ObjectSorter() {
        this.xBase = 0;
    }

    @Override
    public int compare(core.object.GameObject o1, core.object.GameObject o2) {

        // descending order (ascending order would be:
        // o1.getGrade()-o2.getGrade())
        double x1 = o1.getCurrentPosition().getX();
        double x2 = o2.getCurrentPosition().getX();

        return (int) (x1 - x2);

        //return
    }
}