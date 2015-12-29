package core;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by jonathanbrodie on 10/3/15.
 */
public class MapNode {

    public List<MapNode> connectedTo;
    private Level nodeLevel;

    public MapNode(Level level) {
        this.nodeLevel=level;
        this.connectedTo=new ArrayList<MapNode>();
    }

    /**
     *
     * @param newNode
     */
    public void addConnectedNode(MapNode newNode) {
        this.connectedTo.add(newNode);
        newNode.connectedTo.add(this);
    }

    /**
     *
     * @return
     */
    public Level getLevel() {
        return this.nodeLevel;
    }

    public boolean isConnected(MapNode other) {

        if (other.connectedTo.contains(this) && this.connectedTo.contains(other)) {
            return true;
        }
        return false;
    }

}
